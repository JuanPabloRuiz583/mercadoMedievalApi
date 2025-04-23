package br.com.fiap.mercadoApi.repository;

import br.com.fiap.mercadoApi.models.Personagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Page<Personagem> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Personagem> findByClasse(Personagem.ClassePersonagem classe, Pageable pageable);
}