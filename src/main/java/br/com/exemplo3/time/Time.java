package br.com.exemplo3.time;
/**
 * Classe de adaptação para converter solicitações de time em entidades de time e vice-versa.
 */
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TIMES")
public class Time {

//	TIME (id, nome, ano de fundação, cidade, estado)
	
    @Id
    private UUID id;
    private String nome;
    private Integer anoDeFundacao;
    private String cidade;
    private String estado;
    
	public UUID getId() {
		return id;
	}
	public Time setId(UUID id) {
		this.id = id;
		return this;
	}
	public String getNome() {
		return nome;
	}
	public Time setNome(String nome) {
		this.nome = nome;
		return this;
	}
	public Integer getAnoDeFundacao() {
		return anoDeFundacao;
	}
	public Time setAnoDeFundacao(Integer anoDeFundacao) {
		this.anoDeFundacao = anoDeFundacao;
		return this;
	}
	public String getCidade() {
		return cidade;
	}
	public Time setCidade(String cidade) {
		this.cidade = cidade;
		return this;
	}
	public String getEstado() {
		return estado;
	}
	public Time setEstado(String estado) {
		this.estado = estado;
		return this;
	}
    
}
