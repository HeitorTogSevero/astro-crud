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

@WebServlet(name = "ServletCreateFuncionario", value = "/adicionar-funcionario")
public class ServletCreateFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO do funcionário quando o servlet e iniciado
        funcionarioDAO = new FuncionarioDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Os dados chegam em bits, aqui converte ele para linguagem normal
        request.setCharacterEncoding("UTF-8");

        final Pattern cpfRegex =
                Pattern.compile("^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$");

        final Pattern emailRegex =
                Pattern.compile("^[a-zA-Z0-9._%+#$!\\-]+@(gmail|hotmail|yahoo|outlook|institutojef)\\.(com|com\\.br|org\\.br)$");

        // Le os valores enviados no formulário de cadastro
        String nome = request.getParameter("nome");

        String cargo = request.getParameter("cargo");

        String cpf = request.getParameter("cpf");

        String email = request.getParameter("email");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String idDepartamentoTexto = request.getParameter("id_departamento");

        String mensagem = "";

        // Verifica campos obrigatórios e limites antes de converter os dados
        if (nome == null || nome.isBlank()) {

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

            mensagem = "formato do CPF inválido. Use 000.000.000-00";
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

            // Converte os campos numéricos e a data para os tipos usados pelo modelo
            try {

                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                int idDepartamento = Integer.parseInt(idDepartamentoTexto);

                // Confere regras de negocio antes de gravar o alerta
                if (idEmpresa <= 0) {
                    mensagem = "o id da empresa deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if (idDepartamento <= 0) {
                    mensagem = "o id do departamento deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else {

                    // Monta o modelo e pede ao DAO para inserir o novo registro
                    Funcionario funcionario = new Funcionario(
                            nome,
                            cargo,
                            cpf,
                            email,
                            idEmpresa,
                            idDepartamento
                    );

                    funcionarioDAO.inserir(funcionario);

                    // Após a inserção redireciona para a listagem atualizada
                    response.sendRedirect(request.getContextPath() + "/listar-funcionarios");
                    return;
                }

            }

            // Trata valores que nao podem ser convertidos para número ou data
            catch (NumberFormatException e) {
                mensagem = "os ids devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulário com a mensagem de validação.
        request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);
    }
}