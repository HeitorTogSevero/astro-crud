package org.astro.astro_app.Controller.Administrador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.*;

import java.io.IOException;

@WebServlet(name = "ServletAdministrador", value = "/tela-inicial")
public class ServletAdministrador extends HttpServlet{

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

        empresaDAO.contarEmpresas();
        funcionarioDAO.contarFuncionarios();
        certificadoDAO.contarCertificados();
        departamentoDAO.contarDepartamentos();

        empresaDAO.listarUltimasTresEmpresas();
    }
}
