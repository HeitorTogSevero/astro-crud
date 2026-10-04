package org.astro.astro_app.Controller.Certificado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.CertificadoDAO;

import java.io.IOException;

@WebServlet(name = "ServletDeleteCertificado", value = "/deletar-certificado")
public class ServletDeleteCertificado extends HttpServlet {

    private CertificadoDAO certificadoDAO;

    @Override
    public void init() {
        certificadoDAO = new CertificadoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String acao = request.getParameter("");

        if("excluir".equals(acao)){

            String idTexto = request.getParameter("id");
            int id = Integer.parseInt(idTexto);

            certificadoDAO.remover(id);

            response.sendRedirect(request.getContextPath() + "/certificado");
        }
    }
}