(() => {
// FORMULÁRIO PARA INSERIR//


    // Declarando as Variáveis //
    const form = document.getElementById("formulario-adicionar-departamento");

    const nome = document.getElementById("campo-nome");

    const descricao = document.getElementById("campo-descricao");

    const grauRisco = document.getElementById("campo-grau_risco");

    const atividade = document.getElementById("campo-atividade");

    const idEmpresa = document.getElementById("campo-id_empresa");

    // Variaveis de Erro
    const erroNome = document.getElementById("erroNome");

    const erroDescr = document.getElementById("erroDescr");

    const erroRisco = document.getElementById("erroRisco");

    const erroIdEmpresa = document.getElementById("erroIdEmpresa");

    const erroAtv = document.getElementById("erroAtv");


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

        // só mostra erro de campo vazio depois que o usuário digitou algo nele
        if (descricao.value === ""){

            erroDescr.textContent = "";
            erroDescr.classList.remove("invalido", "valido");
        }

        else if (descricao.value.length > 250){

            erroDescr.textContent = "máximo 250 caracteres"

            erroDescr.classList.add("invalido")
            erroDescr.classList.remove("valido")

        }

        else if (descricao.value.trim() === ""){

            erroDescr.textContent = "Este campo não pode ser só espaços!";

            erroDescr.classList.add("invalido")
            erroDescr.classList.remove("valido")
        }

        else {

            erroDescr.textContent = "Opção válida";

            erroDescr.classList.add("valido")
            erroDescr.classList.remove("invalido")
        }

    })

    // Validações dos Dados - atividade //
    form.addEventListener("input", function () {

        if (atividade.value === "") {

            erroAtv.textContent = "";
            erroAtv.classList.remove("invalido", "valido");

        } else if (atividade.value.length > 15) {

            erroAtv.textContent = "máximo 15 caracteres"

            erroAtv.classList.add("invalido")
            erroAtv.classList.remove("valido")

        } else if (atividade.value.trim() === "") {

            erroAtv.textContent = "Este campo não pode ser só espaços!";

            erroAtv.classList.add("invalido")
            erroAtv.classList.remove("valido")
        } else {

            erroAtv.textContent = "Opção válida";

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

        else if (!["1","2","3","4"].includes(grauRisco.value.trim())){

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

        else if (!Number.isInteger(Number(idEmpresa.value)) || Number(idEmpresa.value) <= 0) {

            erroIdEmpresa.textContent = "O id deve ser um inteiro maior que 0";

            erroIdEmpresa.classList.add("invalido");
            erroIdEmpresa.classList.remove("valido");
        }

        else {

            erroIdEmpresa.textContent = "Opção válida";

            erroIdEmpresa.classList.add("valido");
            erroIdEmpresa.classList.remove("invalido");
        }
    })

    // SUBMETER FORMULÁRIO //
    form.addEventListener("submit", function (event){

        if (!Number.isInteger(Number(idEmpresa.value)) || Number(idEmpresa.value) <= 0 ) {
            event.preventDefault();
        }

        else if (descricao.value.trim() === "" || descricao.value.length > 250){
            event.preventDefault();
        }

        else if (nome.value.trim() === "" || nome.value.length > 100){
            event.preventDefault();
        }

        else if (atividade.value.trim() === "" || atividade.value.length > 15){
            event.preventDefault();
        }

        else if (!["1","2","3","4"].includes(grauRisco.value.trim())){
            event.preventDefault();
        }

    })
})();