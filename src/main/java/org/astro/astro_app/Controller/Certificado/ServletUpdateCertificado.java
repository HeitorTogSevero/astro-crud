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

@WebServlet(name = "ServletUpdateCertificado", value = "/alterar-certificado")
public class ServletUpdateCertificado extends HttpServlet {

    private CertificadoDAO certificadoDAO;

    @Override
    public void init() {
        certificadoDAO = new CertificadoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idCertificadoTexto = request.getParameter("id_certificado");

        String idFuncionarioTexto = request.getParameter("id_funcionario");

        String idNrFuncionarioTexto = request.getParameter("id_nrfuncionario");

        String idNrEmpresaTexto = request.getParameter("id_nrempresa");

        String dataEmissaoTexto = request.getParameter("dt_emissao");

        String validadeTexto = request.getParameter("validade");

        Date dataEmissao = null;
        Date validade = null;
        String mensagem = "";

        if (idCertificadoTexto == null || idCertificadoTexto.isBlank()) {
            mensagem = "Erro: id do certificado não informado";
            request.setAttribute("erro", mensagem);
        }

        else if (dataEmissaoTexto == null || dataEmissaoTexto.isBlank()) {
            mensagem = "Erro: a data de emissão não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if (validadeTexto == null || validadeTexto.isBlank()) {
            mensagem = "Erro: a validade não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if (idFuncionarioTexto == null || idFuncionarioTexto.isBlank()) {
            mensagem = "Erro: o id do funcionário não pode ser nulo";
            request.setAttribute("erro", mensagem);
        }

        else if (idNrFuncionarioTexto == null || idNrFuncionarioTexto.isBlank()) {
            mensagem = "Erro: o id da NR do funcionário não pode ser nulo";
            request.setAttribute("erro", mensagem);
        }

        else if (idNrEmpresaTexto == null || idNrEmpresaTexto.isBlank()) {
            mensagem = "Erro: o id da NR da empresa não pode ser nulo";
            request.setAttribute("erro", mensagem);
        }

        else {
            try {

                int idCertificado = Integer.parseInt(idCertificadoTexto);

                int idFuncionario = Integer.parseInt(idFuncionarioTexto);

                int idNrFuncionario = Integer.parseInt(idNrFuncionarioTexto);

                int idNrEmpresa = Integer.parseInt(idNrEmpresaTexto);

                dataEmissao = Date.valueOf(dataEmissaoTexto);
                validade = Date.valueOf(validadeTexto);

                LocalDate localDataEmissao = dataEmissao.toLocalDate();
                LocalDate localValidade = validade.toLocalDate();

                if (idCertificado <= 0) {
                    mensagem = "o id do certificado é invalido";
                    request.setAttribute("erro", mensagem);
                }

                else if (idFuncionario <= 0) {
                    mensagem = "o id do funcionário deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if (idNrFuncionario <= 0) {
                    mensagem = "o id da NR do funcionario deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if (idNrEmpresa <= 0) {
                    mensagem = "o id da NR da empresa deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if (localDataEmissao.isAfter(LocalDate.now())) {
                    mensagem = "a data deve ser anterior ou no dia atual";
                    request.setAttribute("erro", mensagem);
                }

                else if (localValidade.isBefore(LocalDate.now())) {
                    mensagem = "a validade deve ser a partir do dia atual";
                    request.setAttribute("erro", mensagem);
                }

                else {

                    Certificado certificadoAtualizado = new Certificado(
                            idCertificado,
                            idFuncionario,
                            idNrFuncionario,
                            idNrEmpresa,
                            dataEmissao,
                            validade
                    );

                    certificadoDAO.alterarCertificado(certificadoAtualizado);
                    response.sendRedirect(request.getContextPath() + "/lista-certificados");
                    return;
                }

            }
            catch (NumberFormatException e) {
                mensagem = "os ids devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            }
            catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        //teve erro, volta pra pagina
        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request, response);
    }
}