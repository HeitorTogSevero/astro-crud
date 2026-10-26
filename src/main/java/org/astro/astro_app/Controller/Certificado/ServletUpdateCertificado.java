package org.astro.astro_app.Controller.Certificado;

// Biblioteca:
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

        // Concede o acesso ao DAO do certificado quando o servlet e iniciado
        certificadoDAO = new CertificadoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Le o identificador e os novos valores enviados pelo formulario de edicao
        String idCertificadoTexto = request.getParameter("id_certificado");

        String idFuncionarioTexto = request.getParameter("id_funcionario");

        String idNrFuncionarioTexto = request.getParameter("id_nrfuncionario");

        String idNrEmpresaTexto = request.getParameter("id_nrempresa");

        String dataEmissaoTexto = request.getParameter("dt_emissao");

        String validadeTexto = request.getParameter("validade");

        // Guarda a data convertida e a mensagem de erro, se houver
        Date dataEmissao = null;
        Date validade = null;
        String mensagem = "";

        // Valida campos obrigatorios e o tamanho da descricao antes da conversao
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

                // Converte identificadores, codigo e data para os tipos esperados
                int idCertificado = Integer.parseInt(idCertificadoTexto);

                int idFuncionario = Integer.parseInt(idFuncionarioTexto);

                int idNrFuncionario = Integer.parseInt(idNrFuncionarioTexto);

                int idNrEmpresa = Integer.parseInt(idNrEmpresaTexto);

                dataEmissao = Date.valueOf(dataEmissaoTexto);
                validade = Date.valueOf(validadeTexto);

                LocalDate localDataEmissao = dataEmissao.toLocalDate();
                LocalDate localValidade = validade.toLocalDate();

                // Aplica as regras de negócio antes de atualizar o registro
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

                    // Monta o modelo atualizado e envia os dados ao DAO
                    Certificado certificadoAtualizado = new Certificado(
                            idCertificado,
                            idFuncionario,
                            idNrFuncionario,
                            idNrEmpresa,
                            dataEmissao,
                            validade
                    );

                    // Altera caso todos os pontos tenham sido compridos
                    int resultado = certificadoDAO.alterarCertificado(certificadoAtualizado);

                    if (resultado > 0) {

                        // Redireciona para a lista apos concluir a atualizacao.
                        response.sendRedirect(request.getContextPath() + "/listar-certificados");
                        return;

                    }

                    // Mensagem de erro
                    mensagem = "Não foi possivel alterar o certificado. Confira os dados e tente novamente.";
                    request.setAttribute("erro", mensagem);
                }

            }

            // Trata identificadores invalidos e datas fora do formato esperado
            catch (NumberFormatException e) {
                mensagem = "os ids devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            }
            catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulario com a mensagem de validacao
        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request, response);

    }
}
