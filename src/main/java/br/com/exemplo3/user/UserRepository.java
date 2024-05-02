package br.com.exemplo3.user;
/**
 * Classe auxiliar para representar um filtro de consulta com nome, operador de comparação e valor.
 */
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

	Optional<User> findByEmail(String email);

}
