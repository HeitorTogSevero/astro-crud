package org.astro.astro_app.Controller.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;
import org.astro.astro_app.model.Departamento;

import java.io.IOException;

@WebServlet(name = "ServletCreateDepartamento", value = "adicionar-departamento")
public class ServletCreateDepartamento extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init(){
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idEmpresaTexto = request.getParameter("id_empresa");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);

        String grauRisco = request.getParameter("grau_risco");

        String nome = request.getParameter("nome");

        String descricao = request.getParameter("descricao");

        String atividade = request.getParameter("atividade");

        String mensagem;

        if(idEmpresa <= 0 || idEmpresaTexto.equals(" ")){
            mensagem = "Erro : o id deve ser > 0";
            request.setAttribute("erro", mensagem);
        }
        else if(nome == null || nome.equals(" ")) {
            mensagem = "Erro: o nome não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if(nome.length() > 100){
            mensagem = "Erro: o nome deve conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(descricao == null || descricao.equals(" ")){
            mensagem = "Erro: a descrição não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if(descricao.length() > 250){
            mensagem = "Erro: a descricao deve conter até 250 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(grauRisco == null || grauRisco.equals(" ")){
            mensagem = "Erro: o grau de risco não pode ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(!grauRisco.equals("1") && !grauRisco.equals("2") && !grauRisco.equals("3") && !grauRisco.equals("4") || grauRisco.length() > 6){
            mensagem = "Erro: o grau de risco deve ser (1, 2, 3 ou 4)";
            request.setAttribute("erro", mensagem);
        }

        else if(atividade == null || atividade.equals(" ")){
            mensagem = "Erro: a atividade não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if(atividade.length() > 15){
            mensagem = "Erro: a atividade deve conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else {
            Departamento departamento = new Departamento(
                    idEmpresa,
                    grauRisco,
                    nome,
                    descricao,
                    atividade
            );

            departamentoDAO.inserir(departamento);

            response.sendRedirect(request.getContextPath() + "landing page");
        }


    }
}
