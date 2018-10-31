package com.nbohn.pundit.rest.service;

import com.nbohn.pundit.rest.model.entity.Pun;

import java.util.List;

/**
 * Pun Resource Service
 */
public interface PunService {

    /**
     * List all Pun Resources.
     * @return  List of Pun Resources.
     */
    List<Pun> list();

    /**
     * Get Pun Resource for Id.
     * @param id Pun Resource Id
     * @return  Pun Resource for Id.
     */
    Pun get(final Long id);

    /**
     * Create Pun Resource.
     * @param pun Pun Resource to create.
     * @return Pun Resource created.
     */
    Pun create(Pun pun);
}
