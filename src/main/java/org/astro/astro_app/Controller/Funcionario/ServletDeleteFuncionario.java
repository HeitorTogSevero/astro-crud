package org.astro.astro_app.Controller.Funcionario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.FuncionarioDAO;
import org.astro.astro_app.model.Funcionario;

import java.io.IOException;

@WebServlet(name = "ServletDeleteFuncionario", value = "/deletar-funcionario")
public class ServletDeleteFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init(){
        funcionarioDAO = new FuncionarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("");

        if("excluir".equals(acao)){

            String idTexto = request.getParameter("id");
            int id = Integer.parseInt(idTexto);

            funcionarioDAO.remover(id);

            response.sendRedirect(request.getContextPath() + "/funcionario");
        }



    }
}
