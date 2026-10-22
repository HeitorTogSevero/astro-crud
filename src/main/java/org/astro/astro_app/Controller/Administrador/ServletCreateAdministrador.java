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

    final Pattern emailRegex =
            Pattern.compile("^[a-zA-Z0-9._%+#$!\\-]+@(gmail|hotmail|yahoo|outlook|institutojef)\\.(com|com\\.br|org\\.br)$");

    final Pattern senhaRegex =
            Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$");

    @Override
    public void init(){
        administradorDAO = new AdministradorDAO();
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/tela_login/cadastro.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");

        String email = request.getParameter("email");

        String senha = request.getParameter("senha");

        String confirmarSenha = request.getParameter("confirmar_senha");


    }





}