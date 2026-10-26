package org.astro.astro_app.Controller.Departamento;


// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;

import java.io.IOException;

@WebServlet(name = "ServletDeleteDepartamento", value = "/deletar-departamento")
public class ServletDeleteDepartamento extends HttpServlet {

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init(){

        // Concede o acesso ao DAO do departamento quando o servlet e iniciado
        departamentoDAO = new DepartamentoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Le o identificador do alerta enviado pelo formulario de exclusao
        String idDepartamentoTexto = request.getParameter("id_departamento");
        String mensagem = "";


        // Impede a exclusão quando o identificador nao foi informado
        if(idDepartamentoTexto == null || idDepartamentoTexto.isBlank()){

            mensagem = "id não informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);
            return;

        }

        try {

            // Converte e valida o identificador antes de chamar o DAO
            int idDepartamento = Integer.parseInt(idDepartamentoTexto);

            if(idDepartamento<=0){
                mensagem = "id do departamento deve ser > 0";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);
                return;
            }

            // Remove o alerta do banco de dados
            departamentoDAO.remover(idDepartamento);

            // Redireciona para a listagem apos a exclusao
            response.sendRedirect(request.getContextPath() + "/listar-departamentos");

        }catch (NumberFormatException e){

            // Se o identificador nao for numérico, informa o erro no formulário
            mensagem = "o id do departamento deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);

        }
    }
}