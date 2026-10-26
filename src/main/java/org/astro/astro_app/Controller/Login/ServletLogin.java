package org.astro.astro_app.Controller.Login;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AdministradorDAO;
import org.astro.astro_app.model.Administrador;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "ServletLogin", value = "/login")
public class ServletLogin extends HttpServlet {

    private AdministradorDAO administradorDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO de alertas quando o servlet e iniciado
        administradorDAO = new AdministradorDAO();

    }

    //Abre a tela de login
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/tela_login/login.jsp").forward(request, response);
    }

    //Processa o formulário de login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Define a regra (Regex) de quais domínios e formatos de e-mail são permitidos
        final Pattern emailRegex =
                Pattern.compile("^[a-zA-Z0-9._%+#$!\\-]+@(gmail|hotmail|yahoo|outlook|institutojef)\\.(com|com\\.br|org\\.br)$");

        String email = request.getParameter("email");

        String senha = request.getParameter("senha");

        String mensagem = "";

        // Validações de preenchimento e formato
        if (email == null || email.isBlank()) {

            mensagem = "Este campo não pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (senha == null || senha.isBlank()) {

            mensagem = "Este campo não pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (email.length() > 100) {

            mensagem = "Máx. de caracteres é 100";

            request.setAttribute("erro", mensagem);

        }

        else if (!emailRegex.matcher(email).matches()) {

            mensagem = "Formato Inválido";

            request.setAttribute("erro", mensagem);

        }

        else if (senha.length() < 8) {

            mensagem = "A senha deve ter no mínimo 8 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else {

            // Tenta autenticar o administrador no banco de dados
            Administrador admin = administradorDAO.autenticar(email.trim(), senha);

            if (admin != null) {
                // Se encontrado, cria a sessão para manter o usuário logado entre as páginas
                HttpSession session = request.getSession(); //a sessão guarda os dados do usuário enquanto ele está logado, entre várias páginas. É importante falar também que, sem sessão, cada request esquece quem é o usuário, e teria que logar denovo em cada página
                session.setAttribute("adminLogado", admin);

                // Redireciona para a página inicial do painel administrativo
                response.sendRedirect(request.getContextPath() + "/tela-inicial");
                return;

            } else {

                mensagem = "Email ou senha inválidos";
                request.setAttribute("erro", mensagem);

            }
        }

        // Em caso de erro, retorna ao formulario com a mensagem de validacao
        request.getRequestDispatcher("/tela_login/login.jsp").forward(request, response);
    }
}