package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/*
 LibraryLogic: Interface to the library data. Provides an entity-free interface to the outside.
 Responisibilities: Takes data from outside (e.g. via strings) and creates or updates the entities in the repository
 Collaborations: Uses LibraryRepository to persist and retrieve entities

 */
@ApplicationScoped
public class LibraryLogic {
    @Inject
    LibraryRepository repository;


}
