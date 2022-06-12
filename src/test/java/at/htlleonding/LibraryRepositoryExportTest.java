package at.htlleonding;

import at.htlleonding.persistence.LibraryRepository;
import at.htlleonding.persistence.entities.Author;
import at.htlleonding.persistence.entities.Genre;
import at.htlleonding.persistence.entities.MediaExemplar;
import at.htlleonding.persistence.entities.MediaItem;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
public class LibraryRepositoryExportTest {
    @Inject
    LibraryRepository target;

    private void insertTestData() {
        var author = new Author();
        author.setLastName("Orwell");
        author.setFirstName("George");

        //Build mediaitem
        var mediaItem = new MediaItem();
        mediaItem.setTitle("1984");

        var genre = new Genre("reality");

        target.add(genre);
        target.add(author);
        target.add(mediaItem);

        //Relations
        mediaItem.setGenre(genre);
        mediaItem.getAuthors().add(author);
        author.getMediaItems().add(mediaItem);

        //update entities
        target.update(author);
        target.update(mediaItem);
    }

    @Test
    @Transactional
    public void getWorksOfAuthorsFromRepository_checkCompleteStructure() {
        insertTestData();

        var export = target.getMediaOfAuthors();

        Assertions.assertNotNull(export);
        Assertions.assertEquals(1, export.getAuthors().size());
        Assertions.assertEquals("Orwell", export.getAuthors().get(0).getLastName());
        Assertions.assertEquals(1, export.getAuthors().get(0).getWorks().size());
        Assertions.assertEquals(0, export.getAuthors().get(0).getWorks().get(0).getPublications().size());
    }
}
