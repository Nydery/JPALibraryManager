package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import at.htlleonding.persistence.entities.Author;
import at.htlleonding.persistence.entities.Book;
import at.htlleonding.persistence.entities.Genre;
import at.htlleonding.persistence.entities.Topic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
 LibraryLogic: Interface to the library data. Provides an entity-free interface to the outside.
 Responisibilities: Takes data from outside (e.g. via strings) and creates or updates the entities in the repository
 Collaborations: Uses LibraryRepository to persist and retrieve entities

 */
@ApplicationScoped
public class LibraryLogic {
    //FirstName, LastName, Date of birth, Date of death.
    //To parse the date:
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    //var dateOfBirth = LocalDate.parse("27.05.1958", dtf);
    private final String[][] authors = {
            new String[] {"Manfred", "Spitzer", "27.05.1958", ""},
            new String[] {"George", "Orwell", "25.06.1903", "21.01.1950"},
            new String[] {"Winston", "Smith", "25.06.1903", "21.01.1945"}
    };

    //Author LastName, Title, Genre [, other authors' lastName]*
    private final String[][] books = {
            new String[]{"Spitzer", "Lernen", "Nonfiction"},
            new String[]{"Spitzer", "Musik im Kopf", "Nonfiction"},
            new String[]{"Orwell", "1984", "Fiction", "Smith"},
            new String[]{"Orwell", "Animal Farm", "Fiction"}
    };

    //Author LastName, Title, [Topic]+
    private final String[][] topics = {
            new String[]{"Spitzer", "Lernen", "Lernpsychologie", "Psychologie", "Populärwissenschaft"},
            new String[]{"Spitzer", "Musik im Kopf", "Lernpsychologie", "Musik"},
            new String[]{"Orwell", "1984", "Dystopie", "Prophezeiung"},
            new String[]{"Orwell", "Animal Farm", "Dystopie", "Biographie"}
    };

    @Inject
    private LibraryRepository repository;

    //TODO 3: use the data in the above string arrays to add some books to the library, using the methods in the repository
    //it is ok to hard code the data in the statements
    @Transactional
    public void createSampleData() {
        var authorObjs = Arrays.stream(authors)
                .map(al -> new Author(al[0], al[1], al[2], al[3]))
                .collect(Collectors.toList());

        var genres = new LinkedList<Genre>();
        for(var bdata : books) {
            var book = new Book();
            book.setTitle(bdata[1]);
            var bookGenre = getGenre(genres, bdata[2]);
            var bookAuthors = getAuthors(authorObjs, bdata);
            var bookTopics = getTopics(topics, book);

            //Persist
            boolean first = true;
            for (var a : bookAuthors) {
                repository.add(book, a, first);
                first = false;
            }

            for (var t : bookTopics) {
                repository.add(book, t);
            }

            repository.add(book, bookGenre);
        }
    }

    private Topic[] getTopics(String[][] topics, Book book) {
        var result = new LinkedList<Topic>();

        for(var tdata : topics){
            if(tdata[1].equals(book.getTitle())){
                var topic = new Topic(tdata[1]);
                result.add(topic);
            }
        }

        return result.toArray(Topic[]::new);
    }

    private Author[] getAuthors(List<Author> authors, String[] data){
        var result = new LinkedList<Author>();

        var names = new LinkedList<String>();
        names.add(data[0]);

        names.addAll(Arrays.asList(data).subList(3, data.length));

        //Get from author list
        for (var a : authors) {
            for(var n : names){
                if(a.getLastName().equals(n))   //FUCKING ";" FUCKED UP MY FUCKING CODE
                    result.add(a);
            }
        }

        return result.toArray(Author[]::new);
    }

    private Genre getGenre(List<Genre> genres, String keyword){
        var result = genreExists(genres, keyword);
        if(result == null){
            result = new Genre(keyword);
            genres.add(result);
        }

        return result;
    }

    private Genre genreExists(List<Genre> genres, String keyword){
        for(var g : genres){
            if(g.getKeyword().equals(keyword))
                return g;
        }

        return null;
    }
}
