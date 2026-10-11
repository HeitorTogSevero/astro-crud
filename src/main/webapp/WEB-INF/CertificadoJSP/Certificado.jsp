<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List,
java.util.ArrayList,
java.text.SimpleDateFormat,
org.astro.astro_app.model.Certificado"%>
<%! private String v(String s) {
return s == null ? "" : s;
}

//escapa < > " & ' (evita que um texto digitado na URL vire codigo na pagina)
private String h(String s) {
if (s == null) return "";
return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
.replace("\"", "&quot;").replace("'", "&#39;");
}%>
<%  String caminho = request.getContextPath();
ArrayList<Certificado> certificados = (ArrayList<Certificado>) request.getAttribute("certificados");
    //formato  dd-MM-yyyy
    SimpleDateFormat formatoExibicao = new SimpleDateFormat("dd-MM-yyyy");
    //valores de filtro(pra nao ficar null
    String fDtEmissaoMin = v(request.getParameter("dt_emissao_min"));
    String fDtEmissaoMax = v(request.getParameter("dt_emissao_max"));
    String fIdFuncionario = v(request.getParameter("id_funcionario")); %>
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
            <a href="<%=caminho%>/lista-alerta" class="nav-item">
                <i class="fa-regular fa-calendar-check"></i>alerta
            </a>
            <a href="<%=caminho%>/listar-certificados" class="nav-item active">
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
                <button class="btn-adicionar" id="btn-adicionar-certificado" type="button">
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
        <p style="color: <%= cor %>"><%= h(mensagem) %></p>
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
                        <!-- Mostra dd-MM-yyyy na tabela -->
                        <td><%= c.getDtEmissao() != null ? formatoExibicao.format(c.getDtEmissao()) : "" %></td>
                        <td><%= c.getDtValidade() != null ? formatoExibicao.format(c.getDtValidade()) : "" %></td>
                        <td><%= c.getIdFuncionario() %></td>
                        <td><%= c.getIdNrFuncionario() %></td>
                        <td><%= c.getIdNrEmpresa() %></td>
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
                        <td colspan="7">Nenhum certificado encontrado.</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <p class="rodape-tabela">Mostrando <%= certificados != null ? certificados.size() : 0 %> certificados</p>
        </section>

    </main>

    <!-- MODAL ADICIONAR -->
    <div class="modal-fundo" id="modal-adicionar" hidden>
        <div class="modal-caixa" role="dialog" aria-modal="true">
            <div class="modal-cabecalho">
                <h2>Adicionar em certificado</h2>
                <button type="button" class="modal-fechar" id="fechar-modal"><i class="fa-solid fa-xmark"></i></button>
            </div>

            <form id="formulario-adicionar" class="modal-formulario"
                  action="<%=caminho%>/adicionar-certificado" method="post">

                <div class="modal-campo">
                    <label for="campo-dt_emissao">Data de emissão:</label>
                    <!-- type="date" sempre trabalha com aaaa-mm-dd -->
                    <input type="date" id="campo-dt_emissao" name="dt_emissao" required>
                </div>
                <div class="modal-campo">
                    <label for="campo-validade">Validade:</label>
                    <input type="date" id="campo-validade" name="validade" required>
                </div>
                <div class="modal-campo">
                    <label for="campo-id_funcionario">Funcionário (id):</label>
                    <input type="number" id="campo-id_funcionario" name="id_funcionario" min="1" required>
                </div>
                <div class="modal-campo">
                    <label for="campo-id_nrfuncionario">NR do funcionário (id):</label>
                    <input type="number" id="campo-id_nrfuncionario" name="id_nrfuncionario" min="1" required>
                </div>
                <div class="modal-campo">
                    <label for="campo-id_nrempresa">NR da empresa (id):</label>
                    <input type="number" id="campo-id_nrempresa" name="id_nrempresa" min="1" required>
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
                <h2>Filtrar certificados</h2>
                <button type="button" class="modal-fechar" id="fechar-modal-filtro"><i class="fa-solid fa-xmark"></i></button>
            </div>

            <form id="formulario-filtro" class="modal-formulario"
                  action="<%=caminho%>/listar-certificados" method="get">

                <div class="modal-campo">
                    <label>Data de emissão:</label>
                    <div class="modal-campo-faixa">
                        <input type="date" name="dt_emissao_min" value="<%= h(fDtEmissaoMin) %>">
                        <span>até</span>
                        <input type="date" name="dt_emissao_max" value="<%= h(fDtEmissaoMax) %>">
                    </div>
                </div>
                <div class="modal-campo">
                    <label for="filtro-id_funcionario">Funcionário (id)</label>
                    <input type="number" id="filtro-id_funcionario" name="id_funcionario" min="0" value="<%= h(fIdFuncionario) %>">
                </div>

                <div class="modal-rodape">
                    <a href="<%=caminho%>/listar-certificados" class="botao-cancelar">Limpar</a>
                    <button type="submit" class="botao-salvar">Aplicar filtro</button>
                </div>
            </form>
        </div>
    </div>

    <!-- MODAL EDITAR -->
    <div class="modal-fundo" id="modal-editar" hidden>
        <div class="modal-caixa" role="dialog" aria-modal="true">
            <div class="modal-cabecalho">
                <h2>Editar certificado</h2>
                <button type="button" class="modal-fechar" id="fechar-modal-editar"><i class="fa-solid fa-xmark"></i></button>
            </div>

            <form id="formulario-editar" class="modal-formulario"
                  action="<%=caminho%>/alterar-certificado" method="post">

                <input type="hidden" id="editar-id-certificado" name="id_certificado">

                <div class="modal-campo">
                    <label for="editar-dt_emissao">Data de emissão:</label>
                    <!-- type="date" precisa receber aaaa-mm-dd -->
                    <input type="date" id="editar-dt_emissao" name="dt_emissao" required>
                </div>
                <div class="modal-campo">
                    <label for="editar-validade">Validade:</label>
                    <input type="date" id="editar-validade" name="validade" required>
                </div>
                <div class="modal-campo">
                    <label for="editar-id_funcionario">Funcionário (id):</label>
                    <input type="number" id="editar-id_funcionario" name="id_funcionario" min="1" required>
                </div>
                <div class="modal-campo">
                    <label for="editar-id_nrfuncionario">NR do funcionário (id):</label>
                    <input type="number" id="editar-id_nrfuncionario" name="id_nrfuncionario" min="1" required>
                </div>
                <div class="modal-campo">
                    <label for="editar-id_nrempresa">NR da empresa (id):</label>
                    <input type="number" id="editar-id_nrempresa" name="id_nrempresa" min="1" required>
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
                Tem certeza que deseja excluir o certificado <strong id="excluir-nome"></strong>?
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
        // FILTRO
        const modalFiltro = document.getElementById('modal-filtro');
        document.querySelector('.btn-filtrar').addEventListener('click', () => modalFiltro.hidden = false);
        document.getElementById('fechar-modal-filtro').addEventListener('click', () => modalFiltro.hidden = true);
        modalFiltro.addEventListener('click', e => { if (e.target === modalFiltro) modalFiltro.hidden = true; });

        // Converte dd-MM-yyyy (tabela) -> yyyy-MM-dd (input type="date")
        function converterDataBrParaIso(dataBr) {
            if (!dataBr) return '';
            // Já está no formato do banco?
            if (dataBr.length === 10 && dataBr.charAt(4) === '-') return dataBr;

            const partes = dataBr.split('-');
            if (partes.length !== 3) return '';
            const [dia, mes, ano] = partes;
            return ano + '-' + mes.padStart(2, '0') + '-' + dia.padStart(2, '0');
        }

        // EDITAR
        const corpoTabela = document.getElementById('corpo-tabela-certificado');
        const modalEditar = document.getElementById('modal-editar');

        corpoTabela.addEventListener('click', function (evento) {
            const botao = evento.target.closest('.btn-editar');
            if (!botao) return;

            const c = botao.closest('tr').children;
            const t = i => c[i].textContent.trim();

            document.getElementById('editar-id-certificado').value = t(0);
            // Converte as datas da tabela (dd-MM-yyyy) para o input date (yyyy-MM-dd)
            document.getElementById('editar-dt_emissao').value = converterDataBrParaIso(t(1));
            document.getElementById('editar-validade').value = converterDataBrParaIso(t(2));
            document.getElementById('editar-id_funcionario').value = t(3);
            document.getElementById('editar-id_nrfuncionario').value = t(4);
            document.getElementById('editar-id_nrempresa').value = t(5);

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
            document.getElementById('excluir-id').value = linha.dataset.idCertificado;
            // o certificado nao tem nome, entao mostra o id
            document.getElementById('excluir-nome').textContent = linha.children[0].textContent.trim();
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
