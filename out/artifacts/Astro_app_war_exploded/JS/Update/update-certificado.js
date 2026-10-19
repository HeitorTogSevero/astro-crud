// FORMULÁRIO PARA EDITAR//


// Declarando as Variáveis //
const form = document.getElementById("formulario-editar")

const idFunc = document.getElementById("editar-id_funcionario")

const idNrFunc = document.getElementById("editar-id_nrfuncionario")

const idNrEmpresa = document.getElementById("editar-id_nrempresa")

const dataEmissao = document.getElementById("editar-dt_emissao")

const validade = document.getElementById("editar-validade")

//Variavéis de Erro//
const erroIdFunc = document.getElementById("editar-erroIdFunc")

const erroIdNrFunc = document.getElementById("editar-erroIdNrFunc")

const erroIdNrEmp = document.getElementById("editar-erroIdNrEmp")

const erroDataEmissao = document.getElementById("editar-erroDataEmissao")

const erroValidade = document.getElementById("editar-erroValidade")

// Regex DATA:
const regexData = /^\d{4}\/(0?[1-9]|1[0-2])\/\d{2}$/;

const data = new Date();
const ano = data.getFullYear();
const mes = String(data.getMonth() + 1).padStart(2, "0");
const dia = String(data.getDate()).padStart(2, "0");

const dataAtual = `${ano}/${mes}/${dia}`;



// Validações de Dados - Id Funcionario//
form.addEventListener("input", function (){

    if (idFunc.value.trim() === "") {

        erroIdFunc.textContent = "";
        erroIdFunc.classList.remove("invalido", "valido");
    }

    else if (Number(idFunc.value) <= 0) {

        erroIdFunc.textContent = "O código deve ser maior que 0";

        erroIdFunc.classList.add("invalido");
        erroIdFunc.classList.remove("valido");
    }

    else {

        erroIdFunc.textContent = "Opção válida!";

        erroIdFunc.classList.add("valido");
        erroIdFunc.classList.remove("invalido");
    }
})

// Validações de Dados - Id NR_Funcionario//
form.addEventListener("input", function (){

    if (idNrFunc.value.trim() === "") {

        erroIdNrFunc.textContent = "";
        erroIdNrFunc.classList.remove("invalido", "valido");
    }

    else if (Number(idNrFunc.value) <= 0) {

        erroIdNrFunc.textContent = "O código deve ser maior que 0";

        erroIdNrFunc.classList.add("invalido");
        erroIdNrFunc.classList.remove("valido");
    }

    else {

        erroIdNrFunc.textContent = "Opção válida!";

        erroIdNrFunc.classList.add("valido");
        erroIdNrFunc.classList.remove("invalido");
    }
})

// Validações de Dados - Id NR_Empresa//
form.addEventListener("input", function (){

    if (idNrEmpresa.value.trim() === "") {

        erroIdNrEmp.textContent = "";
        erroIdNrEmp.classList.remove("invalido", "valido");
    }

    else if (Number(idNrEmpresa.value) <= 0) {

        erroIdNrEmp.textContent = "O código deve ser maior que 0";

        erroIdNrEmp.classList.add("invalido");
        erroIdNrEmp.classList.remove("valido");
    }

    else {

        erroIdNrEmp.textContent = "Opção válida!";

        erroIdNrEmp.classList.add("valido");
        erroIdNrEmp.classList.remove("invalido");
    }
})

// Validações de Dados - Data Emissao//
form.addEventListener("input", function (){

    if (dataEmissao.value.trim() === "") {

        erroDataEmissao.textContent = "";
        erroDataEmissao.classList.remove("invalido", "valido");
    }

    else if (!regexData.test(dataEmissao.value)){

        erroDataEmissao.textContent = "Formato Inválido, o correto é: AAAA/MM/DD";

        erroDataEmissao.classList.add("invalido");
        erroDataEmissao.classList.remove("valido")
    }

    else if (dataEmissao.value >= dataAtual){

        erroDataEmissao.textContent = "A data deve ser <= a data atual";

        erroDataEmissao.classList.add("invalido");
        erroDataEmissao.classList.remove("valido")
    }


    else{

        erroDataEmissao.textContent = "Opção válida";

        erroDataEmissao.classList.add("valido");
        erroDataEmissao.classList.remove("invalido")
    }
});

// Validações de Dados - Validade
form.addEventListener("input", function (){

    if (validade.value.trim() === "") {

        erroValidade.textContent = "";
        erroValidade.classList.remove("invalido", "valido");
    }

    else if (!regexData.test(validade.value)){

        erroValidade.textContent = "Formato Inválido, o correto é: AAAA/MM/DD";

        erroValidade.classList.add("invalido");
        erroValidade.classList.remove("valido")
    }

    else if (validade.value <= dataAtual){

        erroValidade.textContent = "A data deve ser >= a data atual";

        erroValidade.classList.add("invalido");
        erroValidade.classList.remove("valido")
    }


    else{

        erroValidade.textContent = "Opção válida";

        erroValidade.classList.add("valido");
        erroValidade.classList.remove("invalido")
    }
});

// SUBMETER O FORMULÁRIO //
form.addEventListener("submit", function (event){
    if (Number(idNrFunc.value) <= 0 ) {
        event.preventDefault();
    }

    else if (Number(idNrEmpresa.value) <= 0){
        event.preventDefault();
    }

    else if (Number(idFunc.value) <= 0){
        event.preventDefault();
    }

    else if (!regexData.test(dataEmissao.value)){
        event.preventDefault()
    }

    else if (dataEmissao >= dataAtual){
        event.preventDefault()
    }

    else if (!regexData.test(validade.value)){
        event.preventDefault()
    }

    else if (validade <= dataAtual){
        event.preventDefault()
    }
})