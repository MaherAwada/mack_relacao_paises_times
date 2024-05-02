package br.com.exemplo3.pais;
/**
 * Serviço para lidar com lógica de negócios relacionada a países.
 * Realiza operações de busca, salvamento e exclusão de países.
 */
public class PaisRequest {

    private String nome;
    private String continente;
    private Integer populacao;
    
	public String getNome() {
		return nome;
	}
	public PaisRequest setNome(String nome) {
		this.nome = nome;
		return this;
	}
	public String getContinente() {
		return continente;
	}
	public PaisRequest setContinente(String continente) {
		this.continente = continente;
		return this;
	}
	public Integer getPopulacao() {
		return populacao;
	}
	public PaisRequest setPopulacao(Integer populacao) {
		this.populacao = populacao;
		return this;
	}
	
	public boolean todosEstaoNulos() {
		return nome==null && continente==null && populacao==null;
	}
	
}
