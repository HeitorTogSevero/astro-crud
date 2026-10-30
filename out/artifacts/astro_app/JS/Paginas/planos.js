const switchPlano = document.getElementById("switch-plano");

const precoPlutao = document.getElementById("preco-plutao");
const precoTerra = document.getElementById("preco-terra");
const precoJupiter = document.getElementById("preco-jupiter");

const periodoPlutao = document.getElementById("periodo-plutao");
const periodoTerra = document.getElementById("periodo-terra");
const periodoJupiter = document.getElementById("periodo-jupiter");

const cobrancaPlutao = document.getElementById("cobranca-plutao");
const cobrancaTerra = document.getElementById("cobranca-terra");
const cobrancaJupiter = document.getElementById("cobranca-jupiter");

const planos = document.querySelector(".os-planos");
const mensalAnual = document.querySelector(".mensal-anual");

const imagemPlutao = document.querySelector(
    ".o-plano .plano-titulo img"
);

const imagemTerra = document.querySelector(
    ".o-plano2 .plano-titulo2 img"
);

const imagemJupiter = document.querySelector(
    ".o-plano:nth-child(3) .plano-titulo img"
);


switchPlano.addEventListener("change", function () {

    /*
     * Começa a animação dos PLANOS:
     * pequeno pulo + blur
     */
    planos.classList.add("trocando");


    /*
     * Começa a troca do gradiente
     */
    if (this.checked) {
        mensalAnual.classList.add("anual");
    } else {
        mensalAnual.classList.remove("anual");
    }


    /*
     * Troca o conteúdo enquanto os planos
     * estão no meio da animação
     */
    setTimeout(() => {

        if (this.checked) {

            // =========================
            // ANUAL
            // =========================

            planos.classList.add("anual");

            imagemPlutao.src = "imagens/plutao-anual.png";
            imagemTerra.src = "imagens/terra-anual.png";
            imagemJupiter.src = "imagens/jupiter-anual.png";

            precoPlutao.textContent = "526,28";
            precoTerra.textContent = "1.222,24";
            precoJupiter.textContent = "2.807,03";

            periodoPlutao.textContent = "/ano";
            periodoTerra.textContent = "/ano";
            periodoJupiter.textContent = "/ano";

            cobrancaPlutao.textContent = "Cobrança anual";
            cobrancaTerra.textContent = "Cobrança anual";
            cobrancaJupiter.textContent = "Cobrança anual";

        } else {

            // =========================
            // MENSAL
            // =========================

            planos.classList.remove("anual");

            imagemPlutao.src = "imagens/plutao.png";
            imagemTerra.src = "imagens/terra.png";
            imagemJupiter.src = "imagens/jupiter.png";

            precoPlutao.textContent = "48,73";
            precoTerra.textContent = "113,71";
            precoJupiter.textContent = "259,91";

            periodoPlutao.textContent = "/mês";
            periodoTerra.textContent = "/mês";
            periodoJupiter.textContent = "/mês";

            cobrancaPlutao.textContent = "Cobrança mensal";
            cobrancaTerra.textContent = "Cobrança mensal";
            cobrancaJupiter.textContent = "Cobrança mensal";
        }

        requestAnimationFrame(() => {

            requestAnimationFrame(() => {

                planos.classList.remove("trocando");

            });

        });

    }, 180);

});

const parametros = new URLSearchParams(window.location.search);

if (parametros.get("periodo") === "anual") {
    switchPlano.checked = true;
}

// Executa a atualização inicial dos planos
switchPlano.dispatchEvent(new Event("change"));