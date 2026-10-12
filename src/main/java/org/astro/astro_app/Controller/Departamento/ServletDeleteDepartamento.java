package org.astro.astro_app.Controller.Departamento;


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
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idDepartamentoTexto = request.getParameter("id_departamento");
        String mensagem = "";


        if(idDepartamentoTexto == null || idDepartamentoTexto.isBlank()){
            mensagem = "id não informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);
            return;
        }
        try {
            int idDepartamento = Integer.parseInt(idDepartamentoTexto);

            if(idDepartamento<=0){
                mensagem = "id do departamento deve ser > 0";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);
                return;
            }

            departamentoDAO.remover(idDepartamento);

            response.sendRedirect(request.getContextPath() + "/listar-departamentos");

        }catch (NumberFormatException e){
            mensagem = "o id do departamento deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/Departamento.jsp").forward(request,response);
        }
    }
}