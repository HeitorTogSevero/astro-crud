package org.astro.astro_app.Controller.Funcionario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.CertificadoDAO;
import org.astro.astro_app.dao.FuncionarioDAO;
import org.astro.astro_app.model.Departamento;
import org.astro.astro_app.model.Funcionario;

import java.io.IOException;

@WebServlet(name = "ServletUpdateFuncionario", value = "/alterar-funcionario")
public class ServletUpdateFuncionario extends HttpServlet {

    private FuncionarioDAO funcionarioDAO;

    @Override
    public void init(){
        funcionarioDAO = new FuncionarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTexto = request.getParameter("id");
        int id = Integer.parseInt(idTexto);

        Funcionario funcionario= funcionarioDAO.buscarPorIdFunc(id);

        if (funcionario == null) {
            response.sendRedirect(request.getContextPath() + "/funcionarios");
            return;
        }

        request.setAttribute("departamento", funcionario);
        request.getRequestDispatcher("/WEB-INF/FuncionarioJSP/UpdateFuncionario.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //os dados chegam como bytes, tem que transformar em padrão :
        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String cargo = request.getParameter("cargo");
        String cpf = request.getParameter("cpf");

        String idEmpresaTexto = request.getParameter("id_empresa");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);

        String idFuncionarioTexto = request.getParameter("id_funcionario");
        int idFuncionario = Integer.parseInt(idFuncionarioTexto);

        String idDepartamentoTexto = request.getParameter("id_departamento");
         int idDepartamento = Integer.parseInt(idDepartamentoTexto);

         Funcionario funcionario = new Funcionario(
                 nome,
                 cargo,
                 cpf,
                 idEmpresa,
                 idFuncionario,
                 idDepartamento
         );

         funcionarioDAO.alterarFuncionario(funcionario);

         response.sendRedirect(request.getContextPath() + "/funcionarios");


    }
}
