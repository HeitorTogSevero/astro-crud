// FORMULÁRIO PARA INSERIR//


// Declarando as Variáveis //
const form = document.getElementById("formulario-adicionar")
const nome = document.getElementById("campo-nome")
const descricao = document.getElementById("campo-descricao")
const grauRisco = document.getElementById("campo-grau_risco")
const atividade = document.getElementById("campo-atividade")
const idEmpresa = document.getElementById("campo-id_empresa")

// Variaveis de Erro
const erroNome = document.getElementById("erroNome");
const erroDescr = document.getElementById("erroDescr");
const erroRisco = document.getElementById("erroRisco")
const erroIdEmpresa = document.getElementById("erroIdEmpresa")
const erroIdDepart = document.getElementById("erroIdDepart")
const erroAtv = document.getElementById("erroAtv")


// Validações dos Dados - Nome //
form.addEventListener("input", function (){

    if (codigo.value.trim() === "") {

        erroCod.textContent = "";
        erroCod.classList.remove("invalido", "valido");
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
form.addEventListener("input", function (){

    if (atividade.value.length > 15){

        erroAtv.textContent = "máximo 15 caracteres"

        erroAtv.classList.add("invalido")
        erroAtv.classList.remove("valido")

    }

    else if (atividade.value.trim() === ""){

        erroAtv.textContent = "Este campo deve ser preenchido!!";

        erroAtv.classList.add("invalido")
        erroAtv.classList.remove("valido")
    }

    else {

        erroAtv.textContent = "Opção válida!";

        erroAtv.classList.add("valido")
        erroAtv.classList.remove("invalido")
    }

})

