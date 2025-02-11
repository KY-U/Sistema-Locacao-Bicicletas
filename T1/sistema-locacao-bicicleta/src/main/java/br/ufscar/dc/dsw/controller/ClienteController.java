package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.model.Cliente;
import br.ufscar.dc.dsw.model.Locadora;

import br.ufscar.dc.dsw.dao.LocadoraDAO;

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

@WebServlet(urlPatterns = "/clientes/*")
public class ClienteController extends HttpServlet {

    private ClienteDAO clienteDAO;
    private LocadoraDAO locadoraDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
        locadoraDAO = new LocadoraDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HttpSession session = request.getSession();
        //String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        /*if (!"admin".equals(tipoUsuario)) {
            response.sendRedirect("login.jsp");
            return;
        }*/
        String action = request.getPathInfo();
        System.out.println("Action" + action);
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
                    listClientes(request, response);
                    break;
                case "/dashboard":
                    showDashboard(request, response);
                    break;
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
        String emailOriginal = request.getParameter("emailOriginal");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));
    
        Cliente cliente = new Cliente(email, senha, cpf, nome, telefone, sexo, dataNascimento);
        clienteDAO.updateCliente(emailOriginal, cliente);
        response.sendRedirect("listClientes");
    }

    private void deleteCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String email = request.getParameter("email");
        clienteDAO.deleteUsuario(email);
        response.sendRedirect("list");
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email == null) {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }

        // Carregar informações do cliente
        Cliente cliente = clienteDAO.searchUsuario(email);
        request.setAttribute("cliente", cliente);

        //carrega locadoras
        List<Locadora> listaLocadoras = locadoraDAO.listaLocadora();
        request.setAttribute("listaLocadoras", listaLocadoras);


        // Locações do cliente podem ser obtidas de um DAO específico para locações
        // Exemplo: List<Locacao> locacoes = locacaoDAO.listLocacoesByClient(email);
        // request.setAttribute("locacoes", locacoes);

        request.getRequestDispatcher("/cliente-dashboard.jsp").forward(request, response);
    }
}
