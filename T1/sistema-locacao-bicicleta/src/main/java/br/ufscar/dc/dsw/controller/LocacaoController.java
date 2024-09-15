package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocacoesDAO;
import br.ufscar.dc.dsw.model.Locacoes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

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
        //path n existe
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/locacao-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String action = request.getPathInfo();

        if (action.equals("/new")) {
            //path n existe
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/locacao-form.jsp");
            dispatcher.forward(request, response);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Locacoes locacao = dao.getLocacaoById(id);
            request.setAttribute("locacao", locacao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/locacao-form.jsp");
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
        Timestamp dataHora = Timestamp.valueOf(dataLocacaoStr + ":00");

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
        Timestamp dataHora = Timestamp.valueOf(request.getParameter("dataHora"));

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
}
