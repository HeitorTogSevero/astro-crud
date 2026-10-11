package org.astro.astro_app.Controller.DivisaoTreinamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import java.io.IOException;

@WebServlet(name = "ServletDeleteDivisaoTreinamento", value = "/deletar-divisao")
public class ServletDeleteDivisaoTreinamento extends HttpServlet{

    private DivisaoTreinamentoDAO divisaoTreinamentoDAO;

    @Override
    public void init(){
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        String idDivisaoTreinamentoTexto = request.getParameter("id_divisao");
        String mensagem = "";

        if(idDivisaoTreinamentoTexto == null || idDivisaoTreinamentoTexto.isBlank()){
            mensagem = "id nao informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);
            return;
        }

        try {
            int idDivisaoTreinamento = Integer.parseInt(idDivisaoTreinamentoTexto);

            if (idDivisaoTreinamento <=0){
                mensagem = "O id deve ser > 0";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);
                return;
            }

            divisaoTreinamentoDAO.remover(idDivisaoTreinamento);
            response.sendRedirect(request.getContextPath() + "/listar-divisoes");

        }catch (NumberFormatException e){
            mensagem = "o id da divisao deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);

        }

    }
}