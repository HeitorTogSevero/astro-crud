package org.astro.astro_app.Controller.Departamento;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;
import org.astro.astro_app.model.Departamento;

import java.io.IOException;

@WebServlet(name = "ServletCreateDepartamento", value = "/adicionar-departamento")
public class ServletCreateDepartamento extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO do departamento quando o servlet e iniciado
        departamentoDAO = new DepartamentoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Os dados chegam em bits, aqui converte ele para linguagem normal
        request.setCharacterEncoding("UTF-8");

        // Le os valores enviados no formulario de cadastro
        String idEmpresaTexto = request.getParameter("id_empresa");

        String grauRisco = request.getParameter("grau_risco");

        String nome = request.getParameter("nome");

        String descricao = request.getParameter("descricao");

        String atividade = request.getParameter("atividade");


        String mensagem = "";

        // Verifica campos obrigatorios e limites antes de converter os dados
        if (nome == null || nome.isBlank()) {

            mensagem = "o nome não pode ser vazio";
            request.setAttribute("erro", mensagem);


        }

        else if (nome.length() > 100) {

            mensagem = "o nome deve conter até 100 caracteres";
            request.setAttribute("erro", mensagem);


        }

        else if (descricao == null || descricao.isBlank()) {

            mensagem = "a descrição não pode ser vazia";
            request.setAttribute("erro", mensagem);


        }

        else if (descricao.length() > 250) {

            mensagem = "a descricao deve conter até 250 caracteres";
            request.setAttribute("erro", mensagem);


        }

        else if (grauRisco == null || grauRisco.isBlank()) {

            mensagem = "o grau de risco não pode ser vazio";
            request.setAttribute("erro", mensagem);


        }

        else if (!grauRisco.equals("1") && !grauRisco.equals("2")
                && !grauRisco.equals("3") && !grauRisco.equals("4")) {
            mensagem = "o grau de risco deve ser (1, 2, 3 ou 4)";
            request.setAttribute("erro", mensagem);

        }

        else if (atividade == null || atividade.isBlank()) {
            mensagem = "a atividade não pode ser vazia";
            request.setAttribute("erro", mensagem);

        }

        else if (atividade.length() > 15) {
            mensagem = "a atividade deve conter até 15 caracteres";
            request.setAttribute("erro", mensagem);

        }

        else if (idEmpresaTexto == null || idEmpresaTexto.isBlank()) {
            mensagem = "Informe o ID de uma empresa cadastrada.";
            request.setAttribute("erro", mensagem);

        }
        else {

            // Converte os campos numericos e a data para os tipos usados pelo modelo
            try {
                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                if (idEmpresa <= 0) {
                    mensagem = "o id da empresa deve ser > 0";
                    request.setAttribute("erro", mensagem);

                } else {

                    // Nele é inserido apenas o número, então adicionamos o "Grau" para ficar: Grau 1, Grau 2...
                    String grauNoBanco = "Grau " + grauRisco;

                    // Monta o modelo e pede ao DAO para inserir o novo registro
                    Departamento departamento = new Departamento(
                            idEmpresa,
                            grauNoBanco,
                            nome,
                            descricao,
                            atividade
                    );

                    if (departamentoDAO.inserir(departamento)) {
                        response.sendRedirect(request.getContextPath() + "/listar-departamentos");
                        return;
                    }

                    // Mensagem de erro
                    mensagem = "Não foi possível inserir o departamento. Confira o ID da empresa e tente novamente.";
                    request.setAttribute("erro", mensagem);
                }
            }

            // Trata valores que nao podem ser convertidos para numero ou data
            catch (NumberFormatException e) {
                mensagem = "o id deve ser numero valido";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulario com a mensagem de validacao.
        request.setAttribute("departamentos", departamentoDAO.buscar("SELECT * FROM departamento ORDER BY 1"));
        request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request, response);

    }
}