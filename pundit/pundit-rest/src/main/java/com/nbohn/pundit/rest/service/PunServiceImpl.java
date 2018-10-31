package com.nbohn.pundit.rest.service;

import com.nbohn.pundit.rest.model.entity.Pun;
import com.nbohn.pundit.rest.repository.PunRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Pun Service - Default Implementation.
 */
@Service
public class PunServiceImpl implements PunService {

    /** Pun Repository */
    private final PunRepository punRepository;

    /**
     * Constructor.
     * @param punRepository Pun Repository.
     */
    public PunServiceImpl(PunRepository punRepository) {
        this.punRepository = punRepository;
    }

    /**
     * List all Puns.
     * @return Pun List.
     */
    @Override
    public List<Pun> list() {
        return punRepository.findAll();
    }

    /**
     * Get Pun for Id.
     * @param id Pun Id
     * @return Pun
     */
    @Override
    public Pun get(Long id) {
        return punRepository.findById(id).get();
    }

    /**
     * Create Pun Resource.
     * @param pun Pun Resource to create.
     * @return Pun Resource created.
     */
    @Override
    public Pun create(Pun pun) {
        return punRepository.save(pun);
    }
}
