package com.escola.crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Anotação do Lombok para gerar Getters, Setters, toString, etc.
@Entity // Indica que esta classe é uma entidade JPA (mapeada para uma tabela)
public class Aluno {

    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String matricula;
}
