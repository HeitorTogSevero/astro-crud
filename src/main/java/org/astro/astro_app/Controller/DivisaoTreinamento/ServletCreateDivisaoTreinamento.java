package org.astro.astro_app.Controller.DivisaoTreinamento;

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
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        String idCronogramaTexto = request.getParameter("id_cronograma");
        String codigoTexto = request.getParameter("codigo");
        String idEmpresaTexto = request.getParameter("id_empresa");
        String nome = request.getParameter("nome");

        String mensagem = "";

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
            try{
                int idCronograma = Integer.parseInt(idCronogramaTexto);
                int codigo = Integer.parseInt(codigoTexto);
                int idEmpresa = Integer.parseInt(idEmpresaTexto);
                if(idCronograma <= 0){
                    mensagem = "o id do cronograma deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }
                else if(codigo <= 0){
                    mensagem = "o codigo deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }
                else if(idEmpresa <= 0){
                    mensagem = "o id da empresa deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }
                else {
                    DivisaoTreinamento divisaoTreinamento = new DivisaoTreinamento(
                            idCronograma,
                            codigo,
                            idEmpresa,
                            nome
                    );

                    divisaoTreinamentoDAO.inserir(divisaoTreinamento);

                    response.sendRedirect(request.getContextPath() + "/listar-divisoes");
                    return;
                }
            }catch (NumberFormatException e ){
                mensagem = "codigo ou idEmpresa ou idCronograma devem ser numeros válidos";
                request.setAttribute("erro", mensagem);
            }
        }
        request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);
    }
}