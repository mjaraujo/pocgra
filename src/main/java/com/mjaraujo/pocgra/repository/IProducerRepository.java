package com.mjaraujo.pocgra.repository;

import com.mjaraujo.pocgra.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducerRepository extends JpaRepository<Producer, Long>, JpaSpecificationExecutor<Producer> {

}
