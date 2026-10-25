package org.astro.astro_app.Controller.Administrador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.astro.astro_app.dao.AdministradorDAO;
import org.astro.astro_app.dao.CertificadoDAO;
import org.astro.astro_app.dao.DepartamentoDAO;
import org.astro.astro_app.dao.EmpresaDAO;
import org.astro.astro_app.dao.FuncionarioDAO;

import java.io.IOException;

@WebServlet(name = "ServletAdministrador", value = "/tela-inicial")
public class ServletReadAdministrador extends HttpServlet {

    private AdministradorDAO administradorDAO;

    private EmpresaDAO empresaDAO;

    private FuncionarioDAO funcionarioDAO;

    private CertificadoDAO certificadoDAO;

    private DepartamentoDAO departamentoDAO;

    @Override
    public void init() {
        administradorDAO = new AdministradorDAO();

        empresaDAO = new EmpresaDAO();

        funcionarioDAO = new FuncionarioDAO();

        certificadoDAO = new CertificadoDAO();

        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        request.setAttribute("totalEmpresas", empresaDAO.contarEmpresas());

        request.setAttribute("totalFuncionarios", funcionarioDAO.contarFuncionarios());

        request.setAttribute("totalDepartamentos", departamentoDAO.contarDepartamentos());

        request.setAttribute("totalCertificados", certificadoDAO.contarCertificados());

        request.setAttribute("ultimasEmpresas", empresaDAO.listarUltimasTresEmpresas());


        request.getRequestDispatcher("/WEB-INF/crud.jsp").forward(request, response);
    }
}
