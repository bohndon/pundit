package com.nbohn.pundit.rest.model.enumeration;


import org.junit.Test;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Pun Type Test
 */
public class PunTypeTest {

    private List<PunType> punTypes = Arrays.asList(PunType.values());

    @Test
    public void testExistence() {
        assertNotNull("Pun Types are null.", punTypes);
        assertTrue("Pun Types are empty.", punTypes.size() > 0);
    }

    @Test
    public void testDescriptions() {
        punTypes.forEach(punType -> {
            assertNotNull(MessageFormat.format("{0} Description is null", punType), punType.getDescription());
            assertTrue(MessageFormat.format("{0} Description is empty.", punType), punType.getDescription().length() > 0);
        });
    }
}
