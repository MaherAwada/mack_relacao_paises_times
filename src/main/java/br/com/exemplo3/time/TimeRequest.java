package br.com.exemplo3.time;
/**
 * Serviço para lidar com lógica de negócios relacionada a times.
 * Realiza operações de busca, salvamento e exclusão de times.
 */
public class TimeRequest {

    private String nome;
    private Integer anoDeFundacao;
    private String cidade;
    private String estado;
    
	public String getNome() {
		return nome;
	}
	public TimeRequest setNome(String nome) {
		this.nome = nome;
		return this;
	}
	public Integer getAnoDeFundacao() {
		return anoDeFundacao;
	}
	public TimeRequest setAnoDeFundacao(Integer anoDeFundacao) {
		this.anoDeFundacao = anoDeFundacao;
		return this;
	}
	public String getCidade() {
		return cidade;
	}
	public TimeRequest setCidade(String cidade) {
		this.cidade = cidade;
		return this;
	}
	public String getEstado() {
		return estado;
	}
	public TimeRequest setEstado(String estado) {
		this.estado = estado;
		return this;
	}
    
	public boolean todosEstaoNulos() {
		return nome==null && anoDeFundacao==null && estado==null && cidade==null;
	}
	
}
