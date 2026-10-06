package org.astro.astro_app.Controller.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.EmpresaDAO;
import org.astro.astro_app.model.Empresa;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletEmpresa", value = "/listar-empresas")
public class ServletReadEmpresa extends HttpServlet{

    private EmpresaDAO empresaDAO;

    public void init(){
        empresaDAO = new EmpresaDAO();
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException {

        List<Empresa> empresas = empresaDAO.buscar();

        request.setAttribute("empresas", empresas);

        request.getRequestDispatcher(
                "/webapp/WEB-INF/alerta.jsp"
        ).forward(request, response);

        response.sendRedirect(request.getContextPath() + "/empresas");
    }
}
