(() => {
// FORMULÁRIO PARA INSERIR//


    // Declarando as Variáveis //
    const form = document.getElementById("formulario-adicionar-divisao");

    const idCronograma = document.getElementById("campo-id_cronograma");

    const codigo = document.getElementById("campo-codigo");

    const idEmpresa = document.getElementById("campo-id_empresa");

    const nome = document.getElementById("campo-nome");

    // Variáveis de Erro //
    const erroIdCrono = document.getElementById("erroIdCrono");

    const erroIdEmpresa = document.getElementById("erroIdEmpresa");

    const erroCod = document.getElementById("erroCod");

    const erroNome = document.getElementById("erroNome");

    // Validações de Dados - Nome //
    form.addEventListener("input", function (){

        if (nome.value.trim() === "") {

            erroNome.textContent = "";
            erroNome.classList.remove("invalido", "valido");
        }

        else if (nome.value.length > 50){

            erroNome.textContent = "máximo 50 caracteres"

            erroNome.classList.add("invalido")
            erroNome.classList.remove("valido")

        }

        else{

            erroNome.textContent = "Opção válida"

            erroNome.classList.add("valido")
            erroNome.classList.remove("invalido")
        }
    })

    // Validações dos Dados - codigo //
    form.addEventListener("input", function(){

        if (codigo.value.trim() === "") {

            erroCod.textContent = "";
            erroCod.classList.remove("invalido", "valido");
        }

        else if (!Number.isInteger(Number(codigo.value)) || Number(codigo.value) <= 0) {

            erroCod.textContent = "O código deve ser um inteiro maior que 0";

            erroCod.classList.add("invalido");
            erroCod.classList.remove("valido");
        }

        else {

            erroCod.textContent = "Opção válida!";

            erroCod.classList.add("valido");
            erroCod.classList.remove("invalido");
        }

    });

    // Validações dos Dados - Id Cronograma//
    form.addEventListener("input", function (){

        if (idCronograma.value.trim() === "") {

            erroIdCrono.textContent = "";
            erroIdCrono.classList.remove("invalido", "valido");
        }

        else if (!Number.isInteger(Number(idCronograma.value)) || Number(idCronograma.value) <= 0) {

            erroIdCrono.textContent = "O id deve ser um inteiro maior que 0";

            erroIdCrono.classList.add("invalido");
            erroIdCrono.classList.remove("valido");
        }

        else {

            erroIdCrono.textContent = "Opção válida";

            erroIdCrono.classList.add("valido");
            erroIdCrono.classList.remove("invalido");
        }
    })

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

        if (!Number.isInteger(Number(codigo.value)) || Number(codigo.value) <= 0 ) {
            event.preventDefault();
        }

        else if (nome.value.trim() === "" || nome.value.length > 50){
            event.preventDefault();
        }

        else if (!Number.isInteger(Number(idEmpresa.value)) || Number(idEmpresa.value) <= 0){
            event.preventDefault();
        }

        else if (!Number.isInteger(Number(idCronograma.value)) || Number(idCronograma.value) <= 0){
            event.preventDefault();
        }
    })
})();