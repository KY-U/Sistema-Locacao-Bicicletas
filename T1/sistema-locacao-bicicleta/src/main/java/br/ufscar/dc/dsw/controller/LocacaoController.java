package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocacoesDAO;
import br.ufscar.dc.dsw.model.Locacoes;
import br.ufscar.dc.dsw.model.Locadora;

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

        // Chama o DAO para cadastrar a locação
        dao.cadastrarLocacoes(locacao);

        // Redireciona de volta para o dashboard do cliente após inserir a locação
        response.sendRedirect("cliente-dashboard.jsp");
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
        String cpfCliente = (String) session.getAttribute("cpf");

        List<Locacoes> listaLocacoesByCPF = dao.listaLocacoesByCPF(cpfCliente);
        request.setAttribute("locacoes", listaLocacoesByCPF);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista-locacoes.jsp");
        dispatcher.forward(request, response);
    }

    private void listaLocacoesByCNPJ(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        HttpSession session = request.getSession();

        Locadora locadora = (Locadora) session.getAttribute("locadora");

        //essa linha que não sei fazer direito
        String cnpjLocadora = locadora.getCnpj();

        List<Locacoes> listaLocacoesByCNPJ = dao.listaLocacoesByCNPJ(cnpjLocadora);
        request.setAttribute("locacoes", listaLocacoesByCNPJ);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista-locacoes.jsp");
        dispatcher.forward(request, response);
    }

}
