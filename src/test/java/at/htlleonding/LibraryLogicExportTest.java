package at.htlleonding;

import at.htlleonding.logic.LibraryLogic;
import at.htlleonding.persistence.enums.MediaTypes;
import at.htlleonding.persistence.models.*;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@QuarkusTest
public class LibraryLogicExportTest {
    @Inject
    LibraryLogic target;

    @Transactional
    private void insertTestData() {
        //Build relation models
        var author = new AuthorModel();
        author.setLastName("Orwell");
        author.setFirstName("George");
        author.setDateOfBirth(LocalDate.parse("25.06.1903", DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        author.setDateOfDeath(LocalDate.parse("21.01.1950", DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        var mediaItem = new MediaItemModel();
        mediaItem.setTitle("1984");
        mediaItem.getAuthors().add(author);

        var genre = new GenreModel();
        genre.setKeyword("reality");
        mediaItem.setGenre(genre);

        var mediatype = new MediaTypeModel();
        mediatype.setPrice(10.0);
        mediatype.setType(MediaTypes.Book);

        var language = new LanguageModel();
        language.setKeyword("German");

        var publisher = new PublisherModel();
        publisher.setName("Simon RS");

        //Assemble mediaexemplar
        var mediaExemplar = new MediaExemplarModel();
        mediaExemplar.setBuyDate(LocalDate.now());
        mediaExemplar.setForRent(false);
        mediaExemplar.setForSale(false);
        mediaExemplar.setMediaItem(mediaItem);
        mediaExemplar.setMediaType(mediatype);
        mediaExemplar.setLanguage(language);
        mediaExemplar.setPublisher(publisher);

        //insert
        target.addMediaExemplar(mediaExemplar);
    }

    @Test
    @Transactional
    public void insertTestData_exportToXml_checkOutput() throws IOException {
        insertTestData();

        var filepath = "./currentLibrary.xml";
        var exportSuccess = target.exportToXML(filepath);

        Assertions.assertTrue(exportSuccess);
    }
}
