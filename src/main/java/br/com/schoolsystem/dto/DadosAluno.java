package br.com.schoolsystem.dto;

import br.com.schoolsystem.enums.Curso;
import br.com.schoolsystem.model.Endereco;


public record DadosAluno(String nome, String email, String cpf, Curso curso, String telefone, Endereco endereco) {

}
