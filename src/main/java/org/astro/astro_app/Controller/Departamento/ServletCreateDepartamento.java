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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idEmpresaTexto = request.getParameter("id_empresa");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);

        String grauRisco = request.getParameter("grau_risco");

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String atividade = request.getParameter("atividade");

        Departamento departamento = new Departamento(
                idEmpresa,
                grauRisco,
                nome,
                descricao,
                atividade
        );

        departamentoDAO.inserir(departamento);

        response.sendRedirect(request.getContextPath() + "landing page"); //mudar depois


    }
}
