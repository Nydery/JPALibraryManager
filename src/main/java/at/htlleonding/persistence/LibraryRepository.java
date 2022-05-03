package at.htlleonding.persistence;
///home/peter/src/dbi4/quarkus-hibernate-cmdline

import at.htlleonding.persistence.entities.*;

import java.lang.reflect.Type;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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


    @Transactional
    public void add(IEntity entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity");

        entityManager.persist(entity);
    }

    @Transactional
    public void remove (IEntity entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity");

        entityManager.remove(entity);
    }

    public List getAll(Type entityType) {
        String tableName = entityType.getTypeName();

        return entityManager.createQuery(String.format("select t from %s t", tableName))
                .getResultList();
    }

    @Transactional
    public void saveChanges() {
        entityManager.flush();
    }

    //----------------------------
    //Special adds

    //MediaItem
    @Transactional
    public void add(MediaItem item, Author author) {
        if(item == null)
            throw new IllegalArgumentException("item");
        else if(author == null)
            throw new IllegalArgumentException("author");

        item.getAuthors().add(author);
        author.getMediaItems().add(item);

        entityManager.persist(item);
    }

    @Transactional
    public void add(MediaItem item, Topic topic) {
        if(item == null)
            throw new IllegalArgumentException("item");
        else if (topic == null)
            throw new IllegalArgumentException("topic");

        item.getTopics().add(topic);
        topic.getMediaItems().add(item);

        entityManager.persist(item);
    }
}
