package org.astro.astro_app.Controller.DivisaoTreinamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import org.astro.astro_app.model.DivisaoTreinamento;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletReadDivisaoTreinamento", value = "/listar-divisoes")
public class ServletReadDivisaoTreinamento extends HttpServlet{

    private DivisaoTreinamentoDAO divisaoTreinamentoDAO;

    @Override
    public void init(){
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        String codigoTexto = request.getParameter("codigo");
        String idEmpresaTexto = request.getParameter("id_empresa");
        String nome = request.getParameter("nome");

        StringBuilder sql = new StringBuilder("SELECT * FROM divisao_treinamento WHERE 1=1");

        try {
            if (codigoTexto != null && !codigoTexto.isBlank() && !codigoTexto.equals("0")) {
                sql.append(" AND codigo = ").append(Integer.parseInt(codigoTexto));
            }
            if (idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")) {
                sql.append(" AND id_empresa = ").append(Integer.parseInt(idEmpresaTexto));
            }
            if (nome != null && !nome.isBlank()) {
                sql.append(" AND nome ILIKE '%").append(nome.trim().replace("'", "''")).append("%'");
            }

        } catch (IllegalArgumentException e) {
            sql = new StringBuilder("SELECT * FROM divisao_treinamento WHERE 1=1");
            request.setAttribute("erro", "filtro invalido");
        }
        sql.append(" ORDER BY 1");

        ArrayList<DivisaoTreinamento> divisaoTreinamentos = divisaoTreinamentoDAO.buscar(sql.toString());

        request.setAttribute("divisoes", divisaoTreinamentos);

        request.setAttribute("filtroCodigo", codigoTexto);
        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);
        request.setAttribute("filtroNome", nome);

        request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request, response);
    }
}