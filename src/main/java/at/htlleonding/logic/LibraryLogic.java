package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import at.htlleonding.persistence.entities.*;
import at.htlleonding.persistence.models.*;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.awt.*;

/*
 LibraryLogic: Interface to the library data. Provides an entity-free interface to the outside.
 Responisibilities: Takes data from outside (e.g. via strings) and creates or updates the entities in the repository
 Collaborations: Uses LibraryRepository to persist and retrieve entities
 */
@ApplicationScoped
public class LibraryLogic {
    @Inject
    LibraryRepository repository;

    ModelMapper mapper = new ModelMapper();

    public Long addAuthor(AuthorModel authorModel) {
        var authorDB = mapper.map(authorModel, Author.class);
        return repository.add(authorDB).getId();
    }

    public Long addMediaExemplar(MediaExemplarModel exemplarModel) {
        var mediaExemplarDB = mapper.map(exemplarModel, MediaExemplar.class);
        return repository.add(mediaExemplarDB).getId();
    }

    public Long addCustomer(CustomerModel customerModel){
        var customerDB = mapper.map(customerModel, Customer.class);
        return repository.add(customerDB).getId();
    }

    public Long addEmployee(EmployeeModel employeeModel){
        var employeeDB = mapper.map(employeeModel, Customer.class);
        return repository.add(employeeDB).getId();
    }

    public Long addGenre(GenreModel genreModel){
        var genreDB = mapper.map(genreModel, Genre.class);
        return repository.add(genreDB).getId();
    }

    public Long addLanguage(LanguageModel languageModel){
        var languageDB = mapper.map(languageModel, Language.class);
        return repository.add(languageDB).getId();
    }

    public Long addMediaItem(MediaItemModel mediaItemModel){
        var mediaItemDB = mapper.map(mediaItemModel, MediaItem.class);
        return repository.add(mediaItemDB).getId();
    }

    public Long addMediaType(MediaTypeModel mediaTypeModel){
        var mediaTypeDB = mapper.map(mediaTypeModel, MediaType.class);
        return repository.add(mediaTypeDB).getId();
    }

    public Long addPerson(PersonModel personModel){
        var personDB = mapper.map(personModel, Person.class);
        return repository.add(personDB).getId();
    }


    public CustomerModel getCustomerById(long id){
        Customer cust = (Customer) repository.getById(Customer.class, id);
        CustomerModel model = new CustomerModel();
        mapper.map(cust, model);
        return model;
    }

    public EmployeeModel getEmployeeById(long id){
        Employee emp = (Employee) repository.getById(Employee.class, id);
        EmployeeModel model = new EmployeeModel();
        mapper.map(emp, model);
        return model;
    }
    /*
    public void add(IEntity entity) {
        repository.add(entity);
    }

    public void remove(IEntity entity) {
        repository.remove(entity);
    }
    */
}
