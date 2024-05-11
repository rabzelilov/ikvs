package ru.abzelilov.ikvs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.abzelilov.ikvs.model.Dna;
import ru.abzelilov.ikvs.model.Simplest;

@Repository
public interface SimplestRepository extends JpaRepository <Simplest, Long>, JpaSpecificationExecutor<Simplest> {
}
