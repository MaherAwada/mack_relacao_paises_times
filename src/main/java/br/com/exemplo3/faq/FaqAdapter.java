package br.com.exemplo3.faq;

import br.com.exemplo3.pais.Pais;
import br.com.exemplo3.pais.PaisRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FaqAdapter {

    public Faq toEntity(FaqRequest req, Faq entity) {
        return entity
                .setPergunta(req.getPergunta())
                .setResposta(req.getResposta());
    }

    public Faq toEntity(FaqRequest req) {
        return toEntity(req, new Faq().setId(UUID.randomUUID()));
    }
}
