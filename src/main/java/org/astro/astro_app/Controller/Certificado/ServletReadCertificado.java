package org.astro.astro_app.Controller.Certificado;

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
        certificadoDAO = new CertificadoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        //pega os filtros
        String dataEmissaoMinimaTexto = request.getParameter("dt_emissao_min");

        String dataEmissaoMaximaTexto = request.getParameter("dt_emissao_max");

        String idFuncionarioTexto = request.getParameter("id_funcionario");


        //monta a query
        StringBuilder sql = new StringBuilder("SELECT * FROM certificado WHERE 1=1");


        //filtros
        try {
            //guarda os filtros convertidos antes de montar a consulta
            Date dataEmissaoMinima = null;
            Date dataEmissaoMaxima = null;
            Integer idFuncionario = null;

            //filtro dtminima
            if (dataEmissaoMinimaTexto != null && !dataEmissaoMinimaTexto.isBlank()) {
                //valida o formato ano-mes-dia e converte a data minima
                dataEmissaoMinima = Date.valueOf(dataEmissaoMinimaTexto);
            }
            //filtro dtmaxima
            if (dataEmissaoMaximaTexto != null && !dataEmissaoMaximaTexto.isBlank()) {
                //valida o formato ano-mes-dia e converte a data maxima
                dataEmissaoMaxima = Date.valueOf(dataEmissaoMaximaTexto);
            }
            //filtro idfuncionario
            if (idFuncionarioTexto != null && !idFuncionarioTexto.isBlank() && !idFuncionarioTexto.equals("0")) {
                idFuncionario = Integer.parseInt(idFuncionarioTexto);
                if (idFuncionario < 1) {
                    throw new IllegalArgumentException("ID do funcionário deve ser positivo");
                }
            }
            //confere se a data minima nao esta depois da data maxima
            if (dataEmissaoMinima != null && dataEmissaoMaxima != null && dataEmissaoMinima.after(dataEmissaoMaxima)) {
                throw new IllegalArgumentException("Data inicial posterior à data final");
            }

            //adiciona a data minima quando ela foi informada
            if (dataEmissaoMinima != null) {
                sql.append(" AND dt_emissao >= '").append(dataEmissaoMinima).append("'");
            }
            //adiciona a data maxima quando ela foi informada
            if (dataEmissaoMaxima != null) {
                sql.append(" AND dt_emissao <= '").append(dataEmissaoMaxima).append("'");
            }
            //adiciona o id do funcionario quando ele foi informado
            if (idFuncionario != null) {
                sql.append(" AND id_funcionario = ").append(idFuncionario);
            }
        } catch (IllegalArgumentException e) {
            //nao retorna registros como se o filtro invalido tivesse sido aplicado
            sql = new StringBuilder("SELECT * FROM certificado WHERE 1=0");
            request.setAttribute("erro", "filtro invalido");
        }
        sql.append(" ORDER BY id_certificado");


        //execta no bd
        ArrayList<Certificado> certificados= certificadoDAO.buscar(sql.toString());

        //envia a lista e filtros de volta para o jsp
        request.setAttribute("certificados", certificados);

        //coloquei isso mas é opcional(é só p manter os valores no filtro)
        request.setAttribute("filtroDtEmissaoMin", dataEmissaoMinimaTexto);
        request.setAttribute("filtroDtEmissaoMax", dataEmissaoMaximaTexto);
        request.setAttribute("filtroIdFuncionario", idFuncionarioTexto);

        //encaminha pra o jsp
        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);

    }
}
