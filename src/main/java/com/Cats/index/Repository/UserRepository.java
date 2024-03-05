package com.Cats.index.Repository;

import com.Cats.index.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    User findById(Long id);

    List<User> findAllByEmpresaId(Long empresa_id);
}
