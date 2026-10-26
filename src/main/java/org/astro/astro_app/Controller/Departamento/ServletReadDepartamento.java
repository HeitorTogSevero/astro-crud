package org.astro.astro_app.Controller.Departamento;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;
import org.astro.astro_app.model.Departamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletReadDepartamento", value = "/listar-departamentos")
public class ServletReadDepartamento extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init(){

        // Concede o acesso ao DAO do departamento quando o servlet e iniciado
        departamentoDAO = new DepartamentoDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Lê os filtros opcionais enviados pela tela de listagem
        String grauRiscoTexto = request.getParameter("grau_risco");

        String atividade = request.getParameter("atividade");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String nome = request.getParameter("nome");

        // Inicia a consulta e adiciona somente os filtros preenchidos
        StringBuilder sql = new StringBuilder("SELECT * FROM departamento WHERE 1=1");

        // Valida os valores dos filtros antes de incluir eles na consulta
        try {

            // Guarda os filtros convertidos antes de montar a consulta
            Integer grauRisco = null;
            Integer idEmpresa = null;

            // Filtro grau de risco
            if (grauRiscoTexto != null && !grauRiscoTexto.isBlank()) {

                grauRisco = Integer.parseInt(grauRiscoTexto);

                if (grauRisco < 1 || grauRisco > 4) {

                    throw new IllegalArgumentException("Grau de risco deve estar entre 1 e 4");

                }

            }

            // Filtro id Empresa
            if (idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")) {

                idEmpresa = Integer.parseInt(idEmpresaTexto);

                if (idEmpresa < 0) {

                    throw new IllegalArgumentException("ID da empresa deve ser positivo");

                }

            }

            // Adiciona o grau de risco quando ele for informado
            if (grauRisco != null) {

                sql.append(" AND grau_risco = 'Grau ").append(grauRisco).append("'");

            }

            // Adiciona a atividade quando ela for informada
            if (atividade != null && !atividade.isBlank()) {

                sql.append(" AND atividade ILIKE '%").append(atividade.trim().replace("'", "''")).append("%'");

            }

            // Adiciona o IdEmpresa quando ele for informada
            if (idEmpresa != null && idEmpresa > 0) {

                sql.append(" AND id_empresa = ").append(idEmpresa);

            }

            // Verifica se a variável não é nula e não contém apenas espaços em branco
            if (nome != null && !nome.isBlank()) {

                sql.append(" AND nome ILIKE '%").append(nome.trim().replace("'", "''")).append("%'");

            }

        }

        catch (IllegalArgumentException e) {

            // NÃo retorna registros como se o filtro invalido tivesse sido aplicado
            sql = new StringBuilder("SELECT * FROM departamento WHERE 1=0");
            request.setAttribute("erro", "filtro invalido");

        }

        sql.append(" ORDER BY 1");

        // Executa a consulta e guarda os resultados para a pagina JSP
        ArrayList<Departamento> departamentos = departamentoDAO.buscar(sql.toString());

        // Disponibiliza resultados e filtros para manter a tela preenchida
        request.setAttribute("departamentos", departamentos);

        // Mantém os valores no filtro(opicional)
        request.setAttribute("filtroGrauRisco", grauRiscoTexto);

        request.setAttribute("filtroAtividade", atividade);

        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);

        // Encaminha internamente para a tela que apresenta a listagem
        request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);

    }
}
