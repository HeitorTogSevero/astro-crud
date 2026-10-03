package org.astro.astro_app.Controller.Alerta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;

import java.io.IOException;

@WebServlet(name = "alertas", value = "deletar-alerta")
public class ServletDeletarAlerta extends HttpServlet{

    private AlertaDAO alertaDAO;


    public void init(){
        alertaDAO = new AlertaDAO();
    }


    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException{

        String acao = request.getParameter("");

        if("excluir".equals(acao)){

            String IdTexto = request.getParameter("");
            int id = Integer.parseInt(IdTexto);

            alertaDAO.remover(id);

            response.sendRedirect(request.getContextPath() + "/alertas");
        }
    }
}
