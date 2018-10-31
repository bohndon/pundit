package com.nbohn.pundit.rest.model.entity;

import com.nbohn.pundit.rest.model.enumeration.PunType;

import java.time.ZonedDateTime;

/**
 * Sample Pun Entity Bean.
 */
public class PunSample {

    /**
     * Constructor.
     */
    private PunSample() {
        // Non-instantiable.
    }

    /**
     * Get Sample Pun Entity Bean.
     * @return  Sample Pun Entity Bean.
     */
    public static Pun getSample() {
        return getSample(null);
    }

    /**
     * Get Sample Pun Entity Bean.
     * @param punId Pun Id.
     * @return  Sample Pun Entity Bean.
     */
    public static Pun getSample(final Long punId) {
        final Pun pun = new Pun();
        pun.setPunId(punId);
        pun.setVersion(0);
        pun.setCreated(ZonedDateTime.now());
        pun.setEdited(ZonedDateTime.now());
        pun.setTitle("Title Sample");
        pun.setPrompt("Prompt Sample");
        pun.setStatement("Statement Sample");
        pun.setPunType(PunType.HOMOPHONIC);
        pun.setEnabled(true);
        return pun;
    }
}
