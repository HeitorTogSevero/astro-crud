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
import java.util.ArrayList;

@WebServlet(name = "ServletReadFuncionario", value = "/listar-funcionarios")
public class ServletReadFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO do funcionário quando o servlet e iniciado
        funcionarioDAO = new FuncionarioDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lê os filtros opcionais enviados pela tela de listagem
        String nome = request.getParameter("nome");

        String cargo = request.getParameter("cargo");

        String cpf = request.getParameter("cpf");

        String email = request.getParameter("email");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String idDepartamentoTexto = request.getParameter("id_departamento");

        // Inicia a consulta e adiciona somente os filtros preenchidos
        StringBuilder sql = new StringBuilder("SELECT id_funcionario, nome, cargo, email, id_empresa, id_departamento, pgp_sym_decrypt(cpf, 'funcionariosAstro') AS cpf FROM funcionario WHERE 1=1");

        // Valida os valores dos filtros antes de incluir eles na consulta
        try {

            // filtro nome
            if (nome != null && !nome.isBlank()) {

                sql.append(" AND nome ILIKE '%").append(nome.trim().replace("'", "''")).append("%'");

            }

            // filtro cargo
            if (cargo != null && !cargo.isBlank()) {

                sql.append(" AND cargo ILIKE '%").append(cargo.trim().replace("'", "''")).append("%'");

            }

            // filtro cpf
            if (cpf != null && !cpf.isBlank()) {

                sql.append(" AND pgp_sym_decrypt(cpf, 'funcionariosAstro') ILIKE '%").append(cpf.trim().replace("'", "''")).append("%'");

            }

            // filtro email
            if (email != null && !email.isBlank()) {

                sql.append(" AND email ILIKE '%").append(email.trim().replace("'", "''")).append("%'");

            }

            // filtro id_empresa
            if (idEmpresaTexto != null && !idEmpresaTexto.isBlank() && !idEmpresaTexto.equals("0")) {

                sql.append(" AND id_empresa = ").append(Integer.parseInt(idEmpresaTexto));

            }

            // filtro id_departamento
            if (idDepartamentoTexto != null && !idDepartamentoTexto.isBlank() && !idDepartamentoTexto.equals("0")) {

                sql.append(" AND id_departamento = ").append(Integer.parseInt(idDepartamentoTexto));

            }

        }

        catch (NumberFormatException e) {

            // NÃo retorna registros como se o filtro invalido tivesse sido aplicado
            sql = new StringBuilder("SELECT id_funcionario, nome, cargo, email, id_empresa, id_departamento, pgp_sym_decrypt(cpf, 'funcionariosAstro') AS cpf FROM funcionario WHERE 1=1");
            request.setAttribute("erro", "filtro invalido");

        }

        sql.append(" ORDER BY 1");

        // Executa a consulta e guarda os resultados para a pagina JSP
        ArrayList<Funcionario> funcionarios = funcionarioDAO.buscar(sql.toString());

        // Disponibiliza resultados e filtros para manter a tela preenchida
        request.setAttribute("funcionarios", funcionarios);

        // Mantém os valores no filtro(opicional)
        request.setAttribute("filtroNome", nome);

        request.setAttribute("filtroCargo", cargo);

        request.setAttribute("filtroCpf", cpf);

        request.setAttribute("filtroEmail", email);

        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);

        request.setAttribute("filtroIdDepartamento", idDepartamentoTexto);

        // Encaminha internamente para a tela que apresenta a listagem
        request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);

    }
}