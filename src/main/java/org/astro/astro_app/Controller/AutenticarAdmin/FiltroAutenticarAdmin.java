package org.astro.astro_app.Controller.AutenticarAdmin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/tela-inicial", "/listar-empresas", "/adicionar-empresa", "/editar-empresa", "/deletar-empresa", "/listar-funcionarios", "/adicionar-funcionario", "/editar-funcionario", "/deletar-funcionario", "/listar-departamentos", "/adicionar-departamento", "/alterar-departamento", "/deletar-departamento", "/listar-divisoes", "/adicionar-divisao", "/alterar-divisao", "/deletar-divisao", "/listar-alerta", "/adicionar-alerta", "/alterar-alerta", "/deletar-alerta", "/listar-certificados", "/adicionar-certificado", "/alterar-certificado", "/deletar-certificado"})
public class FiltroAutenticarAdmin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request1 = (HttpServletRequest) request;

        HttpServletResponse response1 = (HttpServletResponse) response;

        HttpSession session = request1.getSession(false);

        boolean logado = session != null && session.getAttribute("adminLogado") != null;

        if (logado) {
            chain.doFilter(request, response); // deixa seguir
        } else {
            response1.sendRedirect(request1.getContextPath() + "/login"); //se não, ele manda voce direto para o login
        }
    }
}