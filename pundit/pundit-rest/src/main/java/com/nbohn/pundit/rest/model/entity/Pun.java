package com.nbohn.pundit.rest.model.entity;

import com.nbohn.pundit.rest.model.enumeration.PunType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.MessageFormat;
import java.time.ZonedDateTime;

import static com.nbohn.pundit.rest.validation.ValidationConstants.SIZE_NAME_MAX;
import static com.nbohn.pundit.rest.validation.ValidationConstants.SIZE_NOTE_MAX;
import static com.nbohn.pundit.rest.validation.ValidationConstants.SIZE_STRING_MIN;
import static com.nbohn.pundit.rest.validation.ViolationMessages.VIOLATION_PAST_OR_PRESENT;
import static com.nbohn.pundit.rest.validation.ViolationMessages.VIOLATION_POSITIVE_OR_ZERO;
import static com.nbohn.pundit.rest.validation.ViolationMessages.VIOLATION_SIZE;

/**
 * Pun Entity.
 */
@Data
@Entity
public class Pun implements Serializable {

    /** Class version id used for serialization comparison across JVM versions. */
    private static final long serialVersionUID = 1L;

    /** Sequence Generator Name. */
    private static final String SEQUENCE_GENERATOR_NAME = "ENTITY_SEQUENCE_GENERATOR";

    /** Id Sequence Generator name. */
    private static final String ENTITY_ID_SEQUENCE = "PUN_ID_SEQ";


    /** Pun Id */
    @Id
    @SequenceGenerator(name = SEQUENCE_GENERATOR_NAME, sequenceName = ENTITY_ID_SEQUENCE)
    @GeneratedValue(generator = SEQUENCE_GENERATOR_NAME)
//    @NotNull(message = VIOLATION_NOT_NULL)
    private Long punId;

    /** Record Version */
    @PositiveOrZero(message = VIOLATION_POSITIVE_OR_ZERO)
    @Version
    @Column
    private Integer version;

    /** Record Created */
    @PastOrPresent(message = VIOLATION_PAST_OR_PRESENT)
    @Column
    private ZonedDateTime created;

    /** Record Edited */
    @PastOrPresent(message = VIOLATION_PAST_OR_PRESENT)
    @Column
    private ZonedDateTime edited;

    /** Title */
    @Size(min = SIZE_STRING_MIN, max = SIZE_NAME_MAX, message = VIOLATION_SIZE)
    @Column
    private String title;

    /** Prompt (optional) */
    @Size(min = SIZE_STRING_MIN, max = SIZE_NOTE_MAX, message = VIOLATION_SIZE)
    @Column
    private String prompt;

    /** Statement */
    @Size(min = SIZE_STRING_MIN, max = SIZE_NOTE_MAX, message = VIOLATION_SIZE)
    @Column
    private String statement;

    /** Pun Type */
    @Enumerated(EnumType.STRING)
    private PunType punType;

    /** Enabled */
    @Column
    private Boolean enabled;


}
