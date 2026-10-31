(() => {
    // Ao voltar pelo navegador (bfcache), remove o fade para a página não ficar em branco
    window.addEventListener("pageshow", function () {
        document.body.classList.remove("fade-out");
    });

    document.addEventListener("click", (e) => {
        const link = e.target.closest("a");
        if (!link) return;

        // Não interfere em nova aba / download
        if (link.target === "_blank" || link.hasAttribute("download")) return;

        const href = link.getAttribute("href");
        if (!href || href === "#") return;

        // Âncora só na mesma página (#secao)
        if (href.startsWith("#")) return;

        // Links externos e especiais
        const lower = href.toLowerCase();
        if (
            lower.startsWith("http://") ||
            lower.startsWith("https://") ||
            lower.startsWith("mailto:") ||
            lower.startsWith("tel:") ||
            lower.startsWith("javascript:")
        ) {
            return;
        }

        // URL com âncora (ex.: sobre.html#equipe) — navega normal para o salto funcionar
        if (href.includes("#")) return;

        e.preventDefault();

        document.body.classList.add("fade-out");

        setTimeout(() => {
            window.location.href = href;
        }, 400);
    });
})();
