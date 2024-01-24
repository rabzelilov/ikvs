package ru.abzelilov.ikvs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.abzelilov.ikvs.model.Strain;

@Repository
public interface StrainRepository extends JpaRepository <Strain, Long>, JpaSpecificationExecutor<Strain> {
}
