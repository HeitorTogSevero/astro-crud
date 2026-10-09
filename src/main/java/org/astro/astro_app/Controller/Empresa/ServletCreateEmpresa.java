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

@WebServlet(name = "ServletEmpresa", value = "/adicionar-empresa")
public class ServletCreateEmpresa extends HttpServlet{

    private EmpresaDAO empresaDAO;

    @Override
    public void init(){
        empresaDAO = new EmpresaDAO();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )throws ServletException, IOException{

        final Pattern cepRegex =
                Pattern.compile("^(\\d{5}-\\d{3})| \\d{8}");

        final Pattern cnpjRegex =
                Pattern.compile("^(\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{0}|\\d{14})");

        final Pattern cnaeRegex =
                Pattern.compile("^(\\d{4}-\\d{1}/\\d{2})");

        String cnae = request.getParameter("");

        String nome = request.getParameter("");

        String cep = request.getParameter("");

        String cnpj = request.getParameter("");

        String rua = request.getParameter("");

        String bairro = request.getParameter("");

        String cidade = request.getParameter("");

        String estado = request.getParameter("");

        String dataRegistroTexto = request.getParameter("");

        Date dataRegistro = null;

        LocalDate localDataRegistro = dataRegistro.toLocalDate();

        String mensagem;

        if(dataRegistroTexto != null && !dataRegistroTexto.isBlank()){
            dataRegistro = Date.valueOf(dataRegistroTexto);
        }

        else if(dataRegistroTexto == null && dataRegistroTexto.isBlank()){
            mensagem = "Erro: a data não poder ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(nome == null || nome.equals(" ")){
            mensagem = "Erro: o nome não poder ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(nome.length() > 100){
            mensagem = "Erro: o nome pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);

        }

        else if(rua == null || rua.equals(" ")){
            mensagem = "Erro: o nome rua não poder ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(rua.length() > 100){
            mensagem = "Erro: o nome da rua pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(bairro == null || bairro.equals(" ")){
            mensagem = "Erro: o nome do bairro não poder ser vazio";
            request.setAttribute("erro", mensagem);
        }

        else if(bairro.length() > 100){
            mensagem = "Erro: o nome do bairro pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);

        }

        else if(cidade == null || cidade.equals(" ")){
            mensagem = "Erro: o nome da cidade não poder ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(cidade.length() > 100){
            mensagem = "Erro: o nome da cidade pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(estado == null || estado.equals(" ")){
            mensagem = "Erro: o nome do estado não poder ser vazio";
            request.setAttribute("erro", mensagem);

        }

        else if(estado.length() > 100){
            mensagem = "Erro: o nome do estado pode conter até 100 caracteres";
            request.setAttribute("erro", mensagem);
        }

        else if(!cepRegex.matcher(cep).matches()){
            mensagem = "Erro: o formato do cep não é aceito";
            request.setAttribute("erro", mensagem);
        }

        else if(!cnpjRegex.matcher(cnpj).matches()){
            mensagem = "Erro: o formato do cnpj não é aceito";
            request.setAttribute("erro", mensagem);
        }

        else if(!cnaeRegex.matcher(cnae).matches()){
            mensagem = "Erro: o formato do cnae não é aceito";
            request.setAttribute("erro", mensagem);
        }

        else if(localDataRegistro.isAfter(LocalDate.now())){
            mensagem = "Erro: a data de registro deve ser antes ou no dia atual";
            request.setAttribute("erro", mensagem);
        }

        else {
            Empresa novaEmpresa = new Empresa(
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

            empresaDAO.inserir(novaEmpresa);

            response.sendRedirect(request.getContextPath() + "/empresas");

        }
    }
}
