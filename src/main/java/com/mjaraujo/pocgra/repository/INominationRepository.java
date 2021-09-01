package com.mjaraujo.pocgra.repository;

import com.mjaraujo.pocgra.entity.Nomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INominationRepository extends JpaRepository<Nomination, Long> {


}
