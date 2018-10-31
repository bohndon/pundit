package com.nbohn.pundit.rest.initialization;

import com.nbohn.pundit.rest.model.entity.Pun;
import com.nbohn.pundit.rest.model.enumeration.PunType;
import com.nbohn.pundit.rest.repository.PunRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * Initialize Data
 * This class is run when Spring Boot is started.
 */
@Component
public class InitializeData implements CommandLineRunner {

    /** Logger */
    private static final Logger LOGGER = LogManager.getLogger(InitializeData.class);

    /** Pun Repository */
    private final PunRepository punRepository;

    /**
     * Constructor
     * @param punRepository Pun Repository
     */
    public InitializeData(PunRepository punRepository) {
        this.punRepository = punRepository;
    }

    /**
     * Run
     * @param args Arguments
     * @throws Exception Runtime exception.
     */
    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("Initializing data ...");

        Long records = 0L;

        Pun pun = new Pun();
        records++;
        pun.setPunId(records);
        pun.setVersion(0);
        pun.setCreated(ZonedDateTime.now());
        pun.setEdited(pun.getCreated());
        pun.setTitle("First Pun");
        pun.setPrompt("Two silk worms had a race.");
        pun.setStatement("It ended in a tie.");
        pun.setPunType(PunType.HOMOPHONIC);
        pun.setEnabled(true);
        punRepository.save(pun);

        pun = new Pun();
        records++;
        pun.setPunId(2L);
        pun.setVersion(0);
        pun.setCreated(ZonedDateTime.now());
        pun.setEdited(pun.getCreated());
        pun.setTitle("Second Pun");
        pun.setPrompt("Why do you have to go to the Optometrist?");
        pun.setStatement("Because I can't see any other way.");
        pun.setPunType(PunType.HOMOGRAPHIC);
        pun.setEnabled(false);
        punRepository.save(pun);

        LOGGER.info("Inserted {} records at {}.", records, ZonedDateTime.now());

    }
}
