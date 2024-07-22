package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.model.Cliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/clientes/*")
public class ClienteController extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
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
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCliente(request, response);
                    break;
                case "/delete":
                    deleteCliente(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCliente(request, response);
                    break;
                case "/list":
                default:
                    listClientes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Cliente> listaClientes = clienteDAO.listaUsuario();
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("/cliente-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cliente", new Cliente());
        request.getRequestDispatcher("/cliente-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String email = request.getParameter("email");
        Cliente cliente = clienteDAO.getCliente(email);
        request.setAttribute("cliente", cliente);
        request.getRequestDispatcher("/cliente-form.jsp").forward(request, response);
    }

    private void insertCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));

        Cliente cliente = new Cliente(email, senha, cpf, nome, telefone, sexo, dataNascimento);
        clienteDAO.cadastrarUsuario(cliente);
        response.sendRedirect("list");
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));

        Cliente cliente = new Cliente(email, senha, cpf, nome, telefone, sexo, dataNascimento);
        clienteDAO.updateUsuario(cliente);
        response.sendRedirect("list");
    }

    private void deleteCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String email = request.getParameter("email");
        clienteDAO.deleteUsuario(email);
        response.sendRedirect("list");
    }
}
