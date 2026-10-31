(() => {
// FORMULÁRIO PARA INSERIR//


    // Declarando as Variáveis //
    const form = document.getElementById("formulario-adicionar-funcionario");

    const nome = document.getElementById("campo-nome");

    const cargo = document.getElementById("campo-cargo");

    const cpf = document.getElementById("campo-cpf");

    const email = document.getElementById("campo-email");

    const idEmpresa = document.getElementById("campo-id_empresa");

    const idDepart = document.getElementById("campo-id_departamento");

    // Variáveis de Erro //
    const erroNome = document.getElementById("erroNome");

    const erroCargo = document.getElementById("erroCargo");

    const erroCpf = document.getElementById("erroCpf");

    const erroEmail = document.getElementById("erroEmail");

    const erroIdEmpresa = document.getElementById("erroIdEmpresa");

    const erroIdDepart = document.getElementById("erroIdDepart");

    // REGEX //
    const regexCpf = /^(\d{3}\.\d{3}\.\d{3}-\d{2}|\d{11})$/

    const regexEmail = /^[a-zA-Z0-9._%+#$!\-]+@(gmail|hotmail|yahoo|outlook|institutojef)\.(com|com\.br|org\.br)$/

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

    // Validações dos Dados - Cargo //
    form.addEventListener("input", function (){

        if (cargo.value.trim() === "") {

            erroCargo.textContent = "";
            erroCargo.classList.remove("invalido", "valido");
        }

        else if (cargo.value.length > 100){

            erroCargo.textContent = "máximo 100 caracteres"

            erroCargo.classList.add("invalido")
            erroCargo.classList.remove("valido")

        }

        else{

            erroCargo.textContent = "Opção válida"

            erroCargo.classList.add("valido")
            erroCargo.classList.remove("invalido")
        }
    })

    // Validações dos Dados - CPF //
    form.addEventListener("input", function (){

        if (cpf.value.trim() === "") {

            erroCpf.textContent = "";
            erroCpf.classList.remove("invalido", "valido");
        }

        else if (!regexCpf.test(cpf.value)){

            erroCpf.textContent = "Formato inválido: 000.000.000-00 (11 dígitos)"

            erroCpf.classList.add("invalido")
            erroCpf.classList.remove("valido")

        }

        else{

            erroCpf.textContent = "Opção válida"

            erroCpf.classList.add("valido")
            erroCpf.classList.remove("invalido")
        }
    })

    // Validações dos Dados - Email //
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

            erroIdEmpresa.textContent = "Opção válida";

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

        else if (Number(idDepart.value) <= 0) {

            erroIdDepart.textContent = "O id deve ser maior que 0";

            erroIdDepart.classList.add("invalido");
            erroIdDepart.classList.remove("valido");
        }

        else {

            erroIdDepart.textContent = "Opção válida";

            erroIdDepart.classList.add("valido");
            erroIdDepart.classList.remove("invalido");
        }
    })

    // SUBMETER FORMULÀRIO //
    form.addEventListener("submit", function (event){

        if (Number(idEmpresa.value) <= 0 ) {
            event.preventDefault();
        }

        else if (Number(idDepart.value) <= 0){
            event.preventDefault();
        }

        else if (cargo.value.length > 100){
            event.preventDefault();
        }

        else if (nome.value.length > 100){
            event.preventDefault();
        }

        else if (!regexCpf.test(cpf.value)){
            event.preventDefault();
        }

        else if (!regexEmail.test(email.value)){
            event.preventDefault();
        }

    })
})();
