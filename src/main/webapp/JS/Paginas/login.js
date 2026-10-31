(() => {
    const form = document.getElementById("formulario-login");

    if (!form) return;

    const email = document.getElementById("email-login");

    const erroEmail = document.getElementById("erroEmail");

    const regexEmail = /^[a-zA-Z0-9._%+#$!\-]+@(gmail|hotmail|yahoo|outlook|institutojef)\.(com|com\.br|org\.br)$/;

    function setErro(el, texto, ok) {

        if (!el) return;

        el.textContent = texto;

        el.classList.remove("invalido", "valido");

        if (texto) el.classList.add(ok ? "valido" : "invalido");
    }

    form.addEventListener("input", function () {
        if (!email) return;

        if (email.value.trim() === "") {
            setErro(erroEmail, "", false);
        }
        else if (!regexEmail.test(email.value)) {
            setErro(erroEmail, "Formato inválido! exemplo@gmail.com", false);
        }
        else {
            setErro(erroEmail, "Opção válida", true);
        }
    });

    form.addEventListener("submit", function (event) {
        if (!email) return;

        if (email.value.trim() === "" || !regexEmail.test(email.value)) {
            event.preventDefault();
        }
    });
})();
