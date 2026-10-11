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
        //pega o id alerta
        String idAlertaTexto = request.getParameter("id_alerta");
        String mensagem = "";

        //validacao
        if(idAlertaTexto == null || idAlertaTexto.isBlank()){
            mensagem = "id nao inforamdo";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
            return;
        }

        try{
            int idAlerta = Integer.parseInt(idAlertaTexto);
            if (idAlerta <=0){
                mensagem = "id invalido";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
                return;
            }

            certificadoDAO.remover(idAlerta);
            response.sendRedirect(request.getContextPath() + "lista-alertas");

        }catch (NumberFormatException e) {
            mensagem = "o id do certificad deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
        }
    }
}