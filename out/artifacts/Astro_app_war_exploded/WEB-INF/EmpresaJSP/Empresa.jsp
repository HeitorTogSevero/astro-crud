<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.ArrayList,
java.text.SimpleDateFormat,
org.astro.astro_app.model.Empresa"
%>
<%!
private String v(String s) {
return s == null ? "" : s;
}
%>
<%  String caminho = request.getContextPath();
    ArrayList<Empresa> empresas = (ArrayList<Empresa>) request.getAttribute("empresas");
    //formato  dd-MM-yyyy
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    String[] estados = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
    //valores de filtro(pra nao ficar null
    String fEstado = v(request.getParameter("estado"));
    String fCidade = v(request.getParameter("cidade"));
    String fCnae   = v(request.getParameter("cnae"));
    String fNome   = v(request.getParameter("nome"));
    String fDtMin  = v(request.getParameter("dt_registro_min"));
    String fDtMax  = v(request.getParameter("dt_registro_max"));
    %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Astro - Empresas</title>
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
        <a href="<%=caminho%>/crud.jsp" class="nav-item"><i class="fa-solid fa-house"></i>Início</a>
        <a href="<%=caminho%>/listar-empresas" class="nav-item active"><i class="fa-regular fa-building"></i>empresa</a>
        <a href="<%=caminho%>/listar-funcionarios" class="nav-item"><i class="fa-solid fa-users"></i>funcionario</a>
        <a href="<%=caminho%>/listar-departamentos" class="nav-item"><i class="fa-solid fa-layer-group"></i>departamento</a>
        <a href="<%=caminho%>/listar-divisoes" class="nav-item"><i class="fa-regular fa-file-lines"></i>divisao_treinamento</a>
        <a href="<%=caminho%>/lista-alerta" class="nav-item"><i class="fa-regular fa-calendar-check"></i>alerta</a>
        <a href="<%=caminho%>/listar-certificados" class="nav-item"><i class="fa-solid fa-award"></i>certificado</a>
        <div class="sidebar-bottom">
            <a href="#" class="nav-item" id="exit-bottom"><i class="fa-solid fa-right-from-bracket"></i>Sair</a>
        </div>
    </nav>
</aside>

<main class="main-content">
    <header class="header cabecalho-pagina">
        <div class="titulo-container">
            <h1><strong>empresa - </strong> <span id="periodo-titulo">Setembro 2026</span></h1>
        </div>
    </header>

    <section class="barra-controles">
        <div class="controle-esquerda">
            <div class="search-bar">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" id="busca-empresa" placeholder="Pesquisar empresa...">
            </div>
        </div>
        <div class="controle-direita">
            <button class="btn-filtrar" type="button"><i class="fa-solid fa-filter"></i> Filtrar</button>
            <button class="btn-adicionar" type="button"><i class="fa-solid fa-plus"></i> Adicionar empresa</button>
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
            <table class="data-table" id="tabela-empresas">
                <thead class="data-cabecalho">
                <tr>
                    <th class="ordenavel" data-coluna="id_empresa"><strong>id_empresa</strong> <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="nome">nome <i class="fa-solid fa-sort"></i></th>
                    <th data-coluna="cnpj">cnpj</th>
                    <th data-coluna="cnae">cnae</th>
                    <th class="ordenavel" data-coluna="bairro">bairro <i class="fa-solid fa-sort"></i></th>
                    <th data-coluna="cep">cep</th>
                    <th class="ordenavel" data-coluna="rua">rua <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="estado">estado <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="cidade">cidade <i class="fa-solid fa-sort"></i></th>
                    <th class="ordenavel" data-coluna="dt_registro">dt_registro <i class="fa-solid fa-sort"></i></th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody id="corpo-tabela-empresas">
                <%
                if (empresas != null && !empresas.isEmpty()) {
                for (Empresa e : empresas) {
                %>
                <tr data-id-empresa="<%= e.getIdEmpresa() %>">
                    <td><%= e.getIdEmpresa() %></td>
                    <td><%= e.getNome() %></td>
                    <td><%= e.getCnpj() %></td>
                    <td><%= e.getCnae() %></td>
                    <td><%= e.getBairro() %></td>
                    <td><%= e.getCep() %></td>
                    <td><%= e.getRua() %></td>
                    <td><%= e.getEstado() %></td>
                    <td><%= e.getCidade() %></td>
                    <td><%= e.getDtRegistro() != null ? formato.format(e.getDtRegistro()) : "" %></td>
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
                <tr><td colspan="11">Nenhuma empresa encontrada.</td></tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <p class="rodape-tabela">Mostrando <%= empresas != null ? empresas.size() : 0 %> empresas</p>
    </section>
</main>

<!-- MODAL ADICIONAR -->
<div class="modal-fundo" id="modal-adicionar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Adicionar em empresa</h2>
            <button type="button" class="modal-fechar" id="fechar-modal"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/adicionar-empresa" method="post">
            <div class="modal-campo">
                <label for="campo-cnpj">cnpj</label>
                <input type="text" id="campo-cnpj" name="cnpj" placeholder="00.000.000/0000-00" required>
            </div>
            <div class="modal-campo">
                <label for="campo-nome">nome</label>
                <input type="text" id="campo-nome" name="nome" required>
            </div>
            <div class="modal-campo">
                <label for="campo-cnae">cnae</label>
                <input type="text" id="campo-cnae" name="cnae" required>
            </div>
            <div class="modal-campo">
                <label for="campo-bairro">bairro</label>
                <input type="text" id="campo-bairro" name="bairro" required>
            </div>
            <div class="modal-campo">
                <label for="campo-cep">cep</label>
                <input type="text" id="campo-cep" name="cep" placeholder="00000-000" required>
            </div>
            <div class="modal-campo">
                <label for="campo-rua">rua</label>
                <input type="text" id="campo-rua" name="rua" required>
            </div>
            <div class="modal-campo">
                <label for="campo-estado">estado</label>
                <input type="text" id="campo-estado" name="estado" maxlength="2" required>
            </div>
            <div class="modal-campo">
                <label for="campo-cidade">cidade</label>
                <input type="text" id="campo-cidade" name="cidade" required>
            </div>
            <div class="modal-campo">
                <label for="campo-dt_registro">dt_registro</label>
                <input type="date" id="campo-dt_registro" name="dt_registro" required>
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
            <h2>Filtrar empresas</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-filtro"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/listar-empresas" method="get">
            <div class="modal-campo">
                <label for="filtro-estado">estado</label>
                <select id="filtro-estado" name="estado">
                    <option value="" <%= fEstado.isEmpty() ? "selected" : "" %>>Todos</option>
                    <% for (String uf : estados) { %>
                    <option value="<%= uf %>" <%= uf.equals(fEstado) ? "selected" : "" %>><%= uf %></option>
                    <% } %>
                </select>
            </div>
            <div class="modal-campo">
                <label for="filtro-cidade">cidade</label>
                <input type="text" id="filtro-cidade" name="cidade" value="<%= fCidade %>">
            </div>
            <div class="modal-campo">
                <label for="filtro-cnae">cnae</label>
                <input type="text" id="filtro-cnae" name="cnae" value="<%= fCnae %>">
            </div>
            <div class="modal-campo">
                <label for="filtro-nome">Nome</label>
                <input type="text" id="filtro-nome" name="nome" value="<%= fNome %>">
            </div>
            <div class="modal-campo">
                <label>dt_registro</label>
                <div class="modal-campo-faixa">
                    <input type="date" name="dt_registro_min" value="<%= fDtMin %>">
                    <span>até</span>
                    <input type="date" name="dt_registro_max" value="<%= fDtMax %>">
                </div>
            </div>
            <div class="modal-rodape">
                <a href="<%=caminho%>/listar-empresas" class="botao-cancelar">Limpar</a>
                <button type="submit" class="botao-salvar">Aplicar filtro</button>
            </div>
        </form>
    </div>
</div>

<!-- MODAL EDITAR -->
<div class="modal-fundo" id="modal-editar" hidden>
    <div class="modal-caixa" role="dialog" aria-modal="true">
        <div class="modal-cabecalho">
            <h2>Editar empresa</h2>
            <button type="button" class="modal-fechar" id="fechar-modal-editar"><i class="fa-solid fa-xmark"></i></button>
        </div>
        <form class="modal-formulario" action="<%=caminho%>/editar-empresa" method="post">
            <input type="hidden" id="editar-id-empresa" name="id_empresa">
            <div class="modal-campo">
                <label for="editar-nome">nome</label>
                <input type="text" id="editar-nome" name="nome" required>
            </div>
            <div class="modal-campo">
                <label for="editar-cnpj">cnpj</label>
                <input type="text" id="editar-cnpj" name="cnpj" required>
            </div>
            <div class="modal-campo">
                <label for="editar-cnae">cnae</label>
                <input type="text" id="editar-cnae" name="cnae" required>
            </div>
            <div class="modal-campo">
                <label for="editar-bairro">bairro</label>
                <input type="text" id="editar-bairro" name="bairro" required>
            </div>
            <div class="modal-campo">
                <label for="editar-cep">cep</label>
                <input type="text" id="editar-cep" name="cep" required>
            </div>
            <div class="modal-campo">
                <label for="editar-rua">rua</label>
                <input type="text" id="editar-rua" name="rua" required>
            </div>
            <div class="modal-campo">
                <label for="editar-estado">estado</label>
                <input type="text" id="editar-estado" name="estado" maxlength="2" required>
            </div>
            <div class="modal-campo">
                <label for="editar-cidade">cidade</label>
                <input type="text" id="editar-cidade" name="cidade" required>
            </div>
            <div class="modal-campo">
                <label for="editar-dt-registro">dt_registro</label>
                <input type="date" id="editar-dt-registro" name="dt_registro" required>
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
            Tem certeza que deseja excluir <strong id="excluir-nome"></strong>?
            Essa ação não pode ser desfeita.
        </p>
        <form action="<%=caminho%>/deletar-empresa" method="post">
            <input type="hidden" id="excluir-id" name="id_empresa">
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

    function converterDataBrParaIso(dataBr) {
        if (!dataBr) return '';
        if (dataBr.length === 10 && dataBr.charAt(4) === '-') return dataBr;
        const partes = dataBr.split('-');
        if (partes.length !== 3) return '';
        const [dia, mes, ano] = partes;
        return ano + '-' + mes.padStart(2, '0') + '-' + dia.padStart(2, '0');
    }

    // ordem das colunas: id, nome, cnpj, cnae, bairro, cep, rua, estado, cidade, dt_registro
    const corpoTabela = document.getElementById('corpo-tabela-empresas');
    const modalEditar = document.getElementById('modal-editar');

    corpoTabela.addEventListener('click', function (evento) {
        const botao = evento.target.closest('.btn-editar');
        if (!botao) return;
        const c = botao.closest('tr').children;
        const t = i => c[i].textContent.trim();

        document.getElementById('editar-id-empresa').value = t(0);
        document.getElementById('editar-nome').value = t(1);
        document.getElementById('editar-cnpj').value = t(2);
        document.getElementById('editar-cnae').value = t(3);
        document.getElementById('editar-bairro').value = t(4);
        document.getElementById('editar-cep').value = t(5);
        document.getElementById('editar-rua').value = t(6);
        document.getElementById('editar-estado').value = t(7);
        document.getElementById('editar-cidade').value = t(8);
        document.getElementById('editar-dt-registro').value = converterDataBrParaIso(t(9));
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
        document.getElementById('excluir-id').value = linha.dataset.idEmpresa;
        document.getElementById('excluir-nome').textContent = linha.children[1].textContent.trim();
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