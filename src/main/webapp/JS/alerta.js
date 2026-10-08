
// Declarando as Variáveis // 
const form = document.getElementById(); 
const codigo = document.getElementById(); 
const idEmpresa = document.getElementById(); 
const descricao = document.getElementById(); 
const dtLimite = document.getElementById(); 
const erroNome = document.getElementById(); 

// Validações dos Dados - CÓDIGO // 
form.addEventListener("input", function(){

    if(codigo <= 0){
        
        erroNome.textContent = "O código deve ser maior que 0"; 

        erroNome.classList.add("invalido")
        erroNome.classList.remove("valido")
    }
    
    else{

        erroNome.textContent = "opção válida"

        erroNome.classList.add("valido")
        erroNome.classList.remove("invalido")
    }

}); 
