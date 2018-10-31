package com.nbohn.pundit.rest.repository;

import com.nbohn.pundit.rest.model.entity.Pun;
import com.nbohn.pundit.rest.model.enumeration.PunType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Pun Repository
 */
@Repository     // Identify this class as a Spring Data Repository.
public interface PunRepository extends JpaRepository<Pun, Long> {

    /**
     * Find By: Title
     * @param title Title
     * @return Puns matching title.
     */
    List<Pun> findByTitle(String title);

    /**
     * Find By: Pun Type
     * @param type Pun Type
     * @return Puns matching pun type.
     */
    List<Pun> findByPunType(PunType type);

    /**
     * Delete By: Title
     * @param title Title
     * @return Entities deleted.
     */
    Long deleteByTitle(String title);
}
