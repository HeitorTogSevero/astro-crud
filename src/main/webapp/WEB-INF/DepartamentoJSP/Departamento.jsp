<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List,
    java.util.ArrayList,
    org.astro.astro_app.model.Departamento"%>
<%!private String v(String s) {
return s == null ? "" : s;}%>
<%  String caminho = request.getContextPath();
    ArrayList<Departamento> departamentos = (ArrayList<Departamento>) request.getAttribute("departamentos");
    //valores de filtro(pra nao ficar null
    String fGrauRisco  = v(request.getParameter("grau_risco"));
    String fAtividade  = v(request.getParameter("atividade"));
    String fIdEmpresa  = v(request.getParameter("id_empresa"));%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro - Departamentos</title>

    <link rel="stylesheet" href="<%=caminho%>/style/crud.css">
    <link rel="stylesheet" href="<%=caminho%>/style/tabelas.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<aside class="sidebar">
    <div class="logo">
        <img src="<%=caminho%>/imagens/logotipo.png" alt="Logotipo" class="img-logo">
    </div>

    <nav class="nav-menu">
        <a href="<%=caminho%>/tela-inicial" class="nav-item">
            <i class="fa-solid fa-house"></i>Início
        </a>
        <a href="<%=caminho%>/listar-empresas" class="nav-item">
            <i class="fa-regular fa-building"></i>empresa
        </a>
        <a href="<%=caminho%>/listar-funcionarios" class="nav-item">
            <i class="fa-solid fa-users"></i>funcionario
        </a>
        <a href="<%=caminho%>/listar-departamentos" class="nav-item active">
            <i class="fa-solid fa-layer-group"></i>departamento
        </a>
        <a href="<%=caminho%>/listar-divisoes" class="nav-item">
            <i class="fa-regular fa-file-lines"></i>divisao_treinamento
        </a>
        <a href="<%=caminho%>/listar-alerta" class="nav-item">
            <i class="fa-regular fa-calendar-check"></i>alerta
        </a>
        <a href="<%=caminho%>/listar-certificados" class="nav-item">
            <i class="fa-solid fa-award"></i>certificado
        </a>
        <div class="sidebar-bottom">
            <a href="<%=request.getContextPath()%>/lading-page.html" class="nav-item" id="exit-bottom">
                <i class="fa-solid fa-right-from-bracket"></i>Sair
            </a>
        </div>
    </nav>
</aside>

<main class="main-content">

    <header class="header cabecalho-pagina">
        <div class="titulo-container">
            <h1><strong>departamento - </strong> <span id="periodo-titulo">Setembro 2026</span></h1>
        </div>
    </header>

    <section class="barra-controles">
        <div class="controle-esquerda">
            <div class="search-bar">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" id="busca-departamento" name="busca-departamento" placeholder="Pesquisar departamento...">
            </div>
        </div>

        <div class="controle-direita">
            <button class="btn-filtrar" id="btn-filtrar-departamento" type="button">
                <i class="fa-solid fa-filter"></i> Filtrar
            </button>
            <button class="btn-adicionar" id="btn-adicionar-departamento" type="button">
                <i class="fa-solid fa-plus"></i> Adicionar departamento
            </button>
        </div>
    </section>

    <%String mensagem = (String) request.getAttribute("erro");
    if (mensagem == null) {
    mensagem = (String) request.getAttribute("mensagem");
    }
    if (mensagem != null) {
    String cor = mensagem.toLowerCase().contains("sucesso") ? "green" : "red";
    %>
    <p style="color: <%= cor %>"><%= mensagem %></p>
    <% } %>

    <section class="panel painel-tabela">
        <div class="tabela-rolavel">
            <table class="data-table" id="tabela-departamentos">
                <thead class="data-cabecalho">
                <tr>
                    <th class="ordenavel" data-coluna="id_departamento"><strong>ID do Departamento</strong> <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="nome">Nome <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="descricao">Descrição <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="grau_risco">Grau de Riso <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="atividade">Atividade <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_empresa">Empresa <i class="fa-solid fa-sort"></i></th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody id="corpo-tabela-departamentos">
                <%
                if (departamentos != null && !departamentos.isEmpty()) {
                for (Departamento d : departamentos) {
                %>
                <tr data-id-departamento="<%= d.getIdDepartamento() %>">
                    <td><%= d.getIdDepartamento() %></td>
                    <td><%= d.getNome() %></td>
                    <td><%= d.getDescricao() %></td>
                    <td><%= d.getGrauRisco() %></td>
                    <td><%= d.getAtividade() %></td>
                    <td><%= d.getIdEmpresa() %></td>
                    <td>
                        <div class="celula-acoes">
                            <button class="btn-editar" type="button">
                                <i class="fa-solid fa-pen"></i> Editar
                            </button>
                            <button class="btn-remover" type="button">
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </div>
                    </td>
                </tr>
                <%
                }
                } else {
                %>
                <tr>
                    <td colspan="7">Nenhum departamento encontrado.</td>

                <tr id_departamento="" nome="" descricao="" grau_risco="" atividade="" id_empresa="">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <div class="celula-acoes">
                            <button class="btn-editar" type="button" data-acao="editar"><i class="fa-solid fa-pen"></i> Editar</button>
                            <button class="btn-remover" type="button" data-acao="remover"><i class="fa-solid fa-trash"></i> </button>
                        </div>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <p class="rodape-tabela">
            Mostrando <%= departamentos != null ? departamentos.size() : 0 %> departamentos
        </p>
    </section>

</main>

<!--ADICIONAR-->
<div class="modal-fundo" id="modal-adicionar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Adicionar em departamento</h2>
            <button type="button" class="modal-fechar" id="fechar-modal">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-adicionar" class="modal-formulario"
              action="<%=caminho%>/adicionar-departamento" method="post">

            <div class="modal-campo">
                <label for="campo-nome">Nome:</label>
                <input type="text" id="campo-nome" name="nome" required>
                <span id="erroNome"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-descricao">Descricao:</label>
                <input type="text" id="campo-descricao" name="descricao" required>
                <span id="erroDescr"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-grau_risco">Grau de risco:</label>
                <input type="number" id="campo-grau_risco" name="grau_risco" min="1" max="4" required>
                <span id="erroRisco"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-atividade">Atividade:</label>
                <input type="text" id="campo-atividade" name="atividade" required>
                <span id="erroAtv"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-id_empresa">id_empresa:</label>
                <input type="number" id="campo-id_empresa" name="id_empresa" min="0" required>
                <span id="erroIdEmpresa"></span>
            </div>

            <div class="modal-rodape">
                <button type="button" class="botao-cancelar" id="cancelar-modal">Cancelar</button>
                <button type="submit" class="botao-salvar">Salvar</button>
            </div>
        </form>
    </div>
</div>

<!--FILTRAR-->
<div class="modal-fundo" id="modal-filtro" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Filtrar departamentos</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-filtro">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-filtro" class="modal-formulario"
              action="<%=caminho%>/listar-departamentos" method="get">

            <div class="modal-campo">
                <label for="filtro-grau_risco">grau_risco</label>
                <input type="number" id="filtro-grau_risco" name="grau_risco" min="1" max="4"
                       value="<%= fGrauRisco %>">
            </div>

            <div class="modal-campo">
                <label for="filtro-atividade">atividade</label>
                <input type="text" id="filtro-atividade" name="atividade"
                       value="<%= fAtividade %>">
            </div>

            <div class="modal-campo">
                <label for="filtro-id_empresa">id_empresa</label>
                <input type="number" id="filtro-id_empresa" name="id_empresa" min="0"
                       value="<%= fIdEmpresa %>">
            </div>

            <div class="modal-rodape">
                <a href="<%=caminho%>/listar-departamentos" class="botao-cancelar">Limpar</a>
                <button type="submit" class="botao-salvar">Aplicar filtro</button>
            </div>
        </form>
    </div>
</div>

<!--EDITAR-->
<div class="modal-fundo" id="modal-editar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Editar departamento</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-editar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-editar" class="modal-formulario"
              action="<%=caminho%>/alterar-departamento" method="post">

            <input type="hidden" id="editar-id-departamento" name="id_departamento">

            <div class="modal-campo">
                <label for="editar-nome">Nome:</label>
                <input type="text" id="editar-nome" name="nome" required>
                <span id="editar-erroNome"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-descricao">Descricao:</label>
                <input type="text" id="editar-descricao" name="descricao" required>
                <span id="editar-erroDescr"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-grau_risco">Grau de risco:</label>
                <input type="number" id="editar-grau_risco" name="grau_risco" min="1" max="4" required>
                <span id="editar-erroRisco"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-atividade">Atividade:</label>
                <input type="text" id="editar-atividade" name="atividade" required>
                <span id="editar-erroAtv"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-id_empresa">id_empresa</label>
                <input type="number" id="editar-id_empresa" name="id_empresa" min="0" required>
                <span id="editar-erroIdEmpresa"></span>
            </div>

            <div class="modal-rodape">
                <button type="button" class="botao-cancelar" id="cancelar-modal-editar">Cancelar</button>
                <button type="submit" class="botao-salvar">Salvar alterações</button>
            </div>
        </form>
    </div>
</div>

<!--EXCLUIR-->
<div class="modal-fundo" id="modal-excluir" hidden>
    <div class="modal-caixa modal-caixa-pequena" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Excluir registro</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-excluir">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <p class="modal-texto-confirmacao">
            Tem certeza que deseja excluir
            <strong id="excluir-nome"></strong>?
            Essa ação não pode ser desfeita.
        </p>

        <form id="formulario-excluir" action="<%=caminho%>/deletar-departamento" method="post">
            <input type="hidden" id="excluir-id" name="id_departamento">
            <div class="modal-rodape">
                <button type="button" class="botao-cancelar" id="cancelar-modal-excluir">Cancelar</button>
                <button type="submit" class="botao-excluir">Excluir</button>
            </div>
        </form>
    </div>
</div>

<script>
    // FILTRO
    const modalFiltro = document.getElementById('modal-filtro');
    document.querySelector('.btn-filtrar').addEventListener('click', () => modalFiltro.hidden = false);
    document.getElementById('fechar-modal-filtro').addEventListener('click', () => modalFiltro.hidden = true);
    modalFiltro.addEventListener('click', e => { if (e.target === modalFiltro) modalFiltro.hidden = true; });

    // EDITAR
    const corpoTabela = document.getElementById('corpo-tabela-departamentos');
    const modalEditar = document.getElementById('modal-editar');

    corpoTabela.addEventListener('click', function (evento) {
        const botao = evento.target.closest('.btn-editar');
        if (!botao) return;

        const c = botao.closest('tr').children;
        const t = i => c[i].textContent.trim();

        document.getElementById('editar-id-departamento').value = t(0);
        document.getElementById('editar-nome').value = t(1);
        document.getElementById('editar-descricao').value = t(2);
        document.getElementById('editar-grau_risco').value = t(3);
        document.getElementById('editar-atividade').value = t(4);
        document.getElementById('editar-id_empresa').value = t(5);

        modalEditar.hidden = false;
    });

    document.getElementById('fechar-modal-editar').addEventListener('click', () => modalEditar.hidden = true);
    document.getElementById('cancelar-modal-editar').addEventListener('click', () => modalEditar.hidden = true);
    modalEditar.addEventListener('click', e => { if (e.target === modalEditar) modalEditar.hidden = true; });

    // EXCLUIR
    const modalExcluir = document.getElementById('modal-excluir');

    corpoTabela.addEventListener('click', function (evento) {
        const botao = evento.target.closest('.btn-remover');
        if (!botao) return;

        const linha = botao.closest('tr');
        document.getElementById('excluir-id').value = linha.dataset.idDepartamento;
        document.getElementById('excluir-nome').textContent = linha.children[1].textContent.trim();
        modalExcluir.hidden = false;
    });

    document.getElementById('fechar-modal-excluir').addEventListener('click', () => modalExcluir.hidden = true);
    document.getElementById('cancelar-modal-excluir').addEventListener('click', () => modalExcluir.hidden = true);
    modalExcluir.addEventListener('click', e => { if (e.target === modalExcluir) modalExcluir.hidden = true; });

    // ADICIONAR
    const modalAdicionar = document.getElementById('modal-adicionar');
    document.querySelector('.btn-adicionar').addEventListener('click', () => modalAdicionar.hidden = false);
    document.getElementById('fechar-modal').addEventListener('click', () => modalAdicionar.hidden = true);
    document.getElementById('cancelar-modal').addEventListener('click', () => modalAdicionar.hidden = true);
    modalAdicionar.addEventListener('click', e => { if (e.target === modalAdicionar) modalAdicionar.hidden = true; });
</script>

</body>
</html>