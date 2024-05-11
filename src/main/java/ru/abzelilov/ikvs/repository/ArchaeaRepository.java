package ru.abzelilov.ikvs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.abzelilov.ikvs.model.Archaea;
import ru.abzelilov.ikvs.model.Bacteria;

@Repository
public interface ArchaeaRepository extends JpaRepository <Archaea, Long>, JpaSpecificationExecutor<Archaea> {
}
