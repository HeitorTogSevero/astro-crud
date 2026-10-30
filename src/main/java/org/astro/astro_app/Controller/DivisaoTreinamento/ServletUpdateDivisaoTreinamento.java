package org.astro.astro_app.Controller.DivisaoTreinamento;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import org.astro.astro_app.model.DivisaoTreinamento;

import java.io.IOException;

@WebServlet(name = "ServletUpdateDivisaoTreinamento", value ="/alterar-divisao")
public class ServletUpdateDivisaoTreinamento extends HttpServlet{

    private DivisaoTreinamentoDAO divisaoTreinamentoDAO;

    @Override
    public void init(){

        // Concede o acesso ao DAO da divisão treinamento, quando o servlet e iniciado
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        // Le o identificador e os novos valores enviados pelo formulário de edição
        String idDivisaoTexto = request.getParameter("id_divisao");

        String idCronogramaTexto = request.getParameter("id_cronograma");

        String codigoTexto = request.getParameter("codigo");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String nome = request.getParameter("nome");

        String mensagem = "";

        // Valida campos obrigatórios e o tamanho da descrição antes da conversão
        if(idDivisaoTexto == null || idDivisaoTexto.isBlank()){

            mensagem = "o id da divisão não informado";

            request.setAttribute("erro", mensagem);

        }

        else if(idCronogramaTexto == null || idCronogramaTexto.isBlank()){

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

            try{

                // Converte identificadores, codigo e data para os tipos esperados
                int idDivisao = Integer.parseInt(idDivisaoTexto);

                int idCronograma = Integer.parseInt(idCronogramaTexto);

                int codigo = Integer.parseInt(codigoTexto);

                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                // Aplica as regras de negócio antes de atualizar o registro
                if(idDivisao <=0){

                    mensagem = "Id da divisao é invalido";

                    request.setAttribute("erro", mensagem);

                }

                else if(idCronograma <=0){

                    mensagem = "id do cronograma é inválido";

                    request.setAttribute("erro", mensagem);
                }

                else if(codigo <= 0){

                    mensagem = "o codigo é inválido";

                    request.setAttribute("erro", mensagem);
                }

                else if(idEmpresa <=0){

                    mensagem = "o id da empresa é inválido";

                    request.setAttribute("erro", mensagem);
                }

                else{

                    // Monta o modelo atualizado e envia os dados ao DAO
                    DivisaoTreinamento divisaoTreinamento = new DivisaoTreinamento(
                            idDivisao,
                            idCronograma,
                            codigo,
                            idEmpresa,
                            nome
                    );

                    // Altera caso todos os pontos tenham sido compridos
                    divisaoTreinamentoDAO.alterarDivisaoTreinamento(divisaoTreinamento);

                    // Redireciona para a lista apos concluir a atualização.
                    response.sendRedirect(request.getContextPath() + "/listar-divisoes");
                    return;
                }
            }

            // Trata identificadores inválidos e datas fora do formato esperado
            catch (NumberFormatException e){
                mensagem = "Os ids devem ser números validos";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulário com a mensagem de validação
        request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);

    }
}