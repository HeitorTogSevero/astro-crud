package org.astro.astro_app.Controller.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;
import org.astro.astro_app.model.Departamento;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletReadDepartamento", value = "listar-departamento")
public class ServletReadDepartamento extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init(){
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Departamento> departamentos = departamentoDAO.buscar();

        request.setAttribute("departamentos", departamentos);

        request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/ReadDeprtamento.jsp").forward(request,response);
    }
}
