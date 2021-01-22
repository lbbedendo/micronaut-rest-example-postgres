package diecast.collector.api.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Consumer;

import static java.nio.file.Files.readAllBytes;

public abstract class AbstractIntegrationTest {

    @Inject
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    private Consumer<String> runSript = scriptName -> {
        try {
            EntityTransaction transaction = getActiveTransaction();

            URL sqlResource = getClass().getClassLoader()
                    .getResource(scriptName);

            if (sqlResource == null) {
                throw new FileNotFoundException("Resource not found: " + scriptName);
            }

            String sqlResourcePath = sqlResource.getPath();
            Path sqlFilePath = new File(sqlResourcePath).toPath();

            entityManager.createNativeQuery(new String(readAllBytes(sqlFilePath)))
                    .executeUpdate();

            transaction.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @BeforeEach
    public void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void cleanUp() {
        entityManager.close();
    }

    protected void runSql(String... sqlFileNames) {
        Arrays.stream(sqlFileNames).forEach(runSript);
    }

    private EntityTransaction getActiveTransaction() {
        EntityTransaction transaction = entityManager.getTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }

        return transaction;
    }
}
