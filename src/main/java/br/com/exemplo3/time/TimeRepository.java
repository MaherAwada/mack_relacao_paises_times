package br.com.exemplo3.time;
/**
 * Objeto de solicitação que representa os atributos de um time em uma solicitação.
 */
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, UUID> {
}
