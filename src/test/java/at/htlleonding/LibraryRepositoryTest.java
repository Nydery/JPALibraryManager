package at.htlleonding;

import at.htlleonding.persistence.LibraryRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class LibraryRepositoryTest {
    @Inject
    LibraryRepository repository;

    @Test
    @TestTransaction
    public void add___() {
        //Implement tests
    }

    //Further testing
}
