package org.astro.astro_app.Controller.DivisaoTreinamento;

// Biblioteca:
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

        // Concede o acesso ao DAO da divisão treinamentp quando o servlet e iniciado
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        // Lê os filtros opcionais enviados pela tela de listagem
        String codigoTexto = request.getParameter("codigo");
        String idEmpresaTexto = request.getParameter("id_empresa");
        String nome = request.getParameter("nome");

        // Inicia a consulta e adiciona somente os filtros preenchidos
        StringBuilder sql = new StringBuilder("SELECT * FROM divisao_treinamento WHERE 1=1");

        // Valida os valores dos filtros antes de incluir eles na consulta
        try {

            // Adiciona o código quando não for vazio e diferente de 0
            if (codigoTexto != null && !codigoTexto.isBlank() && !codigoTexto.equals("0")) {

                sql.append(" AND codigo = ").append(Integer.parseInt(codigoTexto));

            }

            // Adiciona o idEmpresa quando não for vazio e diferente de 0
            if (idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")) {

                sql.append(" AND id_empresa = ").append(Integer.parseInt(idEmpresaTexto));

            }

            // Adiciona o nome quando não for vazio
            if (nome != null && !nome.isBlank()) {

                sql.append(" AND nome ILIKE '%").append(nome.trim().replace("'", "''")).append("%'");

            }

        }

        catch (IllegalArgumentException e) {

            // NÃo retorna registros como se o filtro invalido tivesse sido aplicado
            sql = new StringBuilder("SELECT * FROM divisao_treinamento WHERE 1=1");
            request.setAttribute("erro", "filtro invalido");

        }

        sql.append(" ORDER BY 1");

        // Executa a consulta e guarda os resultados para a pagina JSP
        ArrayList<DivisaoTreinamento> divisaoTreinamentos = divisaoTreinamentoDAO.buscar(sql.toString());

        // Disponibiliza resultados e filtros para manter a tela preenchida
        request.setAttribute("divisoes", divisaoTreinamentos);

        // Mantém os valores no filtro(opicional)
        request.setAttribute("filtroCodigo", codigoTexto);

        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);

        request.setAttribute("filtroNome", nome);


        // Encaminha internamente para a tela que apresenta a listagem
        request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request, response);

    }
}