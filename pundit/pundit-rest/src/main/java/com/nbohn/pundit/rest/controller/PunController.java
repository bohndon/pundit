package com.nbohn.pundit.rest.controller;

import com.nbohn.pundit.rest.model.entity.Pun;
import com.nbohn.pundit.rest.service.PunService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Pun Controller
 */
@RestController
@RequestMapping(PunController.PATH_MAPPING_BASE)
public class PunController {

    /** Path Mapping: Base */
    static final String PATH_MAPPING_BASE = "/puns";

    /** Path Mapping: Id */
    private static final String PATH_MAPPING_ID = "/{id}";

    /** Pun Service */
    private final PunService punService;

    /**
     * Constructor
     * @param punService Pun Service
     */
    public PunController(PunService punService) {
        this.punService = punService;
    }

    /**
     * List all Resources.
     * @return  List of Resources.
     */
    @GetMapping
    public List<Pun> list() {
        return punService.list();
    }

    /**
     * Get a Pun Resource.
     * @param id Pun Resource Id.
     * @return Pun Resource for Id.
     */
    @GetMapping(PATH_MAPPING_ID)
    public Pun get(@PathVariable final Long id) {
        return punService.get(id);
    }

    /**
     * Create a Pun Resource.
     * @param pun Pun Resource to create.
     * @return Pun Resource created.
     */
    @PostMapping
    @ResponseStatus(CREATED)
    public Pun post(@RequestBody Pun pun) {
        return punService.create(pun);
    }
}
