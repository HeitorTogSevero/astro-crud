package org.astro.astro_app.Controller.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.EmpresaDAO;
import org.astro.astro_app.model.Empresa;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletEmpresa", value = "/editar-empresa")
public class ServletUpdateEmpresa extends HttpServlet{

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

        String acao = request.getParameter("");

        if("editar".equals(acao)){
            int id = Integer.parseInt(request.getParameter("id"));

            Empresa Empresa = (Empresa) empresaDAO.buscarPorIdEmpresa(id);

            request.getRequestDispatcher(
                    ""
            ).forward(request, response);
        }

        List<Empresa> empresas = empresaDAO.buscar();

        request.setAttribute("empresas", empresas);

        request.getRequestDispatcher(
                ""
        ).forward(request, response);

        response.sendRedirect(request.getContextPath() + "/empresas");
    }
}
