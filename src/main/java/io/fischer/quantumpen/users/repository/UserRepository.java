package io.fischer.quantumpen.users.repository;

import io.fischer.quantumpen.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
