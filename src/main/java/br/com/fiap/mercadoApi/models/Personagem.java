package br.com.fiap.mercadoApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A classe é obrigatória")
    @Enumerated(EnumType.STRING)
    private ClassePersonagem classe;

    @Min(value = 1, message = "O nível mínimo é 1")
    @Max(value = 99, message = "O nível máximo é 99")
    private int nivel;

    private int moedas;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    private List<Item> itens;

    public enum ClassePersonagem {
        GUERREIRO, MAGO, ARQUEIRO
    }
}