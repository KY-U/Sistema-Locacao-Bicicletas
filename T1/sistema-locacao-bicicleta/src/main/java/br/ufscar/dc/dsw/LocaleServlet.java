package br.ufscar.dc.dsw;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

public class LocaleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        Locale locale;
        if ("en".equals(lang)) {
            locale = Locale.ENGLISH;
        } else {
            locale = new Locale("pt", "BR"); // Default to Portuguese
        }
        HttpSession session = request.getSession();
        session.setAttribute("org.apache.struts.action.LOCALE", locale);
        response.sendRedirect(request.getHeader("Referer")); // Redirect to the referring page
    }
}

