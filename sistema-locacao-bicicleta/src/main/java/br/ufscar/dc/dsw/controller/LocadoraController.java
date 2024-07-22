package main.java.br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.model.Locadora;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/locadoras/*")
public class LocadoraController extends HttpServlet {

    private LocadoraDAO locadoraDAO;

    @Override
    public void init() {
        locadoraDAO = new LocadoraDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertLocadora(request, response);
                    break;
                case "/delete":
                    deleteLocadora(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateLocadora(request, response);
                    break;
                case "/list":
                default:
                    listLocadoras(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listLocadoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Locadora> listaLocadoras = locadoraDAO.listaLocadora();
        request.setAttribute("listaLocadoras", listaLocadoras);
        request.getRequestDispatcher("/locadora-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("locadora", new Locadora());
        request.getRequestDispatcher("/locadora-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        Locadora locadora = locadoraDAO.get(email);
        request.setAttribute("locadora", locadora);
        request.getRequestDispatcher("/locadora-form.jsp").forward(request, response);
    }

    private void insertLocadora(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");

        Locadora locadora = new Locadora(email, senha, cnpj, nome, cidade);
        locadoraDAO.insert(locadora);
        response.sendRedirect("list");
    }

    private void updateLocadora(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");

        Locadora locadora = new Locadora(email, senha, cnpj, nome, cidade);
        locadoraDAO.update(locadora);
        response.sendRedirect("list");
    }

    private void deleteLocadora(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        locadoraDAO.delete(email);
        response.sendRedirect("list");
    }
}
