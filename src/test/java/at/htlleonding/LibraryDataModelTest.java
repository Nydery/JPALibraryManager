package at.htlleonding;

import at.htlleonding.persistence.entities.Author;
import at.htlleonding.persistence.entities.Genre;
import at.htlleonding.persistence.entities.MediaItem;
import at.htlleonding.persistence.entities.Topic;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@QuarkusTest
public class LibraryDataModelTest {
    @Inject
    EntityManager target;

    Author author1;
    Author author2;
    Topic topic1;
    Topic topic2;
    Genre genre;
    MediaItem mediaItem;

    private void createAuthors() {
        author1 = new Author("George", "Orwell");
        author2 = new Author("William", "Shakespeare");

        target.persist(author1);
        target.persist(author2);
    }

    private void createTopics() {
        topic1 = new Topic("Politics");
        topic2 = new Topic("SciFi");

        target.persist(topic1);
        target.persist(topic2);
    }

    private void createGenre() {
        genre = new Genre("Dystopian");

        target.persist(genre);
    }

    private void create1984() {
        mediaItem = new MediaItem();
        mediaItem.setTitle("1984");
        mediaItem.setGenre(genre);
        var authors = new Author[] {
                author1, author2
        };

        mediaItem.getAuthors().addAll(List.of(authors));

        var topics = new Topic[] {
                topic1, topic2
        };
        mediaItem.getTopics().addAll(List.of(topics));

        target.persist(mediaItem);
    }

    @Test
    @TestTransaction
    public void createMediaItem_ExpectOneResultInDB() {
        createAuthors();
        createTopics();
        createGenre();
        create1984();

        Assertions.assertTrue(mediaItem.getId() > 0);

        target.flush();
        target.clear();

        var item = target.createQuery("select m from MediaItem m", MediaItem.class).getSingleResult();

        Assertions.assertEquals(2, item.getAuthors().size());
        Assertions.assertEquals(2, item.getTopics().size());
        Assertions.assertNotNull(item.getGenre());
    }
}
