package com.Cats.index.Repository;

import com.Cats.index.Entity.Aplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AplicationRepository extends JpaRepository<Aplication, Integer> {

    boolean existsByPath(String path);
}
