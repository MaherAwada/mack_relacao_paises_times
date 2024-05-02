package br.com.exemplo3.time;
/**
 * Entidade que representa um usuário da aplicação.
 * Mapeia a tabela de usuários no banco de dados.
 * Implementa a interface UserDetails do Spring Security para autenticação.
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
public class TimeService {

	@Autowired TimeRepository repository;
	@PersistenceContext EntityManager em;
    
	public Time save(Time time) {
        return repository.save(time);
    }

    public Optional<Time> findById(UUID id) {
    	return repository.findById(id);
    }

	public boolean delete(Time time) {
		repository.delete(time);
		return true;
	}
	
    public List<Time> findAll(TimeRequest req) {
    	if (req.todosEstaoNulos()) {
    		return repository.findAll();
    	} else {
    		List<Filtro> filtros = new ArrayList<>();
    		if (req.getNome()!=null)
    			filtros.add(new Filtro("nome", "ILIKE", req.getNome()));
    		if (req.getAnoDeFundacao()!=null)
    			filtros.add(new Filtro("anoDeFundacao", "=", req.getAnoDeFundacao()));
    		if (req.getEstado()!=null)
    			filtros.add(new Filtro("estado", "ILIKE", req.getEstado()));
    		if (req.getCidade()!=null)
    			filtros.add(new Filtro("cidade", "ILIKE", req.getCidade()));
    		
    		String sql = "from Time p where " + filtros.stream().map(f -> f.vars().replace("p.${nome} ${comparacao} :${nome}")).collect(joining(" and "));
    		TypedQuery<Time> query = em.createQuery(sql, Time.class);
    		filtros.forEach(f -> query.setParameter(f.nome(), f.sqlValor()));
    		
    		return query.getResultList();
    	}
    }
    
}
