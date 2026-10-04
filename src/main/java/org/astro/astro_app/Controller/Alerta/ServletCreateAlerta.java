package org.astro.astro_app.Controller.Alerta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;
import java.sql.Date;

import java.io.IOException;

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

        Date DataLimite = null;

        if(dtLimiteTexto != null && !dtLimiteTexto.isBlank()){
            DataLimite = Date.valueOf(dtLimiteTexto);// Transformei o parametro em Date
            return;
        }

        Alerta novoAlerta = new Alerta(
                codigo,
                idEmpresa,
                descricao,
                DataLimite
        );

        alertaDAO.inserir(novoAlerta);

        response.sendRedirect(request.getContextPath() + "/alertas");

    }


}
