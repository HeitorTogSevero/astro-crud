package org.astro.astro_app.Controller.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.EmpresaDAO;
import org.astro.astro_app.model.Empresa;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletReadEmpresa", value = "/listar-empresas")
public class ServletReadEmpresa extends HttpServlet {

    private EmpresaDAO empresaDAO;

    @Override
    public void init() {
        empresaDAO = new EmpresaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String cnae = request.getParameter("cnae");
        String nome = request.getParameter("nome");
        String dataMin = request.getParameter("dt_registro_min");
        String dataMax    = request.getParameter("dt_registro_max");

        StringBuilder sql = new StringBuilder("SELECT * FROM empresa WHERE 1=1");

        try {
            // filtro estado
            if (estado != null && !estado.isBlank()) {
                sql.append(" AND estado = '").append(estado.trim().replace("'", "''")).append("'");
            }
            // filtro cidade
            if (cidade != null && !cidade.isBlank()) {
                sql.append(" AND cidade ILIKE '%").append(cidade.trim().replace("'", "''")).append("%'");
            }
            // filtro cnae
            if (cnae != null && !cnae.isBlank()) {
                sql.append(" AND cnae ILIKE '%").append(cnae.trim().replace("'", "''")).append("%'");
            }
            // filtro nome
            if (nome != null && !nome.isBlank()) {
                sql.append(" AND nome ILIKE '%").append(nome.trim().replace("'", "''")).append("%'");
            }
            // filtro data min
            if (dataMin != null && !dataMin.isBlank()) {
                sql.append(" AND dt_registro >= '").append(dataMin).append("'");
            }
            // filtro data max
            if (dataMax != null && !dataMax.isBlank()) {
                sql.append(" AND dt_registro <= '").append(dataMax).append("'");
            }

        } catch (IllegalArgumentException e) {
            sql = new StringBuilder("SELECT * FROM empresa WHERE 1=1");
            request.setAttribute("erro", "filtro invalido");
        }

        sql.append(" ORDER BY 1");

        ArrayList<Empresa> empresas = empresaDAO.buscar(sql.toString());

        request.setAttribute("empresas", empresas);

        // mantem os filtros na tela
        request.setAttribute("filtroEstado", estado);
        request.setAttribute("filtroCidade", cidade);
        request.setAttribute("filtroCnae", cnae);
        request.setAttribute("filtroNome", nome);
        request.setAttribute("filtroDataMin", dataMin);
        request.setAttribute("filtroDataMax", dataMax);

        request.getRequestDispatcher("/WEB-INF/EmpresaJSP/Empresa.jsp").forward(request, response);
    }
}