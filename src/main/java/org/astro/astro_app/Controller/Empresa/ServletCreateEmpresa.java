package org.astro.astro_app.Controller.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.EmpresaDAO;
import org.astro.astro_app.model.Empresa;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "ServletEmpresa", value = "/adicionar-empresa")
public class ServletCreateEmpresa extends HttpServlet{

    private EmpresaDAO empresaDAO;

    @Override
    public void init(){
        empresaDAO = new EmpresaDAO();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException{

        String cnae = request.getParameter("");

        String nome = request.getParameter("");

        String cep = request.getParameter("");

        String cnpj = request.getParameter("");

        String rua = request.getParameter("");

        String bairro = request.getParameter("");

        String cidade = request.getParameter("");

        String estado = request.getParameter("");

        String dataRegistroTexto = request.getParameter("");

        Date dataRegistro = null;

        if(dataRegistroTexto != null && !dataRegistroTexto.isBlank()){
            dataRegistro = Date.valueOf(dataRegistroTexto);
        }

        Empresa novaEmpresa = new Empresa(
                cnae,
                nome,
                cep,
                cnpj,
                rua,
                estado,
                bairro,
                cidade,
                dataRegistro
        );

        empresaDAO.inserir(novaEmpresa);

        response.sendRedirect(request.getContextPath() + "/empresas");

    }
}
