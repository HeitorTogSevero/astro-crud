package org.astro.astro_app.Controller.Alerta;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "ServletReadAlerta", value = "/listar-alerta")
public class ServletReadAlerta extends HttpServlet {

    private AlertaDAO alertaDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO de alertas quando o servlet e iniciado
        alertaDAO = new AlertaDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lê os filtros opcionais enviados pela tela de listagem
        String codigoTexto = request.getParameter("código");

        String dtLimiteMin = request.getParameter("dt_limite_min");

        String dtLimiteMax = request.getParameter("dt_limite_max");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String descricao = request.getParameter("descricao");

        // Inicia a consulta e adiciona somente os filtros preenchidos
        StringBuilder sql = new StringBuilder("SELECT * FROM alerta WHERE 1=1");

        // Valida os valores dos filtros antes de incluir eles na consulta
        try {

            // Filtro por codigo
            if (codigoTexto != null && !codigoTexto.isBlank() && !codigoTexto.equals("0")) {

                sql.append(" AND código = ").append(Integer.parseInt(codigoTexto));

            }

            // Filtro por dtminima
            if (dtLimiteMin != null && !dtLimiteMin.isBlank()) {

                Date.valueOf(dtLimiteMin); //ve o formato ano-mes-dia, se for invalido mostra o erro

                sql.append(" AND dt_limite >= '").append(dtLimiteMin).append("'");

            }

            // Filtro por dtmaxima
            if (dtLimiteMax != null && !dtLimiteMax.isBlank()) {

                Date.valueOf(dtLimiteMax); //ve o formato ano-mes-dia, se for invalido mostra o erro

                sql.append(" AND dt_limite <= '").append(dtLimiteMax).append("'");

            }

            // Filtro por idempresa
            if (idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")) {

                sql.append(" AND id_empresa = ").append(Integer.parseInt(idEmpresaTexto));

            }

            // Pesquisar nome
            if (descricao != null && !descricao.isBlank()) {

                sql.append(" AND descricao ILIKE '%").append(descricao.trim().replace("'", "''")).append("%'");

            }
        }

        catch (IllegalArgumentException e) {

            // Se algum filtro for invalido, consulta todos os alertas e informa o erro
            sql = new StringBuilder("SELECT * FROM alerta WHERE 1=1");

            request.setAttribute("erro", "Filtro invalido");

        }

        sql.append(" ORDER BY id_alerta");

        // Executa a consulta e guarda os resultados para a pagina JSP
        ArrayList<Alerta> alertas = alertaDAO.buscar(sql.toString());

        // Disponibiliza resultados e filtros para manter a tela preenchida
        request.setAttribute("alertas", alertas);

        // Mantém os valores no filtro(opicional)
        request.setAttribute("filtroCodigo", codigoTexto);

        request.setAttribute("filtroDtMin", dtLimiteMin);

        request.setAttribute("filtroDtMax", dtLimiteMax);

        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);

        // Encaminha internamente para a tela que apresenta a listagem
        request.getRequestDispatcher("/WEB-INF/AlertaJSP/alerta.jsp").forward(request, response);

    }
}
