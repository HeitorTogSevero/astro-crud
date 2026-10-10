package org.astro.astro_app.Controller.Alerta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;

import java.io.IOException;

@WebServlet(name = "ServletDeleteAlerta", value = "/deletar-alerta")
public class ServletDeleteAlerta extends HttpServlet {

    private AlertaDAO alertaDAO;

    public void init() {
        alertaDAO = new AlertaDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pega o id alerta
        String idAlertaTexto = request.getParameter("id_alerta");
        String mensagem = null;

        //validacao
        if (idAlertaTexto == null || idAlertaTexto.isBlank()) {
            mensagem = "O id do alerta não informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/AlertaJSP/alertaJSP.jsp").forward(request, response);
            return;
        }

        try {
            int idAlerta = Integer.parseInt(idAlertaTexto);

            if (idAlerta <= 0) {
                mensagem = "id do alerta invalido";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/AlertaJSP/alertaJSP.jsp").forward(request, response);
                return;
            }

            //removendo do bd
            alertaDAO.remover(idAlerta);

            //volta pra a lista
            response.sendRedirect(request.getContextPath() + "/lista-alerta");

        } catch (NumberFormatException e) {
            mensagem = "id do alerta deve ser uma numero valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/AlertaJSP/alertaJSP.jsp").forward(request, response);
        }
    }
}
