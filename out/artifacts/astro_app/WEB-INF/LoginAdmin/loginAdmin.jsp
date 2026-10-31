<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%  String caminho = request.getContextPath();
String erro = (String) request.getAttribute("erro");
%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro – Entrar</title>
    <link rel="stylesheet" href="<%=caminho%>/style/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" type="image/png" href="<%=caminho%>/imagens/isotipo.png">

</head>
<body class="pagina-login">

<div class="fundo-planeta" aria-hidden="true" style="background-image: url('<%=caminho%>/imagens/planeta_login.png');"></div>

<a href="<%=caminho%>/landing-page.html"
   onclick="if(document.referrer) { history.back(); return false; }"
   class="botao-voltar"
   id="botao-voltar"
   aria-label="Voltar">
    <i class="fa-solid fa-arrow-left"></i>
</a>

<div class="login-container">

    <section class="login-lado-imagem">
        <div class="login-lado-conteudo">
            <div class="login-logo">
                <img src="<%=caminho%>/imagens/imagotipo.png" alt="Logotipo" class="img-logo">
            </div>

            <h1 class="login-titulo1">Olá!<br></h1>
            <h1 class="login-titulo2">Informe seus dados<br>para entrar.</h1>
            <p class="login-subtitulo">Seu próximo passo para a conformidade está por vir!</p>
        </div>
    </section>

    <section class="login-lado-formulario">
        <div class="cartao-login">
            <h2>Acesse sua conta</h2>
            <p class="cartao-subtitulo">Acesse sua conta para continuar</p>

            <form id="formulario-login"
                  class="formulario-login"
                  action="<%=caminho%>/login"
                  method="post">

                <div class="campos-login">
                    <div class="campo-formulario">
                        <label for="email-login">Email</label>
                        <input type="email"
                               id="email-login"
                               name="email"
                               placeholder="exemplo@gmail.com"
                               autocomplete="email"
                               required>
                    </div>

                    <div class="campo-formulario">
                        <label for="senha-login">Senha</label>
                        <input type="password"
                               id="senha-login"
                               name="senha"
                               placeholder="Inserir senha"
                               autocomplete="current-password"
                               required>

                        <a href="#" class="link-esqueci-senha" id="link-esqueci-senha">Esqueci minha senha</a>
                    </div>
                </div>

                <% if (erro != null) { %>
                <p class="mensagem-erro" id="mensagem-erro-login"><%= erro %></p>
                <% } else { %>
                <p class="mensagem-erro" id="mensagem-erro-login" hidden>Email ou senha incorretos.</p>
                <% } %>

                <button type="submit" class="botao-continuar" id="botao-continuar-login">Continuar</button>

                <p class="texto-criar-conta">
                    Não tem uma conta?
                    <a href="<%=caminho%>/tela-cadastro" id="link-criar-workspace">Criar workspace</a>
                </p>
            </form>
        </div>
    </section>
</div>

<footer>
    <div class="conteudo-rodape">

        <div class="marca-rodape">
            <img src="<%=caminho%>/imagens/imagotipo.png" alt="Astro" class="logo-rodape">
            <p>Conformidade em órbita,<br>sempre em vigência.</p>
        </div>

        <div class="links-rodape">

            <div class="coluna-links">
                <h4>Produto</h4>
                <a href="<%=caminho%>/produtos.html#funcionalidades">Funcionalidades</a>
                <a href="<%=caminho%>/produtos.html#integracoes">Integrações</a>
                <a href="<%=caminho%>/produtos.html#seguranca">Segurança</a>
            </div>

            <div class="coluna-links">
                <h4>Recursos</h4>
                <a href="<%=caminho%>/recursos.html#materiais">Materiais</a>
                <a href="<%=caminho%>/recursos.html#blog">Blog</a>
                <a href="<%=caminho%>/recursos.html#glossario">Glossário</a>
            </div>

            <div class="coluna-links">
                <h4>Planos</h4>
                <a href="<%=caminho%>/planos.html">Mensal</a>
                <a href="<%=caminho%>/planos.html?periodo=anual">Anual</a>
                <a href="<%=caminho%>/planos.html#visao-geral">Visão geral</a>
            </div>

            <div class="coluna-links">
                <h4>Sobre</h4>
                <a href="<%=caminho%>/sobre.html#equipe">Quem somos</a>
                <a href="<%=caminho%>/sobre.html#missao">Missão e valores</a>
                <a href="<%=caminho%>/sobre.html#contato">Contato</a>
            </div>

            <div class="coluna-links social">
                <h4>
                    Siga o <img id="siga" src="<%=caminho%>/imagens/logotipoAstro.png" alt="astro">
                </h4>

                <div class="icones-sociais">
                    <a href="mailto:app.4str0@gmail.com">
                        <div class="icone-gmail">
                            <img src="<%=caminho%>/imagens/gmail.png" alt="Gmail">
                        </div>
                    </a>

                    <a href="https://www.instagram.com/astro.4pp/" target="_blank" rel="noopener noreferrer">
                        <div class="icone-social">
                            <img src="<%=caminho%>/imagens/instagram.png" alt="Instagram">
                        </div>
                    </a>

                    <a href="https://www.youtube.com/@astro-jef" target="_blank" rel="noopener noreferrer">
                        <div class="icone-social">
                            <img src="<%=caminho%>/imagens/youtube.png" alt="YouTube">
                        </div>
                    </a>
                </div>
            </div>

        </div>
    </div>

    <div class="rodape-inferior">
        <p>© 2026 astro. Todos os direitos reservados.</p>
        <div class="links-legais-rodape">
            <a href="<%=caminho%>/termos.html">Privacidade e Termos de Uso</a>
            <a href="<%=caminho%>/opcaoEntrada.html">Área Restrita</a>
        </div>
    </div>
</footer>

<script src="<%=caminho%>/JS/Paginas/carregamento.js"></script>
<script src="<%=caminho%>/JS/Paginas/login.js"></script>

</body>
</html>