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

@WebServlet(name = "ServletCreateCertificado", value = "/adicionar-certificado")
public class ServletCreateCertificado extends HttpServlet {

    private CertificadoDAO certificadoDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO de certificados quando o servlet e iniciado
        certificadoDAO = new CertificadoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Os dados chegam em bits, aqui converte ele para linguagem normal
        request.setCharacterEncoding("UTF-8");

        // Le os valores enviados no formulario de cadastro
        String idFuncionarioTexto = request.getParameter("id_funcionario");

        String idNrFuncionarioTexto = request.getParameter("id_nrfuncionario");

        String idNrEmpresaTexto = request.getParameter("id_nrempresa");

        String dataEmissaoTexto = request.getParameter("dt_emissao");

        String validadeTexto = request.getParameter("validade");

        // Guarda a data como sqlDate depois de validar e converter o texto recebido
        Date dataEmissao = null;
        Date validade = null;
        String mensagem = "";

        // Verifica campos obrigatorios e limites antes de converter os dados
        if (dataEmissaoTexto == null || dataEmissaoTexto.isBlank()) {

            mensagem = "a data de emissao nao pode ser vazia";
            request.setAttribute("erro", mensagem);

        }

        else if (validadeTexto == null || validadeTexto.isBlank()) {

            mensagem = "a validade nao pode ser vazia";
            request.setAttribute("erro", mensagem);

        }

        else if (idFuncionarioTexto == null || idFuncionarioTexto.isBlank()) {

            mensagem = "o id do funcionario não pode ser nulo";
            request.setAttribute("erro", mensagem);

        }

        else if (idNrFuncionarioTexto == null || idNrFuncionarioTexto.isBlank()) {

            mensagem = "o id da NR do funcionario nao pode ser nulo";
            request.setAttribute("erro", mensagem);

        }

        else if (idNrEmpresaTexto == null || idNrEmpresaTexto.isBlank()) {

            mensagem = "o id da NR da empresa nao pode ser nulo";
            request.setAttribute("erro", mensagem);

        }

        else {

            // Converte os campos numericos e a data para os tipos usados pelo modelo
            try {

                int idFuncionario = Integer.parseInt(idFuncionarioTexto);

                int idNrFuncionario = Integer.parseInt(idNrFuncionarioTexto);

                int idNrEmpresa = Integer.parseInt(idNrEmpresaTexto);

                dataEmissao = Date.valueOf(dataEmissaoTexto);
                validade = Date.valueOf(validadeTexto);

                LocalDate localDataEmissao = dataEmissao.toLocalDate();
                LocalDate localValidade = validade.toLocalDate();

                // Confere regras de negocio antes de gravar o alerta
                if (idFuncionario <= 0) {

                    mensagem = "o id do funcionario deve ser > 0";
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

                    mensagem = "a data de emissao nao pode ser futura";
                    request.setAttribute("erro", mensagem);

                }

                else if (localValidade.isBefore(LocalDate.now())) {

                    mensagem = "a validade deve ser a partir do dia atual";
                    request.setAttribute("erro", mensagem);

                }

                else {

                    // Monta o modelo e pede ao DAO para inserir o novo registro
                    Certificado certificado = new Certificado(
                            idFuncionario,
                            idNrFuncionario,
                            dataEmissao,
                            validade,
                            idNrEmpresa
                    );

                    if (certificadoDAO.inserir(certificado)) {

                        response.sendRedirect(request.getContextPath() + "/listar-certificados");
                        return;

                    }

                    mensagem = "Nao foi possivel cadastrar o certificado. Confira os dados e tente novamente.";
                    request.setAttribute("erro", mensagem);
                }

            }
            // Trata valores que nao podem ser convertidos para numero ou data
            catch (NumberFormatException e) {
                mensagem = "os ids devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            }
            catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulario com a mensagem de validacao.
        request.getRequestDispatcher("/WEB-INF/CertificadoJSP/Certificado.jsp").forward(request, response);

    }
}
