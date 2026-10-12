// FORMULÁRIO PARA EDITAR//


// Declarando as Variáveis //
const form = document.getElementById("formulario-editar")
const nome = document.getElementById("editar-nome")
const descricao = document.getElementById("editar-descricao")
const grauRisco = document.getElementById("editar-grau_risco")
const atividade = document.getElementById("editar-atividade")
const idEmpresa = document.getElementById("editar-id_empresa")
const idDepart = document.getElementById("editar-id_departamento")

// Variaveis de Erro
const erroNome = document.getElementById("editar-erroNome");
const erroDescr = document.getElementById("editar-erroDescr");
const erroRisco = document.getElementById("editar-erroRisco")
const erroIdEmpresa = document.getElementById("editar-erroIdEmpresa")
const erroIdDepart = document.getElementById("editar-erroIdDepart")
const erroAtv = document.getElementById("editar-erroAtv")


// Validações dos Dados - Nome //
form.addEventListener("input", function (){

    if (nome.value.trim() === "") {

        erroNome.textContent = "";
        erroNome.classList.remove("invalido", "valido");
    }

    else if (nome.value.length > 100){

        erroNome.textContent = "máximo 100 caracteres"

        erroNome.classList.add("invalido")
        erroNome.classList.remove("valido")

    }

    else{

        erroNome.textContent = "Opção válida"

        erroNome.classList.add("valido")
        erroNome.classList.remove("invalido")
    }
})

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

})

// Validações dos Dados - atividade //
form.addEventListener("input", function () {

    if (atividade.value.length > 15) {

        erroAtv.textContent = "máximo 15 caracteres"

        erroAtv.classList.add("invalido")
        erroAtv.classList.remove("valido")

    } else if (atividade.value.trim() === "") {

        erroAtv.textContent = "Este campo deve ser preenchido!!";

        erroAtv.classList.add("invalido")
        erroAtv.classList.remove("valido")
    } else {

        erroAtv.textContent = "Opção válida!";

        erroAtv.classList.add("valido")
        erroAtv.classList.remove("invalido")
    }
});

// Validações dos Dados - Grau de Risco //
form.addEventListener("input", function (){

    if (grauRisco.value.trim() === "") {

        erroRisco.textContent = "";
        erroRisco.classList.remove("invalido", "valido");
    }

    else if (grauRisco.value != 1 && grauRisco.value != 2 && grauRisco.value != 3 && grauRisco.value != 4){

        erroRisco.textContent = "O grau de Risco deve ser de 1 a 4";

        erroRisco.classList.add("invalido")
        erroRisco.classList.remove("valido")
    }

    else{

        erroRisco.textContent = "Opção valida"

        erroRisco.classList.add("valido")
        erroRisco.classList.remove("invalido")
    }

});

// Validações dos Dados - Id Empresa//
form.addEventListener("input", function (){

    if (idEmpresa.value.trim() === "") {

        erroIdEmpresa.textContent = "";
        erroIdEmpresa.classList.remove("invalido", "valido");
    }

    else if (Number(idEmpresa.value) <= 0) {

        erroIdEmpresa.textContent = "O id deve ser maior que 0";

        erroIdEmpresa.classList.add("invalido");
        erroIdEmpresa.classList.remove("valido");
    }

    else {

        erroIdEmpresa.textContent = "Opção válida!";

        erroIdEmpresa.classList.add("valido");
        erroIdEmpresa.classList.remove("invalido");
    }
})

//Validações de Dados - Id Departamento//
form.addEventListener("input", function (){

    if (idDepart.value.trim() === "") {

        erroIdDepart.textContent = "";
        erroIdDepart.classList.remove("invalido", "valido");
    }

    else if (Number(idEmpresa.value) <= 0) {

        erroIdDepart.textContent = "O id deve ser maior que 0";

        erroIdDepart.classList.add("invalido");
        erroIdDepart.classList.remove("valido");
    }

    else {

        erroIdDepart.textContent = "Opção válida!";

        erroIdDepart.classList.add("valido");
        erroIdDepart.classList.remove("invalido");
    }
})

// SUBMETER O FORMULÁRIO //
form.addEventListener("submit", function (event){

    if (Number(idEmpresa.value) <= 0 ) {
        event.preventDefault();
    }

    else if (descricao.value.length > 250){
        event.preventDefault();
    }

    else if (nome.value.length > 100){
        event.preventDefault();
    }

    else if (Number(idDepart.value) <= 0){
        event.preventDefault();
    }

    else if (atividade.value.length > 15){
        event.preventDefault();
    }

    else if (grauRisco.value != 1 && grauRisco.value != 2 && grauRisco.value != 3 && grauRisco.value != 4){
        event.preventDefault();
    }

})
