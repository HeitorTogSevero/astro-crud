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

        String dataEmissaoMinimaTexto = request.getParameter("dt_emissao_min");
        String dataEmissaoMaximaTexto = request.getParameter("dt_emissao_max");
        String idFuncionarioTexto = request.getParameter("id_funcionario");


        //monta a query
        StringBuilder sql = new StringBuilder("SELECT * FROM certificado WHERE 1=1");


        //filtros
        try {
            //filtro dtminima
            if (dataEmissaoMinimaTexto != null && !dataEmissaoMinimaTexto.isBlank()) {
                Date.valueOf(dataEmissaoMaximaTexto); //ve o formato ano-mes-dia, se for invalido mostra o erro
                sql.append(" AND dt_emissao >= '").append(dataEmissaoMinimaTexto).append("'");
            }
            //filtro dtmaxima
            if (dataEmissaoMaximaTexto != null && !dataEmissaoMaximaTexto.isBlank()) {
                Date.valueOf(dataEmissaoMaximaTexto); //ve o formato ano-mes-dia, se for invalido mostra o erro
                sql.append(" AND dt_emissao <= '").append(dataEmissaoMaximaTexto).append("'");
            }
            //filtro idfuncionario
            if (idFuncionarioTexto != null && !idFuncionarioTexto.isBlank() && !idFuncionarioTexto.equals("0")) {
                sql.append(" AND id_funcionario = ").append(Integer.parseInt(idFuncionarioTexto));
            }
        } catch (IllegalArgumentException e) {
            sql = new StringBuilder("SELECT * FROM certificado WHERE 1=1");
            request.setAttribute("erro", "filtro invlido");
        }
        sql.append(" ORDER BY id_certificado");

        //execta no bd
        ArrayList<Certificado> certificados= certificadoDAO.buscar(sql.toString());


        request.setAttribute("certificados", certificados);

        request.setAttribute("filtroDtEmissaoMin", dataEmissaoMinimaTexto);
        request.setAttribute("filtroDtEmissaoMax", dataEmissaoMaximaTexto);
        request.setAttribute("filtroIdFuncionario", idFuncionarioTexto);

        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);

    }
}
