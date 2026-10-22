// FORMULÁRIO PARA INSERIR//


// Declarando as Variáveis //
const form = document.getElementById("formulario-adicionar")

const nome = document.getElementById("campo-nome")

const cnae = document.getElementById("campo-cnae")

const cnpj = document.getElementById("campo-cnpj")

const cep = document.getElementById("campo-cep")

const rua = document.getElementById("campo-rua")

const bairro = document.getElementById("campo-bairro")

const cidade = document.getElementById("campo-cidade")

const estado = document.getElementById("campo-estado")

const dataRegistro = document.getElementById("campo-dt_registro")

// Variáveis de Erro //
const erroNome = document.getElementById("erroNome")

const erroCnae = document.getElementById("erroCnae")

const erroCnpj = document.getElementById("erroCnpj")

const erroCep = document.getElementById("erroCep")

const erroRua = document.getElementById("erroRua")

const erroBairro = document.getElementById("erroBairro")

const erroCidade = document.getElementById("erroCidade")

const erroEstado = document.getElementById("erroEstado")

const erroDataRegistro= document.getElementById("erroDataRegistro")

// REGEX //

const regexCnpj = /^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}|\d{14})$/;

const regexCep = /^(\d{5}-\d{3}|\d{8})$/;

const regexCnae = /^(\d{4}-\d\/\d{2}|\d{7})$/;

const regexData = /^\d{4}-(0[1-9]|1[0-2])-\d{2}$/;

const data = new Date();
const ano = data.getFullYear();
const mes = String(data.getMonth() + 1).padStart(2, "0");
const dia = String(data.getDate()).padStart(2, "0");

const dataAtual = `${ano}-${mes}-${dia}`;

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

// Validações dos Dados - cnae
form.addEventListener("input", function (){

    if (cnae.value.trim() === "") {

        erroCnae.textContent = "";
        erroCnae.classList.remove("invalido", "valido");
    }

    else if(!regexCnae.test(cnae.value.trim())){

        erroCnae.textContent = "Formato Inválido, o correto é 0000-0/00"

        erroCnae.classList.remove("valido")
        erroCnae.classList.add("invalido")
    }

    else{

        erroCnae.textContent = "Opção Válida";

        erroCnae.classList.add("valido")
        erroCnae.classList.remove("invalido")
    }
})

// Validações dos Dados - cep
form.addEventListener("input", function (){

    if (cep.value.trim() === "") {

        erroCep.textContent = "";
        erroCep.classList.remove("invalido", "valido");
    }

    else if(!regexCep.test(cep.value.trim())){

        erroCep.textContent = "Formato Inválido, o correto é 00000-000"

        erroCep.classList.remove("valido")
        erroCep.classList.add("invalido")
    }

    else{

        erroCep.textContent = "Opção Válida";

        erroCep.classList.add("valido")
        erroCep.classList.remove("invalido")
    }
})

// Validações dos Dados - cnpj
form.addEventListener("input", function (){

    if (cnpj.value.trim() === "") {

        erroCnpj.textContent = "";
        erroCnpj.classList.remove("invalido", "valido");
    }

    else if(!regexCnpj.test(cnpj.value.trim())){

        erroCnpj.textContent = "Formato Inválido, o correto é 00.000.000/0000-00"

        erroCnpj.classList.remove("valido")
        erroCnpj.classList.add("invalido")
    }

    else{

        erroCnpj.textContent = "Opção Válida";

        erroCnpj.classList.add("valido")
        erroCnpj.classList.remove("invalido")
    }
})

// Validações dos Dados - Rua //
form.addEventListener("input", function (){

    if (rua.value.trim() === "") {

        erroRua.textContent = "";
        erroRua.classList.remove("invalido", "valido");
    }

    else if (rua.value.length > 100){

        erroRua.textContent = "máximo 100 caracteres"

        erroRua.classList.add("invalido")
        erroRua.classList.remove("valido")

    }

    else{

        erroRua.textContent = "Opção válida"

        erroRua.classList.add("valido")
        erroRua.classList.remove("invalido")
    }
})

// Validações dos Dados - Bairro //
form.addEventListener("input", function (){

    if (bairro.value.trim() === "") {

        erroBairro.textContent = "";
        erroBairro.classList.remove("invalido", "valido");
    }

    else if (bairro.value.length > 100){

        erroBairro.textContent = "máximo 100 caracteres"

        erroBairro.classList.add("invalido")
        erroBairro.classList.remove("valido")

    }

    else{

        erroBairro.textContent = "Opção válida"

        erroBairro.classList.add("valido")
        erroBairro.classList.remove("invalido")
    }
})

// Validações dos Dados - Cidade //
form.addEventListener("input", function (){

    if (cidade.value.trim() === "") {

        erroCidade.textContent = "";
        erroCidade.classList.remove("invalido", "valido");
    }

    else if (cidade.value.length > 100){

        erroCidade.textContent = "máximo 100 caracteres"

        erroCidade.classList.add("invalido")
        erroCidade.classList.remove("valido")

    }

    else{

        erroCidade.textContent = "Opção válida"

        erroCidade.classList.add("valido")
        erroCidade.classList.remove("invalido")
    }
})

// Validações dos Dados - Estado //
form.addEventListener("input", function (){

    if (estado.value.trim() === "") {

        erroEstado.textContent = "";
        erroEstado.classList.remove("invalido", "valido");
    }

    else if (estado.value.trim().length > 2){

        erroEstado.textContent = "máximo 2 caracteres"

        erroEstado.classList.add("invalido")
        erroEstado.classList.remove("valido")

    }

    else{

        erroEstado.textContent = "Opção válida"

        erroEstado.classList.add("valido")
        erroEstado.classList.remove("invalido")
    }
})

// Validações de Dados - Data Registro//
form.addEventListener("input", function (){

    if (dataRegistro.value.trim() === "") {

        erroDataRegistro.textContent = "";
        erroDataRegistro.classList.remove("invalido", "valido");
    }

    else if (!regexData.test(dataRegistro.value)){

        erroDataRegistro.textContent = "Formato Inválido, o correto é: AAAA-MM-DD";

        erroDataRegistro.classList.add("invalido");
        erroDataRegistro.classList.remove("valido")
    }

    else if (dataRegistro.value > dataAtual){

        erroDataRegistro.textContent = "A data deve ser <= a data atual";

        erroDataRegistro.classList.add("invalido");
        erroDataRegistro.classList.remove("valido")
    }

    else{

        erroDataRegistro.textContent = "Opção válida";

        erroDataRegistro.classList.add("valido");
        erroDataRegistro.classList.remove("invalido")
    }
});

// SUBMETER FORMULÁRIO //
form.addEventListener("submit", function (event){

    if (nome.value.trim() === "" || nome.value.length > 100){
        event.preventDefault();
    }

    else if (rua.value.trim() === "" || rua.value.length > 100){
        event.preventDefault();
    }

    else if (bairro.value.trim() === "" || bairro.value.length > 100){
        event.preventDefault();
    }

    else if (cidade.value.trim() === "" || cidade.value.length > 100){
        event.preventDefault();
    }

    else if (estado.value.trim() === "" || estado.value.trim().length > 2){
        event.preventDefault();
    }

    else if(!regexCep.test(cep.value.trim())){
        event.preventDefault()
    }

    else if(!regexCnae.test(cnae.value.trim())){
        event.preventDefault()
    }

    else if(!regexCnpj.test(cnpj.value.trim())){
        event.preventDefault()
    }

    else if (!regexData.test(dataRegistro.value)){
        event.preventDefault()
    }

    else if (dataRegistro.value > dataAtual){
        event.preventDefault()
    }
})