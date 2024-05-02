package br.com.exemplo3.faq;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "FAQ")
public class Faq {
    @Id
    private UUID id;
    private String pergunta;
    private String resposta;

    public UUID getId() {
        return id;
    }

    public Faq setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getPergunta() {
        return pergunta;
    }

    public Faq setPergunta(String pergunta) {
        this.pergunta = pergunta;
        return this;
    }

    public String getResposta() {
        return resposta;
    }

    public Faq setResposta(String resposta) {
        this.resposta = resposta;
        return this;
    }
}