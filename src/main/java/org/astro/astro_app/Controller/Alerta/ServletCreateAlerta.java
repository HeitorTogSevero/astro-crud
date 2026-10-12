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
        alertaDAO = new AlertaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //os dados chegam como bits, aqui converte ele
        request.setCharacterEncoding("UTF-8");

        String codigoTexto = request.getParameter("codigo");
        String idEmpresaTexto = request.getParameter("id_empresa");
        String descricao = request.getParameter("descricao");
        String dtLimiteTexto = request.getParameter("dt_limite");

        Date dataLimite = null;
        String mensagem = null;

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
            // apos os campos estar prenchidos, vai vir pra ca
            try {
                int codigo = Integer.parseInt(codigoTexto);
                int idEmpresa = Integer.parseInt(idEmpresaTexto);
                dataLimite = Date.valueOf(dtLimiteTexto);
                LocalDate localDataLimite = dataLimite.toLocalDate();
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
                    Alerta novoAlerta = new Alerta(
                            codigo,
                            idEmpresa,
                            descricao,
                            dataLimite
                    );

                    alertaDAO.inserir(novoAlerta);
                    //depois manda o usuario pro servletreadAlerta
                    response.sendRedirect(request.getContextPath() + "/listar-alerta");
                    return;
                }

            } catch (NumberFormatException e) {
                mensagem = "Código ou id da empresa devem ser numeros validos";
                request.setAttribute("erro", mensagem);
            } catch (IllegalArgumentException e) {
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        //teve erro,volta para a pagina
        request.getRequestDispatcher("/WEB-INF/AlertaJSP/alerta.jsp").forward(request, response);
    }
}