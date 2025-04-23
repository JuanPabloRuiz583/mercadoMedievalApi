package br.com.fiap.mercadoApi.service;
import br.com.fiap.mercadoApi.models.Personagem;
import br.com.fiap.mercadoApi.repository.PersonagemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonagemService {
    private final PersonagemRepository personagemRepository;

    public PersonagemService(PersonagemRepository personagemRepository) {
        this.personagemRepository = personagemRepository;
    }

    public Personagem salvar(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    public Page<Personagem> listarTodos(Pageable pageable) {
        return personagemRepository.findAll(pageable);
    }

    public Optional<Personagem> buscarPorId(Long id) {
        return personagemRepository.findById(id);
    }

    public void deletar(Long id) {
        personagemRepository.deleteById(id);
    }

    public Page<Personagem> buscarPorNome(String nome, Pageable pageable) {
        return personagemRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Page<Personagem> buscarPorClasse(Personagem.ClassePersonagem classe, Pageable pageable) {
        return personagemRepository.findByClasse(classe, pageable);
    }
}
