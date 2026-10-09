package org.astro.astro_app.Controller.DivisaoTreinamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import org.astro.astro_app.model.DivisaoTreinamento;

import java.io.IOException;

@WebServlet(name = "divisoes", value = "/adicionar-divisao")
public class ServletCreateDivisaoTreinamento extends HttpServlet{

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

        String mensagem;

        if(idCronograma <= 0 || IdCronogramaTexto.equals(" ")){
            mensagem = "Erro: o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(codigo <= 0 || codigoTexto.equals(" ")){
            mensagem = "Erro: o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(idEmpresa <= 0 || idEmpresaTexto.equals(" ")){
            mensagem = "Erro: o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(nome == null || nome.equals(" ")){
            mensagem = "Erro: o nome não pode ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(nome.length() > 50){
            mensagem = "Erro: o nome deve conter até 50 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else {
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
}
