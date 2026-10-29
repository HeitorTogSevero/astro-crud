<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String caminho = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Astro – Entrar</title>

    <link rel="stylesheet" href="<%=caminho%>/style/opcaoEntrada.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" type="image/png" href="<%= caminho%>/imagens/isotipo.png">

</head>
<body class="pagina-login">
<div class="fundo-planeta" aria-hidden="true"></div>

<a href="<%= caminho%>/landing-page.html" class="botao-voltar" id="botao-voltar" aria-label="Voltar" onclick="if (document.referrer) { history.back(); return false; }">
    <i class="fa-solid fa-arrow-left"></i>
</a>

<main id="acess-container">
    <div class="login-logo">
        <img src="<%= caminho%>/imagens/imagotipo.png" alt="Logo Astro" class="img-logo">
    </div>

    <section class="login-acess" aria-labelledby="titulo-acesso">
        <h1 id="titulo-acesso">Área Restrita</h1>

        <a href="<%= caminho %>/login" class="botao-entrar" id="botao-entrar-login">Entrar</a>

        <a href="<%= caminho %>/tela-cadastro" class="botao-cadastro" id="botao-cadastro-login">Cadastrar-se</a>
    </section>
</main>
</body>
</html>


