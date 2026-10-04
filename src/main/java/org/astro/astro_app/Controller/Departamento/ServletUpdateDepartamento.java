package org.astro.astro_app.Controller.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.DepartamentoDAO;
import org.astro.astro_app.model.Certificado;
import org.astro.astro_app.model.Departamento;

import java.io.IOException;


@WebServlet(name = "ServletUpdateDepartamento", value = "/alterar-departamento")
public class ServletUpdateDepartamento extends HttpServlet{

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init(){
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTexto = request.getParameter("id");
        int id = Integer.parseInt(idTexto);

        Departamento departamento = departamentoDAO.buscarPorIdDepart(id);

        if (departamento == null) {
            response.sendRedirect(request.getContextPath() + "/departamentos");
            return;
        }

        request.setAttribute("departamento", departamento);
        request.getRequestDispatcher("/WEB-INF/DepartamentoJSP/UpdateDepartamento.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //os dados chegam como bytes, tem que transformar em padrão :
        request.setCharacterEncoding("UTF-8");

        String idDepartamentoTexto = request.getParameter("id_departamento");
        int idDepartamento = Integer.parseInt(idDepartamentoTexto);
        String idEmpresaTexto = request.getParameter("id_empresa");
        int idEmpresa = Integer.parseInt(idEmpresaTexto);
        String grauRisco = request.getParameter("grau_risco");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String atividade = request.getParameter("atividade");

        Departamento departamento = new Departamento(
                idDepartamento,
                idEmpresa,
                grauRisco,
                nome,
                descricao,
                atividade
        );

        departamentoDAO.alterarDepartamento(departamento);

        response.sendRedirect(request.getContextPath() + "/departamentos");

    }
}
