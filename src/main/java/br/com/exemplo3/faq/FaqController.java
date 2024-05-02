package br.com.exemplo3.faq;
/**
 * Repositório JPA para operações de banco de dados relacionadas a FAQ.
 */

import br.com.exemplo3.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(FaqController.URL)
public class FaqController {

    public static final String URL = "/api/faq";

    @Autowired
    FaqService service;
    @Autowired
    FaqAdapter adapter;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody FaqRequest req) {
        return Optional.ofNullable(req)
                .map(adapter::toEntity)
                .map(service::save)
                .map(pais -> Utils.createLocation(URL, pais.getId()))
                .map(Utils::statusCreated)
                .get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faq> read(@PathVariable("id") UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(Utils.statusNotFound());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody FaqRequest req) {
        return service.findById(id)
                .map(ent -> adapter.toEntity(req, ent))
                .map(service::save)
                .map(Utils::statusNoContent)
                .orElse(Utils.statusNotFound());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        return service.findById(id)
                .map(service::delete)
                .map(Utils::statusNoContent)
                .orElse(Utils.statusNotFound());
    }

    @GetMapping
    public ResponseEntity<List<Faq>> all(
            @RequestParam(value = "pergunta", required = false) String pergunta,
            @RequestParam(value = "resposta", required = false) String resposta
    ) {
        return ResponseEntity.ok(service.findAll(new FaqRequest()
                .setPergunta(pergunta)
                .setResposta(resposta)));
    }
}
