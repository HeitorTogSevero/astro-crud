package org.astro.astro_app.Controller.ServletAlerta;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletAlertas", value = "/lista-alerta")
public class ServletLerAlerta extends HttpServlet{

    private AlertaDAO alertaDAO;


    public void init(){
        alertaDAO = new AlertaDAO();
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException{

        List<Alerta> alertas = alertaDAO.buscar();

        request.setAttribute("alertas", alertas);

        request.getRequestDispatcher(
                "/webapp/WEB-INF/alerta.jsp"
        ).forward(request, response);

        response.sendRedirect(request.getContextPath() + "/alertas");
    }
}
