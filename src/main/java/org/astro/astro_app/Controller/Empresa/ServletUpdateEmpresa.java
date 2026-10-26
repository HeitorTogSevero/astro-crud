package org.astro.astro_app.Controller.Empresa;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.EmpresaDAO;
import org.astro.astro_app.model.Empresa;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

@WebServlet(name = "ServletUpdateEmpresa", value = "/editar-empresa")
public class ServletUpdateEmpresa extends HttpServlet {

    private EmpresaDAO empresaDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO de empresas quando o servlet e iniciado
        empresaDAO = new EmpresaDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        final Pattern cepRegex =
                Pattern.compile("^(\\d{5}-\\d{3}|\\d{8})$");

        final Pattern cnpjRegex =
                Pattern.compile("^(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14})$");

        final Pattern cnaeRegex =
                Pattern.compile("^(\\d{4}-\\d/\\d{2}|\\d{7})$");

        // Le o identificador e os novos valores enviados pelo formulario de edicao
        String idEmpresaTexto = request.getParameter("id_empresa");

        String cnae = request.getParameter("cnae");

        String nome = request.getParameter("nome");

        String cep = request.getParameter("cep");

        String cnpj = request.getParameter("cnpj");

        String rua = request.getParameter("rua");

        String bairro = request.getParameter("bairro");

        String cidade = request.getParameter("cidade");

        String estado = request.getParameter("estado");

        String dataRegistroTexto = request.getParameter("dt_registro");

        String mensagem = "";

        // Guarda a data convertida e a mensagem de erro, se houver
        if (idEmpresaTexto == null || idEmpresaTexto.isBlank()) {

            mensagem = "o id da empresa nao informado";

            request.setAttribute("erro", mensagem);

        }

        else if (nome == null || nome.isBlank()) {

            mensagem = "o nome nao pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (nome.length() > 100) {

            mensagem = "o nome deve conter ate 100 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else if (rua == null || rua.isBlank()) {

            mensagem = "a rua nao pode ser vazia";

            request.setAttribute("erro", mensagem);

        }

        else if (rua.length() > 100) {

            mensagem = "a rua deve conter ate 100 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else if (bairro == null || bairro.isBlank()) {

            mensagem = "o bairro nao pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (bairro.length() > 100) {

            mensagem = "o bairro deve conter ate 100 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else if (cidade == null || cidade.isBlank()) {

            mensagem = "a cidade nao pode ser vazia";

            request.setAttribute("erro", mensagem);

        }

        else if (cidade.length() > 100) {

            mensagem = "a cidade deve conter ate 100 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else if (estado == null || estado.isBlank()) {

            mensagem = "o estado nao pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (estado.length() > 2) {

            mensagem = "o estado deve ter no maximo 2 caracteres (UF)";

            request.setAttribute("erro", mensagem);

        }

        else if (cep == null || cep.isBlank() || !cepRegex.matcher(cep).matches()) {

            mensagem = "o formato do cep nao e aceito";

            request.setAttribute("erro", mensagem);

        }

        else if (cnpj == null || cnpj.isBlank() || !cnpjRegex.matcher(cnpj).matches()) {

            mensagem = "o formato do cnpj nao e aceito";

            request.setAttribute("erro", mensagem);

        }

        else if (cnae == null || cnae.isBlank() || !cnaeRegex.matcher(cnae).matches()) {

            mensagem = "o formato do cnae nao e aceito";

            request.setAttribute("erro", mensagem);

        }

        else if (dataRegistroTexto == null || dataRegistroTexto.isBlank()) {

            mensagem = "a data de registro nao pode ser vazia";

            request.setAttribute("erro", mensagem);

        }

        else {

            try {

                // Converte identificadores, codigo e data para os tipos esperados
                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                Date dataRegistro = Date.valueOf(dataRegistroTexto);

                LocalDate localDataRegistro = dataRegistro.toLocalDate();

                // Aplica as regras de negócio antes de atualizar o registro
                if (idEmpresa <= 0) {

                    mensagem = "o id da empresa e invalido";

                    request.setAttribute("erro", mensagem);

                }

                else if (localDataRegistro.isAfter(LocalDate.now())) {

                    mensagem = "a data de registro deve ser antes ou no dia atual";

                    request.setAttribute("erro", mensagem);

                }

                else {

                    // Monta o modelo atualizado e envia os dados ao DAO
                    Empresa empresa = new Empresa(
                            idEmpresa,
                            cnae,
                            nome,
                            cep,
                            cnpj,
                            rua,
                            estado,
                            bairro,
                            cidade,
                            dataRegistro
                    );

                    // Altera caso todos os pontos tenham sido compridos
                    empresaDAO.alterarEmpresa(empresa);

                    // Redireciona para a lista apos concluir a atualizacao.
                    response.sendRedirect(request.getContextPath() + "/listar-empresas");
                    return;
                }

            }

            // Trata identificadores invalidos e datas fora do formato esperado
            catch (NumberFormatException e) {
                mensagem = "o id deve ser numero valido";
                request.setAttribute("erro", mensagem);
            }
            catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulario com a mensagem de validacao
        request.getRequestDispatcher("/WEB-INF/EmpresaJSP/Empresa.jsp").forward(request, response);

    }
}