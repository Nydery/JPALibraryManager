package at.htlleonding.persistence;

import at.htlleonding.common.Common;
import at.htlleonding.misc.BusinessKey;
import at.htlleonding.persistence.entities.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

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
    public IEntity add(IEntity entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity");

        return findOrInsert(entity);
    }

    @Transactional
    public IEntity update(IEntity entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity");

        return entityManager.merge(entity);
    }

    /*
      Given an Entity with BusinessKey-annotated fields,
      tries to find a matching entity in the DB.
      If found, it returns this entity, otherwise it persists the given entity and returns it.
     */
    public <T extends IEntity> T findOrInsert(T e) {
        Class c = e.getClass();

        if(e.getId() != 0)
            return (T)getById(c, e.getId());


        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(c);
        var root = cq.from(c);

        var fields = c.getDeclaredFields();
        List<Predicate> predicates = new ArrayList<>();

        for (Field f : fields) {
            try {
                if (f.getAnnotation(BusinessKey.class) != null) {
                    f.setAccessible(true);
                    var n = f.getName();
                    var v = f.get(e);
                    var p = cb.equal(root.get(n), v);
                    predicates.add(p);
                }
            }
            catch (IllegalAccessException iae) {
            }
        }

        var q = cq.select(root).where(predicates.toArray(Predicate[]::new));
        var qq = entityManager.createQuery(q);
        T dbEntity = null;
        try {
            dbEntity = (T) qq.getSingleResult();
        }
        catch(NoResultException nre){
            entityManager.persist(e);
            dbEntity = e;
        }

        return dbEntity;
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

    public IEntity getById(Type entityType, long id) {
        String tableName = entityType.getTypeName();

        String query = String.format("select t from %s t where t.id = %s", tableName, id);
        return entityManager.createQuery(query, IEntity.class)
                .getSingleResult();
    }


    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
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

    //Special getters
    public Genre getGenreByKeyword(String keyword) {
        var result = entityManager.createQuery("select g from Genre g where g.keyword = :key")
                .setParameter("key", keyword)
                .getSingleResult();
        return null;


    }
}
