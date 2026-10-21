/* Complemento do glossário: não substitui nem altera o glossario.js original. */
document.addEventListener('DOMContentLoaded', () => {
    const definicoes = {
        'Acidente de Trabalho (AT)': 'É o evento que ocorre durante o trabalho ou em razão dele e causa lesão corporal, perda funcional ou morte. Também pode abranger situações no trajeto, conforme as regras aplicáveis, e doenças relacionadas às atividades profissionais.',
        'AEP': 'A Avaliação Ergonômica Preliminar é o primeiro levantamento das condições de trabalho. Ela observa tarefas, postura, esforço, mobiliário, ritmo e organização para indicar se há necessidade de melhorias ou de uma Análise Ergonômica do Trabalho mais aprofundada.',
        'AET': 'A Análise Ergonômica do Trabalho estuda detalhadamente como a atividade é realizada na prática. Seu objetivo é adaptar trabalho, ferramentas, mobiliário e organização às pessoas, reduzindo desconfortos, fadiga, erros e adoecimentos.',
        'Agente Biológico': 'É qualquer micro-organismo, parasita ou material biológico capaz de causar dano à saúde, como vírus, bactérias, fungos e sangue contaminado. É comum em hospitais, laboratórios, limpeza, coleta de resíduos e atividades semelhantes.',
        'Agente Físico': 'É uma forma de energia presente no ambiente de trabalho que pode afetar a saúde, como ruído, vibração, calor, frio, radiação ou pressão anormal. A avaliação considera intensidade, tempo de exposição e forma de contato.',
        'Agente Químico': 'É uma substância, composto ou produto químico que pode ser inalado, absorvido pela pele ou ingerido acidentalmente. Poeiras, fumos, névoas, gases, vapores e líquidos podem ser agentes químicos.',
        'Análise de Risco': 'É o processo de identificar perigos, entender quem pode ser afetado, estimar a probabilidade e a gravidade de um dano e escolher controles. Ela ajuda a priorizar o que deve ser corrigido primeiro.',
        'APR': 'A Análise Preliminar de Riscos é feita antes de iniciar uma atividade. Ela registra perigos previsíveis, consequências possíveis, medidas de controle, responsáveis e condições necessárias para executar a tarefa com segurança.',
        'ASO': 'O Atestado de Saúde Ocupacional é emitido após o exame ocupacional. Ele informa se a pessoa está apta ou inapta para a função e acompanha momentos como admissão, retorno ao trabalho, mudança de risco e desligamento.',
        'Benzeno': 'É uma substância química presente em derivados de petróleo e reconhecida como cancerígena. Por isso, sua exposição exige controles rigorosos, procedimentos específicos e acompanhamento de saúde quando aplicável.',
        'CA': 'O Certificado de Aprovação é o registro que comprova que um equipamento de proteção individual foi aprovado pelo órgão competente. Um EPI só deve ser usado na finalidade prevista no seu CA e dentro da validade indicada.',
        'CAT': 'A Comunicação de Acidente de Trabalho é o registro formal de acidente, doença ocupacional ou morte relacionada ao trabalho. Ela é importante para fins previdenciários, estatísticos e para garantir os direitos envolvidos.',
        'CIPA': 'A Comissão Interna de Prevenção de Acidentes e de Assédio reúne representantes da organização e dos trabalhadores. Ela atua na prevenção de acidentes e doenças, na identificação de riscos e no combate ao assédio.',
        'CLT': 'A Consolidação das Leis do Trabalho reúne grande parte das regras que regem as relações de trabalho no Brasil. Ela trata de temas como contrato, jornada, férias, remuneração, saúde e segurança.',
        'CNAE': 'A Classificação Nacional de Atividades Econômicas é o código que identifica a atividade principal e as secundárias de uma empresa. Ela influencia cadastros, obrigações e a análise de riscos do negócio.',
        'Controle de Riscos': 'São as medidas adotadas para eliminar perigos ou diminuir a probabilidade e a gravidade de danos. A prioridade é eliminar ou substituir o risco, depois usar proteções coletivas, medidas administrativas e, por último, EPI.',
        'Dano': 'É a consequência negativa de um evento ou exposição: lesão, doença, perda material, impacto ambiental ou prejuízo à operação. O gerenciamento de riscos busca evitar ou reduzir esses resultados.',
        'Doença do Trabalho': 'É a doença adquirida ou desencadeada pelas condições específicas em que o trabalho é realizado. Diferentemente da doença profissional, depende mais do ambiente e da forma de organização do trabalho.',
        'Doença Ocupacional': 'É o termo geral para doenças relacionadas ao trabalho. Inclui tanto a doença profissional, ligada diretamente à atividade exercida, quanto a doença do trabalho, ligada às condições em que ela acontece.',
        'Doença Profissional': 'É a doença produzida ou desencadeada pelo exercício de uma profissão específica. Existe uma ligação típica entre a atividade e o adoecimento, como perdas auditivas em exposições ocupacionais a ruído.',
        'DORT': 'Distúrbios Osteomusculares Relacionados ao Trabalho são alterações em músculos, tendões, nervos e articulações. Podem estar associados a repetição, força, postura inadequada, ritmo intenso e falta de pausas.',
        'EPC': 'Equipamento de Proteção Coletiva é uma proteção instalada para resguardar várias pessoas ao mesmo tempo, como guarda-corpo, exaustão local, enclausuramento de máquinas e sistemas de ventilação.',
        'EPI': 'Equipamento de Proteção Individual é utilizado por uma pessoa para reduzir sua exposição a um risco específico. Deve ser adequado à atividade, ter CA válido, ser entregue corretamente e usado com treinamento.',
        'eSocial': 'É o sistema que reúne informações trabalhistas, previdenciárias e fiscais em um único ambiente digital do governo. Na SST, recebe eventos ligados a acidentes e exposições ocupacionais.',
        'Ergonomia': 'É a adaptação das condições de trabalho às características das pessoas. Considera aspectos físicos, cognitivos e organizacionais para aumentar conforto, segurança, eficiência e bem-estar.',
        'Espaço Confinado': 'É um ambiente não projetado para ocupação humana contínua, com entradas ou saídas limitadas e possibilidade de atmosfera perigosa. Sua entrada exige procedimentos, avaliação, monitoramento e plano de resgate.',
        'Exposição Ocupacional': 'É o contato de uma pessoa com um agente ou condição de risco durante o trabalho. Pode ocorrer por inalação, contato com a pele, ruído, vibração, calor, esforço físico ou outros meios.',
        'FAP': 'O Fator Acidentário de Prevenção é um multiplicador aplicado à contribuição previdenciária da empresa. Ele considera a frequência, a gravidade e o custo de acidentes e doenças relacionados ao trabalho.',
        'Fator de Risco': 'É qualquer elemento, situação ou condição que aumenta a possibilidade de acidente, adoecimento ou outro dano. Pode ser físico, químico, biológico, ergonômico, de acidente ou psicossocial.',
        'Fator de Risco Psicossocial': 'É um aspecto da organização, da gestão ou das relações de trabalho que pode prejudicar a saúde mental. Sobrecarga, assédio, metas inviáveis, baixa autonomia e conflitos são exemplos.',
        'GRO': 'O Gerenciamento de Riscos Ocupacionais é o processo contínuo de identificar perigos, avaliar riscos, implementar controles, acompanhar resultados e revisar medidas sempre que houver mudanças.',
        'Higiene Ocupacional': 'É a área técnica que antecipa, reconhece, avalia e controla agentes ambientais capazes de causar doenças. Ela orienta medições, comparação com limites e escolha de controles.',
        'Identificação de Perigos': 'É a etapa de reconhecer fontes, situações ou atos com potencial de causar lesão ou dano. Ela deve considerar atividades rotineiras e não rotineiras, ambientes, equipamentos e pessoas expostas.',
        'Iluminância': 'É a quantidade de luz que chega a uma superfície, medida em lux. Uma iluminância adequada ajuda a evitar fadiga visual, erros, acidentes e desconfortos durante a tarefa.',
        'Insalubridade': 'É a condição de trabalho com exposição a agentes nocivos acima dos limites previstos na legislação. Sua caracterização depende de avaliação técnica, atividade, agente e critérios legais aplicáveis.',
        'Inventário de Riscos Ocupacionais': 'É o registro organizado dos perigos, riscos, trabalhadores expostos, avaliações, controles e critérios de classificação da empresa. Ele é parte essencial do PGR.',
        'Investigação de Acidentes': 'É a análise estruturada de um acidente ou quase acidente para entender causas imediatas e causas de sistema. O foco é prevenir recorrências, e não apenas atribuir culpa.',
        'Jornada de Trabalho': 'É o período diário, semanal ou mensal em que a pessoa trabalha ou permanece à disposição do empregador. Sua organização influencia descanso, fadiga, exposição e saúde.',
        'LER': 'Lesão por Esforço Repetitivo é uma alteração em músculos, tendões ou nervos associada a movimentos repetidos, força, postura inadequada ou ausência de recuperação suficiente.',
        'Limite de Tolerância': 'É o valor máximo de exposição a determinado agente que a legislação aceita sob condições definidas. Não substitui a necessidade de reduzir exposições sempre que tecnicamente possível.',
        'LTCAT': 'O Laudo Técnico das Condições Ambientais do Trabalho é um documento técnico que avalia a exposição a agentes nocivos para fins previdenciários e apoia o preenchimento de informações específicas.',
        'Matriz de Riscos': 'É uma ferramenta visual que combina probabilidade e severidade para classificar riscos. Ela ajuda a comparar situações diferentes e priorizar ações de prevenção.',
        'Medicina do Trabalho': 'É a especialidade voltada à promoção, prevenção e acompanhamento da saúde de trabalhadores. Atua em exames ocupacionais, vigilância de agravos e orientação sobre aptidão para o trabalho.',
        'Medida de Controle': 'É uma ação, equipamento ou procedimento que elimina um risco ou reduz sua exposição. Pode ser uma proteção coletiva, mudança de processo, procedimento, treinamento ou EPI.',
        'Medida de Prevenção': 'É uma medida implantada antes da ocorrência de um dano para evitá-lo ou minimizar suas consequências. Deve ser planejada, acompanhada e revista quando não for eficaz.',
        'Movimentação de Materiais': 'É a atividade de levantar, transportar, empurrar, puxar, armazenar ou organizar materiais. Exige atenção a peso, postura, percurso, equipamentos de auxílio e treinamento.',
        'Nexo Causal': 'É a relação entre uma exposição, condição ou evento de trabalho e o dano apresentado por uma pessoa. A análise considera informações médicas, técnicas, organizacionais e epidemiológicas.',
        'NTEP': 'O Nexo Técnico Epidemiológico Previdenciário é uma relação estatística entre atividades econômicas e determinadas doenças. Ele pode ser usado pela Previdência na análise de benefícios por incapacidade.',
        'NR': 'Norma Regulamentadora é uma regra obrigatória que estabelece requisitos mínimos de segurança e saúde no trabalho no Brasil. Cada NR trata de um tema, como máquinas, altura, ergonomia ou riscos.',
        'NHO': 'Norma de Higiene Ocupacional é uma referência técnica da Fundacentro para avaliação de agentes ambientais. Ela orienta estratégias, equipamentos e critérios de medição.',
        'Organização do Trabalho': 'É a forma como tarefas, ritmo, metas, jornada, pausas, responsabilidades e relações de trabalho são definidos. Ela tem impacto direto na segurança, ergonomia e saúde mental.',
        'PCA': 'O Programa de Conservação Auditiva reúne medidas para prevenir perdas auditivas relacionadas ao ruído. Inclui avaliação ambiental, monitoramento de saúde, controles, treinamento e uso adequado de proteção.',
        'PCMSO': 'O Programa de Controle Médico de Saúde Ocupacional organiza o acompanhamento médico dos trabalhadores conforme os riscos aos quais estão expostos. Ele define exames e acompanha indicadores de saúde.',
        'Perigo': 'É uma fonte, situação ou ato que tem potencial de causar lesão, doença, dano material ou ambiental. O perigo existe antes de se avaliar a probabilidade de causar dano.',
        'Permissão de Trabalho (PT)': 'É o documento que autoriza e controla uma atividade de maior risco. Define condições de segurança, medidas obrigatórias, responsáveis, validade e liberação antes do início do serviço.',
        'Periculosidade': 'É a condição de trabalho com risco acentuado, como exposição a inflamáveis, explosivos, energia elétrica ou segurança pessoal e patrimonial, nos termos definidos em lei.',
        'PGR': 'O Programa de Gerenciamento de Riscos reúne o inventário de riscos e o plano de ação da organização. Ele estrutura a prevenção e deve ser atualizado quando houver mudanças relevantes.',
        'Plano de Ação': 'É o registro das medidas necessárias para tratar riscos identificados, incluindo responsável, prazo, recursos e acompanhamento. Ele transforma a avaliação de riscos em ações concretas.',
        'PPP': 'O Perfil Profissiográfico Previdenciário reúne o histórico de atividades, exposições e informações ambientais do trabalhador. É utilizado principalmente em processos previdenciários.',
        'PPR': 'O Programa de Proteção Respiratória reúne medidas para prevenir doenças causadas pela inalação de contaminantes. Engloba seleção, uso, manutenção e gestão de respiradores, além de controles ambientais.',
        'Prevenção de Acidentes': 'É o conjunto de práticas técnicas e organizacionais que evita ocorrências capazes de causar lesões ou perdas. Envolve planejamento, controles, capacitação, participação e melhoria contínua.',
        'Primeiros Socorros': 'É o atendimento inicial dado a uma pessoa até a chegada de assistência profissional ou encaminhamento adequado. Deve respeitar os limites de quem atende e os procedimentos da organização.',
        'Quase Acidente': 'É um evento que não causou lesão ou dano, mas tinha potencial para causar. Registrá-lo e investigá-lo permite corrigir falhas antes que ocorra um acidente grave.',
        'Radiação': 'É a emissão de energia que pode ser ionizante ou não ionizante. Dependendo do tipo, intensidade e exposição, exige controles, sinalização, treinamento e monitoramento específicos.',
        'Risco': 'É a combinação entre a probabilidade de um evento perigoso acontecer e a gravidade de suas consequências. Um mesmo perigo pode gerar riscos diferentes conforme a exposição.',
        'Risco Ocupacional': 'É o risco existente no trabalho que pode causar acidente, doença ou outro dano à saúde. Sua gestão envolve identificar perigos, avaliar exposições e implementar controles.',
        'Risco Psicossocial': 'É a possibilidade de dano à saúde mental ou social decorrente da forma como o trabalho é organizado e das relações profissionais. Requer prevenção integrada à gestão.',
        'Ruído': 'É um som indesejado que, quando intenso ou prolongado, pode causar desconforto, falhas de comunicação, fadiga e perda auditiva. A prevenção prioriza controles na fonte e no ambiente.',
        'Saúde Ocupacional': 'É o campo que busca preservar e promover a saúde de pessoas trabalhadoras. Integra prevenção de acidentes, acompanhamento médico, ergonomia e melhoria das condições de trabalho.',
        'Segurança do Trabalho': 'É o conjunto de técnicas, normas e práticas destinado a prevenir acidentes e proteger a integridade das pessoas durante as atividades profissionais.',
        'SESMT': 'Os Serviços Especializados em Segurança e em Medicina do Trabalho são equipes técnicas previstas conforme o porte e o grau de risco da empresa. Apoiam a prevenção e o cumprimento das obrigações de SST.',
        'Sílica': 'É um mineral comum em areia, pedra e concreto. A poeira de sílica respirável pode causar doenças pulmonares graves, por isso atividades que a geram exigem controle rigoroso.',
        'SST': 'Segurança e Saúde no Trabalho reúne normas, práticas e ações para prevenir acidentes e doenças ocupacionais, promover ambientes seguros e proteger a saúde dos trabalhadores.',
        'Trabalho a Quente': 'É a atividade que produz chama, faísca ou calor capaz de iniciar um incêndio, como soldagem, corte, esmerilhamento e uso de maçarico. Exige controle de fontes combustíveis e liberação adequada.',
        'Trabalho em Altura': 'É a atividade executada acima de dois metros do nível inferior, onde exista risco de queda. Requer planejamento, análise de risco, proteção contra quedas e capacitação específica.',
        'Trabalho em Espaço Confinado': 'É a atividade realizada dentro de um espaço confinado. Exige avaliação da atmosfera, permissão de entrada, vigia, comunicação, equipamentos apropriados e plano de resgate.',
        'Treinamento de SST': 'É a capacitação que permite entender riscos, procedimentos, equipamentos e medidas de prevenção ligados às atividades. Deve ser compatível com a função e mantido atualizado.',
        'Toxicidade': 'É a capacidade de uma substância causar efeitos nocivos à saúde. Depende da dose, da via de exposição, do tempo de contato e das características da pessoa exposta.',
        'Umidade': 'É a presença de água ou vapor no ambiente de trabalho. Pode aumentar desconforto térmico, risco de escorregamento, contato elétrico e agravamento de outros riscos.',
        'Vibração': 'É a oscilação mecânica transmitida ao corpo inteiro ou às mãos e braços. Exposições intensas ou prolongadas podem causar desconforto, alterações circulatórias e problemas musculoesqueléticos.',
        'Vigilância em Saúde do Trabalhador': 'É o conjunto de ações de observação, análise e intervenção sobre fatores de trabalho que afetam a saúde. Busca identificar agravos e transformar as condições que os produzem.'
    };

    const lista = document.querySelector('.glossario-dicionario__lista');
    if (!lista) return;

    const naPratica = {
        'AEP': 'Pode ser usada para identificar rapidamente ajustes em postos, ferramentas, pausas e organização antes de uma análise detalhada.',
        'AET': 'Costuma resultar em recomendações concretas sobre mobiliário, ferramentas, ritmo, pausas e distribuição das tarefas.',
        'APR': 'Antes de começar, a equipe confere a APR e confirma se as medidas previstas estão realmente disponíveis no local.',
        'ASO': 'É o documento que normalmente acompanha os exames ocupacionais e deve refletir os riscos da função exercida.',
        'CAT': 'O registro deve ser tratado com seriedade mesmo quando o acidente parece pequeno ou não gera afastamento inicial.',
        'CIPA': 'Na rotina, a comissão pode participar de inspeções, campanhas, diálogos de segurança e recomendações de melhoria.',
        'EPC': 'Exemplo: uma proteção física em uma máquina reduz o risco para todas as pessoas expostas, sem depender apenas do comportamento individual.',
        'EPI': 'O EPI complementa outras medidas: ele não substitui a eliminação do risco nem uma proteção coletiva eficaz.',
        'GRO': 'Na empresa, o GRO transforma riscos observados no dia a dia em avaliações, controles, responsáveis e revisões contínuas.',
        'NR': 'As NRs devem ser consultadas de acordo com a atividade e o risco: não existe uma lista única de requisitos para toda empresa.',
        'PCMSO': 'Se a atividade expõe trabalhadores a riscos específicos, o programa médico deve considerar exames e acompanhamentos compatíveis.',
        'PGR': 'Na prática, ele deve sair do papel: riscos identificados precisam de plano de ação, responsáveis e datas de acompanhamento.',
        'SST': 'Ela funciona melhor quando prevenção, operação, liderança e trabalhadores participam das decisões sobre riscos.',
        'Trabalho em Altura': 'Antes da tarefa, é essencial conferir planejamento, ponto de ancoragem, sistema de proteção e condições climáticas.',
        'Trabalho em Espaço Confinado': 'A atividade não deve começar sem liberação formal, avaliação atmosférica e condições reais de resgate.'
    };

    function contextoDoTermo(termo) {
        return naPratica[termo] || 'Na rotina de SST, este conceito ajuda a reconhecer riscos, orientar medidas de prevenção e registrar decisões que protegem pessoas e a operação.';
    }

    const estilo = document.createElement('style');
    estilo.textContent = [
        '.glossario-dicionario__item[data-termo-popup]{cursor:pointer;}',
        '.glossario-dicionario__item[data-termo-popup] .glossario-dicionario__termo{transition:transform .16s ease;}',
        '.glossario-dicionario__item[data-termo-popup]:hover{background:transparent!important;box-shadow:none!important;}',
        '.glossario-dicionario__item[data-termo-popup]:hover .glossario-dicionario__termo{transform:translateX(3px);}',
        '.glossario-dicionario__item[data-termo-popup]:focus-visible{outline:1px solid rgba(97,75,245,.55);outline-offset:-1px;}',
        '.astro-glossario-modal[hidden]{display:none}.astro-glossario-modal{position:fixed;z-index:3000;inset:0;display:grid;place-items:center;padding:24px;}',
        '.astro-glossario-modal__fundo{position:absolute;inset:0;background:rgba(0,7,25,.8);backdrop-filter:blur(5px);animation:astro-fundo-entrar .24s ease-out both;}',
        '.astro-glossario-modal__caixa{position:relative;width:min(550px,100%);max-height:calc(100vh - 48px);overflow:auto;padding:38px;border:1px solid #0C153D;border-radius:18px;background:#070F25;color:#fff;box-shadow:0 24px 70px rgba(0,0,0,.48);animation:astro-modal-entrar .3s cubic-bezier(.2,.8,.2,1) both;}',
        '.astro-glossario-modal__fechar{position:absolute;top:19px;right:19px;width:30px;height:30px;border:0;color:#8F9BB3;background:transparent;font-size:24px;line-height:1;cursor:pointer;transition:color .18s ease,transform .18s ease;}',
        '.astro-glossario-modal__fechar:hover{color:#fff;transform:rotate(90deg);}.astro-glossario-modal__cabecalho{display:flex;align-items:center;gap:10px;margin-bottom:18px;}.astro-glossario-modal__isotipo{width:19px;height:19px;object-fit:contain;}.astro-glossario-modal__etiqueta{color:#8F9BB3;font-size:11px;font-weight:700;letter-spacing:.14em;}',
        '.astro-glossario-modal h2{margin:0 38px 17px 0;font-size:30px;line-height:1.2;letter-spacing:-.025em}.astro-glossario-modal p{margin:0;color:#D0D0D0;font-size:15px;line-height:1.78;}.astro-glossario-modal__nota{display:grid;grid-template-columns:104px 1fr;gap:16px;margin-top:27px;padding-top:19px;border-top:1px solid #191F30;}.astro-glossario-modal__nota strong{color:#8F9BB3;font-size:11px;letter-spacing:.1em;text-transform:uppercase;}.astro-glossario-modal__nota span{color:#D0D0D0;font-size:13px;line-height:1.65;}',
        '.astro-sugestao-form{display:grid;gap:20px;margin-top:28px;padding-top:22px;border-top:1px solid #191F30}.astro-sugestao-form label{display:grid;grid-template-columns:112px 1fr;gap:16px;align-items:start;color:#8F9BB3;font-size:12px;font-weight:600;letter-spacing:.04em;text-transform:uppercase}.astro-sugestao-form input,.astro-sugestao-form textarea{width:100%;border:0;border-bottom:1px solid #263156;border-radius:0;padding:0 0 10px;outline:0;color:#fff;background:transparent;font:inherit;line-height:1.5;transition:border-color .18s ease}.astro-sugestao-form input:focus,.astro-sugestao-form textarea:focus{border-color:#614BF5}.astro-sugestao-form textarea{min-height:88px;resize:vertical}.astro-sugestao-form button{justify-self:start;margin:5px 0 0 128px;border:1px solid #3443E8;border-radius:27px;padding:12px 24px;color:#fff;background:#3443E8;font:inherit;font-size:14px;font-weight:600;cursor:pointer;transition:transform .18s ease,background-color .18s ease}.astro-sugestao-form button:hover{transform:scale(1.025);background:#3D4CEB}.astro-sugestao-form button:disabled{opacity:.65;cursor:wait}.astro-sugestao-status{min-height:20px;margin-left:128px;text-align:left;font-size:13px}.astro-sugestao-status.erro{color:#ffb7c0}',
        '@keyframes astro-fundo-entrar{from{opacity:0}to{opacity:1}}@keyframes astro-modal-entrar{from{opacity:0;transform:translateY(12px) scale(.985)}to{opacity:1;transform:translateY(0) scale(1)}}',
        '@media(max-width:560px){.astro-glossario-modal{padding:16px;}.astro-glossario-modal__caixa{padding:30px 24px;}.astro-glossario-modal h2{font-size:25px;}.astro-glossario-modal__nota{grid-template-columns:1fr;gap:7px;}.astro-sugestao-form label{grid-template-columns:1fr;gap:7px;}.astro-sugestao-form button,.astro-sugestao-status{margin-left:0;}}'
    ].join('');
    document.head.appendChild(estilo);

    function marcarItens() {
        lista.querySelectorAll('.glossario-dicionario__item').forEach((item) => {
            if (item.dataset.termoPopup) return;
            const titulo = item.querySelector('strong')?.textContent.trim();
            if (!titulo || !definicoes[titulo]) return;
            item.dataset.termoPopup = titulo;
            item.setAttribute('role', 'button');
            item.setAttribute('tabindex', '0');
            item.setAttribute('aria-label', 'Ver explicação de ' + titulo);
            item.addEventListener('keydown', (evento) => {
                if (evento.key !== 'Enter' && evento.key !== ' ') return;
                evento.preventDefault();
                abrirModal(titulo);
            });
        });
    }

    function criarModal() {
        document.body.insertAdjacentHTML('beforeend',
            '<div class="astro-glossario-modal" hidden>' +
            '<div class="astro-glossario-modal__fundo" data-fechar-glossario></div>' +
            '<div class="astro-glossario-modal__caixa" role="dialog" aria-modal="true" aria-labelledby="titulo-modal-glossario">' +
            '<button class="astro-glossario-modal__fechar" type="button" aria-label="Fechar" data-fechar-glossario>×</button>' +
            '<div class="astro-glossario-modal__cabecalho"><img class="astro-glossario-modal__isotipo" src="lp/isotipo.png" alt=""><span class="astro-glossario-modal__etiqueta">GLOSSÁRIO</span></div>' +
            '<h2 id="titulo-modal-glossario"></h2><p></p><div class="astro-glossario-modal__nota"><strong>Na prática</strong><span></span></div>' +
            '</div>' +
            '</div>'
        );
        return document.querySelector('.astro-glossario-modal');
    }

    let modal;
    function abrirModal(termo) {
        modal = modal || criarModal();
        modal.querySelector('h2').textContent = termo;
        modal.querySelector('p').textContent = definicoes[termo];
        modal.querySelector('.astro-glossario-modal__nota span').textContent = contextoDoTermo(termo);
        modal.hidden = false;
        modal.querySelector('[data-fechar-glossario]').focus();
    }
    function fecharModal() {
        if (modal) modal.hidden = true;
    }

    let termoPressionado = null;
    let inicioDoClique = null;
    lista.addEventListener('pointerdown', (evento) => {
        termoPressionado = evento.target.closest('[data-termo-popup]');
        inicioDoClique = { x: evento.clientX, y: evento.clientY };
    });
    window.addEventListener('pointerup', (evento) => {
        if (!termoPressionado || !inicioDoClique) return;
        const moveu = Math.hypot(evento.clientX - inicioDoClique.x, evento.clientY - inicioDoClique.y) > 8;
        const termo = termoPressionado.dataset.termoPopup;
        termoPressionado = null;
        inicioDoClique = null;
        if (!moveu) abrirModal(termo);
    });
    window.addEventListener('pointercancel', () => {
        termoPressionado = null;
        inicioDoClique = null;
    });

    const EMAILJS_PUBLIC_KEY = 'COLE_AQUI_SUA_PUBLIC_KEY';
    const EMAILJS_SERVICE_ID = 'COLE_AQUI_SEU_SERVICE_ID';
    const EMAILJS_TEMPLATE_ID = 'COLE_AQUI_SEU_TEMPLATE_ID';
    if (window.emailjs && !EMAILJS_PUBLIC_KEY.startsWith('COLE_')) {
        emailjs.init({ publicKey: EMAILJS_PUBLIC_KEY });
    }
    let modalSugestao;

    function abrirSugestao() {
        if (!modalSugestao) {
            document.body.insertAdjacentHTML('beforeend',
                '<div class="astro-glossario-modal" hidden><div class="astro-glossario-modal__fundo" data-fechar-sugestao></div>' +
                '<div class="astro-glossario-modal__caixa" role="dialog" aria-modal="true" aria-labelledby="titulo-sugestao">' +
                '<button class="astro-glossario-modal__fechar" type="button" aria-label="Fechar" data-fechar-sugestao>×</button>' +
                '<div class="astro-glossario-modal__cabecalho"><img class="astro-glossario-modal__isotipo" src="lp/isotipo.png" alt=""><span class="astro-glossario-modal__etiqueta">CONSTRUA COM A GENTE</span></div><h2 id="titulo-sugestao">Sugerir um termo</h2>' +
                '<p>Compartilhe uma expressão que faria diferença no glossário da Astro. Sua sugestão vai direto para nossa equipe.</p>' +
                '<form class="astro-sugestao-form"><label>Termo ou sigla *<input name="termo" required maxlength="120"></label>' +
                '<label>Explique brevemente o termo<textarea name="detalhes" rows="4" maxlength="1000"></textarea></label>' +
                '<label>Seu nome<input name="nome" maxlength="100"></label><label>Seu e-mail<input name="email" type="email" maxlength="160"></label>' +
                '<button type="submit">Enviar sugestão</button><p class="astro-sugestao-status" aria-live="polite"></p></form></div></div>'
            );
            const modais = document.querySelectorAll('.astro-glossario-modal');
            modalSugestao = modais[modais.length - 1];
            modalSugestao.querySelector('form').addEventListener('submit', enviarSugestao);
        }
        modalSugestao.hidden = false;
        modalSugestao.querySelector('input[name="termo"]').focus();
    }

    function fecharSugestao() {
        if (modalSugestao) modalSugestao.hidden = true;
    }

    async function enviarSugestao(evento) {
        evento.preventDefault();
        const formulario = evento.currentTarget;
        const status = formulario.querySelector('.astro-sugestao-status');
        const botao = formulario.querySelector('button');
        const configurado = !EMAILJS_PUBLIC_KEY.startsWith('COLE_') && !EMAILJS_SERVICE_ID.startsWith('COLE_') && !EMAILJS_TEMPLATE_ID.startsWith('COLE_');
        if (!window.emailjs || !configurado) {
            status.className = 'astro-sugestao-status erro';
            status.textContent = 'O envio ainda não foi configurado.';
            return;
        }
        botao.disabled = true;
        botao.textContent = 'Enviando...';
        try {
            await emailjs.send(EMAILJS_SERVICE_ID, EMAILJS_TEMPLATE_ID, {
                termo_sugerido: formulario.termo.value.trim(),
                mensagem: formulario.detalhes.value.trim() || 'Sem explicação adicional.',
                remetente_nome: formulario.nome.value.trim() || 'Não informado',
                remetente_email: formulario.email.value.trim() || 'Não informado',
                reply_to: formulario.email.value.trim() || 'app.4str0@gmail.com'
            });
            formulario.reset();
            status.className = 'astro-sugestao-status';
            status.textContent = 'Sugestão enviada. Obrigada por contribuir!';
        } catch (erro) {
            status.className = 'astro-sugestao-status erro';
            status.textContent = 'Não foi possível enviar agora. Tente novamente.';
        } finally {
            botao.disabled = false;
            botao.textContent = 'Enviar sugestão';
        }
    }

    document.querySelector('.botao-sugerir')?.addEventListener('click', (evento) => {
        evento.preventDefault();
        abrirSugestao();
    });

    document.addEventListener('click', (evento) => {
        if (evento.target.closest('[data-fechar-glossario]')) fecharModal();
        if (evento.target.closest('[data-fechar-sugestao]')) fecharSugestao();
    });
    document.addEventListener('keydown', (evento) => {
        if (evento.key === 'Escape') fecharModal();
    });

    new MutationObserver(marcarItens).observe(lista, { childList: true, subtree: true });
    marcarItens();
});
