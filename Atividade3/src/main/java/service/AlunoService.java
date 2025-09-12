package service;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.AlunoDAO;
import model.Aluno;

@Path("/aluno")
public class AlunoService {
    private AlunoDAO alunoDAO = new AlunoDAO();
    
    // Classe interna para representar a resposta - MOVIDA PARA O INÍCIO
    public static class Resposta {
        private boolean status;
        private String mensagem;

        public Resposta() {
        }

        public Resposta(boolean status, String mensagem) {
            this.status = status;
            this.mensagem = mensagem;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunos() {
        try {
            List<Aluno> alunos = alunoDAO.getOrderByMatricula();
            return Response.ok(alunos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(false, "Erro ao buscar alunos: " + e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAluno(@PathParam("matricula") int matricula) {
        try {
            Aluno aluno = alunoDAO.get(matricula);
            if (aluno != null) {
                return Response.ok(aluno).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new Resposta(false, "Aluno não encontrado"))
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(false, "Erro ao buscar aluno: " + e.getMessage()))
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirAluno(Aluno aluno) {
        try {
            boolean status = alunoDAO.insert(aluno);
            return Response.status(status ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(status, status ? "Aluno inserido com sucesso" : "Erro ao inserir aluno"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(false, "Erro ao inserir aluno: " + e.getMessage()))
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarAluno(Aluno aluno) {
        try {
            boolean status = alunoDAO.update(aluno);
            return Response.status(status ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(status, status ? "Aluno atualizado com sucesso" : "Erro ao atualizar aluno"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(false, "Erro ao atualizar aluno: " + e.getMessage()))
                    .build();
        }
    }

    @DELETE
    @Path("{matricula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirAluno(@PathParam("matricula") int matricula) {
        try {
            boolean status = alunoDAO.delete(matricula);
            return Response.status(status ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(status, status ? "Aluno excluído com sucesso" : "Erro ao excluir aluno"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Resposta(false, "Erro ao excluir aluno: " + e.getMessage()))
                    .build();
        }
    }
}