<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.ArrayList,
java.text.SimpleDateFormat,
org.astro.astro_app.model.Alerta"
%>
<%!
    //metodo auxiliar
    private String v(String s) {
        return s == null ? "" : s;
    }
%>
<%  String caminho = request.getContextPath();
    ArrayList<Alerta> alertas = (ArrayList<Alerta>) request.getAttribute("alertas");
    SimpleDateFormat formatoExibicao = new SimpleDateFormat("dd-MM-yyyy");
    //valores de filtro(pra nao ficar null
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
        <a href="<%=caminho%>/tela-inicial" class="nav-item">
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
        <a href="<%=caminho%>/listar-alerta" class="nav-item active">
            <i class="fa-regular fa-calendar-check"></i>alerta
        </a>
        <a href="<%=caminho%>/listar-certificados" class="nav-item">
            <i class="fa-solid fa-award"></i>certificado
        </a>
        <div class="sidebar-bottom">
            <a href="<%=request.getContextPath()%>/landing-page.html" class="nav-item" id="exit-bottom">
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
                <input type="text" id="busca-alerta" name="busca-alerta" placeholder="Pesquisar alerta...">
            </div>
        </div>

        <div class="controle-direita">
            <button class="btn-filtrar" id="btn-filtrar-alerta" type="button">
                <i class="fa-solid fa-filter"></i> Filtrar
            </button>
            <button class="btn-adicionar" id="btn-adicionar-registro" type="button" data-acao="adicionar-registro">
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
                    <th class="ordenavel" data-coluna="id_alerta"><strong>ID do Alerta</strong> <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="descricao">Descrição <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="codigo">Código <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="dt_limite">Data Limite<i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_empresa">Empresa<i class="fa-solid fa-sort"></i></th>
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
                            <button class="btn-editar" type="button" data-acao="editar">
                                <i class="fa-solid fa-pen"></i> Editar
                            </button>
                            <button class="btn-remover" type="button" data-acao="remover">
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
    <div class="modal-caixa" role="dialog" aria-modal="true" aria-labelledby="modal-titulo">
        <div class="modal-cabecalho">
            <h2 id="modal-titulo">Adicionar em alerta</h2>
            <button type="button" class="modal-fechar" id="fechar-modal" aria-label="Fechar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-adicionar" class="modal-formulario"
              action="<%=caminho%>/adicionar-alerta" method="post">

            <div class="modal-campo">
                <label for="campo-descricao">Descricao: </label>
                <input type="text" id="campo-descricao" name="descricao" placeholder="Nome" required>
                <span id="erroDescr"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-codigo">Código:</label>
                <input type="number" id="campo-codigo" name="codigo" placeholder="ID" required>
                <span id="erroCod"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-dt_limite">Data Limite:</label>
                <input type="date" id="campo-dt_limite" name="dt_limite" required>
                <span id="erroData"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-id_empresa">Empresa (id): </label>
                <input type="number" id="campo-id_empresa" name="id_empresa" placeholder="ID" required>
                <span id="erroIdEmpresa"></span>
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
    <div class="modal-caixa" role="dialog" aria-modal="true" aria-labelledby="modal-titulo-filtro">
        <div class="modal-cabecalho">
            <h2 id="modal-titulo-filtro">Filtrar alertas</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-filtro" aria-label="Fechar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-filtro" class="modal-formulario"
              action="<%=caminho%>/lista-alerta" method="get">

            <div class="modal-campo">
                <label for="filtro-codigo">Código</label>
                <input type="number" id="filtro-codigo" name="codigo" min="0" placeholder="0" value="<%= fCodigo %>">
            </div>

            <div class="modal-campo">
                <label>Data Limite:</label>
                <div class="modal-campo-faixa">
                    <input type="date" id="filtro-dt-limite-min" name="dt_limite_min" value="<%= fDtMin %>">
                    <span>até</span>
                    <input type="date" id="filtro-dt-limite-max" name="dt_limite_max" value="<%= fDtMax %>">
                </div>
            </div>

            <div class="modal-campo">
                <label for="filtro-id_empresa">Empresa (id)</label>
                <input type="number" id="filtro-id_empresa" name="id_empresa" min="0" placeholder="0" value="<%= fIdEmpresa %>">
            </div>

            <div class="modal-rodape">
                <a href="<%=caminho%>/lista-alerta" class="botao-cancelar" id="limpar-filtro">Limpar</a>
                <button type="submit" class="botao-salvar">Aplicar filtro</button>
            </div>
        </form>
    </div>
</div>

<!-- MODAL EDITAR -->
<div class="modal-fundo" id="modal-editar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true" aria-labelledby="modal-titulo-editar">
        <div class="modal-cabecalho">
            <h2 id="modal-titulo-editar">Editar alerta</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-editar" aria-label="Fechar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-editar" class="modal-formulario"
              action="<%=caminho%>/alterar-alerta" method="post">

            <input type="hidden" id="editar-id-alerta" name="id_alerta">

            <div class="modal-campo">
                <label for="editar-descricao">Descricao: </label>
                <input type="text" id="editar-descricao" name="descricao" required>
                <span id="editar-erroDescr"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-codigo">Código: </label>
                <input type="number" id="editar-codigo" name="codigo" min="0" required>
                <span id="editar-erroCod"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-dt_limite">Data Limite: </label>
                <input type="date" id="editar-dt_limite" name="dt_limite" required>
                <span id="editar-erroData"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-id-empresa">Empresa (id): </label>
                <input type="number" id="editar-id-empresa" name="id_empresa" min="0" required>
                <span id="editar-erroId"></span>
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
    <div class="modal-caixa modal-caixa-pequena" role="dialog" aria-modal="true" aria-labelledby="modal-titulo-excluir">
        <div class="modal-cabecalho">
            <h2 id="modal-titulo-excluir">Excluir registro</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-excluir" aria-label="Fechar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <p class="modal-texto-confirmacao">
            Tem certeza que deseja excluir
            <strong id="excluir-nome"></strong>?
            Essa ação não pode ser desfeita.
        </p>

        <form id="formulario-excluir" action="<%=caminho%>/deletar-alerta" method="post">
            <input type="hidden" id="excluir-id" name="id_alerta">
            <div class="modal-rodape">
                <button type="button" class="botao-cancelar" id="cancelar-modal-excluir">Cancelar</button>
                <button type="submit" class="botao-excluir">Excluir</button>
            </div>
        </form>
    </div>
</div>

<script>
    const botaoAbrirFiltro = document.querySelector('.btn-filtrar');
    const modalFiltro = document.getElementById('modal-filtro');
    const botaoFecharFiltro = document.getElementById('fechar-modal-filtro');

    function abrirFiltro() { modalFiltro.hidden = false; }
    function fecharFiltro() { modalFiltro.hidden = true; }

    botaoAbrirFiltro.addEventListener('click', abrirFiltro);
    botaoFecharFiltro.addEventListener('click', fecharFiltro);
    modalFiltro.addEventListener('click', function (evento) {
        if (evento.target === modalFiltro) fecharFiltro();
    });

    const cabecalhosOrdenaveis = document.querySelectorAll('.ordenavel');
    cabecalhosOrdenaveis.forEach(function (cabecalho) {
        cabecalho.addEventListener('click', function () {
            const direcaoAtual = cabecalho.dataset.direcao || 'nenhuma';
            let novaDirecao;
            if (direcaoAtual === 'nenhuma') novaDirecao = 'asc';
            else if (direcaoAtual === 'asc') novaDirecao = 'desc';
            else novaDirecao = 'nenhuma';

            cabecalhosOrdenaveis.forEach(function (outro) {
                if (outro !== cabecalho) {
                    delete outro.dataset.direcao;
                    outro.querySelector('i').className = 'fa-solid fa-sort';
                }
            });

            if (novaDirecao === 'nenhuma') {
                delete cabecalho.dataset.direcao;
                cabecalho.querySelector('i').className = 'fa-solid fa-sort';
            } else {
                cabecalho.dataset.direcao = novaDirecao;
                cabecalho.querySelector('i').className =
                    novaDirecao === 'asc' ? 'fa-solid fa-sort-up' : 'fa-solid fa-sort-down';
            }
        });
    });

    function converterDataBrParaIso(dataBr) {
        if (!dataBr) return '';
        if (dataBr.length === 10 && dataBr.charAt(4) === '-') return dataBr;
        const partes = dataBr.split('-');
        if (partes.length !== 3) return '';
        const [dia, mes, ano] = partes;
        return ano + '-' + mes.padStart(2, '0') + '-' + dia.padStart(2, '0');
    }

    const corpoTabelaAlerta = document.getElementById('corpo-tabela-alerta');
    const modalEditar = document.getElementById('modal-editar');
    const botaoFecharEditar = document.getElementById('fechar-modal-editar');
    const botaoCancelarEditar = document.getElementById('cancelar-modal-editar');

    function abrirEditar() { modalEditar.hidden = false; }
    function fecharEditar() { modalEditar.hidden = true; }

    corpoTabelaAlerta.addEventListener('click', function (evento) {
        const botaoEditar = evento.target.closest('.btn-editar');
        if (!botaoEditar) return;

        const linha = botaoEditar.closest('tr');
        document.getElementById('editar-id-alerta').value = linha.children[0].textContent.trim();
        document.getElementById('editar-descricao').value = linha.children[1].textContent.trim();
        document.getElementById('editar-codigo').value = linha.children[2].textContent.trim();
        document.getElementById('editar-dt_limite').value = converterDataBrParaIso(linha.children[3].textContent.trim());
        document.getElementById('editar-id_empresa').value = linha.children[4].textContent.trim();
        abrirEditar();
    });

    botaoFecharEditar.addEventListener('click', fecharEditar);
    botaoCancelarEditar.addEventListener('click', fecharEditar);
    modalEditar.addEventListener('click', function (evento) {
        if (evento.target === modalEditar) fecharEditar();
    });

    const modalExcluir = document.getElementById('modal-excluir');
    const botaoFecharExcluir = document.getElementById('fechar-modal-excluir');
    const botaoCancelarExcluir = document.getElementById('cancelar-modal-excluir');

    function abrirExcluir() { modalExcluir.hidden = false; }
    function fecharExcluir() { modalExcluir.hidden = true; }

    corpoTabelaAlerta.addEventListener('click', function (evento) {
        const botaoRemover = evento.target.closest('.btn-remover');
        if (!botaoRemover) return;

        const linha = botaoRemover.closest('tr');
        document.getElementById('excluir-id').value = linha.dataset.idAlerta;
        document.getElementById('excluir-nome').textContent = linha.children[1].textContent.trim();
        abrirExcluir();
    });

    botaoFecharExcluir.addEventListener('click', fecharExcluir);
    botaoCancelarExcluir.addEventListener('click', fecharExcluir);
    modalExcluir.addEventListener('click', function (evento) {
        if (evento.target === modalExcluir) fecharExcluir();
    });

    const botaoAbrirModal = document.querySelector('.btn-adicionar');
    const modal = document.getElementById('modal-adicionar');
    const botaoFecharModal = document.getElementById('fechar-modal');
    const botaoCancelarModal = document.getElementById('cancelar-modal');

    function abrirModal() { modal.hidden = false; }
    function fecharModal() { modal.hidden = true; }

    botaoAbrirModal.addEventListener('click', abrirModal);
    botaoFecharModal.addEventListener('click', fecharModal);
    botaoCancelarModal.addEventListener('click', fecharModal);
    modal.addEventListener('click', function (evento) {
        if (evento.target === modal) fecharModal();
    });
</script>

<script src="<%=caminho%>/JS/Create/create-alerta.js"></script>
<script src="<%=caminho%>/JS/Update/update-alerta.js"></script>

</body>
</html>