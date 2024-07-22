package main.java.br.ufscar.dc.dsw.controller;

import main.java.br.ufscar.dc.dsw.dao.LocacoesDAO;
import main.java.br.ufscar.dc.dsw.model.Locacoes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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

        switch (action) {
            case "/new":
                showForm(request, response);
                break;
            case "/insert":
                insert(request, response);
                break;
            case "/delete":
                delete(request, response);
                break;
            case "/edit":
                showForm(request, response);
                break;
            case "/update":
                update(request, response);
                break;
            default:
                list(request, response);
                break;
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Locacao> listaLocacoes = dao.listaLocacoes();
        request.setAttribute("listaLocacoes", listaLocacoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/locacao-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action.equals("/new")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/locacao-form.jsp");
            dispatcher.forward(request, response);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Locacao locacao = dao.get(id);
            request.setAttribute("locacao", locacao);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/locacao-form.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clienteEmail = request.getParameter("clienteEmail");
        String locadoraCnpj = request.getParameter("locadoraCnpj");
        Timestamp dataHora = Timestamp.valueOf(request.getParameter("dataHora"));

        Locacao locacao = new Locacao(clienteEmail, locadoraCnpj, dataHora);
        dao.insert(locacao);
        response.sendRedirect("list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String clienteEmail = request.getParameter("clienteEmail");
        String locadoraCnpj = request.getParameter("locadoraCnpj");
        Timestamp dataHora = Timestamp.valueOf(request.getParameter("dataHora"));

        Locacao locacao = new Locacao(id, clienteEmail, locadoraCnpj, dataHora);
        dao.update(locacao);
        response.sendRedirect("list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.delete(id);
        response.sendRedirect("list");
    }
}
