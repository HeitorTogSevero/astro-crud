package org.astro.astro_app.Controller.Departamento;

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
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String grauRiscoTexto = request.getParameter("grau_risco");

        String atividade = request.getParameter("atividade");

        String idEmpresaTexto = request.getParameter("id_empresa");

        StringBuilder sql = new StringBuilder("SELECT * FROM departamento WHERE 1=1");

        String nome = request.getParameter("nome");

        try {
            Integer grauRisco = null;
            Integer idEmpresa = null;

            if (grauRiscoTexto != null && !grauRiscoTexto.isBlank()) {
                grauRisco = Integer.parseInt(grauRiscoTexto);
                if (grauRisco < 1 || grauRisco > 4) {
                    throw new IllegalArgumentException("Grau de risco deve estar entre 1 e 4");
                }
            }
            if (idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")) {
                idEmpresa = Integer.parseInt(idEmpresaTexto);
                if (idEmpresa < 0) {
                    throw new IllegalArgumentException("ID da empresa deve ser positivo");
                }
            }

            if (grauRisco != null) {
                sql.append(" AND grau_risco = 'Grau ").append(grauRisco).append("'");
            }
            if (atividade != null && !atividade.isBlank()) {
                sql.append(" AND atividade ILIKE '%").append(atividade.trim().replace("'", "''")).append("%'");
            }
            if (idEmpresa != null && idEmpresa > 0) {
                sql.append(" AND id_empresa = ").append(idEmpresa);
            }
            if (nome != null && !nome.isBlank()) {
                sql.append(" AND nome ILIKE '%").append(nome.trim().replace("'", "''")).append("%'");
            }
        }catch (IllegalArgumentException e) {
            sql = new StringBuilder("SELECT * FROM departamento WHERE 1=0");
            request.setAttribute("erro", "filtro invalido");
        }
        sql.append(" ORDER BY 1");

        ArrayList<Departamento> departamentos = departamentoDAO.buscar(sql.toString());

        request.setAttribute("departamentos", departamentos);

        request.setAttribute("filtroGrauRisco", grauRiscoTexto);
        request.setAttribute("filtroAtividade", atividade);
        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);

        request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);
    }
}
