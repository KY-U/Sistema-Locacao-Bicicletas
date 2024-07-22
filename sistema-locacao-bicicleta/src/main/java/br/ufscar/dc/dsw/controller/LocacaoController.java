package main.java.br.ufscar.dc.dsw.controller;

import main.java.br.ufscar.dc.dsw.dao.LocacoesDAO;
import br.ufscar.dc.dsw.domain.Locacoes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/locacoes/*")
public class LocacaoController extends HttpServlet {

    private LocacaoDAO dao;

    @Override
    public void init() {
        dao = new LocacaoDAO();
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

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
        int locadoraId = Integer.parseInt(request.getParameter("locadoraId"));
        Timestamp dataHora = Timestamp.valueOf(request.getParameter("dataHora"));

        Locacao locacao = new Locacao(clienteId, locadoraId, dataHora);
        dao.insert(locacao);
        response.sendRedirect("list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
        int locadoraId = Integer.parseInt(request.getParameter("locadoraId"));
        Timestamp dataHora = Timestamp.valueOf(request.getParameter("dataHora"));

        Locacao locacao = new Locacao(id, clienteId, locadoraId, dataHora);
        dao.update(locacao);
        response.sendRedirect("list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.delete(id);
        response.sendRedirect("list");
    }
}
