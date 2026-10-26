package org.astro.astro_app.Controller.Alerta;

// Biblioteca:
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.AlertaDAO;
import org.astro.astro_app.model.Alerta;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "ServletUpdateAlerta", value = "/alterar-alerta")
public class ServletUpdateAlerta extends HttpServlet {

    private AlertaDAO alertaDAO;

    @Override
    public void init() {

        // Concede o acesso ao DAO de alertas quando o servlet e iniciado
        alertaDAO = new AlertaDAO();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Le o identificador e os novos valores enviados pelo formulario de edicao
        String idAlertaTexto = request.getParameter("id_alerta");

        String codigoTexto = request.getParameter("codigo");

        String idEmpresaTexto = request.getParameter("id_empresa");

        String descricao = request.getParameter("descricao");

        String dtLimiteTexto = request.getParameter("dt_limite");

        // Guarda a data convertida e a mensagem de erro, se houver
        Date dataLimite = null;
        String mensagem = null;

        // Valida campos obrigatorios e o tamanho da descricao antes da conversao
        if (idAlertaTexto == null || idAlertaTexto.isBlank()) {

            mensagem = "id do alerta não informado";

            request.setAttribute("erro", mensagem);

        }

        else if (dtLimiteTexto == null || dtLimiteTexto.isBlank()) {

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

            try {

                // Converte identificadores, codigo e data para os tipos esperados
                int idAlerta = Integer.parseInt(idAlertaTexto);

                int codigo = Integer.parseInt(codigoTexto);

                int idEmpresa = Integer.parseInt(idEmpresaTexto);

                dataLimite = Date.valueOf(dtLimiteTexto);
                LocalDate localDataLimite = dataLimite.toLocalDate();

                // Aplica as regras de negócio antes de atualizar o registro
                if (idAlerta <= 0) {

                    mensagem = "O id do alerta invalido";

                    request.setAttribute("erro", mensagem);

                }

                else if (codigo <= 0) {

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

                    // Monta o modelo atualizado e envia os dados ao DAO
                    Alerta alertaAtualizado = new Alerta(
                            idAlerta,
                            codigo,
                            idEmpresa,
                            descricao,
                            dataLimite
                    );

                    // Altera caso todos os pontos tenham sido compridos
                    alertaDAO.alterarAlerta(alertaAtualizado);

                    // Redireciona para a lista apos concluir a atualizacao.
                    response.sendRedirect(request.getContextPath() + "/listar-alerta");
                    return;
                }

            }

            // Trata identificadores invalidos e datas fora do formato esperado
            catch (NumberFormatException e) {
                mensagem = "Codigo ou id da empresa devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            }
            catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato AAAA-MM-DD";
                request.setAttribute("erro", mensagem);
            }
        }

        // Em caso de erro, retorna ao formulario com a mensagem de validacao
        request.getRequestDispatcher("/WEB-INF/AlertaJSP/alerta.jsp").forward(request, response);

    }
}
