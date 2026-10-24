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
        //pega o id certificado
        String idCertificadoTexto = request.getParameter("id_certificado");
        String mensagem = "";

        //validacao
        if(idCertificadoTexto == null || idCertificadoTexto.isBlank()){
            mensagem = "id nao informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
            return;
        }

        try{
            int idCertificado = Integer.parseInt(idCertificadoTexto);
            if (idCertificado <=0){
                mensagem = "id invalido";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
                return;
            }

            int resultado = certificadoDAO.remover(idCertificado);
            if (resultado > 0) {
                response.sendRedirect(request.getContextPath() + "/listar-certificados");
                return;
            }

            mensagem = "Nao foi possivel excluir o certificado. Confira o ID e tente novamente.";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);

        }catch (NumberFormatException e) {
            mensagem = "o id do certificado deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
        }
    }
}
