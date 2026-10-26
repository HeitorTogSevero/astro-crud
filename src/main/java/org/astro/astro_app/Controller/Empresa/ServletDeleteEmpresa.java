package org.astro.astro_app.Controller.Empresa;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.EmpresaDAO;

import java.io.IOException;

@WebServlet(name = "ServletDeleteEmpresa", value = "/deletar-empresa")
public class ServletDeleteEmpresa extends HttpServlet {

    private EmpresaDAO empresaDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO de empresas quando o servlet e iniciado
        empresaDAO = new EmpresaDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Le o identificador do alerta enviado pelo formulario de exclusao
        String idEmpresaTexto = request.getParameter("id_empresa");
        String mensagem = "";

        // Impede a exclusao quando o identificador nao foi informado
        if (idEmpresaTexto == null || idEmpresaTexto.isBlank()) {

            mensagem = "id nao informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/EmpresaJSP/Empresa.jsp").forward(request, response);
            return;

        }

        try {

            // Converte e valida o identificador antes de chamar o DAO
            int idEmpresa = Integer.parseInt(idEmpresaTexto);

            if (idEmpresa <= 0) {

                mensagem = "id da empresa deve ser > 0";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/EmpresaJSP/Empresa.jsp").forward(request, response);
                return;

            }

            // Remove o alerta do banco de dados
            empresaDAO.remover(idEmpresa);

            // Redireciona para a listagem apos a exclusao
            response.sendRedirect(request.getContextPath() + "/listar-empresas");

        } catch (NumberFormatException e) {

            // Se o identificador nao for numerico, informa o erro no formulario
            mensagem = "o id da empresa deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/EmpresaJSP/Empresa.jsp").forward(request, response);
        }
    }
}