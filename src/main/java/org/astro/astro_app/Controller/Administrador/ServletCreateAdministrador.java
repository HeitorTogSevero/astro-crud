package org.astro.astro_app.Controller.Administrador;

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
        administradorDAO = new AdministradorDAO();
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        final Pattern emailRegex =
                Pattern.compile("^[a-zA-Z0-9._%+#$!\\-]+@(gmail|hotmail|yahoo|outlook)\\.(com|com\\.br|org\\.br)$");

        final Pattern senhaRegex =
                Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$");

        String nome = request.getParameter("nome");

        String email = request.getParameter("email");

        String senha = request.getParameter("senha");

        String mensagem = "";

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

        else{
            try{
                Administrador administrador = new Administrador(
                        nome,
                        email,
                        senha
                );

                administradorDAO.inserirAdmin(administrador);

                response.sendRedirect(request.getContextPath() + "/tela-inicial");
                return;

            }catch (NumberFormatException e) {
                mensagem = "Código ou id da empresa devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            }
            catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        request.getRequestDispatcher("/crud.jsp").forward(request, response);
    }
}