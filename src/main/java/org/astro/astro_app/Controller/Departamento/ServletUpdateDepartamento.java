package org.astro.astro_app.Controller.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;
import org.astro.astro_app.model.Departamento;

import java.io.IOException;

@WebServlet(name = "ServletUpdateDepartamento", value = "/alterar-departamento")
public class ServletUpdateDepartamento extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init() {
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idDepartamentoTexto = request.getParameter("id_departamento");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String grauRisco = request.getParameter("grau_risco");

        String nome = request.getParameter("nome");

        String descricao = request.getParameter("descricao");

        String atividade = request.getParameter("atividade");

        String mensagem = null;

        if (idDepartamentoTexto == null || idDepartamentoTexto.isBlank()) {
            mensagem = "Informe o ID do departamento.";
            request.setAttribute("erro", mensagem);
        }
        else if (nome == null || nome.isBlank()) {
            mensagem = "O nome não pode ser vazio.";
            request.setAttribute("erro", mensagem);
        }
        else if (nome.length() > 100) {
            mensagem = "O nome deve conter até 100 caracteres.";
            request.setAttribute("erro", mensagem);
        }
        else if (descricao == null || descricao.isBlank()) {
            mensagem = "A descrição não pode ser vazia.";
            request.setAttribute("erro", mensagem);
        }
        else if (descricao.length() > 250) {
            mensagem = "A descrição deve conter até 250 caracteres.";
            request.setAttribute("erro", mensagem);
        }
        else if (grauRisco == null || grauRisco.isBlank()) {
            mensagem = "O grau de risco não pode ser vazio.";
            request.setAttribute("erro", mensagem);
        }
        else if (!grauRisco.equals("1") && !grauRisco.equals("2")
                && !grauRisco.equals("3") && !grauRisco.equals("4")) {
            mensagem = "O grau de risco deve ser 1, 2, 3 ou 4.";
            request.setAttribute("erro", mensagem);
        }
        else if (atividade == null || atividade.isBlank()) {
            mensagem = "A atividade não pode ser vazia.";
            request.setAttribute("erro", mensagem);
        }
        else if (atividade.length() > 15) {
            mensagem = "A atividade deve conter até 15 caracteres.";
            request.setAttribute("erro", mensagem);
        }
        else if (idEmpresaTexto == null || idEmpresaTexto.isBlank()) {
            mensagem = "Informe o ID de uma empresa cadastrada.";
            request.setAttribute("erro", mensagem);
        }
        else {
            try {
                int idDepartamento = Integer.parseInt(idDepartamentoTexto);
                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                if (idDepartamento <= 0) {
                    mensagem = "O ID do departamento deve ser maior que zero.";
                    request.setAttribute("erro", mensagem);
                }
                else if (idEmpresa <= 0) {
                    mensagem = "O ID da empresa deve ser maior que zero.";
                    request.setAttribute("erro", mensagem);
                }
                else {
                    String grauNoBanco = "Grau " + grauRisco;

                    Departamento departamento = new Departamento(
                            idDepartamento,
                            idEmpresa,
                            grauNoBanco,
                            nome,
                            descricao,
                            atividade
                    );

                    int resultado = departamentoDAO.alterarDepartamento(departamento);

                    if (resultado > 0) {
                        response.sendRedirect(request.getContextPath() + "/listar-departamentos");
                        return;
                    }

                    mensagem = "Não foi possível alterar o departamento. Confira os dados e tente novamente.";
                    request.setAttribute("erro", mensagem);
                }
            } catch (NumberFormatException e) {
                mensagem = "Os IDs devem ser números válidos.";
                request.setAttribute("erro", mensagem);
            }
        }

        request.setAttribute("departamentos", departamentoDAO.buscar("SELECT * FROM departamento ORDER BY 1"));
        request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request, response);
    }
}