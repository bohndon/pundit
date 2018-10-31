package com.nbohn.pundit.rest.model.enumeration;

/**
 * Pun Types.
 */
public enum PunType {

    /** Homographic */
    HOMOGRAPHIC("Looking Alike - Words or phrases that have identical spelling but have different meanings and possibly different pronunciations."),

    /** Homophonic */
    HOMOPHONIC("Sounding Alike - Words or phrases that have similar pronunciation but have different spellings and meanings."),

    ;

    /** Pun Type Description */
    private final String description;


    /**
     * Constructor
     * @param description Description.
     */
    PunType(final String description) {
        this.description = description;
    }

    /**
     * Get Pun Type Description.
     * @return  Description
     */
    public String getDescription() {
        return description;
    }
}
