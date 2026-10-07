package org.astro.astro_app.Controller.DivisaoTreinamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import org.astro.astro_app.model.DivisaoTreinamento;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletDivisoes", value ="/alterar-tarefa")
public class ServletUpdateDivisaoTreinamento extends HttpServlet{

    private DivisaoTreinamentoDAO divisaoTreinamentoDAO;

    @Override
    public void init(){
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException{

        String acao = request.getParameter("");

        if ("editar".equals(acao)) {
            int id = Integer.parseInt(request.getParameter("id_divisao"));

            DivisaoTreinamento divisaoTreinamento = (DivisaoTreinamento) divisaoTreinamentoDAO.buscarPorIdDiv(id);

            request.getRequestDispatcher(
                    ""
            ).forward(request, response);

        }

        List<DivisaoTreinamento> divisoes = divisaoTreinamentoDAO.buscar();

        request.setAttribute("divisoes", divisoes);

        request.getRequestDispatcher(
                ""
        ).forward(request, response);

        response.sendRedirect(request.getContextPath() + "/divisoes");
    }
}
