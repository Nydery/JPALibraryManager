package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import at.htlleonding.persistence.entities.*;
import at.htlleonding.persistence.models.*;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

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

    public void flushAndClear() {
        repository.flushAndClear();
    }

    public Long addAuthor(AuthorModel authorModel) {
        var authorDB = mapper.map(authorModel, Author.class);
        var result = repository.add(authorDB);
        
        return result.getId();
    }

    public Long addMediaExemplar(MediaExemplarModel exemplarModel) {
        //Add reference objects to db first
        addMediaItem(exemplarModel.getMediaItem());
        addLanguage(exemplarModel.getLanguage());
        addPublisher(exemplarModel.getPublisher());

        var mediaExemplarDB = mapper.map(exemplarModel, MediaExemplar.class);
        var result =  repository.add(mediaExemplarDB);

        return result.getId();
    }

    @Transactional
    public Long addCustomer(CustomerModel customerModel){
        var customerDB = mapper.map(customerModel, Customer.class);
        var result =  repository.add(customerDB);

        return result.getId();
    }

    public Long addEmployee(EmployeeModel employeeModel){
        var employeeDB = mapper.map(employeeModel, Employee.class);
        var result =  repository.add(employeeDB);

        return result.getId();
    }

    public Long addGenre(GenreModel genreModel){
        var genreDB = mapper.map(genreModel, Genre.class);
        var result =  repository.add(genreDB);

        return result.getId();
    }

    public Long addLanguage(LanguageModel languageModel){
        var languageDB = mapper.map(languageModel, Language.class);
        var result =  repository.add(languageDB);

        return result.getId();
    }

    public Long addMediaItem(MediaItemModel mediaItemModel){
        //Add reference objects to db first
        addGenre(mediaItemModel.getGenre());

        var mediaItemDB = mapper.map(mediaItemModel, MediaItem.class);
        var result =  repository.add(mediaItemDB);

        return result.getId();
    }

    public Long addMediaType(MediaTypeModel mediaTypeModel){
        var mediaTypeDB = mapper.map(mediaTypeModel, MediaType.class);
        var result =  repository.add(mediaTypeDB);

        return result.getId();
    }

    public Long addPublisher(PublisherModel publisherModel) {
        var publisherDB = mapper.map(publisherModel, Publisher.class);
        var result =  repository.add(publisherDB);

        return result.getId();
    }

    public Long addReceipt(ReceiptModel receiptModel){
        addCustomer(receiptModel.getCustomer());
        addEmployee(receiptModel.getEmployee());

        var receiptDB = mapper.map(receiptModel, Receipt.class);
        var result =  repository.add(receiptDB);

        return result.getId();
    }

    public Long addReservation(ReservationModel reservationModel){
        addCustomer(reservationModel.getCustomer());
        addEmployee(reservationModel.getEmployee());

        var reservationDB = mapper.map(reservationModel, Reservation.class);
        var result =  repository.add(reservationDB);

        return result.getId();
    }

    public Long addSale(SaleModel saleModel){
        addReceipt(saleModel.getReceipt());

        var saleDB = mapper.map(saleModel, Sale.class);
        var result =  repository.add(saleDB);

        return result.getId();
    }

    public Long addTopic(TopicModel topicModel){
        var topicDB = mapper.map(topicModel, Topic.class);
        var result =  repository.add(topicDB);

        return result.getId();
    }

    //-------------------------------------------------------
    // Get by Id methods
    //-------------------------------------------------------


    public CustomerModel getCustomerById(long id){
        //var temp = repository.getById(Customer.class, id);
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



    public boolean isMediaExemplarRentable(long mediaExemplarId) {
        return ((MediaExemplar)repository.getById(MediaExemplar.class, mediaExemplarId)).isForRent();
    }

}
