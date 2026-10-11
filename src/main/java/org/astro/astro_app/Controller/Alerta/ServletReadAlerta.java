package org.astro.astro_app.Controller.Alerta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "ServletReadAlerta", value = "/lista-alerta")
public class ServletReadAlerta extends HttpServlet {

    private AlertaDAO alertaDAO;

    public void init() {
        alertaDAO = new AlertaDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //pega os filtros
        String codigoTexto = request.getParameter("codigo");
        String dtLimiteMin = request.getParameter("dt_limite_min");
        String dtLimiteMax = request.getParameter("dt_limite_max");
        String idEmpresaTexto = request.getParameter("id_empresa");

        //monta a query
        StringBuilder sql = new StringBuilder("SELECT * FROM alerta WHERE 1=1");

        //filtros
        try {
            //filtro por código
            if (codigoTexto != null && !codigoTexto.isBlank() && !codigoTexto.equals("0")) {
                sql.append(" AND codigo = ").append(Integer.parseInt(codigoTexto));
            }
            //filtro por dtminima
            if (dtLimiteMin != null && !dtLimiteMin.isBlank()) {
                Date.valueOf(dtLimiteMin); //ve o formato ano-mes-dia, se for invalido mostra o erro
                sql.append(" AND dt_limite >= '").append(dtLimiteMin).append("'");
            }
            //filtro por dtmaxima
            if (dtLimiteMax != null && !dtLimiteMax.isBlank()) {
                Date.valueOf(dtLimiteMax); //ve o formato ano-mes-dia, se for invalido mostra o erro
                sql.append(" AND dt_limite <= '").append(dtLimiteMax).append("'");
            }
            //filtro por idempresa
            if (idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")) {
                sql.append(" AND id_empresa = ").append(Integer.parseInt(idEmpresaTexto));
            }
        } catch (IllegalArgumentException e) {
            sql = new StringBuilder("SELECT * FROM alerta WHERE 1=1");
            request.setAttribute("erro", "Filtro invalido");
        }
        sql.append(" ORDER BY id_alerta");

        //executa no banco
        ArrayList<Alerta> alertas = alertaDAO.buscar(sql.toString());

        //envia a lista e filtros de volta para o jsp
        request.setAttribute("alertas", alertas);

        //coloquei isso mas é opcional(é só p manter os valores no filtro)
        request.setAttribute("filtroCodigo", codigoTexto);
        request.setAttribute("filtroDtMin", dtLimiteMin);
        request.setAttribute("filtroDtMax", dtLimiteMax);
        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);

        //encaminha pra o jsp
        request.getRequestDispatcher("/WEB-INF/AlertaJSP/alertaJSP.jsp").forward(request, response);
    }
}