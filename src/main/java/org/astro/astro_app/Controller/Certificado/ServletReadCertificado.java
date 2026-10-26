package org.astro.astro_app.Controller.Certificado;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.CertificadoDAO;
import org.astro.astro_app.model.Certificado;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletReadCertificados", value = "/listar-certificados")
public class ServletReadCertificado extends HttpServlet {

    private CertificadoDAO certificadoDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO do certificado quando o servlet e iniciado
        certificadoDAO = new CertificadoDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        // Lê os filtros opcionais enviados pela tela de listagem
        String dataEmissaoMinimaTexto = request.getParameter("dt_emissao_min");

        String dataEmissaoMaximaTexto = request.getParameter("dt_emissao_max");

        String idFuncionarioTexto = request.getParameter("id_funcionario");


        // Inicia a consulta e adiciona somente os filtros preenchidos
        StringBuilder sql = new StringBuilder("SELECT * FROM certificado WHERE 1=1");


        // Valida os valores dos filtros antes de incluir eles na consulta
        try {

            // Guarda os filtros convertidos antes de montar a consulta
            Date dataEmissaoMinima = null;
            Date dataEmissaoMaxima = null;
            Integer idFuncionario = null;

            // Filtro dtminima
            if (dataEmissaoMinimaTexto != null && !dataEmissaoMinimaTexto.isBlank()) {

                // Valida o formato ano-mes-dia e converte a data minima
                dataEmissaoMinima = Date.valueOf(dataEmissaoMinimaTexto);

            }

            // Filtro dtmaxima
            if (dataEmissaoMaximaTexto != null && !dataEmissaoMaximaTexto.isBlank()) {

                // Valida o formato ano-mes-dia e converte a data máxima
                dataEmissaoMaxima = Date.valueOf(dataEmissaoMaximaTexto);

            }

            // Filtro idfuncionario
            if (idFuncionarioTexto != null && !idFuncionarioTexto.isBlank() && !idFuncionarioTexto.equals("0")) {

                idFuncionario = Integer.parseInt(idFuncionarioTexto);

                if (idFuncionario < 1) {

                    throw new IllegalArgumentException("ID do funcionário deve ser positivo");

                }

            }

            // Confere se a data minima não estiver depois da data maxima
            if (dataEmissaoMinima != null && dataEmissaoMaxima != null && dataEmissaoMinima.after(dataEmissaoMaxima)) {

                throw new IllegalArgumentException("Data inicial posterior à data final");

            }

            // Adiciona a data minima quando ela foi informada
            if (dataEmissaoMinima != null) {

                sql.append(" AND dt_emissao >= '").append(dataEmissaoMinima).append("'");

            }

            // Adiciona a data maxima quando ela foi informada
            if (dataEmissaoMaxima != null) {

                sql.append(" AND dt_emissao <= '").append(dataEmissaoMaxima).append("'");

            }

            // Adiciona o id do funcionário quando ele foi informado
            if (idFuncionario != null) {

                sql.append(" AND id_funcionario = ").append(idFuncionario);

            }
        }

        catch (IllegalArgumentException e) {

            // NÃo retorna registros como se o filtro invalido tivesse sido aplicado
            sql = new StringBuilder("SELECT * FROM certificado WHERE 1=0");
            request.setAttribute("erro", "filtro invalido");

        }

        sql.append(" ORDER BY id_certificado");


        // Executa a consulta e guarda os resultados para a pagina JSP
        ArrayList<Certificado> certificados= certificadoDAO.buscar(sql.toString());

        // Disponibiliza resultados e filtros para manter a tela preenchida
        request.setAttribute("certificados", certificados);

        // Mantém os valores no filtro(opicional)
        request.setAttribute("filtroDtEmissaoMin", dataEmissaoMinimaTexto);

        request.setAttribute("filtroDtEmissaoMax", dataEmissaoMaximaTexto);

        request.setAttribute("filtroIdFuncionario", idFuncionarioTexto);

        // Encaminha internamente para a tela que apresenta a listagem
        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);

    }
}
