package org.astro.astro_app.Controller.Funcionario;

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
        funcionarioDAO = new FuncionarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String idEmpresaTexto = request.getParameter("id_empresa");
        String idDepartamentoTexto = request.getParameter("id_departamento");

        StringBuilder sql = new StringBuilder("SELECT id_funcionario, nome, cargo, email, id_empresa, id_departamento, pgp_sym_decrypt(cpf, 'funcionariosAstro') AS cpf FROM funcionario WHERE 1=1");

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

        } catch (NumberFormatException e) {
            sql = new StringBuilder("SELECT id_funcionario, nome, cargo, email, id_empresa, id_departamento, pgp_sym_decrypt(cpf, 'funcionariosAstro') AS cpf FROM funcionario WHERE 1=1");
            request.setAttribute("erro", "filtro invalido");
        }

        sql.append(" ORDER BY 1");

        ArrayList<Funcionario> funcionarios = funcionarioDAO.buscar(sql.toString());

        request.setAttribute("funcionarios", funcionarios);

        // mantém os filtros na tela
        request.setAttribute("filtroNome", nome);
        request.setAttribute("filtroCargo", cargo);
        request.setAttribute("filtroCpf", cpf);
        request.setAttribute("filtroEmail", email);
        request.setAttribute("filtroIdEmpresa", idEmpresaTexto);
        request.setAttribute("filtroIdDepartamento", idDepartamentoTexto);

        request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/Funcionario.jsp").forward(request, response);
    }
}