package br.com.exemplo3.pais;
/**
 * Objeto de solicitação que representa os atributos de um país em uma solicitação.
 */
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<Pais, UUID> {
}
