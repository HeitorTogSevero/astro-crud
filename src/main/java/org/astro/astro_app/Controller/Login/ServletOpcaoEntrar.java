package org.astro.astro_app.Controller.Login;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ServletOpcaoEntrada", value = "/opcao-entrada")
public class ServletOpcaoEntrar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        boolean liberado = session != null && session.getAttribute("acessoLiberado") != null;

        if (!liberado) {
            response.sendRedirect(request.getContextPath() + "/acess-admin");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/LoginAdmin/opcaoEntrada.jsp").forward(request, response);
    }
}
