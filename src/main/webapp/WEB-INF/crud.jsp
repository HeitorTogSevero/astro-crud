<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.ArrayList,
java.text.SimpleDateFormat,
org.astro.astro_app.model.Empresa"%>
<%  String caminho = request.getContextPath();
    ArrayList<Empresa> ultimasEmpresas = (ArrayList<Empresa>) request.getAttribute("ultimasEmpresas");

    Integer totalEmpresas = (Integer) request.getAttribute("totalEmpresas");
    Integer totalDepartamentos = (Integer) request.getAttribute("totalDepartamentos");
    Integer totalFuncionarios = (Integer) request.getAttribute("totalFuncionarios");
    Integer totalCertificados = (Integer) request.getAttribute("totalCertificados");

    if (totalEmpresas == null) totalEmpresas = 0;
    if (totalDepartamentos == null) totalDepartamentos = 0;
    if (totalFuncionarios == null) totalFuncionarios = 0;
    if (totalCertificados == null) totalCertificados = 0;

    //formato  dd-MM-yyyy
    SimpleDateFormat formatoExibicao = new SimpleDateFormat("dd-MM-yyyy");
%>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro - Painel de Administração</title>

    <link rel="stylesheet" href="<%=caminho%>/style/crud.css">
    <link rel="stylesheet" href="<%=caminho%>/style/tabelas.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" type="image/png" href="<%=caminho%>/imagens/isotipo.png">

</head>
<body>

<aside class="sidebar">
    <div class="logo">
        <img src="<%=caminho%>/imagens/logotipo.png" alt="Logotipo" class="img-logo">
    </div>
    <nav class="nav-menu">
        <a href="<%=caminho%>/tela-inicial" class="nav-item active">
            <i class="fa-solid fa-house"></i>Início
        </a>
        <a href="<%=caminho%>/listar-empresas" class="nav-item">
            <i class="fa-regular fa-building"></i>Empresa
        </a>
        <a href="<%=caminho%>/listar-funcionarios" class="nav-item">
            <i class="fa-solid fa-users"></i>Funcionário
        </a>
        <a href="<%=caminho%>/listar-departamentos" class="nav-item">
            <i class="fa-solid fa-layer-group"></i>Departamento
        </a>
        <a href="<%=caminho%>/listar-divisoes" class="nav-item">
            <i class="fa-regular fa-file-lines"></i>Divisão Treinamento
        </a>
        <a href="<%=caminho%>/listar-alerta" class="nav-item">
            <i class="fa-regular fa-calendar-check"></i>Alerta
        </a>
        <a href="<%=caminho%>/listar-certificados" class="nav-item">
            <i class="fa-solid fa-award"></i>Certificado
        </a>
        <div class="sidebar-bottom">
            <a href="<%=request.getContextPath()%>/logout" class="nav-item" id="exit-bottom">
                <i class="fa-solid fa-right-from-bracket"></i>Sair
            </a>
        </div>
    </nav>
</aside>

<main class="main-content">

    <header class="header">
        <h1>
            <strong>Início</strong> - Painel de Administração Geral
        </h1>
    </header>

    <section class="welcome-banner">
        <div class="welcome-text">
            <h2>Olá Admin! 👋</h2>
            <p>Gerencie os dados e acompanhe o desempenho do sistema em tempo real.</p>
        </div>
    </section>

    <section class="stats-grid">
        <div class="stat-card">
            <h3><%= totalEmpresas %></h3>
            <p>Empresas<br>ativas</p>
        </div>
        <div class="stat-card">
            <h3><%= totalDepartamentos %></h3>
            <p>Departamentos<br>ativos</p>
        </div>
        <div class="stat-card">
            <h3><%= totalFuncionarios %></h3>
            <p>Funcionários<br>gerenciados</p>
        </div>
        <div class="stat-card">
            <h3><%= totalCertificados %></h3>
            <p>Certificados<br>registrados</p>
        </div>
    </section>

    <section class="bottom-grid">
        <div class="panel recent-activity">
            <h2 class="mt-4">Últimas 3 empresas registradas:</h2>

            <div class="tabela-rolavel" id="tabela-rolavel-inicio">
                <table class="data-table" id="tabela-empresas">
                    <thead class="data-cabecalho">
                    <tr>
                        <th>Numero Empresa</th>
                        <th>CNPJ</th>
                        <th>Nome</th>
                        <th>CNAE</th>
                        <th>Bairro</th>
                        <th>CEP</th>
                        <th>Rua</th>
                        <th>Estado</th>
                        <th>Cidade</th>
                        <th>Data de Registro</th>
                    </tr>
                    </thead>
                    <tbody id="corpo-tabela-empresas">
                    <%
                        if (ultimasEmpresas != null && !ultimasEmpresas.isEmpty()) {
                            for (Empresa e : ultimasEmpresas) {
                    %>
                    <tr data-id-empresa="<%= e.getIdEmpresa() %>">
                        <td><%= e.getIdEmpresa()%></td>
                        <td><%= e.getCnpj()%></td>
                        <td><%= e.getNome()%></td>
                        <td><%= e.getCnae()%></td>
                        <td><%= e.getBairro()%></td>
                        <td><%= e.getCep()%></td>
                        <td><%= e.getRua()%></td>
                        <td><%= e.getEstado()%></td>
                        <td><%= e.getCidade()%></td>
                        <td><%= e.getDtRegistro() != null ? formatoExibicao.format(e.getDtRegistro()) : "" %></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="10">Nenhuma empresa encontrada.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

</main>

<script src="<%=caminho%>/JS/Paginas/carregamento.js"></script>

</body>
</html>
