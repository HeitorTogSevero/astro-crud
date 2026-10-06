package org.astro.astro_app.Controller.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.EmpresaDAO;

import java.io.IOException;

@WebServlet(name = "ServletEmpresa", value = "/excluir-empresa")
public class ServletDeleteEmpresa extends HttpServlet {

    private EmpresaDAO empresaDAO;

    @Override
    public void init(){
        empresaDAO = new EmpresaDAO();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException {

        String acao = request.getParameter("");

        if("excluir".equals(acao)){

            String idTexto = request.getParameter("id");
            int id = Integer.parseInt(idTexto);

            empresaDAO.remover(id);

            response.sendRedirect(request.getContextPath() + "/empresas");
        }
    }
}
