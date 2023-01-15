package com.example.GameBaron.Repositories;

import com.example.GameBaron.Entities.Possession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PossessionRepository extends JpaRepository<Possession, Integer> {
}
