package org.astro.astro_app.Controller.DivisaoTreinamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import org.astro.astro_app.model.DivisaoTreinamento;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletDivisoes", value = "/listar-divisoes")
public class ServletReadDivisaoTreinamento extends HttpServlet{

    private DivisaoTreinamentoDAO divisaoTreinamentoDAO;

    @Override
    public void init(){
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException{

        List<DivisaoTreinamento> divisoes = divisaoTreinamentoDAO.buscar();

        request.setAttribute("divisoes", divisoes);

        request.getRequestDispatcher(
                ""
        ).forward(request, response);
    }
}
