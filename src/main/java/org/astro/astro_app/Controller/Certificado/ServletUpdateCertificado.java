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

@WebServlet(name="ServletUpdateCertificado", value = "/alterar-certificado")
public class ServletUpdateCertificado extends HttpServlet {

    private CertificadoDAO certificadoDAO;

    @Override
    public void init(){
        certificadoDAO = new CertificadoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idTexto = request.getParameter("id");
        int id = Integer.parseInt(idTexto);

        Certificado certificado = certificadoDAO.buscarPorIdCertificado(id);

        if (certificado == null) {
            response.sendRedirect(request.getContextPath() + "/certificados");
            return;
        }

        request.setAttribute("certificado", certificado);
        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/UpdateCertificado.jsp").forward(request,response);
    }
    //salvando alterações
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //os dados chegam como bytes, tem que transformar em padrão :
        request.setCharacterEncoding("UTF-8");

        String idCertificadoTexto = request.getParameter("id_certificado");
        int idCertificado = Integer.parseInt(idCertificadoTexto);

        String idFuncionarioTexto = request.getParameter("id_funcionario");
        int idFuncionario = Integer.parseInt(idFuncionarioTexto);

        String idNrFuncionarioTexto = request.getParameter("id_nrfuncionario");
        int idNrFuncionario = Integer.parseInt(idNrFuncionarioTexto);

        String idNrEmpresaTexto = request.getParameter("id_nrempresa");
        int idNrEmpresa = Integer.parseInt(idNrEmpresaTexto);

        String emissaoTexto = request.getParameter("dt_emissao");
        Date dataEmissao = null;

        if (emissaoTexto != null && !emissaoTexto.isBlank()) {
            dataEmissao = Date.valueOf(emissaoTexto);
        }

        String validadeTexto = request.getParameter("validade");
        Date validade = null;
        if (validadeTexto != null && !validadeTexto.isBlank()) {
            validade = Date.valueOf(validadeTexto);
        }


        Certificado certificado = new Certificado(idCertificado, idFuncionario, idNrFuncionario, idNrEmpresa, dataEmissao, validade);

        certificadoDAO.alterarCertificado(certificado);

        response.sendRedirect(request.getContextPath() + "/certificados");


    }
}
