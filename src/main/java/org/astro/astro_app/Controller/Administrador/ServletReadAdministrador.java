package org.astro.astro_app.Controller.Administrador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.*;

import java.io.IOException;

@WebServlet(name = "ServletReadAdministrador", value = "/tela-inicial")
public class ServletReadAdministrador extends HttpServlet{

    private AdministradorDAO administradorDAO;
    private EmpresaDAO empresaDAO;
    private FuncionarioDAO funcionarioDAO;
    private CertificadoDAO certificadoDAO;
    private DepartamentoDAO departamentoDAO;

    @Override
    public void init(){
        administradorDAO = new AdministradorDAO();

        empresaDAO = new EmpresaDAO();

        funcionarioDAO = new FuncionarioDAO();

        certificadoDAO = new CertificadoDAO();

        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        request.setAttribute("totalEmpresas", empresaDAO.contarEmpresas());

        request.setAttribute("totalFuncionarios", funcionarioDAO.contarFuncionarios());

        request.setAttribute("totalDepartamentos", departamentoDAO.contarDepartamentos());

        request.setAttribute("totalCertificados", certificadoDAO.contarCertificados());

        request.setAttribute("ultimasTresEmpresas", empresaDAO.listarUltimasTresEmpresas());

        request.getRequestDispatcher("/webapp/crud.jsp");
    }
}