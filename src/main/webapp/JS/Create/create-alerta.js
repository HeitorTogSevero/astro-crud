// FORMULÁRIO PARA INSERIR//


// Declarando as Variáveis //
const form = document.getElementById("formulario-adicionar");
const codigo = document.getElementById("campo-codigo");
const idEmpresa = document.getElementById("campo-id_empresa");
const descricao = document.getElementById("campo-descricao");
const dtLimite = document.getElementById("campo-dt_limite");

// Variaveis de Erro
const erroCod = document.getElementById("erroCod");
const erroDescr = document.getElementById("erroDescr");
const erroData = document.getElementById("erroData")
const erroId = document.getElementById("erroId")

// Regex DATA:
const regexData = /^\d{4}\/(0?[1-9]|1[0-2])\/\d{2}$/;
const data = new Date();
const ano = data.getFullYear();
const mes = String(data.getMonth() + 1).padStart(2, "0");
const dia = String(data.getDate()).padStart(2, "0");

const dataAtual = `${ano}/${mes}/${dia}`;


// Validações dos Dados - codigo //
form.addEventListener("input", function(){

    if (codigo.value.trim() === "") {

        erroCod.textContent = "";
        erroCod.classList.remove("invalido", "valido");
    }

    else if (Number(codigo.value) <= 0) {

        erroCod.textContent = "O código deve ser maior que 0";

        erroCod.classList.add("invalido");
        erroCod.classList.remove("valido");
    }

    else {

        erroCod.textContent = "Opção válida!";

        erroCod.classList.add("valido");
        erroCod.classList.remove("invalido");
    }

});

// Validações dos Dados - descricao //

    form.addEventListener("input", function (){

        if (descricao.value.length > 250){

            erroDescr.textContent = "máximo 250 caracteres"

            erroDescr.classList.add("invalido")
            erroDescr.classList.remove("valido")

        }

         else if (descricao.value.trim() === ""){

            erroDescr.textContent = "Este campo deve ser preenchido!!";

            erroDescr.classList.add("invalido")
            erroDescr.classList.remove("valido")
        }

         else {

             erroDescr.textContent = "Opção válida!";

             erroDescr.classList.add("valido")
             erroDescr.classList.remove("invalido")
         }
    });

// Validações dos Dados - Data Limite //

form.addEventListener("input", function (){

    if (dtLimite.value.trim() === "") {

        erroData .textContent = "";
        erroData.classList.remove("invalido", "valido");
    }

    else if (!regexData.test(dtLimite.value)){

        erroData.textContent = "Formato Inválido, o correto é: AAAA/MM/DD";

        erroData.classList.add("invalido");
        erroData.classList.remove("valido")
    }

    else if (dtLimite.value <= dataAtual){

        erroData.textContent = "A data deve ser >= a data atual";

        erroData.classList.add("invalido");
        erroData.classList.remove("valido")
    }


    else{

        erroData.textContent = "Opção válida";

        erroData.classList.add("valido");
        erroData.classList.remove("invalido")
    }
});

// Validações dos Dados - Id Empresa //
form.addEventListener("input", function(){

    if (idEmpresa.value.trim() === "") {

        erroId.textContent = "";
        erroId.classList.remove("invalido", "valido");
    }

    else if (Number(idEmpresa.value) <= 0) {

        erroId.textContent = "O id deve ser maior que 0";

        erroId.classList.add("invalido");
        erroId.classList.remove("valido");
    }

    else {

        erroId.textContent = "Opção válida!";

        erroId.classList.add("valido");
        erroId.classList.remove("invalido");
    }

});


// SUBMETER O FORMULÁRIO //
form.addEventListener("submit", function(event) {

    if (Number(codigo.value) <= 0 ) {
        event.preventDefault();
    }

    else if (descricao.value.length > 250){
        event.preventDefault();
    }

    else if (Number(idEmpresa.value) <= 0){
        event.preventDefault();
    }

    else if (!regexData.test(dtLimite.value)){
        event.preventDefault()
    }

    else if (dtLimite.value <= dataAtual){
        event.preventDefault()
    }

});
