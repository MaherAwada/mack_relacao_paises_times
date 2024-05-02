package br.com.exemplo3.pais;
/**
 * Entidade que representa um time esportivo.
 * Mapeia a tabela de times no banco de dados.
 * Define atributos como ID, nome, ano de fundação, cidade e estado.
 */
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplo3.utils.Filtro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class PaisService {

	@Autowired PaisRepository repository;
	@PersistenceContext EntityManager em;
    
	public Pais save(Pais pais) {
        return repository.save(pais);
    }

    public Optional<Pais> findById(UUID id) {
    	return repository.findById(id);
    }

	public boolean delete(Pais pais) {
		repository.delete(pais);
		return true;
	}
	
    public List<Pais> findAll(PaisRequest req) {
    	if (req.todosEstaoNulos()) {
    		return repository.findAll();
    	} else {
    		
    		List<Filtro> filtros = new ArrayList<>();
    		if (req.getNome()!=null)
    			filtros.add(new Filtro("nome", "ILIKE", req.getNome()));
    		if (req.getContinente()!=null)
    			filtros.add(new Filtro("continente", "ILIKE", req.getContinente()));
    		if (req.getPopulacao()!=null)
    			filtros.add(new Filtro("populacao", "=", req.getPopulacao()));
    		
    		String sql = "from Pais p where " + filtros.stream().map(f -> f.vars().replace("p.${nome} ${comparacao} :${nome}")).collect(joining(" and "));
    		TypedQuery<Pais> query = em.createQuery(sql, Pais.class);
    		filtros.forEach(f -> query.setParameter(f.nome(), f.sqlValor()));
    		
    		return query.getResultList();
    	}
    }
    
}
