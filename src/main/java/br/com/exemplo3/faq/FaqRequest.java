package br.com.exemplo3.faq;

public class FaqRequest {
    private String pergunta;
    private String resposta;

    public String getPergunta() {
        return pergunta;
    }

    public FaqRequest setPergunta(String pergunta) {
        this.pergunta = pergunta;
        return this;
    }

    public String getResposta() {
        return resposta;
    }

    public FaqRequest setResposta(String resposta) {
        this.resposta = resposta;
        return this;
    }

    public boolean todosEstaoNulos() {
        return (pergunta==null || pergunta.isEmpty()) && (resposta==null || resposta.isEmpty());
    }
}
