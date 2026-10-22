<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.ArrayList,
org.astro.astro_app.model.Funcionario"
%>
<%!
private String v(String s) {
return s == null ? "" : s;
}
%>
<%  String caminho = request.getContextPath();
    ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>) request.getAttribute("funcionarios");
    //valores de filtro(pra nao ficar null
    String fCargo = v(request.getParameter("cargo"));
    String fIdEmpresa = v(request.getParameter("id_empresa"));
    String fIdDepartamento = v(request.getParameter("id_departamento"));
    %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro - Funcionários</title>
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

        <a href="<%=caminho%>/listar-funcionarios" class="nav-item active">
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

        <a href="<%=caminho%>/listar-certificados" class="nav-item">
            <i class="fa-solid fa-award"></i>certificado
        </a>

        <div class="sidebar-bottom">
            <a href="<%=request.getContextPath()%>/lading-page.html" class="nav-item" id="exit-bottom"><i class="fa-solid fa-right-from-bracket"></i>Sair</a>
        </div>
    </nav>
</aside>

<main class="main-content">
    <header class="header cabecalho-pagina">
        <div class="titulo-container">
            <h1><strong>funcionario - </strong> <span id="periodo-titulo">Setembro 2026</span></h1>
        </div>
    </header>

    <section class="barra-controles">
        <div class="controle-esquerda">
            <div class="search-bar">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" id="busca-funcionario" placeholder="Pesquisar funcionário...">
            </div>
        </div>
        <div class="controle-direita">
            <button class="btn-filtrar" type="button"><i class="fa-solid fa-filter"></i> Filtrar</button>
            <button class="btn-adicionar" type="button"><i class="fa-solid fa-plus"></i> Adicionar funcionario</button>
        </div>
    </section>

    <%
    String mensagem = (String) request.getAttribute("erro");
    if (mensagem == null) mensagem = (String) request.getAttribute("mensagem");
    if (mensagem != null) {
    String cor = mensagem.toLowerCase().contains("sucesso") ? "green" : "red";
    %>
    <p style="color: <%= cor %>"><%= mensagem %></p>
    <% } %>

    <section class="panel painel-tabela">
        <div class="tabela-rolavel">
            <table class="data-table" id="tabela-funcionarios">
                <thead class="data-cabecalho">
                <tr>
                    <th class="ordenavel" data-coluna="id_funcionario"><strong>ID do Funcionário</strong> <i class="fa-solid fa-sort"></i></th>
                    <th data-coluna="cpf">CPF</th>
                    <th class="ordenavel" data-coluna="nome">Nome <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="cargo">Cargo <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="email">Email <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_empresa">Empresa <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="id_departamento">Departamento <i class="fa-solid fa-sort"></i></th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody id="corpo-tabela-funcionarios">
                <%
                if (funcionarios != null && !funcionarios.isEmpty()) {
                for (Funcionario f : funcionarios) {
                %>
                <tr data-id-funcionario="<%= f.getIdFuncionario() %>">
                    <td><%= f.getIdFuncionario() %></td>
                    <td><%= f.getCpf() %></td>
                    <td><%= f.getNome() %></td>
                    <td><%= f.getCargo() %></td>
                    <td><%= f.getEmail() %></td>
                    <td><%= f.getIdEmpresa() %></td>
                    <td><%= f.getIdDepartamento() %></td>
                    <td>
                        <div class="celula-acoes">
                            <button class="btn-editar" type="button"><i class="fa-solid fa-pen"></i> Editar</button>
                            <button class="btn-remover" type="button"><i class="fa-solid fa-trash"></i></button>
                        </div>
                    </td>
                </tr>
                <%
                }
                } else {
                %>
                <tr>
                    <td colspan="8">Nenhum funcionário encontrado.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <p class="rodape-tabela">
            Mostrando <%= funcionarios != null ? funcionarios.size() : 0 %> funcionários
        </p>
    </section>
</main>

<!-- MODAL ADICIONAR -->
<div class="modal-fundo" id="modal-adicionar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Adicionar em funcionario</h2>
            <button type="button" class="modal-fechar" id="fechar-modal"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/adicionar-funcionario" method="post">
            <div class="modal-campo">
                <label for="campo-cpf">cpf</label>
                <input type="text" id="campo-cpf" name="cpf" placeholder="000.000.000-00" required>
            </div>
            <div class="modal-campo">
                <label for="campo-nome">nome</label>
                <input type="text" id="campo-nome" name="nome" required>
            </div>
            <div class="modal-campo">
                <label for="campo-cargo">cargo</label>
                <input type="text" id="campo-cargo" name="cargo" required>
            </div>
            <div class="modal-campo">
                <label for="campo-email">email</label>
                <input type="text" id="campo-email" name="email" required>
            </div>
            <div class="modal-campo">
                <label for="campo-id_empresa">id_empresa</label>
                <input type="number" id="campo-id_empresa" name="id_empresa" min="0" required>
            </div>
            <div class="modal-campo">
                <label for="campo-id_departamento">id_departamento</label>
                <input type="number" id="campo-id_departamento" name="id_departamento" min="0" required>
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
            <h2>Filtrar funcionários</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-filtro"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/listar-funcionarios" method="get">
            <div class="modal-campo">
                <label for="filtro-cargo">Cargo</label>
                <input type="text" id="filtro-cargo" name="cargo" value="<%= fCargo %>">
            </div>
            <div class="modal-campo">
                <label for="filtro-id_empresa">id_empresa</label>
                <input type="number" id="filtro-id_empresa" name="id_empresa" min="0" value="<%= fIdEmpresa %>">
            </div>
            <div class="modal-campo">
                <label for="filtro-id_departamento">id_departamento</label>
                <input type="number" id="filtro-id_departamento" name="id_departamento" min="0" value="<%= fIdDepartamento %>">
            </div>
            <div class="modal-rodape">
                <a href="<%=caminho%>/listar-funcionarios" class="botao-cancelar">Limpar</a>
                <button type="submit" class="botao-salvar">Aplicar filtro</button>
            </div>
        </form>
    </div>
</div>

<!-- MODAL EDITAR -->
<div class="modal-fundo" id="modal-editar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Editar funcionário</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-editar"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/alterar-funcionario" method="post">
            <input type="hidden" id="editar-id-funcionario" name="id_funcionario">
            <div class="modal-campo">
                <label for="editar-cpf">cpf</label>
                <input type="text" id="editar-cpf" name="cpf" placeholder="000.000.000-00" required>
            </div>
            <div class="modal-campo">
                <label for="editar-nome">nome</label>
                <input type="text" id="editar-nome" name="nome" required>
            </div>
            <div class="modal-campo">
                <label for="editar-cargo">cargo</label>
                <input type="text" id="editar-cargo" name="cargo" required>
            </div>
            <div class="modal-campo">
                <label for="editar-email">email</label>
                <input type="text" id="editar-email" name="email" required>
            </div>
            <div class="modal-campo">
                <label for="editar-id_empresa">id_empresa</label>
                <input type="number" id="editar-id_empresa" name="id_empresa" min="0" required>
            </div>
            <div class="modal-campo">
                <label for="editar-id_departamento">id_departamento</label>
                <input type="number" id="editar-id_departamento" name="id_departamento" min="0" required>
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
            <button type="button" class="modal-fechar" id="fechar-modal-excluir"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <p class="modal-texto-confirmacao">
            Tem certeza que deseja excluir
            <strong id="excluir-nome"></strong>?
            Essa ação não pode ser desfeita.
        </p>
        <form action="<%=caminho%>/deletar-funcionario" method="post">
            <input type="hidden" id="excluir-id" name="id_funcionario">
            <div class="modal-rodape">
                <button type="button" class="botao-cancelar" id="cancelar-modal-excluir">Cancelar</button>
                <button type="submit" class="botao-excluir">Excluir</button>
            </div>
        </form>
    </div>
</div>

<script>
    const modalFiltro = document.getElementById('modal-filtro');
    document.querySelector('.btn-filtrar').addEventListener('click', () => modalFiltro.hidden = false);
    document.getElementById('fechar-modal-filtro').addEventListener('click', () => modalFiltro.hidden = true);
    modalFiltro.addEventListener('click', e => { if (e.target === modalFiltro) modalFiltro.hidden = true; });

    const corpoTabela = document.getElementById('corpo-tabela-funcionarios');
    const modalEditar = document.getElementById('modal-editar');

    corpoTabela.addEventListener('click', function (evento) {
        const botao = evento.target.closest('.btn-editar');
        if (!botao) return;
        const c = botao.closest('tr').children;
        const t = i => c[i].textContent.trim();

        document.getElementById('editar-id-funcionario').value = t(0);
        document.getElementById('editar-cpf').value = t(1);
        document.getElementById('editar-nome').value = t(2);
        document.getElementById('editar-cargo').value = t(3);
        document.getElementById('editar-email').value = t(4);
        document.getElementById('editar-id_empresa').value = t(5);
        document.getElementById('editar-id_departamento').value = t(6);
        modalEditar.hidden = false;
    });

    document.getElementById('fechar-modal-editar').addEventListener('click', () => modalEditar.hidden = true);
    document.getElementById('cancelar-modal-editar').addEventListener('click', () => modalEditar.hidden = true);
    modalEditar.addEventListener('click', e => { if (e.target === modalEditar) modalEditar.hidden = true; });

    const modalExcluir = document.getElementById('modal-excluir');
    corpoTabela.addEventListener('click', function (evento) {
        const botao = evento.target.closest('.btn-remover');
        if (!botao) return;
        const linha = botao.closest('tr');
        document.getElementById('excluir-id').value = linha.dataset.idFuncionario;
        document.getElementById('excluir-nome').textContent = linha.children[2].textContent.trim();
        modalExcluir.hidden = false;
    });
    document.getElementById('fechar-modal-excluir').addEventListener('click', () => modalExcluir.hidden = true);
    document.getElementById('cancelar-modal-excluir').addEventListener('click', () => modalExcluir.hidden = true);
    modalExcluir.addEventListener('click', e => { if (e.target === modalExcluir) modalExcluir.hidden = true; });

    const modalAdicionar = document.getElementById('modal-adicionar');
    document.querySelector('.btn-adicionar').addEventListener('click', () => modalAdicionar.hidden = false);
    document.getElementById('fechar-modal').addEventListener('click', () => modalAdicionar.hidden = true);
    document.getElementById('cancelar-modal').addEventListener('click', () => modalAdicionar.hidden = true);
    modalAdicionar.addEventListener('click', e => { if (e.target === modalAdicionar) modalAdicionar.hidden = true; });
</script>
</body>
</html>