package org.astro.astro_app.Controller.Alerta;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;

import java.io.IOException;

@WebServlet(name = "ServletDeleteAlerta", value = "/deletar-alerta")
public class ServletDeleteAlerta extends HttpServlet {

    private AlertaDAO alertaDAO;

    public void init() {

        // Concede o acesso ao DAO de alertas quando o servlet e iniciado
        alertaDAO = new AlertaDAO();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Le o identificador do alerta enviado pelo formulário de exclusão
        String idAlertaTexto = request.getParameter("id_alerta");
        String mensagem = null;

        // Impede a exclusão quando o identificador não foi informado
        if (idAlertaTexto == null || idAlertaTexto.isBlank()) {

            mensagem = "o id do alerta não informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/AlertaJSP/alerta.jsp").forward(request, response);
            return;

        }

        try {

            // Converte e valida o identificador antes de chamar o DAO
            int idAlerta = Integer.parseInt(idAlertaTexto);

            if (idAlerta <= 0) {

                mensagem = "id do alerta invalido";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/AlertaJSP/alerta.jsp").forward(request, response);
                return;

            }

            // Remove o alerta do banco de dados
            alertaDAO.remover(idAlerta);

            // Redireciona para a listagem apos a exclusao
            response.sendRedirect(request.getContextPath() + "/listar-alerta");

        } catch (NumberFormatException e) {

            // Se o identificador nao for numérico, informa o erro no formulário
            mensagem = "id do alerta deve ser uma numero valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/AlertaJSP/alerta.jsp").forward(request, response);

        }
    }
}
