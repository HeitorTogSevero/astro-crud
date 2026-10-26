package org.astro.astro_app.Controller.DivisaoTreinamento;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import org.astro.astro_app.model.DivisaoTreinamento;
import java.io.IOException;

@WebServlet(name = "ServletCreateDivisaoTreinamento", value = "/adicionar-divisao")
public class ServletCreateDivisaoTreinamento extends HttpServlet{

    private DivisaoTreinamentoDAO divisaoTreinamentoDAO;

    @Override
    public void init(){

        // Concede o acesso ao DAO da divisão treinamento, quando o servlet e iniciado
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        // Os dados chegam em bits, aqui converte ele para linguagem normal
        request.setCharacterEncoding("UTF-8");

        // Le os valores enviados no formulário de cadastro
        String idCronogramaTexto = request.getParameter("id_cronograma");

        String codigoTexto = request.getParameter("codigo");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String nome = request.getParameter("nome");

        String mensagem = "";

        // Verifica campos obrigatórios e limites antes de converter os dados
        if(idCronogramaTexto == null || idCronogramaTexto.isBlank()){

            mensagem = "o id do cronograma não pode ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(codigoTexto == null || codigoTexto.isBlank()){

            mensagem = "o codigo não pode ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(idEmpresaTexto == null || idEmpresaTexto.isBlank()){

            mensagem = "o id da empresa não pode ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(nome == null || nome.isBlank()){

            mensagem = "o nome não pode ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(nome.length() > 50){

            mensagem = "o nome deve conter até 50 caracteres";
            request.setAttribute("erro", mensagem);

        }

        else{

            // Converte os campos numéricos e a data para os tipos usados pelo modelo
            try{

                int idCronograma = Integer.parseInt(idCronogramaTexto);

                int codigo = Integer.parseInt(codigoTexto);

                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                // Confere regras de negocio antes de gravar o alerta
                if(idCronograma <= 0){
                    mensagem = "o id do cronograma deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if(codigo <= 0){
                    mensagem = "o código deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if(idEmpresa <= 0){
                    mensagem = "o id da empresa deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else {

                    // Monta o modelo e pede ao DAO para inserir o novo registro
                    DivisaoTreinamento divisaoTreinamento = new DivisaoTreinamento(
                            idCronograma,
                            codigo,
                            idEmpresa,
                            nome
                    );

                    divisaoTreinamentoDAO.inserir(divisaoTreinamento);

                    // Após a inserção redireciona para a listagem atualizada
                    response.sendRedirect(request.getContextPath() + "/listar-divisoes");
                    return;
                }
            }

            // Trata valores que não podem ser convertidos para número ou data
            catch (NumberFormatException e ){
                mensagem = "código ou idEmpresa ou idCronograma devem ser números válidos";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulário com a mensagem de validação.
        request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);

    }
}