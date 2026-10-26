package org.astro.astro_app.Controller.AutenticarAdmin;

// Biblioteca:
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// Define que este filtro protege todas as rotas administrativas listadas abaixo
@WebFilter(urlPatterns = {"/tela-inicial", "/listar-empresas", "/adicionar-empresa", "/editar-empresa", "/deletar-empresa", "/listar-funcionarios", "/adicionar-funcionario", "/editar-funcionario", "/deletar-funcionario", "/listar-departamentos", "/adicionar-departamento", "/alterar-departamento", "/deletar-departamento", "/listar-divisoes", "/adicionar-divisao", "/alterar-divisao", "/deletar-divisao", "/listar-alerta", "/adicionar-alerta", "/alterar-alerta", "/deletar-alerta", "/listar-certificados", "/adicionar-certificado", "/alterar-certificado", "/deletar-certificado"})
public class FiltroAutenticarAdmin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Converte os objetos para o protocolo HTTP
        HttpServletRequest request1 = (HttpServletRequest) request;

        HttpServletResponse response1 = (HttpServletResponse) response;

        // Recupera a sessão atual sem criar uma nova caso não exista
        HttpSession session = request1.getSession(false);

        // Verifica se a sessão existe e se o atributo de administrador logado está presente
        boolean logado = session != null && session.getAttribute("adminLogado") != null;

        if (logado) {

            // Se estiver logado, permite que a requisição prossiga para o destino
            chain.doFilter(request, response); // deixa seguir

        } else {

            // Se não estiver logado, bloqueia e redireciona para a página de login
            response1.sendRedirect(request1.getContextPath() + "/login");

        }
    }
}