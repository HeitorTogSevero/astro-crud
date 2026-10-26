package org.astro.astro_app.Controller.Certificado;

// Biblioteca:
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

        // Concede o acesso ao DAO do certificado quando o servlet e iniciado
        certificadoDAO = new CertificadoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Le o identificador do alerta enviado pelo formulário de exclusão
        String idCertificadoTexto = request.getParameter("id_certificado");
        String mensagem = "";

        // Impede a exclusão quando o identificador nao foi informado
        if(idCertificadoTexto == null || idCertificadoTexto.isBlank()){
            mensagem = "id nao informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
            return;
        }

        try{

            // Converte e valida o identificador antes de chamar o DAO
            int idCertificado = Integer.parseInt(idCertificadoTexto);

            if (idCertificado <=0){

                mensagem = "id invalido";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);
                return;

            }

            // Remove o alerta do banco de dados
            int resultado = certificadoDAO.remover(idCertificado);

            // Redireciona para a listagem após a exclusão
            if (resultado > 0) {
                response.sendRedirect(request.getContextPath() + "/listar-certificados");
                return;
            }

            // Mensagem de erro
            mensagem = "Nao foi possivel excluir o certificado. Confira o ID e tente novamente.";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);

        }catch (NumberFormatException e) {

            // Se o identificador nao for numérico, informa o erro no formulário
            mensagem = "o id do certificado deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request,response);

        }
    }
}
