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

    public <T extends IEntity> T updateEntity(T entity) {
        return (T) repository.update(entity);
    }


    //-------------------------------------------------------
    // Add methods
    //-------------------------------------------------------

    @Transactional
    public Long addAuthor(AuthorModel authorModel) {
        var authorDB = mapper.map(authorModel, Author.class);
        var result = repository.add(authorDB);

        return result.getId();
    }

    @Transactional
    public Long addMediaExemplar(MediaExemplarModel exemplarModel) {
        var mediaExemplarDB = mapper.map(exemplarModel, MediaExemplar.class);

        //Denkfehler: Mediaitem also contains other entity references... (Maybe call addMediaItem(MediaItem item) to findOrInsert underlying other entities??)
        var mediaItem = repository.findOrInsert(mediaExemplarDB.getMediaItem());
        var language = repository.findOrInsert(mediaExemplarDB.getLanguage());
        var publisher = repository.findOrInsert((mediaExemplarDB.getPublisher()));

        mediaExemplarDB.setMediaItem(mediaItem);
        mediaExemplarDB.setLanguage(language);
        mediaExemplarDB.setPublisher(publisher);

        var result =  repository.add(mediaExemplarDB);
        return result.getId();
    }

    @Transactional
    public Long addCustomer(CustomerModel customerModel){
        var customerDB = mapper.map(customerModel, Customer.class);
        var result =  repository.add(customerDB);

        return result.getId();
    }

    @Transactional
    public Long addEmployee(EmployeeModel employeeModel){
        var employeeDB = mapper.map(employeeModel, Employee.class);
        var result =  repository.add(employeeDB);

        return result.getId();
    }

    @Transactional
    public Long addGenre(GenreModel genreModel){
        var genreDB = mapper.map(genreModel, Genre.class);
        var result =  repository.add(genreDB);

        return result.getId();
    }

    @Transactional
    public Long addLanguage(LanguageModel languageModel){
        var languageDB = mapper.map(languageModel, Language.class);
        var result =  repository.add(languageDB);

        return result.getId();
    }

    @Transactional
    public Long addMediaItem(MediaItemModel mediaItemModel){
        //Add reference objects to db first
        var mediaItemDB = mapper.map(mediaItemModel, MediaItem.class);

        var genre = repository.findOrInsert(mediaItemDB.getGenre());
        mediaItemDB.setGenre(genre);

        var result =  repository.add(mediaItemDB);

        return result.getId();
    }

    @Transactional
    public Long addMediaType(MediaTypeModel mediaTypeModel){
        var mediaTypeDB = mapper.map(mediaTypeModel, MediaType.class);
        var result =  repository.add(mediaTypeDB);

        return result.getId();
    }

    @Transactional
    public Long addPublisher(PublisherModel publisherModel) {
        var publisherDB = mapper.map(publisherModel, Publisher.class);
        var result =  repository.add(publisherDB);

        return result.getId();
    }

    @Transactional
    public Long addReceipt(ReceiptModel receiptModel){
        var receiptDB = mapper.map(receiptModel, Receipt.class);

        var customer = repository.findOrInsert(receiptDB.getCustomer());
        var employee = repository.findOrInsert(receiptDB.getEmployee());
        receiptDB.setCustomer(customer);
        receiptDB.setEmployee(employee);

        var result =  repository.add(receiptDB);

        return result.getId();
    }

    @Transactional
    public Long addReservation(ReservationModel reservationModel){
        //addCustomer(reservationModel.getCustomer());
        //addEmployee(reservationModel.getEmployee());

        var reservationDB = mapper.map(reservationModel, Reservation.class);
        var result =  repository.add(reservationDB);

        return result.getId();
    }

    @Transactional
    public Long addSale(SaleModel saleModel){
        //addReceipt(saleModel.getReceipt());

        var saleDB = mapper.map(saleModel, Sale.class);
        var result =  repository.add(saleDB);

        return result.getId();
    }

    @Transactional
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
        return mapper.map(cust, CustomerModel.class);
    }

    public EmployeeModel getEmployeeById(long id){
        Employee emp = (Employee) repository.getById(Employee.class, id);
        return mapper.map(emp, EmployeeModel.class);
    }

    public AuthorModel getAuthorById(long id) {
        Author a = (Author) repository.getById(Author.class, id);
        var result = mapper.map (a, AuthorModel.class);
        return result;
    }

    public MediaExemplarModel getMediaExemplarById(long id) {
        MediaExemplar me = (MediaExemplar) repository.getById(MediaExemplar.class, id);
        return mapper.map(me, MediaExemplarModel.class);
    }

    public MediaItemModel getMediaItemById(long id) {
        MediaItem mi = (MediaItem) repository.getById(MediaItem.class, id);
        return mapper.map(mi, MediaItemModel.class);
    }


    //-------------------------------------------------------
    // Special getters
    //-------------------------------------------------------

    public GenreModel getGenreByKeyword(String keyword) {
        //var result = repository.
        return null;
    }

    //-------------------------------------------------------
    // Special methods
    //-------------------------------------------------------

    public boolean isMediaExemplarRentable(long mediaExemplarId) {
        var ientity = repository.getById(MediaExemplar.class, mediaExemplarId);
        var result = (MediaExemplar)ientity;

        return result.isForRent();
    }

}
