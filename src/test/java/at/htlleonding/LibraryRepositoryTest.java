package at.htlleonding;

import at.htlleonding.persistence.LibraryRepository;
import at.htlleonding.persistence.entities.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

@QuarkusTest
public class LibraryRepositoryTest {
    @Inject
    LibraryRepository repository;

    private Author[] createAuthors() {
        Author a1 = new Author();
        a1.setFirstName("George");
        a1.setLastName("Orwell");

        Author a2 = new Author();
        a2.setFirstName("Astred");
        a2.setLastName("Lindgren");

        return new Author[] {a1, a2};
    }

    private Genre createGenre() {
        Genre g1 = new Genre();
        g1.setKeyword("Fantasy");

        return g1;
    }

    private Topic[] createTopics() {
        Topic t1 = new Topic();
        t1.setKeyword("Philosophy");

        Topic t2 = new Topic();
        t2.setKeyword("Literature");

        return new Topic[] {t1, t2};
    }

    private MediaItem createMediaItem(String title, Genre genre, Author[] authors, Topic[] topics) {
        var mediaItem = new MediaItem();
        mediaItem.setTitle(title);
        mediaItem.setGenre(genre);

        if(topics != null && topics.length > 0)
            mediaItem.getTopics().addAll(List.of(topics));
        if(authors != null && authors.length > 0)
            mediaItem.getAuthors().addAll(List.of(authors));

        return mediaItem;
    }

    private MediaItem assembleMediaItem(String title, boolean withAuthors, boolean withTopics) {
        var authors = withAuthors ? createAuthors() : new Author[0];
        var topics = withTopics ? createTopics() : new Topic[0];
        var genre = createGenre();

        return createMediaItem(title, genre, authors, topics);
    }

    @Transactional
    private void persistMediaItem(MediaItem item) {
        repository.add(item.getGenre());

        for(var t : item.getTopics())
            repository.add(t);

        for(var a : item.getAuthors())
            repository.add(a);

        repository.add(item);
    }


    //-------------------------------------------------------
    // Helper methods above
    //
    // Tests from here
    //-------------------------------------------------------

    @Test
    @TestTransaction
    public void addMediaItem_With2Authors2Topics_Expect1ItemInDb() {
        //Create entities and assemble them into mediaitem
        var mediaItem = assembleMediaItem("1984", true, true);

        //persist entities
        persistMediaItem(mediaItem);

        //Check if persisted
        var mediaItems = repository.getAll(MediaItem.class);

        Assertions.assertNotNull(mediaItems);
        Assertions.assertEquals(1, mediaItems.size());

        var actualItem = (MediaItem)mediaItems.get(0);
        Assertions.assertEquals("1984", actualItem.getTitle());
        Assertions.assertEquals(2, actualItem.getAuthors().size());
    }

    @Test
    @TestTransaction
    public void addMediaExemplarBook_ofMediaItem_Expect1ExemplarInDb(){
        var mediaItem = assembleMediaItem("Pipi Langstrumpf", true, true);
        persistMediaItem(mediaItem);

        //var mediaExemplar = createMediaExemplar(mediaItem);
    }

    @Test
    @TestTransaction
    public void add_twoLanguages_Expect2LanguagesAtGetAll() {
        var l1 = new Language();
        l1.setKeyword("Deutsch");

        var l2 = new Language();
        l2.setKeyword("Englisch");

        repository.add(l1);
        repository.add(l2);

        var languages = repository.getAll(Language.class);
        Assertions.assertEquals(2, languages.size());
    }

    @Test
    @TestTransaction
    public void add_PublisherAndGetByIdAgain_ExpectSameObjectAsInserted(){
        var pub = new Publisher();
        pub.setName("Google Inc.");

        var pubId = repository.add(pub).getId();

        Assertions.assertNotEquals(0, pubId);

        var pubAfter = (Publisher) repository.getById(Publisher.class, pubId);

        Assertions.assertNotNull(pubAfter);
        Assertions.assertEquals(pub.getName(), pubAfter.getName());
    }

    @Test
    @TestTransaction
    public void addAndRemove_PublisherUsingGenericRepoMethods_ExpectNoEntityWithIdAfterRemove(){
        var pub = new Publisher();
        pub.setName("Google Inc.");

        //Insert into db
        var pubId = repository.add(pub).getId();
        Assertions.assertNotEquals(0, pubId);

        /* Commented out, because this is checked in another unit test

        //Get by id and check if exists again
        var pubAfterAdd = (Publisher) repository.getById(Publisher.class, pubId);
        Assertions.assertNotNull(pubAfterAdd);
        Assertions.assertEquals(pub.getName(), pubAfterAdd.getName());
         */

        Assertions.assertThrows(NoResultException.class, () -> {
            repository.remove(pub);
            //getting publisher by its previous id should throw NoResultException
            var pubAfterRemove = repository.getById(Publisher.class, pubId);
        });
    }

    @Test
    @TestTransaction
    public void add_MediaItemWithoutTopicsAdd1TopicAfterwards_Expect1TopicInDbAndInMediaItem() {
        var mediaItem = assembleMediaItem("Caillou's revenge", true, false);

        persistMediaItem(mediaItem);

        //check if mediaItem exists
        var actualMediaItem = (MediaItem) repository.getById(MediaItem.class, mediaItem.getId());
        Assertions.assertNotNull(actualMediaItem);
        Assertions.assertEquals(mediaItem.getTitle(), actualMediaItem.getTitle());

        //Add topic to existent mediaitem
        var topic = new Topic();
        topic.setKeyword("Children");

        repository.add(actualMediaItem, topic);

        //check if topic exists in db
        var actualTopic = (Topic)repository.getById(Topic.class, topic.getId());
        Assertions.assertEquals(topic.getKeyword(), actualTopic.getKeyword());

        //check if topic is accessible through mediaitem
        Assertions.assertEquals(1, actualMediaItem.getTopics().size());
    }
}
