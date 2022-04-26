package at.htlleonding;

import at.htlleonding.persistence.entities.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
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
    Language language1;
    Language language2;
    Publisher publisher;


    MediaExemplar exemplar1;

    private void clearDB() {
        target.clear();
        target.flush();
    }

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

    private void createLanguages() {
        language1 = new Language();
        language1.setKeyword("German");

        language2 = new Language();
        language2.setKeyword("English");

        target.persist(language1);
        target.persist(language2);
    }

    private void createPublisher() {
        publisher = new Publisher();
        publisher.setName("Tor");

        target.persist(publisher);
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

    @Test
    @TestTransaction
    public void createMediaExemplar_From1984MediaItem_ExpectOneResult() {
        createLanguages();
        createPublisher();

        exemplar1 = new MediaExemplar();
        exemplar1.setMediaItem(mediaItem);
        exemplar1.setBuyDate(LocalDate.now());
        exemplar1.setPublisher(publisher);
        exemplar1.setLanguage(language1);

        target.persist(exemplar1);

        target.flush();
        target.clear();

        //Check if in DB
        var exempl = target.createQuery("select me from MediaExemplar me", MediaExemplar.class).getSingleResult();

        Assertions.assertNotNull(exempl);
        Assertions.assertEquals(exemplar1.getId(), exempl.getId());

    }


    @Test
    @TestTransaction
    public void deleteMediaExemplar_From1948MediaItem_ExpectedNoResult(){

        createLanguages();
        createPublisher();

        exemplar1 = new MediaExemplar();
        exemplar1.setMediaItem(mediaItem);
        exemplar1.setBuyDate(LocalDate.now());
        exemplar1.setPublisher(publisher);
        exemplar1.setLanguage(language1);

        target.persist(exemplar1);
        target.flush();
        var var_id = exemplar1.getId();

       target.clear();

        var exempl = target.createQuery("select me from MediaExemplar me where me.id = :var_id", MediaExemplar.class)
                .setParameter("var_id", var_id).getSingleResult();

        target.remove(exempl);


        Assertions.assertFalse(target.contains(exempl));
    }

}
