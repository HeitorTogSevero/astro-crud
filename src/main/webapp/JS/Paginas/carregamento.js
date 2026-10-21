(() => {
    "use strict";
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

    window.addEventListener("resize", () => { largura = planeta.clientWidth; });
    movimentoReduzido.addEventListener("change", atualizar);
    document.addEventListener("visibilitychange", atualizar);
    atualizar();
})();
