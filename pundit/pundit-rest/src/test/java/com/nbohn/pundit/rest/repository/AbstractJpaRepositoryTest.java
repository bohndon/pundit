package com.nbohn.pundit.rest.repository;

import com.nbohn.pundit.rest.model.entity.EntityBeanUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Abstract JPA Repository Test
 * <ul>
 *  <li><b>E</b> - Entity class type.</li>
 *  <li><b>I</b> - Entity's id field class type.</li>
 * </ul>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)   // Run tests in name ascending order to ensure proper persistence order.
public abstract class AbstractJpaRepositoryTest<E extends Serializable, I extends Serializable> {

    /** Entity Sample Saved. */
    E entitySaved;

    /**
     * Get Repository
     * @return Repository.
     */
    protected abstract JpaRepository<E, I> getRepository();

    /**
     * Get Entity Sample.
     * @return Entity Sample.
     */
    protected abstract E getEntitySample();

    /**
     * Change Entity to test Updates.
     * @param entityToChange    Entity to change.
     */
    protected abstract void changeEntity(E entityToChange);


    @Before
    public void before() {
        // Seed Repository with sample entity.
        entitySaved = getRepository().save(getEntitySample());
        getRepository().flush();
        assertNotNull("Entity sample not saved.", entitySaved);
    }

    /**
     * Create test.
     * <p>
     *     Creates a test entity and compares the saved entity bean to the original test entity bean.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void test10Create() throws Exception {
        assertNotNull("Created bean is null.", entitySaved);

        // If Id Field:
        final I id = (I) EntityBeanUtil.getAnnotatedFieldValue(entitySaved, Id.class);
        if(id != null) {
            // Set the saved Id in the entity sample with the entity saved Id so it will pass equal assertion.
            EntityBeanUtil.getAnnotatedField(getEntitySample(), Id.class).set(getEntitySample(), id);
        }

        assertEquals("Created entity not equal to sample.", getEntitySample(), entitySaved);
    }

    /**
     * Read All test.
     * <p>
     *  Retrieves all entries for an entity and verifies at least one is found and all entities found are not null.
     * </p>
     * <p>
     *  <b>WARNING:</b> This test may be too expensive if a large volume of entities exist. If this is the case,
     *  the implementor may want to override this method for a particular repository to limit scope and lessen duration.
     * </p>
     */
    @Test
    public void test20ReadAll()
    {
        final List<E> entities = getRepository().findAll();
        assertEntityInList(entitySaved, entities);
    }

    /**
     * Read One test.
     * <p>
     *    Retrieves and tests a single entity for a defined test Id value.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void test30ReadOne() throws Exception {
        final I id = (I) EntityBeanUtil.getAnnotatedFieldValue(entitySaved, Id.class);
        final E entity = getRepository().findById(id).orElse(null);
        assertNotNull("Entity retrieved is null.", entity);
        assertEquals("Entity retrieved not equal to entity sought.", entitySaved, entity);
    }

    /**
     * Update test.
     * <p>
     *     Updates the entity sample saved and verifies:
     *     <ol>
     *      <li>Version (if present) is incremented.</li>
     *      <li>The updated entity equals the entity that was retrieved after updating.</li>
     *     </ol>
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void test40Update() throws Exception {
        final I id = (I) EntityBeanUtil.getAnnotatedFieldValue(entitySaved, Id.class);

        changeEntity(entitySaved);
        getRepository().save(entitySaved);
        getRepository().flush();
        final E entity = getRepository().findById(id).orElse(null);
        assertNotNull("Entity retrieved is null.", entity);

        // If Version Field present:
        Field versionField = EntityBeanUtil.getAnnotatedField(entitySaved, Version.class);
        if(versionField != null) {

            final Object versionPrior = versionField.get(entitySaved);
            final Object versionUpdated = EntityBeanUtil.getAnnotatedFieldValue(entity, Version.class);

            // Assert version increment.
            if(versionPrior != null && versionPrior instanceof Integer) {
                assertEquals("Entity version not increased.", ((Integer) versionPrior + 1), versionUpdated);
            }

            // Set the udpated version in the saved entity so it will pass the equal assertion.
            versionField.set(entitySaved, versionUpdated);
            assertEquals("Entity retrieved not equal to updated entity.", entitySaved, entity);
        }
    }

    /**
     * Delete test.
     * <p>
     *     Deletes the entity sample saved and verifies the entity is no longer retrievable.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void test50Delete() throws Exception {
        final I id = (I) EntityBeanUtil.getAnnotatedFieldValue(entitySaved, Id.class);
        getRepository().delete(entitySaved);
        getRepository().flush();
        final E entity = getRepository().findById(id).orElse(null);
        assertNull("Entity was not deleted.", entity);
    }


    /**
     * Assert that a list is not null, not empty, does not contain any null entities, and contains the specified entity.
     * @param entity Entity that the list should contain.
     * @param list List that is asserted.
     */
    void assertEntityInList(final Object entity, final List list) {
        assertNotNull("List is null.", list);
        assertTrue("List is empty.", list.size() > 0);
        for (Object element : list) {
            assertNotNull("List contains a null entity.", element);
        }
        assertTrue(MessageFormat.format("List does not contain entity: {0}", entity), list.contains(entity));
    }

}
