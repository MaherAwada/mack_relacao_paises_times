package br.com.exemplo3.pais;
/**
 * Classe de adaptação para converter solicitações de país em entidades de país e vice-versa.
 */
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAISES")
public class Pais {

//	PAÍS (id, nome, continente, população)
	
    @Id
    private UUID id;
    private String nome;
    private String continente;
    private Integer populacao;
    
	public UUID getId() {
		return id;
	}
	public Pais setId(UUID id) {
		this.id = id;
		return this;
	}
	public String getNome() {
		return nome;
	}
	public Pais setNome(String nome) {
		this.nome = nome;
		return this;
	}
	public String getContinente() {
		return continente;
	}
	public Pais setContinente(String continente) {
		this.continente = continente;
		return this;
	}
	public Integer getPopulacao() {
		return populacao;
	}
	public Pais setPopulacao(Integer populacao) {
		this.populacao = populacao;
		return this;
	}
    
}
