package org.astro.astro_app.Controller.Funcionario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.FuncionarioDAO;
import org.astro.astro_app.model.Funcionario;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletReadFuncionario", value = "")
public class ServletReadFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init() {
        funcionarioDAO = new FuncionarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> funcionarios = funcionarioDAO.buscar();

        request.setAttribute("funcionarios", funcionarios);

        request.getRequestDispatcher("/WEB-INF/FuncionarioJsp/ReadFuncionario.jsp").forward(request,response);
    }
}
