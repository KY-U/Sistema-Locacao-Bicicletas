package main.java.br.ufscar.dc.dsw.controller;


import main.java.br.ufscar.dc.dsw.dao.ClienteDAO;
import main.java.br.ufscar.dc.dsw.dao.AdminDAO;
import main.java.br.ufscar.dc.dsw.model.Cliente;
import main.java.br.ufscar.dc.dsw.model.Administrador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        Cliente cliente = clienteDAO.get(email);
        Administrador administrador = administradorDAO.get(email);

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
    }
}