package org.astro.astro_app.Controller.Funcionario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.FuncionarioDAO;

import java.io.IOException;

@WebServlet(name = "ServletDeleteFuncionario", value = "/deletar-funcionario")
public class ServletDeleteFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init() {
        funcionarioDAO = new FuncionarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idFuncionarioTexto = request.getParameter("id_funcionario");
        String mensagem = "";

        if (idFuncionarioTexto == null || idFuncionarioTexto.isBlank()) {
            mensagem = "id nao informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);
            return;
        }

        try {
            int idFuncionario = Integer.parseInt(idFuncionarioTexto);

            if (idFuncionario <= 0) {
                mensagem = "id do funcionario deve ser > 0";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);
                return;
            }

            funcionarioDAO.remover(idFuncionario);
            response.sendRedirect(request.getContextPath() + "/listar-funcionarios");

        } catch (NumberFormatException e) {
            mensagem = "o id do funcionario deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);
        }
    }
}