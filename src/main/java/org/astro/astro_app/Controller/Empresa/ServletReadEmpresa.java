package org.astro.astro_app.Controller.Empresa;

// Biblioteca:
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

        // Concede o acesso ao DAO de empresas quando o servlet e iniciado
        empresaDAO = new EmpresaDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lê os filtros opcionais enviados pela tela de listagem
        String estado = request.getParameter("estado");

        String cidade = request.getParameter("cidade");

        String cnae = request.getParameter("cnae");

        String nome = request.getParameter("nome");

        String dataMin = request.getParameter("dt_registro_min");

        String dataMax    = request.getParameter("dt_registro_max");


        // Inicia a consulta e adiciona somente os filtros preenchidos
        StringBuilder sql = new StringBuilder("SELECT * FROM empresa WHERE 1=1");

        // Valida os valores dos filtros antes de incluir eles na consulta
        try {

            // Filtro estado
            if (estado != null && !estado.isBlank()) {

                sql.append(" AND estado = '").append(estado.trim().replace("'", "''")).append("'");

            }

            // Filtro cidade
            if (cidade != null && !cidade.isBlank()) {

                sql.append(" AND cidade ILIKE '%").append(cidade.trim().replace("'", "''")).append("%'");

            }

            // Filtro cnae
            if (cnae != null && !cnae.isBlank()) {

                sql.append(" AND cnae ILIKE '%").append(cnae.trim().replace("'", "''")).append("%'");

            }

            // Filtro nome
            if (nome != null && !nome.isBlank()) {

                sql.append(" AND nome ILIKE '%").append(nome.trim().replace("'", "''")).append("%'");

            }

            // Filtro data min
            if (dataMin != null && !dataMin.isBlank()) {

                sql.append(" AND dt_registro >= '").append(dataMin).append("'");

            }

            // Filtro data max
            if (dataMax != null && !dataMax.isBlank()) {

                sql.append(" AND dt_registro <= '").append(dataMax).append("'");

            }

        }

        catch (IllegalArgumentException e) {

            // NÃo retorna registros como se o filtro invalido tivesse sido aplicado
            sql = new StringBuilder("SELECT * FROM empresa WHERE 1=1");
            request.setAttribute("erro", "filtro invalido");

        }

        sql.append(" ORDER BY 1");

        // Executa a consulta e guarda os resultados para a pagina JSP
        ArrayList<Empresa> empresas = empresaDAO.buscar(sql.toString());

        // Disponibiliza resultados e filtros para manter a tela preenchida
        request.setAttribute("empresas", empresas);

        // Mantém os valores no filtro(opicional)
        request.setAttribute("filtroEstado", estado);

        request.setAttribute("filtroCidade", cidade);

        request.setAttribute("filtroCnae", cnae);

        request.setAttribute("filtroNome", nome);

        request.setAttribute("filtroDataMin", dataMin);

        request.setAttribute("filtroDataMax", dataMax);

        // Encaminha internamente para a tela que apresenta a listagem
        request.getRequestDispatcher("/WEB-INF/EmpresaJSP/Empresa.jsp").forward(request, response);

    }
}