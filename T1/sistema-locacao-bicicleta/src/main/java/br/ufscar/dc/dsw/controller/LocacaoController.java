package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocacoesDAO;
import br.ufscar.dc.dsw.model.Locacoes;
import br.ufscar.dc.dsw.model.Locadora;
import br.ufscar.dc.dsw.model.Cliente;
import br.ufscar.dc.dsw.EmailService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;


@WebServlet(urlPatterns = "/locacoes/*")
public class LocacaoController extends HttpServlet {

    private LocacoesDAO dao;

    @Override
    public void init() {
        dao = new LocacoesDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/list/cliente":
                    listaLocacoesByCPF(request, response);
                    break;
                case "/list/locadora":
                    listaLocacoesByCNPJ(request, response);
                    break;
                case "/new":
                    //showForm(request, response);
                    insert(request, response);
                    break;
                case "/edit":
                    showForm(request, response);
                    break;
                case "/delete":
                    delete(request, response);
                    break;
                default:
                    list(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Locacoes> listaLocacoes = dao.listaLocacoes();
        request.setAttribute("listaLocacoes", listaLocacoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista-locacoes.jsp");
        dispatcher.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String action = request.getPathInfo();

        if (action.equals("/new")) {
            //path n existe
            RequestDispatcher dispatcher = request.getRequestDispatcher("/locacao-form.jsp");
            dispatcher.forward(request, response);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Locacoes locacao = dao.getLocacaoById(id);
            request.setAttribute("locacao", locacao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/locacao-form.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void sendMail(String subject, String body) {
        try {
            EmailService service = new EmailService();
            InternetAddress from = new InternetAddress("sistemalocacao@gmail.com", "Bob", "UTF-8");
            InternetAddress to = new InternetAddress("pedroborges.itapira@gmail.com", "Pedro", "UTF-8");
            service.send(from, to, subject, body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        // Recupera o CPF do cliente (enviado como campo oculto no form)
        String clienteCPF = request.getParameter("cpf");

        // Recupera o CNPJ da locadora selecionada
        String locadoraCnpj = request.getParameter("cnpj");

        // Recupera a data da locação e converte para Timestamp
        String dataLocacaoStr = request.getParameter("dataHora");
        dataLocacaoStr = dataLocacaoStr.replace("T", " ") + ":00";
        Timestamp dataHora = Timestamp.valueOf(dataLocacaoStr);

        // Cria um objeto Locacoes e preenche com os dados
        Locacoes locacao = new Locacoes();
        locacao.setCpfCliente(clienteCPF);
        locacao.setCnpjLocadora(locadoraCnpj);
        locacao.setDataInicio(dataHora);

        try {
            // Verifica se já existe uma locação conflitante
            if (dao.existeLocacaoConflitante(locacao)) {
                // Define uma mensagem de erro e redireciona
                request.getSession().setAttribute("mensagem", "Já existe uma locação para o mesmo cliente e locadora no mesmo horário.");
                response.sendRedirect(request.getContextPath() + "/locacoes/list/cliente");
                return;
            }

            // Chama o DAO para cadastrar a locação
            dao.cadastrarLocacoes(locacao);

            // Envia o e-mail de confirmação
            sendMail("Locação Realizada", "Locação realizada pelo usuário de CPF: "+ clienteCPF + " Locadora de CNPJ: " + locadoraCnpj + ". Data: " + dataHora);

            // Redireciona de volta para a lista do cliente após inserir a locação
            response.sendRedirect(request.getContextPath() + "/locacoes/list/cliente");
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao cadastrar a locação.");
            response.sendRedirect(request.getContextPath() + "/locacoes/list/cliente");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String clienteEmail = request.getParameter("clienteEmail");
        String locadoraCnpj = request.getParameter("locadoraCnpj");
        String dataLocacaoStr = request.getParameter("dataHora");
        dataLocacaoStr = dataLocacaoStr.replace("T", " ") + ":00";
        Timestamp dataHora = Timestamp.valueOf(dataLocacaoStr);

        Locacoes locacao = new Locacoes(id, clienteEmail, locadoraCnpj, dataHora);
        dao.updateLocacoes(locacao);
        response.sendRedirect("list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        dao.deleteLocacoes(cpfCliente, cnpjLocadora);
        response.sendRedirect("list");
    }


    private void listaLocacoesByCPF(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("usuario");
        String cpfCliente = cliente.getCpf();


        List<Locacoes> listaLocacoesByCPF = dao.listaLocacoesByCPF(cpfCliente);
        request.setAttribute("locacoes", listaLocacoesByCPF);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista-locacoes.jsp");
        dispatcher.forward(request, response);
    }

    private void listaLocacoesByCNPJ(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();

        Locadora locadora = (Locadora) session.getAttribute("locadora");

        String cnpjLocadora = locadora.getCnpj();

        List<Locacoes> listaLocacoesByCNPJ = dao.listaLocacoesByCNPJ(cnpjLocadora);
        request.setAttribute("locacoes", listaLocacoesByCNPJ);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista-locacoes.jsp");
        dispatcher.forward(request, response);
    }

}
