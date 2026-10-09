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
import java.time.LocalDate;

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
        LocalDate localDataEmissao = dataEmissao.toLocalDate();

        String validadetexto = request.getParameter("validade");
        Date validade = null;
        LocalDate localValidade = validade.toLocalDate();

        String mensagem = "";

        if(dataEmissaotexto != null && !dataEmissaotexto.isBlank()){
            dataEmissao = java.sql.Date.valueOf(dataEmissaotexto);
        }

        else if(dataEmissaotexto == null && dataEmissaotexto.isBlank()){
            mensagem = "Erro: a data não poder ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if(validadetexto != null && !validadetexto.isBlank()){
            validade = java.sql.Date.valueOf(validadetexto);
        }

        else if(validade == null && validadetexto.isBlank()){
            mensagem = "Erro: a data não poder ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if(idFuncionario <= 0 || idFuncionariotexto.equals(" ")){
            mensagem = "Erro: o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(idNrFuncionario <= 0 || idNrFuncionariotexto.equals(" ")){
            mensagem = "Erro: o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(idNrEmpresa <= 0 || idNrEmpresatexto.equals(" ")){
            mensagem = "Erro: o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(dataEmissao == null || localDataEmissao.isAfter(LocalDate.now())){
            mensagem = "Erro: a data deve ser anterior ou no dia atual";
            request.setAttribute("erro", mensagem);
        }
        else if (validade == null || localValidade.isBefore(LocalDate.now())) {
            mensagem = "Erro: a validade deve ser a partir do dia atual";
            request.setAttribute("erro", mensagem);
        }

        else {
            Certificado certificado = new Certificado(
                    idFuncionario,
                    idNrFuncionario,
                    dataEmissao,
                    validade,
                    idNrEmpresa
            );

            certificadoDAO.inserir(certificado);

            response.sendRedirect(request.getContextPath() + "landingpage");
        }
    }
}
