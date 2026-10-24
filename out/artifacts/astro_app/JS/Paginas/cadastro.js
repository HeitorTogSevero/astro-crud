const form = document.getElementById("formulario-cadastro")
const nome = document.getElementById("nome-gestor")
const email = document.getElementById("email-login")
const senha = document.getElementById("senha-login");

const erroEmail = document.getElementById("erroEmail")
const erroSenha = document.getElementById("erroSenha")
const erroNome = document.getElementById("erroNome")

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

    if (senha.value.trim() === "") {

        erroSenha.textContent = "";
        erroSenha.classList.remove("invalido", "valido");
    }

    else if (!regexSenha.test(senha.value)){

        erroSenha.textContent = "Deve conte 1 letra maiúscula"

        erroSenha.classList.add("invalido")
        erroSenha.classList.remove("valido")
    }

    else{

        erroSenha.textContent = "Opção válida";

        erroSenha.classList.add("valido")
        erroSenha.classList.remove("invalido")
    }


})

form.addEventListener("input", function (){

    if (nome.value.trim() === "") {

        erroNome.textContent = "";
        erroNome.classList.remove("invalido", "valido");
    }

    else if(nome.value.length > 100) {

        erroNome.textContent = "Máx. de caracteres de 100";

        erroNome.classList.add("invalido")
        erroNome.classList.remove("valido")
    }

    else{

        erroNome.textContent = "Opção valida";

        erroNome.classList.add("valido")
        erroNome.classList.remove("invalido")
    }
})

form.addEventListener("submit", function (event){

    if(nome.value.trim() === "" || nome.value.length > 100){
        event.preventDefault();
    }

    else if(!regexSenha.test(senha.value)){
        event.preventDefault();
    }

    else if(!regexEmail.test(email.value)){
        event.preventDefault();
    }

});
