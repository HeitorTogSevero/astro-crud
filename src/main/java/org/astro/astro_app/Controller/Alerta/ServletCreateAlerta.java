package org.astro.astro_app.Controller.Alerta;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;
import java.sql.Date;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ServletCreateAlerta", value = "/adicionar-alerta")
public class ServletCreateAlerta extends HttpServlet {

    private AlertaDAO alertaDAO;

    @Override
    public void init() {
        // concede o acesso ao DAO de alertas quando o servlet e iniciado
        alertaDAO = new AlertaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //os dados chegam em bits, aqui converte ele para linguagem normal
        request.setCharacterEncoding("UTF-8");

        // le os valores enviados no formulario de cadastro
        String codigoTexto = request.getParameter("codigo");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String descricao = request.getParameter("descricao");

        String dtLimiteTexto = request.getParameter("dt_limite");

        // guarda a data como sqlDate depois de validar e converter o texto recebido
        Date dataLimite = null;
        String mensagem = null;

        // verifica campos obrigatorios e limites antes de converter os dados
        if (dtLimiteTexto == null || dtLimiteTexto.isBlank()) {
            mensagem = "a data não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if (descricao == null || descricao.isBlank()) {
            mensagem = "a descricao não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if (descricao.length() > 250) {
            mensagem = "O maximo de caracteres é 250";
            request.setAttribute("erro", mensagem);
        }

        else if (codigoTexto == null || codigoTexto.isBlank()) {
            mensagem = "O codigo nao pode ser nulo";
            request.setAttribute("erro", mensagem);
        }

        else if (idEmpresaTexto == null || idEmpresaTexto.isBlank()) {
            mensagem = "O id da empresa não pode ser nulo";
            request.setAttribute("erro", mensagem);
        }

        else {
            // converte os campos numericos e a data para os tipos usados pelo modelo
            try {

                int codigo = Integer.parseInt(codigoTexto);

                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                dataLimite = Date.valueOf(dtLimiteTexto);
                LocalDate localDataLimite = dataLimite.toLocalDate();

                //confere regras de negocio antes de gravar o alerta
                if (codigo <= 0) {
                    mensagem = "O codigo deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if (idEmpresa <= 0) {
                    mensagem = "Este id deve ser > 0";
                    request.setAttribute("erro", mensagem);
                }

                else if (localDataLimite.isBefore(LocalDate.now())) {
                    mensagem = "A data limite deve ser a partir da data atual";
                    request.setAttribute("erro", mensagem);
                }

                else {
                    //monta o modelo e pede ao DAO para inserir o novo registro
                    Alerta novoAlerta = new Alerta(
                            codigo,
                            idEmpresa,
                            descricao,
                            dataLimite
                    );

                    alertaDAO.inserir(novoAlerta);
                    //apos a insercao redireciona para a listagem atualizada
                    response.sendRedirect(request.getContextPath() + "/listar-alerta");
                    return;
                }

            }
            // trata valores que nao podem ser convertidos para numero ou data
            catch (NumberFormatException e) {
                mensagem = "Código ou id da empresa devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            }
            catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        // em caso de erro, retorna ao formulario com a mensagem de validacao.
        request.getRequestDispatcher("/WEB-INF/AlertaJSP/alerta.jsp").forward(request, response);
    }
}
