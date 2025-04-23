package br.com.fiap.mercadoApi.repository;

import br.com.fiap.mercadoApi.models.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Item> findByTipo(Item.TipoItem tipo, Pageable pageable);
    Page<Item> findByPrecoBetween(double precoMin, double precoMax, Pageable pageable);
    Page<Item> findByRaridade(Item.RaridadeItem raridade, Pageable pageable);
}