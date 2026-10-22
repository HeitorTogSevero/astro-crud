<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%  String caminho = request.getContextPath();
    String erro = (String) request.getAttribute("erro");
    String nomeDigitado = (String) request.getAttribute("nomeDigitado");
    String emailDigitado = (String) request.getAttribute("emailDigitado");
    if (nomeDigitado == null) nomeDigitado = "";
    if (emailDigitado == null) emailDigitado = "";
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro – Cadastro</title>
    <link rel="stylesheet" href="<%=caminho%>/style/cadastro.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" type="image/png" href="<%=caminho%>/imagens/isotipo.png">
</head>
<body class="pagina-cadastro">

<div class="fundo-planeta" aria-hidden="true"></div>

<a href="<%=caminho%>/lading-page.html" onclick="if (document.referrer) { history.back(); return false; }" class="botao-voltar" id="botao-voltar" aria-label="Voltar">
    <i class="fa-solid fa-arrow-left"></i>
</a>

<main class="cadastro-container">
    <section class="cadastro-lado-imagem" aria-labelledby="titulo-cadastro">
        <div class="cadastro-lado-conteudo">
            <a href="<%=caminho%>/lading-page.html" class="cadastro-logo" aria-label="Astro - página inicial">
                <img src="<%=caminho%>/imagens/imagotipo.png" alt="Astro" class="img-logo">
            </a>

            <h1 class="cadastro-titulo1" id="titulo-cadastro">Olá!</h1>
            <h2 class="cadastro-titulo2">Cadastre sua empresa<br>para começar.</h2>
            <p class="cadastro-subtitulo">Crie o workspace da sua empresa e<br class="quebra-desktop"> acompanhe a conformidade das NRs.</p>

            <section class="cadastro-como-funciona" aria-labelledby="titulo-como-funciona">
                <h3 id="titulo-como-funciona">Como funciona</h3>

                <div class="cadastro-passos">
                    <article class="cadastro-passo">
                        <span class="numero-passo">1</span>
                        <div class="texto-passo">
                            <h4>Valide sua licença</h4>
                            <p>Informe a chave de licença recebida do astro.</p>
                        </div>
                    </article>

                    <article class="cadastro-passo">
                        <span class="numero-passo">2</span>
                        <div class="texto-passo">
                            <h4>Crie o workspace</h4>
                            <p>Cadastre sua empresa.</p>
                        </div>
                    </article>

                    <article class="cadastro-passo">
                        <span class="numero-passo">3</span>
                        <div class="texto-passo">
                            <h4>Defina o Gestor do Workspace</h4>
                            <p>Você será o responsável pelo ambiente.</p>
                        </div>
                    </article>
                </div>
            </section>
        </div>
    </section>

    <section class="cadastro-lado-formulario" aria-label="Formulário de cadastro da empresa">
        <div class="cartao-cadastro">
            <header class="cabecalho-cartao-cadastro">
                <h2>Crie seu workspace</h2>
                <p class="cartao-subtitulo">Cadastre sua empresa para continuar</p>
            </header>

            <form id="formulario-cadastro" class="formulario-cadastro" action="<%=caminho%>/tela-cadastro" method="post" accept-charset="UTF-8" novalidate>
                <section class="secao-cadastro secao-gestor" aria-labelledby="titulo-gestor-workspace">
                    <div class="titulo-secao-cadastro">
                        <span class="numero-secao">1</span>
                        <h3 id="titulo-gestor-workspace">Gestor do Workspace</h3>
                        <span class="linha-secao" aria-hidden="true"></span>
                    </div>

                    <div class="campo-formulario campo-nome-gestor">
                        <label for="nome-gestor">Nome completo</label>
                        <input type="text" id="nome-gestor" name="nome" maxlength="100" value="<%= nomeDigitado.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;") %>" placeholder="Seu nome completo" autocomplete="name" required >
                    </div>

                    <div class="campo-formulario campo-email-gestor">
                        <label for="email-gestor">E-mail</label>
                        <input type="email" id="email-gestor" name="email" maxlength="100" value="<%= emailDigitado.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;") %>" placeholder="exemplo@empresa.com.br" autocomplete="email" required >
                    </div>

                    <div class="linha-campos linha-cpf-senha">
                        <div class="campo-formulario">
                            <label for="senha-gestor">Senha</label>
                            <input type="password" id="senha-gestor" name="senha" placeholder="Criar senha" autocomplete="new-password" minlength="8" required>
                        </div>

                        <div class="campo-formulario">
                            <label for="confirmar-senha-gestor">Confirmar senha</label>
                            <input type="password" id="confirmar-senha-gestor" name="confirmar_senha" placeholder="Repetir senha" autocomplete="new-password" minlength="8" required>
                        </div>
                    </div>

                    <p class="dica-senha">Mín. 8 caracteres, com maiúscula, minúscula, número e um símbolo ($ * &amp; @ #).</p>
                </section>

                <p class="termos-cadastro">
                    Ao continuar, você concorda com os <a href="#termos">Termos de uso</a><br>
                    e a <a href="#privacidade">Política de privacidade</a>.
                </p>

                <% if (erro != null) { %>
                <p class="mensagem-erro" id="mensagem-erro-cadastro" role="alert"><%= erro %></p>
                <% } else { %>
                <p class="mensagem-erro" id="mensagem-erro-cadastro" role="alert" hidden></p>
                <% } %>

                <button type="submit" class="botao-continuar botao-criar-workspace" id="botao-criar-workspace">Criar workspace</button>

                <p class="texto-entrar-conta">Já tem uma conta? <a href="<%=caminho%>/login">Entrar</a></p>
            </form>
        </div>
    </section>
</main>

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
                    Siga o <img id="siga" src="<%=caminho%>/imagens/logotipo.png" alt="astro">
                </h4>

                <div class="icones-sociais">

                    <div class="icone-social">
                        <img src="<%=caminho%>/imagens/linkedin.png" alt="LinkedIn">
                    </div>

                    <a href="https://www.instagram.com/astro.4pp/" target="_blank" rel="noopener noreferrer">
                        <div class="icone-social">
                            <img src="<%=caminho%>/imagens/instagram.png" alt="Instagram">
                        </div>
                    </a>

                    <div class="icone-social">
                        <img src="<%=caminho%>/imagens/youtube.png" alt="YouTube">
                    </div>

                </div>
            </div>

        </div>
    </div>

    <div class="rodape-inferior">
        <p>© 2026 astro. Todos os direitos reservados.</p>

        <div class="links-legais-rodape">
            <a href="#">Privacidade</a>
            <a href="#">Termos de uso</a>
        </div>
    </div>
</footer>


<script>
    (function () {
        const form = document.getElementById('formulario-cadastro');
        const erro = document.getElementById('mensagem-erro-cadastro');

        // Mesmas regras do ServletCreateAdministrador (o servidor valida de novo)
        const regexEmail = /^[a-zA-Z0-9._%+#$!\-]+@[a-zA-Z0-9.\-]+\.[a-zA-Z]{2,}$/;
        const regexSenha = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$/;

        function mostrarErro(msg) {
            erro.textContent = msg;
            erro.hidden = false;
        }

        form.addEventListener('submit', function (e) {
            const nome = form.nome.value.trim();
            const email = form.email.value.trim();
            const senha = form.senha.value;
            const confirmar = form.confirmar_senha.value;

            if (!nome || !email || !senha || !confirmar) { e.preventDefault(); return mostrarErro('Preencha todos os campos.'); }
            if (!regexEmail.test(email)) { e.preventDefault(); return mostrarErro('Formato de e-mail inválido.'); }
            if (!regexSenha.test(senha)) { e.preventDefault(); return mostrarErro('A senha não atende aos requisitos.'); }
            if (senha !== confirmar) { e.preventDefault(); return mostrarErro('As senhas não coincidem.'); }
        });
    })();
</script>

</body>
</html>