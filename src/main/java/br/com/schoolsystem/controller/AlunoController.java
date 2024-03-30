package br.com.schoolsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolsystem.AlunoService.AlunoService;
import br.com.schoolsystem.dto.DadosAluno;
import br.com.schoolsystem.model.AlunoModel;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

	@Autowired
	private AlunoService service;
	
	@GetMapping
	public List<AlunoModel> encontrarAluno() {
		
		List<AlunoModel> alunos = service.encontrarAlunos();
		
        List<AlunoModel> alunosFiltrados = new ArrayList<>();

        // Iterar sobre cada aluno e adicionar apenas os campos desejados na nova lista
        for (AlunoModel aluno : alunos) {
        	
            AlunoModel alunoFiltrado = new AlunoModel();
            
            alunoFiltrado.setNome(aluno.getNome());
            alunoFiltrado.setEmail(aluno.getEmail());
            alunoFiltrado.setCurso(aluno.getCurso());
            
            alunosFiltrados.add(alunoFiltrado);
        }

        return alunosFiltrados;
	}
	
	@GetMapping("/{id}")
	public DadosAluno encontrarUmAlunoPorId(@PathVariable Long id) {
		
		Optional<AlunoModel> alunoOptional = service.encontrarUmAlunoPorId(id);
		
		if (alunoOptional.isPresent()) {
			AlunoModel aluno = alunoOptional.get();
			return new DadosAluno(aluno.getNome(), aluno.getEmail(), null, aluno.getCurso(), null, null);
			}
		
		return null;
	}
	
	@PostMapping
	public String cadastrarAluno(@RequestBody DadosAluno dados) {
		service.cadastrarAluno(dados);
		return "Ok!";
	}
	
	@PutMapping("/{id}")
	public String atualizarAluno(@PathVariable Long id, @RequestBody DadosAluno dadosAtualizados) {
		Optional<AlunoModel> alunoOptional = service.encontrarUmAlunoPorId(id);
		
		if (alunoOptional.isPresent()) {
			AlunoModel aluno = alunoOptional.get();
			// atualiza apenas os campos permitidos
			aluno.setNome(dadosAtualizados.nome());
			aluno.setCurso(dadosAtualizados.curso());
			aluno.setTelefone(dadosAtualizados.telefone());
			aluno.setEndereco(dadosAtualizados.endereco());
			
			// salva o aluno atualizado no bd
			service.atualizarAluno(aluno);
			return "Aluno atualizado com sucesso!";
		}
		
		return "Aluno não encontrado!";
	}
	
	@DeleteMapping("/{id}")
	public String deletarAlunoPorId(@PathVariable Long id) {
		service.deletarAlunoPorId(id);
		return "Aluno(a) deletado com sucesso!";
	}
}
