package br.com.exemplo3.pais;
/**
 * Controlador para manipular operações relacionadas a países.
 * Define endpoints para criar, ler, atualizar, excluir e listar países.
 */
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class PaisAdapter {

	public Pais toEntity(PaisRequest req, Pais entity) {
		return entity
			.setNome(req.getNome())
			.setContinente(req.getContinente())
			.setPopulacao(req.getPopulacao());
	}
	
	public Pais toEntity(PaisRequest req) {
		return toEntity(req, new Pais().setId(UUID.randomUUID()));
	}
	
}
