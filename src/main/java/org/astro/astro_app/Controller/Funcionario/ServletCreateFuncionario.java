package org.astro.astro_app.Controller.Funcionario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.FuncionarioDAO;
import org.astro.astro_app.model.Funcionario;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "/ServletCreateFuncionario", value = "/adicionar-funcionario")
public class ServletCreateFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init(){
        funcionarioDAO = new FuncionarioDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       final Pattern cpfRegex =
                Pattern.compile("^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$");

        String nome = request.getParameter("nome");

        String cargo = request.getParameter("cargo");

        String cpf = request.getParameter("cpf");

        String email = request.getParameter("email");

        String idEmpresaTexto = request.getParameter("id_empresa");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);

        String idDepartamentoTexto = request.getParameter("id_departamento");
        int idDepartamento = Integer.parseInt(idDepartamentoTexto);

        if(cpf == null || !cpfRegex.matcher(cpf.toLowerCase()).matches()){
            response.getWriter().println("Erro: Formato do CPF inválido. Use 000.000.000-00");
        }

        if(cargo == null || cargo.length() > 50){
            response.getWriter().println("Erro: O cargo deve ter no max. 50 caracteres");
        }

        if(nome == null || nome.length() > 100){
            response.getWriter().println("Erro: o nome deve ter no max. 100 caracteres");
        }


        Funcionario funcionario = new Funcionario(
                nome,
                cargo,
                cpf,
                email,
                idEmpresa,
                idDepartamento
        );

        funcionarioDAO.inserir(funcionario);

        response.sendRedirect(request.getContextPath() + "landingpage");
    }
}
