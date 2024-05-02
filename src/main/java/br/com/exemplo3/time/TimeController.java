package br.com.exemplo3.time;
/**
 * Repositório JPA para operações de banco de dados relacionadas a times.
 */
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplo3.utils.Utils;

@RestController
@RequestMapping(TimeController.URL)
public class TimeController {
	
	public static final String URL = "/api/time";

    @Autowired TimeService service;
    @Autowired TimeAdapter adapter;

	@PostMapping
    public ResponseEntity<?> create(@RequestBody TimeRequest req) {
		return Optional.ofNullable(req)
			.map(adapter::toEntity)
			.map(service::save)
			.map(time -> Utils.createLocation(URL, time.getId()))
			.map(Utils::statusCreated)
			.get();
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Time> read(@PathVariable("id") UUID id) {
		return service.findById(id)
			.map(ResponseEntity::ok)
			.orElse(Utils.statusNotFound());
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody TimeRequest req) {
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
    public ResponseEntity<List<Time>> all(
    	@RequestParam(value="nome", required = false) String nome,
    	@RequestParam(value="anoDeFundacao", required = false) Integer anoDeFundacao,
    	@RequestParam(value="cidade", required = false) String cidade,
    	@RequestParam(value="estado", required = false) String estado
	) {
        return ResponseEntity.ok(service.findAll(new TimeRequest()
        		.setNome(nome)
                .setAnoDeFundacao(anoDeFundacao)
                .setEstado(estado)
                .setCidade(cidade)));
    }
    
}
