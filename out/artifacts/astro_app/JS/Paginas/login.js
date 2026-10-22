const form = document.getElementById("formulario-login")
const email = document.getElementById("email-login")
const senha = document.getElementById("senha-login");

const erroEmail = document.getElementById("erroEmail")
const erroSenha = document.getElementById("erroSenha")

const regexEmail = /^[a-zA-Z0-9._%+#$!\-]+@(gmail|hotmail|yahoo|outlook|institutojef)\.(com|com\.br|org\.br)$/;
const regexSenha = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$/;


form.addEventListener("input", function (){

    if (email.value.trim() === "") {

        erroEmail.textContent = "";
        erroEmail.classList.remove("invalido", "valido");
    }

    else if (!regexEmail.test(email.value)){

        erroEmail.textContent = "Formato Inválido! exemplo@gmail.com"

        erroEmail.classList.add("invalido")
        erroEmail.classList.remove("valido")

    }

    else{

        erroEmail.textContent = "Opção válida"

        erroEmail.classList.add("valido")
        erroEmail.classList.remove("invalido")
    }
})

form.addEventListener("input", function (){

    if (email.value.trim() === "") {

        erroEmail.textContent = "";
        erroEmail.classList.remove("invalido", "valido");
    }

    else if (!regexSenha.test(senha.value)){

        erroSenha.textContent = ""
    }
})