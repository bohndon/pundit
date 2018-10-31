package com.nbohn.pundit.rest.validation;

/**
 * Validation Violation Messages
 * Violation messages are named after the validation annotations in which they associated.
 */
public final class ViolationMessages {
    /**
     * Private Constructor to prevent instantiation.
     */
    private ViolationMessages() {
    }

    /** Null Violation */
    public static final String VIOLATION_NOT_NULL = "Property can not be null.";

    /** Length Violation */
    public static final String VIOLATION_SIZE = "Value size is out of limits (min or max).";

    /** Positive Non-Zero Violation */
    public static final String VIOLATION_POSITIVE_OR_ZERO = "Value must be positive or zero.";

    /** Past or Present Violation */
    public static final String VIOLATION_PAST_OR_PRESENT = "Value must be in the past or present.";
}
