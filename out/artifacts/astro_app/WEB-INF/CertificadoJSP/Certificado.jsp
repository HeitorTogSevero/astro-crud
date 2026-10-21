<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.ArrayList,
java.text.SimpleDateFormat,
org.astro.astro_app.model.Certificado"
%>
<%!private String v(String s) {
    return s == null ? "" : s;}%>
<%  String caminho = request.getContextPath();
    ArrayList<Certificado> certificados = (ArrayList<Certificado>) request.getAttribute("certificados");
    SimpleDateFormat formatoExibicao = new SimpleDateFormat("dd-MM-yyyy");
    //valores de filtro(pra nao ficar null
    String fDtMin = v(request.getParameter("dt_emissao_min"));
    String fDtMax = v(request.getParameter("dt_emissao_max"));
    String fIdFuncionario = v(request.getParameter("id_funcionario"));%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro - Certificados</title>

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
        <a href="<%=caminho%>/lista-alerta" class="nav-item">
            <i class="fa-regular fa-calendar-check"></i>alerta
        </a>
        <a href="<%=caminho%>/listar-certificados" class="nav-item active">
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
            <h1><strong>certificado - </strong> <span id="periodo-titulo">Setembro 2026</span></h1>
        </div>
    </header>

    <section class="barra-controles">
        <div class="controle-esquerda">
            <div class="search-bar">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" id="busca-certificado" name="busca-certificado" placeholder="Pesquisar certificado...">
            </div>
        </div>

        <div class="controle-direita">
            <button class="btn-filtrar" id="btn-filtrar-certificado" type="button">
                <i class="fa-solid fa-filter"></i> Filtrar
            </button>
            <button class="btn-adicionar" id="btn-adicionar-registro" type="button" data-acao="adicionar-registro">
                <i class="fa-solid fa-plus"></i> Adicionar certificado
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
            <table class="data-table" id="tabela-certificado">
                <thead class="data-cabecalho">
                <tr>
                    <th class="ordenavel" data-coluna="id_certificado"><strong>id_certificado</strong> <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="dt_emissao">dt_emissao <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="validade">validade <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_funcionario">id_funcionario <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_nrfuncionario">id_nrfuncionario <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_nrempresa">id_nrempresa <i class="fa-solid fa-sort"></i></th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody id="corpo-tabela-certificado">
                <%
                if (certificados != null && !certificados.isEmpty()) {
                for (Certificado c : certificados) {
                %>
                <tr data-id-certificado="<%= c.getIdCertificado() %>">
                    <td><%= c.getIdCertificado() %></td>
                    <td><%= c.getDtEmissao() != null ? formatoExibicao.format(c.getDtEmissao()) : "" %></td>
                    <td><%= c.getDtValidade() != null ? formatoExibicao.format(c.getDtValidade()) : "" %></td>
                    <td><%= c.getIdFuncionario() %></td>
                    <td><%= c.getIdNrFuncionario() %></td>
                    <td><%= c.getIdNrEmpresa() %></td>
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
                    <td colspan="7">Nenhum certificado encontrado.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <p class="rodape-tabela">
            Mostrando <%= certificados != null ? certificados.size() : 0 %> certificados
        </p>
    </section>

</main>

<!-- MODAL ADICIONAR -->
<div class="modal-fundo" id="modal-adicionar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true" aria-labelledby="modal-titulo">
        <div class="modal-cabecalho">
            <h2 id="modal-titulo">Adicionar em certificado</h2>
            <button type="button" class="modal-fechar" id="fechar-modal" aria-label="Fechar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-adicionar" class="modal-formulario"
              action="<%=caminho%>/adicionar-certificado" method="post">

            <div class="modal-campo">
                <label for="campo-dt_emissao">dt_emissao: </label>
                <input type="date" id="campo-dt_emissao" name="dt_emissao" required>
                <span id="erroDataEmissao"></span>
            </div>

            <div class="modal-campo">
                <label for="validade">validade: </label>
                <input type="date" id="validade" name="validade" required>
                <span id="erroValidade"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-id_funcionario">id_funcionario: </label>
                <input type="number" id="campo-id_funcionario" name="id_funcionario" placeholder="ID" min="0" required>
                <span id="erroIdFunc"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-id_nrfuncionario">id_nrfuncionario: </label>
                <input type="number" id="campo-id_nrfuncionario" name="id_nrfuncionario" placeholder="ID" min="0" required>
                <span id="erroIdNrFunc"></span>
            </div>

            <div class="modal-campo">
                <label for="campo-id_nrempresa">id_nrempresa: </label>
                <input type="number" id="campo-id_nrempresa" name="id_nrempresa" placeholder="ID" min="0" required>
                <span id="erroIdNrEmp"></span>
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
            <h2 id="modal-titulo-filtro">Filtrar certificados</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-filtro" aria-label="Fechar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-filtro" class="modal-formulario"
              action="<%=caminho%>/listar-certificados" method="get">

            <div class="modal-campo">
                <label>dt_emissao:</label>
                <div class="modal-campo-faixa">
                    <input type="date" id="filtro-dt-emissao-min" name="dt_emissao_min" value="<%= fDtMin %>">
                    <span>até</span>
                    <input type="date" id="filtro-dt-emissao-max" name="dt_emissao_max" value="<%= fDtMax %>">
                </div>
            </div>

            <div class="modal-campo">
                <label for="filtro-id_funcionario">id_funcionario</label>
                <input type="number" id="filtro-id_funcionario" name="id_funcionario" min="0" placeholder="0" value="<%= fIdFuncionario %>">
            </div>

            <div class="modal-rodape">
                <a href="<%=caminho%>/listar-certificados" class="botao-cancelar" id="limpar-filtro">Limpar</a>
                <button type="submit" class="botao-salvar">Aplicar filtro</button>
            </div>
        </form>
    </div>
</div>

<!-- MODAL EDITAR -->
<div class="modal-fundo" id="modal-editar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true" aria-labelledby="modal-titulo-editar">
        <div class="modal-cabecalho">
            <h2 id="modal-titulo-editar">Editar certificado</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-editar" aria-label="Fechar">
                <i class="fa-solid fa-xmark"></i>
            </button>
        </div>

        <form id="formulario-editar" class="modal-formulario"
              action="<%=caminho%>/alterar-certificado" method="post">

            <input type="hidden" id="editar-id-certificado" name="id_certificado">

            <div class="modal-campo">
                <label for="editar-dt_emissao">dt_emissao: </label>
                <input type="date" id="editar-dt_emissao" name="dt_emissao" required>
                <span id="editar-erroDataEmissao"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-validade">validade: </label>
                <input type="date" id="editar-validade" name="validade" required>
                <span id="editar-erroValidade"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-id_funcionario">id_funcionario: </label>
                <input type="number" id="editar-id_funcionario" name="id_funcionario" min="0" required>
                <span id="editar-erroIdFunc"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-id_nrfuncionario">id_nrfuncionario: </label>
                <input type="number" id="editar-id_nrfuncionario" name="id_nrfuncionario" min="0" required>
                <span id="editar-erroIdNrFunc"></span>
            </div>

            <div class="modal-campo">
                <label for="editar-id_nrempresa">id_nrempresa: </label>
                <input type="number" id="editar-id_nrempresa" name="id_nrempresa" min="0" required>
                <span id="editar-erroIdNrEmp"></span>
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

        <form id="formulario-excluir" action="<%=caminho%>/deletar-certificado" method="post">
            <input type="hidden" id="excluir-id" name="id_certificado">
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

    const corpoTabelaCertificado = document.getElementById('corpo-tabela-certificado');
    const modalEditar = document.getElementById('modal-editar');
    const botaoFecharEditar = document.getElementById('fechar-modal-editar');
    const botaoCancelarEditar = document.getElementById('cancelar-modal-editar');

    function abrirEditar() { modalEditar.hidden = false; }
    function fecharEditar() { modalEditar.hidden = true; }

    corpoTabelaCertificado.addEventListener('click', function (evento) {
        const botaoEditar = evento.target.closest('.btn-editar');
        if (!botaoEditar) return;

        const linha = botaoEditar.closest('tr');
        document.getElementById('editar-id-certificado').value = linha.children[0].textContent.trim();
        document.getElementById('editar-dt_emissao').value = converterDataBrParaIso(linha.children[1].textContent.trim());
        document.getElementById('editar-validade').value = converterDataBrParaIso(linha.children[2].textContent.trim());
        document.getElementById('editar-id_funcionario').value = linha.children[3].textContent.trim();
        document.getElementById('editar-id_nrfuncionario').value = linha.children[4].textContent.trim();
        document.getElementById('editar-id_nrempresa').value = linha.children[5].textContent.trim();
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

    corpoTabelaCertificado.addEventListener('click', function (evento) {
        const botaoRemover = evento.target.closest('.btn-remover');
        if (!botaoRemover) return;

        const linha = botaoRemover.closest('tr');
        document.getElementById('excluir-id').value = linha.dataset.idCertificado;
        document.getElementById('excluir-nome').textContent = linha.children[0].textContent.trim();
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

<script src="<%=caminho%>/JS/Create/create-certificado.js"></script>
<script src="<%=caminho%>/JS/Update/update-certificado.js"></script>

</body>
</html>