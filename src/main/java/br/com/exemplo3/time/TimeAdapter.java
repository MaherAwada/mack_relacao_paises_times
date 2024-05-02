package br.com.exemplo3.time;
/**
 * Controlador para manipular operações relacionadas a times.
 * Define endpoints para criar, ler, atualizar, excluir e listar times.
 */
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TimeAdapter {

	public Time toEntity(TimeRequest req, Time entity) {
		return entity
			.setNome(req.getNome())
			.setAnoDeFundacao(req.getAnoDeFundacao())
			.setEstado(req.getEstado())
			.setCidade(req.getCidade());
	}
	
	public Time toEntity(TimeRequest req) {
		return toEntity(req, new Time().setId(UUID.randomUUID()));
	}
	
}
