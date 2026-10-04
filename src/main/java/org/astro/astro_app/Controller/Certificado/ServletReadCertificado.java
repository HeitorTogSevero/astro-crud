package org.astro.astro_app.Controller.Certificado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.CertificadoDAO;
import org.astro.astro_app.model.Certificado;

import java.io.IOException;
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

        List<Certificado> certificados = certificadoDAO.buscar();

        request.setAttribute("certificados", certificados);

        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/ReadCertificado.jsp").forward(request,response);

    }


}
