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
@WebServlet(name = "ServletCreateCertificado", value = "/adicionar-certificado")
public class ServletCreateCertificado extends HttpServlet{

    private CertificadoDAO certificadoDAO;

    @Override
    public void init(){
        certificadoDAO = new CertificadoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String idFuncionariotexto = request.getParameter("id_funcionario");
        int idFuncionario = Integer.parseInt(idFuncionariotexto);

        String idNrFuncionariotexto = request.getParameter("id_nrfuncionario");
        int idNrFuncionario = Integer.parseInt(idFuncionariotexto);

        String idNrEmpresatexto = request.getParameter("id_nrempresa");
        int idNrEmpresa = Integer.parseInt(idNrEmpresatexto);

        String dataEmissaotexto = request.getParameter("dt_emissao");
        Date dataEmissao = null;
        if(dataEmissaotexto != null && !dataEmissaotexto.isBlank()){
            dataEmissao = java.sql.Date.valueOf(dataEmissaotexto);
        }

        String validadetexto = request.getParameter("validade");
        Date validade = null;
        if(validadetexto !=null && !validadetexto.isBlank()){
            validade = java.sql.Date.valueOf(validadetexto);
        }

        Certificado certificado = new Certificado(
                idFuncionario,
                idNrFuncionario,
                dataEmissao,
                validade,
                idNrEmpresa
        );

        certificadoDAO.inserir(certificado);

        response.sendRedirect(request.getContextPath() + "landingpage"); //mudar depois
    }
}
