package org.astro.astro_app.Controller.Funcionario;

// Biblioteca:
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

        // Concede o acesso ao DAO do funcionário quando o servlet e iniciado
        funcionarioDAO = new FuncionarioDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Le o identificador do alerta enviado pelo formulario de exclusao
        String idFuncionarioTexto = request.getParameter("id_funcionario");
        String mensagem = "";

        // Impede a exclusao quando o identificador nao foi informado
        if (idFuncionarioTexto == null || idFuncionarioTexto.isBlank()) {

            mensagem = "id nao informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);
            return;

        }

        try {

            // Converte e valida o identificador antes de chamar o DAO
            int idFuncionario = Integer.parseInt(idFuncionarioTexto);

            if (idFuncionario <= 0) {
                mensagem = "id do funcionario deve ser > 0";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);
                return;
            }

            // Remove o alerta do banco de dados
            funcionarioDAO.remover(idFuncionario);

            // Redireciona para a listagem apos a exclusao
            response.sendRedirect(request.getContextPath() + "/listar-funcionarios");

        } catch (NumberFormatException e) {

            // Se o identificador nao for numerico, informa o erro no formulario
            mensagem = "o id do funcionario deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);

        }
    }
}