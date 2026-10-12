<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.ArrayList,
java.text.SimpleDateFormat,
org.astro.astro_app.model.Alerta"
%>
<%!
private String v(String s) {
return s == null ? "" : s;
}
%>
<%  String caminho = request.getContextPath();
    ArrayList<Alerta> alertas = (ArrayList<Alerta>) request.getAttribute("alertas");
    SimpleDateFormat formatoExibicao = new SimpleDateFormat("dd-MM-yyyy");

    String fCodigo = v(request.getParameter("codigo"));
    String fDtMin = v(request.getParameter("dt_limite_min"));
    String fDtMax = v(request.getParameter("dt_limite_max"));
    String fIdEmpresa = v(request.getParameter("id_empresa"));
    %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro - Alertas</title>
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
        <a href="<%=caminho%>/crud.jsp" class="nav-item">
            <i class="fa-solid fa-house"></i>Início
        </a>
        <a href="<%=caminho%>/listar-empresas" class="nav-item">
            <i class="fa-regular fa-building"></i>empresa
        </a>
        <a href="<%=caminho%>/listar-funcionarios" class="nav-item">
            <i class="fa-solid fa-users"></i>funcionario
        </a>
        <a href="<%=caminho%>/listar-departamentos" class="nav-item">
            <i class="fa-solid fa-layer-group"></i>departamento
        </a>
        <a href="<%=caminho%>/listar-divisoes" class="nav-item">
            <i class="fa-regular fa-file-lines"></i>divisao_treinamento
        </a>
        <a href="<%=caminho%>/lista-alerta" class="nav-item active">
            <i class="fa-regular fa-calendar-check"></i>alerta
        </a>
        <a href="<%=caminho%>/listar-certificados" class="nav-item">
            <i class="fa-solid fa-award"></i>certificado
        </a>
        <div class="sidebar-bottom">
            <a href="#" class="nav-item" id="exit-bottom">
                <i class="fa-solid fa-right-from-bracket"></i>Sair
            </a>
        </div>
    </nav>
</aside>

<main class="main-content">

    <header class="header cabecalho-pagina">
        <div class="titulo-container">
            <h1><strong>alerta - </strong> <span id="periodo-titulo">Setembro 2026</span></h1>
        </div>
    </header>

    <section class="barra-controles">
        <div class="controle-esquerda">
            <div class="search-bar">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" id="busca-alerta" placeholder="Pesquisar alerta...">
            </div>
        </div>
        <div class="controle-direita">
            <button class="btn-filtrar" type="button">
                <i class="fa-solid fa-filter"></i> Filtrar
            </button>
            <button class="btn-adicionar" type="button">
                <i class="fa-solid fa-plus"></i> Adicionar alerta
            </button>
        </div>
    </section>

    <%
    String mensagem = (String) request.getAttribute("erro");
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
            <table class="data-table" id="tabela-alerta">
                <thead class="data-cabecalho">
                <tr>
                    <th class="ordenavel" data-coluna="id_alerta"><strong>id_alerta</strong> <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="descricao">descricao <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="codigo">codigo <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="dt_limite">dt_limite <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_empresa">id_empresa <i class="fa-solid fa-sort"></i></th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody id="corpo-tabela-alerta">
                <%
                if (alertas != null && !alertas.isEmpty()) {
                for (Alerta a : alertas) {
                %>
                <tr data-id-alerta="<%= a.getIdAlerta() %>">
                    <td><%= a.getIdAlerta() %></td>
                    <td><%= a.getDescricao() %></td>
                    <td><%= a.getCodigo() %></td>
                    <td><%= a.getDtLimite() != null ? formatoExibicao.format(a.getDtLimite()) : "" %></td>
                    <td><%= a.getIdEmpresa() %></td>
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
                    <td colspan="6">Nenhum alerta encontrado.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <p class="rodape-tabela">
            Mostrando <%= alertas != null ? alertas.size() : 0 %> alertas
        </p>
    </section>

</main>

<!-- MODAL ADICIONAR -->
<div class="modal-fundo" id="modal-adicionar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Adicionar em alerta</h2>
            <button type="button" class="modal-fechar" id="fechar-modal">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/adicionar-alerta" method="post">
            <div class="modal-campo">
                <label for="campo-descricao">Descricao:</label>
                <input type="text" id="campo-descricao" name="descricao" required>
            </div>
            <div class="modal-campo">
                <label for="campo-codigo">Código:</label>
                <input type="number" id="campo-codigo" name="codigo" min="0" required>
            </div>
            <div class="modal-campo">
                <label for="campo-dt_limite">Data Limite:</label>
                <input type="date" id="campo-dt_limite" name="dt_limite" required>
            </div>
            <div class="modal-campo">
                <label for="campo-id_empresa">Empresa (id):</label>
                <input type="number" id="campo-id_empresa" name="id_empresa" min="0" required>
            </div>
            <div class="modal-rodape">
                <button type="button" class="botao-cancelar" id="cancelar-modal">Cancelar</button>
                <button type="submit" class="botao-salvar">Salvar</button>
            </div>
        </form>
    </div>
</div>

<!-- MODAL FILTRO -->
<div class="modal-fundo" id="modal-filtro" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Filtrar alertas</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-filtro">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/lista-alerta" method="get">
            <div class="modal-campo">
                <label for="filtro-codigo">Código</label>
                <input type="number" id="filtro-codigo" name="codigo" min="0" value="<%= fCodigo %>">
            </div>
            <div class="modal-campo">
                <label>Data Limite:</label>
                <div class="modal-campo-faixa">
                    <input type="date" name="dt_limite_min" value="<%= fDtMin %>">
                    <span>até</span>
                    <input type="date" name="dt_limite_max" value="<%= fDtMax %>">
                </div>
            </div>
            <div class="modal-campo">
                <label for="filtro-id_empresa">Empresa (id)</label>
                <input type="number" id="filtro-id_empresa" name="id_empresa" min="0" value="<%= fIdEmpresa %>">
            </div>
            <div class="modal-rodape">
                <a href="<%=caminho%>/lista-alerta" class="botao-cancelar">Limpar</a>
                <button type="submit" class="botao-salvar">Aplicar filtro</button>
            </div>
        </form>
    </div>
</div>

<!-- MODAL EDITAR -->
<div class="modal-fundo" id="modal-editar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Editar alerta</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-editar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/alterar-alerta" method="post">
            <input type="hidden" id="editar-id-alerta" name="id_alerta">
            <div class="modal-campo">
                <label for="editar-descricao">Descricao:</label>
                <input type="text" id="editar-descricao" name="descricao" required>
            </div>
            <div class="modal-campo">
                <label for="editar-codigo">Código:</label>
                <input type="number" id="editar-codigo" name="codigo" min="0" required>
            </div>
            <div class="modal-campo">
                <label for="editar-dt_limite">Data Limite:</label>
                <input type="date" id="editar-dt_limite" name="dt_limite" required>
            </div>
            <div class="modal-campo">
                <label for="editar-id_empresa">Empresa (id):</label>
                <input type="number" id="editar-id_empresa" name="id_empresa" min="0" required>
            </div>
            <div class="modal-rodape">
                <button type="button" class="botao-cancelar" id="cancelar-modal-editar">Cancelar</button>
                <button type="submit" class="botao-salvar">Salvar alterações</button>
            </div>
        </form>
    </div>
</div>

<!-- MODAL EXCLUIR -->
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
        <form action="<%=caminho%>/deletar-alerta" method="post">
            <input type="hidden" id="excluir-id" name="id_alerta">
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

    // dd-MM-yyyy (tabela) -> yyyy-MM-dd (input type="date")
    function converterDataBrParaIso(dataBr) {
        if (!dataBr) return '';
        if (dataBr.length === 10 && dataBr.charAt(4) === '-') return dataBr;
        const partes = dataBr.split('-');
        if (partes.length !== 3) return '';
        const [dia, mes, ano] = partes;
        return ano + '-' + mes.padStart(2, '0') + '-' + dia.padStart(2, '0');
    }

    // EDITAR — ordem: id, descricao, codigo, dt_limite, id_empresa
    const corpoTabela = document.getElementById('corpo-tabela-alerta');
    const modalEditar = document.getElementById('modal-editar');

    corpoTabela.addEventListener('click', function (evento) {
        const botao = evento.target.closest('.btn-editar');
        if (!botao) return;
        const c = botao.closest('tr').children;
        const t = i => c[i].textContent.trim();

        document.getElementById('editar-id-alerta').value = t(0);
        document.getElementById('editar-descricao').value = t(1);
        document.getElementById('editar-codigo').value = t(2);
        document.getElementById('editar-dt_limite').value = converterDataBrParaIso(t(3));
        document.getElementById('editar-id_empresa').value = t(4);
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
        document.getElementById('excluir-id').value = linha.dataset.idAlerta;
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

<!-- scripts externos (fora do bloco <script> principal) -->
<script src="<%=caminho%>/JS/create-alerta.js"></script>
<script src="<%=caminho%>/JS/update-alerta.js"></script>

</body>
</html>