package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.model.Cliente;
import br.ufscar.dc.dsw.model.Administrador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private ClienteDAO clienteDAO;
    private AdminDAO administradorDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
        administradorDAO = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            Cliente cliente = clienteDAO.getCliente(email);
            Administrador administrador = administradorDAO.getAdmin(email);

            if (cliente != null && cliente.getSenha().equals(senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", cliente);
                session.setAttribute("tipoUsuario", "cliente");
                response.sendRedirect("clientes/list");
            } else if (administrador != null && administrador.getSenha().equals(senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", administrador);
                session.setAttribute("tipoUsuario", "admin");
                response.sendRedirect("admin/dashboard");
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro no acesso ao banco de dados.", e);
        }
    }
}