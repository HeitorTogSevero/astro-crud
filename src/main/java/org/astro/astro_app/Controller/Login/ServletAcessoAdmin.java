package org.astro.astro_app.Controller.Login;

// Biblioteca:
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ServletAcessoAdmin", value = "/acess-admin")
public class ServletAcessoAdmin extends HttpServlet {

    // Abre a tela onde se digita a senha da área restrita
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/acessoAdmin.jsp").forward(request, response);
    }

    // Valida a senha digitada
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String senha = request.getParameter("senha");

        // ignoreIfMissing evita erro 500 caso o .env não esteja no diretório de execução
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        String senhaCorreta = dotenv.get("ACESS_PASSWORD");

        if (senha == null || senha.isBlank()) {

            request.setAttribute("erro", "Este campo não pode ser vazio");

        } else if (senhaCorreta != null && senha.equals(senhaCorreta)) {

            // Marca na sessão que a senha foi aceita
            request.getSession().setAttribute("acessoLiberado", true);

            // Redireciona para o servlet (e não direto para o JSP, que está em WEB-INF)
            response.sendRedirect(request.getContextPath() + "/opcao-entrada");
            return;

        } else {

            request.setAttribute("erro", "Senha incorreta");
        }

        // Em caso de erro, volta para o formulário mostrando a mensagem
        request.getRequestDispatcher("/acessoAdmin.jsp").forward(request, response);
    }
}