package org.astro.astro_app.Controller.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.astro.astro_app.dao.EmpresaDAO;
import org.astro.astro_app.model.Empresa;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

@WebServlet(name = "ServletCreateEmpresa", value = "/adicionar-empresa")
public class ServletCreateEmpresa extends HttpServlet{

    private EmpresaDAO empresaDAO;

    @Override
    public void init(){
        empresaDAO = new EmpresaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");

        final Pattern cepRegex =
                Pattern.compile("^(\\d{5}-\\d{3}|\\d{8})$");

        final Pattern cnpjRegex =
                Pattern.compile("^(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14})$");

        final Pattern cnaeRegex =
                Pattern.compile("^\\d{4}-\\d{1}/\\d{2}$ | \\d{7}");

        String cnae = request.getParameter("cnae");

        String nome = request.getParameter("nome");

        String cep = request.getParameter("cep");

        String cnpj = request.getParameter("cnpj");

        String rua = request.getParameter("rua");

        String bairro = request.getParameter("bairro");

        String cidade = request.getParameter("cidade");

        String estado = request.getParameter("estado");

        String dataRegistroTexto = request.getParameter("dt_registro");

        Date dataRegistro = null;
        String mensagem = "";

        if(dataRegistroTexto == null || dataRegistroTexto.isBlank()){
            mensagem = "a data não pode ser vazia";
            request.setAttribute("erro", mensagem);
        }

        else if(nome == null || nome.isBlank()){
            mensagem = "o nome não pode ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(nome.length() > 100){
            mensagem = "o nome pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);

        }

        else if(rua == null || rua.isBlank()){
            mensagem = "o nome da rua não pode ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(rua.length() > 100){
            mensagem = "o nome da rua pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(bairro == null || bairro.isBlank()){
            mensagem = "o nome do bairro não pode ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(bairro.length() > 100){
            mensagem = "o nome do bairro pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(cidade == null || cidade.isBlank()){
            mensagem = "o nome da cidade não pode ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(cidade.length() > 100){
            mensagem = " nome da cidade pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(estado == null || estado.isBlank()){
            mensagem = "o nome do estado não pode ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(estado.length() > 100){
            mensagem = "o nome do estado pode conter até 2 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(cep == null || !cepRegex.matcher(cep).matches()){
            mensagem = "o formato do cep não é aceito";
            request.setAttribute("erro", mensagem);
        }

        else if(cnpj == null || !cnpjRegex.matcher(cnpj).matches()){
            mensagem = "o formato do cnpj não é aceito";
            request.setAttribute("erro", mensagem);
        }

        else if(cnae == null || !cnaeRegex.matcher(cnae).matches()){
            mensagem = "o formato do cnae não é aceito";
            request.setAttribute("erro", mensagem);
        }

        else{
            try{

                dataRegistro = Date.valueOf(dataRegistroTexto);
                LocalDate localDataRegistro = dataRegistro.toLocalDate();

                if(localDataRegistro.isAfter(LocalDate.now())){
                    mensagem = "Erro: a data de registro deve ser antes ou no dia atual";
                    request.setAttribute("erro", mensagem);
                }

                else{

                    Empresa empresa = new Empresa(
                            cnae,
                            nome,
                            cep,
                            cnpj,
                            rua,
                            estado,
                            bairro,
                            cidade,
                            dataRegistro
                    );

                    empresaDAO.inserir(empresa);
                    //depois manda o usuario pro ReadEmpresa
                    response.sendRedirect(request.getContextPath() + "/listar-empresas");
                    return;
                }

            }catch (IllegalArgumentException e){
                mensagem = "data invalida, use o formato ano-mes-dia";
                request.setAttribute("erro", mensagem);
            }
        }

        //teve erro, volta para a pagina
        request.getRequestDispatcher("/WEB-INF/EmpresaJSP/Empresa.jsp").forward(request,response);
    }
}