package main.java.br.ufscar.dc.dsw.controller;

//import main.java.br.ufscar.dc.dsw.dao.ClienteDAO;
import main.java.br.ufscar.dc.dsw.model.Cliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientes/*")
public class ClienteController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
    }

    @Override
    public void init() {
        dao = new ClienteDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo();
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
                default:
                    listClientes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Cliente> listClientes = dao.listAll();
        request.setAttribute("listClientes", listClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente existingCliente = dao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cliente-form.jsp");
        request.setAttribute("cliente", existingCliente);
        dispatcher.forward(request, response);
    }

    private void insertCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");

        Cliente novoCliente = new Cliente(email, senha, cpf, nome, telefone, sexo, dataNascimento);
        dao.insert(novoCliente);
        response.sendRedirect("list");
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String dataNascimento = request.getParameter("dataNascimento");

        Cliente cliente = new Cliente(id, email, senha, cpf, nome, telefone, sexo, dataNascimento);
        dao.update(cliente);
        response.sendRedirect("list");
    }

    private void deleteCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = new Cliente(id);
        dao.delete(cliente);
        response.sendRedirect("list");
    }

}
