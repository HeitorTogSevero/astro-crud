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

        final Pattern emailRegex =
                Pattern.compile("^[a-zA-Z0-9._%+#$!\\-]+@(gmail|hotmail|yahoo|outlook)\\.(com|com\\.br|org\\.br)$");

        String nome = request.getParameter("nome");

        String cargo = request.getParameter("cargo");

        String cpf = request.getParameter("cpf");

        String email = request.getParameter("email");

        String idEmpresaTexto = request.getParameter("id_empresa");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);

        String idDepartamentoTexto = request.getParameter("id_departamento");
        int idDepartamento = Integer.parseInt(idDepartamentoTexto);

        String mensagem;

        if(!cpfRegex.matcher(cpf).matches()){
            mensagem = "Erro: Formato do CPF inválido. Use 000.000.000-00";
            request.setAttribute("erro", mensagem);
        }

        else if(cargo == null || cargo.length() > 50){
            mensagem = "Erro: O cargo deve ter no max. 50 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(nome == null || nome.equals(" ")){
            mensagem = "Erro: nome não pode ser vazio";
        }

        else if(nome.length() > 100){
            mensagem = "Erro: o nome deve ter no max. 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(idEmpresa <= 0 || idEmpresaTexto.equals(" ")){
            mensagem = "o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(idDepartamento <= 0 || idDepartamentoTexto.equals(" ")){
            mensagem = "o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }

        else if(email.length() > 50){
            mensagem = "Erro: o email pode ter até 50 caracterses";
            request.setAttribute("erro", mensagem);
        }

        else if(!emailRegex.matcher(email).matches()){
            mensagem = "Erro: o formato do email não é aceito";
            request.setAttribute("erro", mensagem);
        }


        else {
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

}
