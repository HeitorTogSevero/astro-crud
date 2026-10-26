package org.astro.astro_app.Controller.Administrador;

// Biblioteca:
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

        // Concede o acesso ao DAO de administrator quando o servlet e iniciado
        administradorDAO = new AdministradorDAO();

        // Concede o acesso ao DAO da empresa quando o servlet e iniciado
        empresaDAO = new EmpresaDAO();

        // Concede o acesso ao DAO do funcionário quando o servlet e iniciado
        funcionarioDAO = new FuncionarioDAO();

        // Concede o acesso ao DAO de certificados quando o servlet e iniciado
        certificadoDAO = new CertificadoDAO();

        // Concede o acesso ao DAO do departamento quando o servlet e iniciado
        departamentoDAO = new DepartamentoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Os dados chegam em bits, aqui converte ele para linguagem normal
        request.setCharacterEncoding("UTF-8");

        // Mantém os valores no filtro(opicional)
        request.setAttribute("totalEmpresas", empresaDAO.contarEmpresas());

        request.setAttribute("totalFuncionarios", funcionarioDAO.contarFuncionarios());

        request.setAttribute("totalDepartamentos", departamentoDAO.contarDepartamentos());

        request.setAttribute("totalCertificados", certificadoDAO.contarCertificados());

        request.setAttribute("ultimasEmpresas", empresaDAO.listarUltimasTresEmpresas());


        // Encaminha internamente para a tela que apresenta a listagem
        request.getRequestDispatcher("/WEB-INF/crud.jsp").forward(request, response);
    }
}
