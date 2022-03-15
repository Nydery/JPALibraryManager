package at.htlleonding.persistence;
///home/peter/src/dbi4/quarkus-hibernate-cmdline

import at.htlleonding.persistence.entities.*;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;

// @Transactional
// https://quarkus.io/guides/transaction
// https://quarkus.io/guides/hibernate-orm
// Mark your CDI bean method as @Transactional and the EntityManager will enlist and flush at commit.
// Make sure to wrap methods modifying your database (e.g. entity.persist()) within a transaction.
// Marking a CDI bean method @Transactional will do that for you and make that method a transaction boundary.
// We recommend doing so at your application entry point boundaries like your REST endpoint controllers.

/*
 LibraryRepository: Implements methods to work with entities.
 Responsibility: Performs actions on entities, e.g. to persist, create associations and retrieve by queries.
 Collaborations: Uses EntityManager to access storage-layer.
 */

@ApplicationScoped
public class LibraryRepository {

    @Inject
    EntityManager entityManager;

    //TODO 2: Add add-Methods for the various entities
    //Note: when adding associations, make sure to update !!!*ALL*!!! PARTICIPANTS of the association!!! (ask me how I know... ;) )
    @Transactional
    public void add(Genre g){
        if(g == null)
            throw new IllegalArgumentException("g");

        entityManager.persist(g);
    }
    @Transactional
    public void add(Author a){
        if(a == null)
            throw new IllegalArgumentException("a");

        entityManager.persist(a);
    }

    @Transactional
    public void add(Topic t) {
        if(t == null)
            throw new IllegalArgumentException("t");

        entityManager.persist(t);
    }

    @Transactional
    public void add(Language l) {
        if(l == null)
            throw new IllegalArgumentException("l");

        entityManager.persist(l);
    }

    @Transactional
    public void add(MediaItem mi) {
        if(mi == null)
            throw new IllegalArgumentException("mi");

        entityManager.persist(mi);
    }

    @Transactional
    public void add(Publisher p) {
        if(p == null)
            throw new IllegalArgumentException("p");

        entityManager.persist(p);
    }

    //----------------------------
    //Special adds
    /*
    @Transactional
    public void add(Book b, Author a, boolean isPrimaryAuthor) {
        if(b == null)
            throw new IllegalArgumentException("b");
        else if (a == null)
            throw new IllegalArgumentException("a");

        add(b);
        add(a);

        var ba = new BookAuthor(b, a, isPrimaryAuthor);
        entityManager.persist(ba);
    }

    //TODO 2: Add add(Book b, Topic t)
    @Transactional
    public void add(Book b, Topic t){
        if(b == null)
            throw new IllegalArgumentException("b");
        else if(t == null)
            throw new IllegalArgumentException("t");

        add(b);
        add(t);

        b.getTopics().add(t);
    }

    //TODO 2: Add add(Book b, Genre g)
    @Transactional
    public void add(Book b, Genre g){
        if(b == null)
            throw new IllegalArgumentException("b");
        else if(g == null)
            throw new IllegalArgumentException("g");

        add(g);

        b.setGenre(g);
    }
 */
}
