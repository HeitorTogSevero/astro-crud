document.addEventListener('DOMContentLoaded', () => {
    const dados = {
        A: [
            ['Acidente de Trabalho (AT)'], ['AEP', 'Avaliação Ergonômica Preliminar'],
            ['AET', 'Análise Ergonômica do Trabalho'], ['Agente Biológico'], ['Agente Físico'],
            ['Agente Químico'], ['Análise de Risco'], ['APR', 'Análise Preliminar de Riscos'],
            ['ASO', 'Atestado de Saúde Ocupacional']
        ],
        B: [['Benzeno']],
        C: [['CA', 'Certificado de Aprovação'], ['CAT', 'Comunicação de Acidente de Trabalho'], ['CIPA', 'Comissão Interna de Prevenção de Acidentes e de Assédio'], ['CLT', 'Consolidação das Leis do Trabalho'], ['CNAE', 'Classificação Nacional de Atividades Econômicas'], ['Controle de Riscos']],
        D: [['Dano'], ['Doença do Trabalho'], ['Doença Ocupacional'], ['Doença Profissional'], ['DORT', 'Distúrbios Osteomusculares Relacionados ao Trabalho']],
        E: [['EPC', 'Equipamento de Proteção Coletiva'], ['EPI', 'Equipamento de Proteção Individual'], ['eSocial'], ['Ergonomia'], ['Espaço Confinado'], ['Exposição Ocupacional']],
        F: [['FAP', 'Fator Acidentário de Prevenção'], ['Fator de Risco'], ['Fator de Risco Psicossocial']],
        G: [['GRO', 'Gerenciamento de Riscos Ocupacionais']],
        H: [['Higiene Ocupacional']],
        I: [['Identificação de Perigos'], ['Iluminância'], ['Insalubridade'], ['Inventário de Riscos Ocupacionais'], ['Investigação de Acidentes']],
        J: [['Jornada de Trabalho']],
        L: [['LER', 'Lesão por Esforço Repetitivo'], ['Limite de Tolerância'], ['LTCAT', 'Laudo Técnico das Condições Ambientais do Trabalho']],
        M: [['Matriz de Riscos'], ['Medicina do Trabalho'], ['Medida de Controle'], ['Medida de Prevenção'], ['Movimentação de Materiais']],
        N: [['Nexo Causal'], ['NTEP', 'Nexo Técnico Epidemiológico Previdenciário'], ['NR', 'Norma Regulamentadora'], ['NHO', 'Norma de Higiene Ocupacional']],
        O: [['Organização do Trabalho']],
        P: [['PCA', 'Programa de Conservação Auditiva'], ['PCMSO', 'Programa de Controle Médico de Saúde Ocupacional'], ['Perigo'], ['Permissão de Trabalho (PT)'], ['Periculosidade'], ['PGR', 'Programa de Gerenciamento de Riscos'], ['Plano de Ação'], ['PPP', 'Perfil Profissiográfico Previdenciário'], ['PPR', 'Programa de Proteção Respiratória'], ['Prevenção de Acidentes'], ['Primeiros Socorros']],
        Q: [['Quase Acidente']],
        R: [['Radiação'], ['Risco'], ['Risco Ocupacional'], ['Risco Psicossocial'], ['Ruído']],
        S: [['Saúde Ocupacional'], ['Segurança do Trabalho'], ['SESMT', 'Serviços Especializados em Segurança e em Medicina do Trabalho'], ['Sílica'], ['SST', 'Segurança e Saúde no Trabalho']],
        T: [['Trabalho a Quente'], ['Trabalho em Altura'], ['Trabalho em Espaço Confinado'], ['Treinamento de SST'], ['Toxicidade']],
        U: [['Umidade']],
        V: [['Vibração'], ['Vigilância em Saúde do Trabalhador']]
    };

    const alfabeto = document.querySelector('.glossario-dicionario__alfabeto');
    const lista = document.querySelector('.glossario-dicionario__lista');
    const resultados = document.querySelector('.glossario-dicionario__resultados');
    const vazio = document.querySelector('.glossario-dicionario__vazio');
    const busca = document.querySelector('.glossario-dicionario__campo-busca');
    const letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
    const todosOsTermos = Object.entries(dados).flatMap(([letra, termos]) => termos.map(([sigla, descricao]) => ({ letra, sigla, descricao })));
    let letraAtiva = 'A';

    letras.forEach((letra) => {
        const botao = document.createElement('button');
        const disponivel = Boolean(dados[letra]);
        botao.type = 'button';
        botao.textContent = letra;
        botao.className = 'glossario-dicionario__letra';
        botao.setAttribute('role', 'tab');
        botao.setAttribute('aria-selected', String(letra === letraAtiva));
        botao.disabled = !disponivel;
        botao.title = disponivel ? `Ver termos com ${letra}` : `Não há termos com ${letra}`;

        if (letra === letraAtiva) botao.classList.add('esta-ativa');
        if (disponivel) botao.addEventListener('click', () => selecionarLetra(letra));
        alfabeto.appendChild(botao);
    });

    function selecionarLetra(letra) {
        letraAtiva = letra;
        busca.value = '';
        renderizarGlossarioCompleto();
        atualizarLetraAtiva(letra);
        rolarParaLetra(letra, true);
    }

    function atualizarLetraAtiva(letra) {
        letraAtiva = letra;
        alfabeto.querySelectorAll('.glossario-dicionario__letra').forEach((botao) => {
            const ativa = botao.textContent === letra;
            botao.classList.toggle('esta-ativa', ativa);
            botao.setAttribute('aria-selected', String(ativa));
        });
    }

    function criarItem({ sigla, descricao }) {
        return `
            <article class="glossario-dicionario__item">
                <div class="glossario-dicionario__termo">
                    <strong class="${descricao ? 'glossario-dicionario__sigla' : 'glossario-dicionario__nome'}">${sigla}</strong>
                    ${descricao ? `<span class="glossario-dicionario__descricao">${descricao}</span>` : ''}
                </div>
                <span class="glossario-dicionario__seta" aria-hidden="true"></span>
            </article>
        `;
    }

    function renderizarGlossarioCompleto() {
        lista.innerHTML = Object.entries(dados).map(([letra, termos]) => `
            <section class="glossario-dicionario__grupo" data-letra="${letra}">
                ${termos.map(([sigla, descricao]) => criarItem({ sigla, descricao })).join('')}
            </section>
        `).join('');

        vazio.hidden = true;
    }

    function renderizarBusca(termos) {
        lista.innerHTML = termos.map(criarItem).join('');

        vazio.hidden = termos.length > 0;
        lista.classList.remove('entrando');
        void lista.offsetWidth;
        lista.classList.add('entrando');
        resultados.scrollTo({ top: 0, behavior: 'smooth' });
    }

    function rolarParaLetra(letra, animar) {
        const grupo = lista.querySelector(`[data-letra="${letra}"]`);
        if (!grupo) return;

        resultados.scrollTo({ top: grupo.offsetTop, behavior: 'smooth' });
        if (animar) {
            grupo.classList.remove('em-foco');
            void grupo.offsetWidth;
            grupo.classList.add('em-foco');
            window.setTimeout(() => grupo.classList.remove('em-foco'), 520);
        }
    }

    busca.addEventListener('input', () => {
        const consulta = busca.value.trim().toLocaleLowerCase('pt-BR');
        if (!consulta) {
            renderizarGlossarioCompleto();
            atualizarLetraAtiva(letraAtiva);
            rolarParaLetra(letraAtiva, false);
            return;
        }

        alfabeto.querySelectorAll('.glossario-dicionario__letra').forEach((botao) => {
            botao.classList.remove('esta-ativa');
            botao.setAttribute('aria-selected', 'false');
        });
        renderizarBusca(todosOsTermos.filter(({ sigla, descricao }) => `${sigla} ${descricao || ''}`.toLocaleLowerCase('pt-BR').includes(consulta)));
    });

    busca.addEventListener('keydown', (evento) => {
        if (evento.key === 'Escape') {
            busca.value = '';
            renderizarGlossarioCompleto();
            atualizarLetraAtiva(letraAtiva);
            rolarParaLetra(letraAtiva, false);
            busca.blur();
        }
    });

    let aguardandoAtualizacao = false;
    resultados.addEventListener('scroll', () => {
        if (busca.value.trim() || aguardandoAtualizacao) return;
        aguardandoAtualizacao = true;
        window.requestAnimationFrame(() => {
            const grupos = [...lista.querySelectorAll('.glossario-dicionario__grupo')];
            const referencia = resultados.scrollTop + 34;
            const grupoVisivel = grupos.reduce((atual, grupo) => grupo.offsetTop <= referencia ? grupo : atual, grupos[0]);
            if (grupoVisivel) atualizarLetraAtiva(grupoVisivel.dataset.letra);
            aguardandoAtualizacao = false;
        });
    });

    let inicioY = 0;
    let inicioScroll = 0;
    resultados.addEventListener('pointerdown', (evento) => {
        inicioY = evento.clientY;
        inicioScroll = resultados.scrollTop;
        resultados.classList.add('arrastando');
        resultados.setPointerCapture(evento.pointerId);
    });

    resultados.addEventListener('pointermove', (evento) => {
        if (!resultados.classList.contains('arrastando')) return;
        resultados.scrollTop = inicioScroll - (evento.clientY - inicioY);
    });

    ['pointerup', 'pointercancel'].forEach((tipo) => resultados.addEventListener(tipo, () => {
        resultados.classList.remove('arrastando');
    }));

    renderizarGlossarioCompleto();
    atualizarLetraAtiva(letraAtiva);
});
