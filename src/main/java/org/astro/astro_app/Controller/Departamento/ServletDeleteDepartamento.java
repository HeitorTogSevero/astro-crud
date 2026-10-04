package org.astro.astro_app.Controller.Departamento;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;

import java.io.IOException;

@WebServlet(name = "ServletDeleteDepartamento", value = "deletar-departamento")
public class ServletDeleteDepartamento extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init(){
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("");

        if("excluir".equals(acao)){

            String idTexto = request.getParameter("id");
            int id = Integer.parseInt(idTexto);

            departamentoDAO.remover(id);

            response.sendRedirect(request.getContextPath() + "/departamento");
        }
    }
}
