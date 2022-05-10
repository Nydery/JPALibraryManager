package at.htlleonding;

import at.htlleonding.persistence.LibraryRepository;
import at.htlleonding.persistence.entities.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@QuarkusTest
public class LibraryRepositoryTest {
    @Inject
    LibraryRepository repository;

    //@Transactional
    private Author[] createAuthors() {
        Author a1 = new Author();
        a1.setFirstName("George");
        a1.setLastName("Orwell");

        Author a2 = new Author();
        a2.setFirstName("Astred");
        a2.setLastName("Lindgren");

        //repository.add(a1);
        //repository.add(a2);

        return new Author[] {a1, a2};
    }

    //@Transactional
    private Genre createGenre() {
        Genre g1 = new Genre();
        g1.setKeyword("Fantasy");

        //repository.add(g1);

        return g1;
    }

    //@Transactional
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

    private MediaItem assembleMediaItem() {
        var authors = createAuthors();
        var topics = createTopics();
        var genre = createGenre();

        return createMediaItem("1984", genre, authors, topics);
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


    @Test
    @TestTransaction
    public void addMediaItem_With2Authors2Topics_Expect1ItemInDb() {
        //Create entities and assemble them into mediaitem
        var mediaItem = assembleMediaItem();

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
        var mediaItem = assembleMediaItem();
        persistMediaItem(mediaItem);


    }

    @Test
    @TestTransaction
    public void getAllLanguages() {
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
    public void addEntity_checkIfExists(){
        var pub = new Publisher();
        pub.setName("Google Inc.");

        repository.add(pub);

        var pubId = pub.getId();
        Assertions.assertNotEquals(0, pubId);

        var pubAfter = repository.getById(Publisher.class, pubId);

        Assertions.assertNotNull(pubAfter);
    }
    //Further testing - min. 2 per method in repository
}
