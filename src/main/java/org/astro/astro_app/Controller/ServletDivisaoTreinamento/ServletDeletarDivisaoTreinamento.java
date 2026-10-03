package org.astro.astro_app.Controller.ServletDivisaoTreinamento;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;

import java.io.IOException;
import java.lang.invoke.StringConcatFactory;

@WebServlet(name = "ServletDivisoes", value = "/excluir-divisao")
public class ServletDeletarDivisaoTreinamento extends HttpServlet{

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

        if("excluir".equals(acao)){

            String idTexto = request.getParameter("id");
            int id = Integer.parseInt(idTexto);

            divisaoTreinamentoDAO.remover(id);

            response.sendRedirect(request.getContextPath() + "/divisoes");
        }
    }
}
