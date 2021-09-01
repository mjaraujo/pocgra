package com.mjaraujo.pocgra.repository;

import com.mjaraujo.pocgra.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudioRepository extends JpaRepository<Studio, Long>, JpaSpecificationExecutor<Studio> {

}
