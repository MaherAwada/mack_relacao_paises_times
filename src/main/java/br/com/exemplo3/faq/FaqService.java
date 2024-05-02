package br.com.exemplo3.faq;

import br.com.exemplo3.pais.Pais;
import br.com.exemplo3.pais.PaisRepository;
import br.com.exemplo3.pais.PaisRequest;
import br.com.exemplo3.utils.Filtro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.joining;

@Service
public class FaqService {
    @Autowired
    FaqRepository repository;
    @PersistenceContext
    EntityManager em;

    public Faq save(Faq pais) {
        return repository.save(pais);
    }

    public Optional<Faq> findById(UUID id) {
        return repository.findById(id);
    }

    public boolean delete(Faq faq) {
        repository.delete(faq);
        return true;
    }

    public List<Faq> findAll(FaqRequest req) {
        if (req.todosEstaoNulos()) {
            return repository.findAll();
        } else {

            List<Filtro> filtros = new ArrayList<>();
            if (req.getPergunta()!=null)
                filtros.add(new Filtro("pergunta", "ILIKE", req.getPergunta()));
            if (req.getResposta()!=null)
                filtros.add(new Filtro("resposta", "ILIKE", req.getResposta()));

            String sql = "from Faq p where " + filtros.stream().map(f -> f.vars().replace("p.${nome} ${comparacao} :${nome}")).collect(joining(" and "));
            TypedQuery<Faq> query = em.createQuery(sql, Faq.class);
            filtros.forEach(f -> query.setParameter(f.nome(), f.sqlValor()));

            return query.getResultList();
        }
    }
}
