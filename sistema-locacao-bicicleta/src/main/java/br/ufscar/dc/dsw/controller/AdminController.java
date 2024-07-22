package main.java.br.ufscar.dc.dsw.controller;

import main.java.br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.model.Administrador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {

    private AdministradorDAO administradorDAO;

    @Override
    public void init() {
        administradorDAO = new AdministradorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        if (!"admin".equals(tipoUsuario)) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        try {
            switch (action) {
                case "/dashboard":
                    showDashboard(request, response);
                    break;
                case "/clientes":
                    listClientes(request, response);
                    break;
                case "/locadoras":
                    listLocadoras(request, response);
                    break;
                // Outras ações do administrador...
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin-dashboard.jsp").forward(request, response);
    }

    private void listClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> listaClientes = clienteDAO.getAll();
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("/admin-clientes-list.jsp").forward(request, response);
    }

    private void listLocadoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = locadoraDAO.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        request.getRequestDispatcher("/admin-locadoras-list.jsp").forward(request, response);
    }
}