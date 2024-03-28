package br.com.schoolsystem.AlunoService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolsystem.AlunoRepository.AlunoRepository;
import br.com.schoolsystem.dto.DadosAluno;
import br.com.schoolsystem.model.AlunoModel;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	public void cadastrarAluno(DadosAluno dados) {
		AlunoModel aluno = new AlunoModel(dados);
		repository.save(aluno);
	}
	
	public List<AlunoModel> encontrarAlunos() {
		return repository.findAll();
	}
	
	public Optional<AlunoModel> encontrarUmAlunoPorId(Long id) {
		return repository.findById(id);
	}
	
	public void atualizarAluno(AlunoModel aluno) {
		repository.save(aluno);
	}
	
	public void deletarAlunoPorId(Long id) {
        repository.deleteById(id);
    }

}
