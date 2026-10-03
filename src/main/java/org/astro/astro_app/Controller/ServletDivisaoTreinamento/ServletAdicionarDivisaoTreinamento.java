package org.astro.astro_app.Controller.ServletDivisaoTreinamento;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import org.astro.astro_app.model.DivisaoTreinamento;

import java.io.IOException;
import java.lang.invoke.StringConcatFactory;

@WebServlet(name = "divisoes", value = "/adicionar-divisao")
public class ServletAdicionarDivisaoTreinamento extends HttpServlet{

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

        String IdCronogramaTexto = request.getParameter("");
        int idCronograma = Integer.parseInt(IdCronogramaTexto);

        String codigoTexto = request.getParameter("");
        int codigo = Integer.parseInt(codigoTexto);

        String idEmpresaTexto = request.getParameter("");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);

        String nome = request.getParameter("");

        DivisaoTreinamento novaDivisaoTreinamento = new DivisaoTreinamento(
                idCronograma,
                codigo,
                idEmpresa,
                nome
        );

        divisaoTreinamentoDAO.inserir(novaDivisaoTreinamento);

        response.sendRedirect(request.getContextPath() + "/divisao-treinamento");

    }
}
