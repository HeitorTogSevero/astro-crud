package org.astro.astro_app.Controller.Alerta;

import jakarta.ejb.Local;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;
import java.sql.Date;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet (name = "ServletAlertas", value = "/adicionar-alerta")
public class ServletCreateAlerta extends HttpServlet{

    private AlertaDAO alertaDAO;


    public void init(){
        alertaDAO = new AlertaDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String codigoTexto = request.getParameter("");
        int codigo = Integer.parseInt(codigoTexto);// Transformei o parametro em Inteiro

        String idEmpresaTexto = request.getParameter("");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);// Transformei o parametro em Inteiro

        String descricao = request.getParameter("");

        String dtLimiteTexto = request.getParameter("");

        Date dataLimite = null;
        LocalDate localDataLimite = dataLimite.toLocalDate();

        if(dtLimiteTexto != null && !dtLimiteTexto.isBlank()){
            dataLimite = Date.valueOf(dtLimiteTexto);// Transformei o parametro em Date
            return;
        }

        else if(descricao == null || descricao.equals(" ")){
            response.getWriter().println("Erro: O campo descrição não foi preenchido");
        }
        else if(descricao.length() > 250){
            response.getWriter().println("Erro: Descrição max. de caracteres é 250");
        }

        else if(codigo <= 0 || codigoTexto.equals(" ")){
            response.getWriter().println("Erro: O código deve ser > 0, e não pode ser nulo");
        } 

        else if(localDataLimite.isBefore(LocalDate.now())){
            response.getWriter().println("Erro: A data deve ser depois ou no dia de Hoje");
        } 
        
        else if (idEmpresa == 0 || idEmpresaTexto.equals(" ")){
            response.getWriter().println("Erro: Este Id deve ser > 0, e não pode der nulo");
        }

        else {
            Alerta novoAlerta = new Alerta(
                    codigo,
                    idEmpresa,
                    descricao,
                    dataLimite
            );

            alertaDAO.inserir(novoAlerta);

            response.sendRedirect(request.getContextPath() + "/alertas");
        }

    }


}
