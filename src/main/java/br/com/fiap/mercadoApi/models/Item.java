package br.com.fiap.mercadoApi.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @NotNull(message = "A raridade é obrigatória")
    @Enumerated(EnumType.STRING)
    private RaridadeItem raridade;

    @Positive(message = "O preço deve ser positivo")
    private double preco;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private Personagem dono;

    public enum TipoItem {
        ARMA, ARMADURA, POCAO, ACESSORIO
    }

    public enum RaridadeItem {
        COMUM, RARO, EPICO, LENDARIO
    }
}
