<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String caminho = request.getContextPath();
    String erro = (String) request.getAttribute("erro");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Astro – Entrar</title>

    <link rel="stylesheet" href="<%= caminho %>/style/acessoAdmin.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" type="image/png" href="<%= caminho %>/imagens/isotipo.png">
</head>

<body class="pagina-login">

<div class="fundo-planeta" aria-hidden="true"></div>

<a href="<%= caminho %>/landing-page.html" onclick="if (document.referrer) { history.back(); return false; }" class="botao-voltar" id="botao-voltar" aria-label="Voltar">
    <i class="fa-solid fa-arrow-left"></i>
</a>

<section id="acess-container">

    <div class="login-logo">
        <img src="<%= caminho %>/imagens/imagotipo.png" alt="Logo Astro" class="img-logo">
    </div>

    <form action="<%= caminho %>/acess-admin" method="post" class="form-acess">

        <h2>Acesse a Área Restrita</h2>

        <p>Preencha os dados para seguir.</p>

        <div class="login-acess">

            <label for="campo-senha" id="acess-password">Senha</label>

            <input type="password" id="campo-senha" name="senha" placeholder="Inserir senha" autocomplete="current-password" required>

            <%if (erro != null) {%>
            <p class="mensagem-erro" id="mensagem-erro-login"><%= erro %></p>
            <%}%>

        </div>

        <button type="submit" class="botao-continuar" id="botao-continuar-login">Continuar</button>

    </form>

</section>

</body>
</html>