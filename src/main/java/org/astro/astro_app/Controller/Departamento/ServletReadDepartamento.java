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

        try {
            //filtro grau risco
            if (grauRiscoTexto != null && !grauRiscoTexto.isBlank() && !grauRiscoTexto.equals("0")){
                sql.append(" AND grau_risco = '").append(Integer.parseInt(grauRiscoTexto)).append("'");
            }
            if (atividade != null && !atividade.isBlank()) {
                sql.append(" AND atividade ILIKE '%").append(atividade.trim().replace("'", "''")).append("%'");
            }
            //filtro id empresa
            if(idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")){
                sql.append(" AND id_empresa = ").append(Integer.parseInt(idEmpresaTexto));
            }
        }catch (IllegalArgumentException e) {
            sql = new StringBuilder("SELECT * FROM departamento WHERE 1=1");
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