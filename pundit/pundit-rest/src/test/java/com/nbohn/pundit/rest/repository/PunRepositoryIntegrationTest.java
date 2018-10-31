package com.nbohn.pundit.rest.repository;

import com.nbohn.pundit.rest.configuration.JpaConfiguration;
import com.nbohn.pundit.rest.model.entity.Pun;
import com.nbohn.pundit.rest.model.entity.PunSample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Pun Repository Integration Test
 */
@RunWith(SpringRunner.class)                    // Use JUnit Spring Test Runner.
//@DataJpaTest                                    // Identify this as a Data JPA Test.
@ContextConfiguration(classes = JpaConfiguration.class) // JPA Configuration
@Transactional                                          // Each test is wrapped in a transaction.
public class PunRepositoryIntegrationTest extends AbstractJpaRepositoryTest<Pun, Long> {

//    /** Spring Test Entity Manager */
//    @Autowired
//    private TestEntityManager testEntityManager;

    /** Repository */
    @Autowired
    private PunRepository repository;

    /** Entity Sample */
    private final Pun entitySample = PunSample.getSample();

    /**
     * Get Repository
     *
     * @return Repository.
     */
    @Override
    protected JpaRepository<Pun, Long> getRepository() {
        return repository;
    }

    /**
     * Get Entity Sample.
     *
     * @return Entity Sample.
     */
    @Override
    protected Pun getEntitySample() {
        return entitySample;
    }

    /**
     * Change Entity to test Updates.
     *
     * @param entityToChange Entity to change.
     */
    @Override
    protected void changeEntity(Pun entityToChange) {
        entityToChange.setEnabled(!entityToChange.getEnabled());
    }


    // Custom Tests for this Entity:
    // -----------------------------

    @Test
    public void testDeleteByTitle() {
        Long deletedCount = repository.deleteByTitle(entitySample.getTitle());
        assertNotNull("Deleted count is null.", deletedCount);
        assertEquals("Deleted count is not 1.", (Long) 1L, deletedCount);
    }

    @Test
    public void testFindByType() {
        List<Pun> puns = repository.findByPunType(entitySample.getPunType());
        assertEntityInList(entitySaved, puns);
    }

    @Test
    public void testFindByName() {
        List<Pun> puns = repository.findByTitle(entitySample.getTitle());
        assertEntityInList(entitySaved, puns);
    }

}
