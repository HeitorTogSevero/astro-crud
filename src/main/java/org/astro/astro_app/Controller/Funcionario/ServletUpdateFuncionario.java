package org.astro.astro_app.Controller.Funcionario;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.FuncionarioDAO;
import org.astro.astro_app.model.Funcionario;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "ServletUpdateFuncionario", value = "/alterar-funcionario")
public class ServletUpdateFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO do funcionário quando o servlet e iniciado
        funcionarioDAO = new FuncionarioDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        final Pattern cpfRegex =
                Pattern.compile("^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$");

        final Pattern emailRegex =
                Pattern.compile("^[a-zA-Z0-9._%+#$!\\-]+@(gmail|hotmail|yahoo|outlook)\\.(com|com\\.br|org\\.br)$");


        // Le o identificador e os novos valores enviados pelo formulario de edicao
        String idFuncionarioTexto = request.getParameter("id_funcionario");

        String nome = request.getParameter("nome");

        String cargo = request.getParameter("cargo");

        String cpf = request.getParameter("cpf");

        String email = request.getParameter("email");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String idDepartamentoTexto = request.getParameter("id_departamento");

        String mensagem = "";

        // Valida campos obrigatorios e o tamanho da descricao antes da conversao
        if (idFuncionarioTexto == null || idFuncionarioTexto.isBlank()) {

            mensagem = "o id do funcionario nao informado";

            request.setAttribute("erro", mensagem);

        }

        else if (nome == null || nome.isBlank()) {

            mensagem = "o nome nao pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (nome.length() > 100) {

            mensagem = "o nome deve ter no max. 100 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else if (cargo == null || cargo.isBlank()) {

            mensagem = "o cargo nao pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (cargo.length() > 50) {

            mensagem = "o cargo deve ter no max. 50 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else if (cpf == null || cpf.isBlank() || !cpfRegex.matcher(cpf).matches()) {

            mensagem = "formato do CPF invalido. Use 000.000.000-00";

            request.setAttribute("erro", mensagem);

        }

        else if (email == null || email.isBlank()) {

            mensagem = "o email nao pode ser vazio";

            request.setAttribute("erro", mensagem);

        }

        else if (email.length() > 50) {

            mensagem = "o email pode ter ate 50 caracteres";

            request.setAttribute("erro", mensagem);

        }

        else if (!emailRegex.matcher(email).matches()) {

            mensagem = "o formato do email nao e aceito";

            request.setAttribute("erro", mensagem);

        }

        else if (idEmpresaTexto == null || idEmpresaTexto.isBlank()) {

            mensagem = "o id da empresa nao pode ser nulo";

            request.setAttribute("erro", mensagem);

        }

        else if (idDepartamentoTexto == null || idDepartamentoTexto.isBlank()) {

            mensagem = "o id do departamento nao pode ser nulo";

            request.setAttribute("erro", mensagem);

        }

        else {

            try {

                // Converte identificadores, codigo e data para os tipos esperados
                int idFuncionario = Integer.parseInt(idFuncionarioTexto);

                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                int idDepartamento = Integer.parseInt(idDepartamentoTexto);

                // Aplica as regras de negócio antes de atualizar o registro
                if (idFuncionario <= 0) {

                    mensagem = "o id do funcionario e invalido";

                    request.setAttribute("erro", mensagem);

                }

                else if (idEmpresa <= 0) {

                    mensagem = "o id da empresa deve ser > 0";

                    request.setAttribute("erro", mensagem);

                }

                else if (idDepartamento <= 0) {

                    mensagem = "o id do departamento deve ser > 0";

                    request.setAttribute("erro", mensagem);

                }

                else {

                    // Monta o modelo atualizado e envia os dados ao DAO
                    Funcionario funcionario = new Funcionario(
                            nome,
                            cargo,
                            cpf,
                            email,
                            idEmpresa,
                            idFuncionario,
                            idDepartamento
                    );

                    // Altera caso todos os pontos tenham sido compridos
                    funcionarioDAO.alterarFuncionario(funcionario);

                    // Redireciona para a lista apos concluir a atualizacao.
                    response.sendRedirect(request.getContextPath() + "/listar-funcionarios");
                    return;
                }

            }

            // Trata identificadores invalidos e datas fora do formato esperado
            catch (NumberFormatException e) {

                mensagem = "os ids devem ser numeros validos";
                request.setAttribute("erro", mensagem);

            }
        }

        // Em caso de erro, retorna ao formulario com a mensagem de validacao
        request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);

    }
}