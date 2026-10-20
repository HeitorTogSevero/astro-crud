document.addEventListener("click", (e) => {
    const link = e.target.closest("a");
    if (!link) return;

    const href = link.getAttribute("href");

    // Ignora links vazios, âncoras (#) e links externos
    if (!href || href === "#" || href.startsWith("#") || href.startsWith("http") || link.target === "_blank") {
        return;
    }

    e.preventDefault();

    // Aplica a dissolução de saída
    document.body.classList.add("fade-out");

    // Espera a animação rodar antes de mudar a URL
    setTimeout(() => {
        window.location.href = href;
    }, 400); // 400ms bate com o tempo do CSS
});