package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocacoesDAO;
import br.ufscar.dc.dsw.model.Locacoes;

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
                case "/new":
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/locacao-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String action = request.getPathInfo();

        if (action.equals("/new")) {
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
        String clienteCPF = request.getParameter("cpf");
        String locadoraCnpj = request.getParameter("cnpj");
        String dataLocacaoStr = request.getParameter("dataHora");
        Timestamp dataHora = Timestamp.valueOf(dataLocacaoStr + ":00");

        Locacoes locacao = new Locacoes();
        locacao.setCpfCliente(clienteCPF);
        locacao.setCnpjLocadora(locadoraCnpj);
        locacao.setDataInicio(dataHora);

        dao.cadastrarLocacoes(locacao);

        response.sendRedirect(request.getContextPath() + "/locacoes/list/cliente");
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

    private void listaLocacoesByCPF(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
        // Obtém o CPF do cliente da sessão
        HttpSession session = request.getSession();
        String cpfCliente = (String) session.getAttribute("cpf");

        if (cpfCliente != null) {
            List<Locacoes> listaLocacoesByCPF = dao.listaLocacoesByCPF(cpfCliente);
            request.setAttribute("listaLocacoesByCPF", listaLocacoesByCPF);
            
            // Redireciona para a página JSP correta
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/lista-locacoes.jsp");
            dispatcher.forward(request, response);
        } else {
            // Se o CPF não estiver na sessão, redireciona para a página de login
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
