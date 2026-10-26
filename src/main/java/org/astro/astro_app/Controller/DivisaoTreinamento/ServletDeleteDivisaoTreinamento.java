package org.astro.astro_app.Controller.DivisaoTreinamento;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.DivisaoTreinamentoDAO;
import java.io.IOException;

@WebServlet(name = "ServletDeleteDivisaoTreinamento", value = "/deletar-divisao")
public class ServletDeleteDivisaoTreinamento extends HttpServlet{

    private DivisaoTreinamentoDAO divisaoTreinamentoDAO;

    @Override
    public void init(){

        // Concede o acesso ao DAO da divisão treinamento quando o servlet e iniciado
        divisaoTreinamentoDAO = new DivisaoTreinamentoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        // Le o identificador do alerta enviado pelo formulário de exclusão
        String idDivisaoTreinamentoTexto = request.getParameter("id_divisao");
        String mensagem = "";

        // Impede a exclusão quando o identificador não foi informado
        if(idDivisaoTreinamentoTexto == null || idDivisaoTreinamentoTexto.isBlank()){

            mensagem = "id nao informado";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);
            return;

        }

        try {

            // Converte e valida o identificador antes de chamar o DAO
            int idDivisaoTreinamento = Integer.parseInt(idDivisaoTreinamentoTexto);

            if (idDivisaoTreinamento <= 0){

                mensagem = "O id deve ser > 0";
                request.setAttribute("erro", mensagem);
                request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);
                return;

            }

            // Remove o alerta do banco de dados
            divisaoTreinamentoDAO.remover(idDivisaoTreinamento);

            // Redireciona para a listagem após a exclusão
            response.sendRedirect(request.getContextPath() + "/listar-divisoes");

        }catch (NumberFormatException e){

            // Se o identificador nao for numérico, informa o erro no formulário
            mensagem = "o id da divisão deve ser valido";
            request.setAttribute("erro", mensagem);
            request.getRequestDispatcher("/WEB-INF/DivisaoTreinamentoJSP/DivisaoTreinamento.jsp").forward(request,response);

        }

    }
}