package org.astro.astro_app.Controller.Administrador;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.*;
import org.astro.astro_app.model.Administrador;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "ServletCreateAdministrador", value = "/tela-cadastro")
public class ServletCreateAdministrador extends HttpServlet{

    private AdministradorDAO administradorDAO;


    @Override
    public void init(){
        // Concede o acesso ao DAO de administrador quando o servlet e iniciado
        administradorDAO = new AdministradorDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Os dados chegam em bits, aqui converte ele para linguagem normal
        request.getRequestDispatcher("/WEB-INF/LoginAdmin/cadastroAdmin.jsp").forward(request, response);

    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // Os dados chegam em bits, aqui converte ele para linguagem normal
        request.setCharacterEncoding("UTF-8");

        // Define os formatos permitidos para email e senha
        final Pattern emailRegex =
                Pattern.compile("^[a-zA-Z0-9._%+#$!\\-]+@(gmail|hotmail|yahoo|outlook|institutojef)\\.(com|com\\.br|org\\.br)$");

        final Pattern senhaRegex =
                Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$");

        // Pega os campos enviados pelo formulario de cadastro
        String nome = request.getParameter("nome");

        String email = request.getParameter("email");

        String senha = request.getParameter("senha");

        String confirmarSenha = request.getParameter("confirmar_senha");

        String mensagem = "";

        // Fica na tela após ser digitado
        request.setAttribute("nomeDigitado", nome != null ? nome : "");
        request.setAttribute("emailDigitado", email != null ? email : "");

        // Valida campos obrigatórios, limites de tamanho e formatos informados.
        if(nome == null || nome.isBlank()){
            mensagem = "Este campo não pode ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(senha == null || senha.isBlank()){
            mensagem = "Este campo não pode ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(email == null || email.isBlank()){
            mensagem = "Este campo não pode ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(nome.length() > 100){
            mensagem = "Máx. de caracteres é 100";
            request.setAttribute("erro", mensagem);
        }

        else if(email.length() > 100){
            mensagem = "Máx. de caracteres é 100";
            request.setAttribute("erro", mensagem);
        }

        else if(!emailRegex.matcher(email).matches()){
            mensagem = "Formato Inválido";
            request.setAttribute("erro", mensagem);
        }

        else if(!senhaRegex.matcher(senha).matches()){
            mensagem = "Formato Inválido";
            request.setAttribute("erro", mensagem);
        }
        else if (!senha.equals(confirmarSenha)) {
            mensagem = "As senhas não coincidem";
            request.setAttribute("erro", mensagem);
        }
        else if (administradorDAO.emailExiste(email.trim())) {
            mensagem = "Este e-mail já está cadastrado";
            request.setAttribute("erro", mensagem);
        }


        else{

            // Com os dados validos, cria o administrador e solicita a inserção no banco
            try{

                Administrador administrador = new Administrador(
                        nome.trim(),
                        email.trim(),
                        senha
                );

                boolean verificarInsersao = administradorDAO.inserirAdmin(administrador);

                if (verificarInsersao){
                    // Ao concluir o cadastro, encaminha o usuário para a tela de login
                    response.sendRedirect(request.getContextPath() + "/login");
                    return;
                }else{
                    mensagem = "Erro ao cadastrar, tente novamente.";
                    request.setAttribute("erro", mensagem);
                }

                request.getRequestDispatcher("/WEB-INF/LoginAdmin/cadastroAdmin.jsp").forward(request, response);

            }catch (Exception e) {
                // Exibe uma mensagem para o usuário e mostra o erro no terminal
                mensagem = "Erro ao cadastrar. Tente novamente.";
                request.setAttribute("erro", mensagem);
                System.out.println(e.getMessage());
            }

        }

        // Se houver erro, retorna ao formulário com a mensagem e os dados preservados
        request.getRequestDispatcher("/tela_login/cadastro.jsp").forward(request, response);
    }
}
