package org.astro.astro_app.Controller.Login;

// Biblioteca:
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ServletLogout", value = "/logout")
public class ServletLogout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Recupera a sessão atual sem criar uma, caso não exista
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }


        // Redireciona o usuário para a tela de login
        response.sendRedirect(request.getContextPath() + "/landing-page.html");

    }
}