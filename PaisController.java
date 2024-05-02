package br.com.exemplo3.pais;
/**
 * Repositório JPA para operações de banco de dados relacionadas a países.
 */

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.exemplo3.utils.Utils;

@RestController
@RequestMapping(PaisController.URL)
public class PaisController {

    public static final String URL = "/api/pais";

    @Autowired
    PaisService service;
    @Autowired
    PaisAdapter adapter;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaisRequest req) {
        return Optional.ofNullable(req)
                .map(adapter::toEntity)
                .map(service::save)
                .map(pais -> Utils.createLocation(URL, pais.getId()))
                .map(Utils::statusCreated)
                .get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> read(@PathVariable("id") UUID id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(Utils.statusNotFound());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody PaisRequest req) {
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
    public ResponseEntity<List<Pais>> all(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "continente", required = false) String continente,
            @RequestParam(value = "populacao", required = false) Integer populacao
    ) {
        return ResponseEntity.ok(service.findAll(new PaisRequest()
                .setNome(nome)
                .setContinente(continente)
                .setPopulacao(populacao)));
    }
}
