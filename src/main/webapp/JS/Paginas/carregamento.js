(() => {
    "use strict";

    const script = document.currentScript;
    const urlScript = script ? script.src : window.location.href;
    const urlCss = new URL("../../style/carregamento.css", urlScript).href;

    if (!document.querySelector('link[href="' + urlCss + '"]')) {
        const estilo = document.createElement("link");
        estilo.rel = "stylesheet";
        estilo.href = urlCss;
        document.head.appendChild(estilo);
    }

    let tela = document.querySelector(".tela-carregamento");
    if (!tela) {
        tela = document.createElement("div");
        tela.className = "tela-carregamento";
        tela.id = "tela-carregamento-envio";
        tela.setAttribute("aria-busy", "true");
        tela.hidden = true;
        tela.innerHTML = `
            <div class="carregamento" role="status" aria-label="Carregando, aguarde">
                <div class="saturno" aria-hidden="true">
                    <img class="saturno__base" alt="" draggable="false">
                    <img class="saturno__bolinha" alt="" draggable="false">
                    <img class="saturno__frente" alt="" draggable="false">
                </div>
                <p class="carregamento__texto" aria-hidden="true">alinhando os planetas<span class="pontos"><span>.</span><span>.</span><span>.</span></span></p>
            </div>`;
        document.body.appendChild(tela);

        tela.querySelector(".saturno__base").src = new URL("../../imagens/saturno-c.png", urlScript).href;
        tela.querySelector(".saturno__bolinha").src = new URL("../../imagens/bolinha-c.png", urlScript).href;
        tela.querySelector(".saturno__frente").src = new URL("../../imagens/saturno-c-1.png", urlScript).href;
    }

    function mostrarCarregamento() {
        tela.hidden = false;
    }

    function esconderCarregamento() {
        tela.hidden = true;
    }

    // Corrige o bug da seta "voltar": página restaurada do cache com a overlay aberta
    window.addEventListener("pageshow", (evento) => {
        // evento.persisted = veio do bfcache (voltar/avançar)
        esconderCarregamento();
    });

    window.addEventListener("popstate", () => {
        esconderCarregamento();
    });

    document.addEventListener("submit", evento => {
        if (evento.defaultPrevented || evento.target.target === "_blank") return;
        mostrarCarregamento();
    });

    document.addEventListener("click", evento => {
        if (evento.defaultPrevented || evento.button !== 0 || evento.metaKey || evento.ctrlKey
            || evento.shiftKey || evento.altKey) return;

        const link = evento.target.closest("a[href]");
        if (!link || link.target === "_blank" || link.hasAttribute("download")) return;

        const href = link.getAttribute("href");
        if (!href || href.startsWith("#") || /^(mailto:|tel:|javascript:)/i.test(href)) return;

        const destino = new URL(href, window.location.href);
        if (destino.origin !== window.location.origin) return;
        if (destino.pathname === window.location.pathname && destino.search === window.location.search
            && destino.hash) return;

        mostrarCarregamento();
    });

    const planeta = document.querySelector(".saturno");
    const bolinha = document.querySelector(".saturno__bolinha");
    if (!planeta || !bolinha) return;

    const DURACAO_ORBITA = 2600;
    const movimentoReduzido = window.matchMedia("(prefers-reduced-motion: reduce)");
    const inclinacao = 10 * Math.PI / 180;
    let largura = planeta.clientWidth;
    let inicio = null;
    let quadro = null;

    function posicionar(angulo) {
        // Elipse alinhada ao anel original, com rotação de 10 graus.
        const x = Math.cos(angulo) * largura * 0.465;
        const y = Math.sin(angulo) * largura * 0.058;
        const dx = x * Math.cos(inclinacao) - y * Math.sin(inclinacao);
        const dy = x * Math.sin(inclinacao) + y * Math.cos(inclinacao);
        bolinha.style.transform = `translate(-50%, -50%) translate(${dx}px, ${dy}px)`;
        // Atrás: abaixo da imagem COMPLETA, para não vazar pelo limite do recorte.
        // Na frente: acima das duas imagens do planeta.
        bolinha.style.zIndex = Math.sin(angulo) >= 0 ? "4" : "0";
    }

    function animar(agora) {
        if (inicio === null) inicio = agora;
        posicionar(Math.PI - ((agora - inicio) % DURACAO_ORBITA) / DURACAO_ORBITA * Math.PI * 2);
        quadro = requestAnimationFrame(animar);
    }

    function atualizar() {
        cancelAnimationFrame(quadro);
        inicio = null;
        largura = planeta.clientWidth;
        if (movimentoReduzido.matches || document.hidden) {
            posicionar(Math.PI);
            return;
        }
        quadro = requestAnimationFrame(animar);
    }

    const observadorTamanho = new ResizeObserver(atualizar);
    observadorTamanho.observe(planeta);
    movimentoReduzido.addEventListener("change", atualizar);
    document.addEventListener("visibilitychange", atualizar);
    atualizar();
})();