package org.astro.astro_app.Controller.Alerta;

import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletAlertas", value = "/alterar-alerta")
public class ServletUpdateAlerta extends HttpServlet{

    private AlertaDAO alertaDAO;

    @Override
    public void init(){
        alertaDAO = new AlertaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String acao = request.getParameter("");

        if("editar".equals(acao)){
            int id = Integer.parseInt(request.getParameter("id"));

            Alerta alerta = (Alerta) alertaDAO.buscarPorIdAlerta(id);

            request.setAttribute("alertas-editar", alerta);

            request.getRequestDispatcher("").forward(request, response);
            return;
        }

        List<Alerta> alertas = alertaDAO.buscar();

        request.setAttribute("alertas",alertas);

        request.getRequestDispatcher("").forward(request, response);

        response.sendRedirect(request.getContextPath() + "/alertas");
    }
}
