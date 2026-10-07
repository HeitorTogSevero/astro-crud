package org.astro.astro_app.Controller.Funcionario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.FuncionarioDAO;
import org.astro.astro_app.model.Funcionario;

import java.io.IOException;

@WebServlet(name = "/ServletCreateFuncionario", value = "/adicionar-funcionario")
public class ServletCreateFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init(){
        funcionarioDAO = new FuncionarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String cpf = request.getParameter("cpf");
        String idEmpresaTexto = request.getParameter("id_empresa");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);
        String idDepartamentoTexto = request.getParameter("id_departamento");
        int idDepartamento = Integer.parseInt(idDepartamentoTexto);

        Funcionario funcionario = new Funcionario(
                nome,
                cargo,
                cpf,
                idEmpresa,
                idDepartamento
        );

        funcionarioDAO.inserir(funcionario);

        response.sendRedirect(request.getContextPath() + "landingpage");
    }
}
