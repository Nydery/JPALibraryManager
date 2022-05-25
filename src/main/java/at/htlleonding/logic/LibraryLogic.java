package at.htlleonding.logic;

import at.htlleonding.persistence.*;
import at.htlleonding.persistence.entities.*;
import at.htlleonding.persistence.models.*;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.stream.Collectors;

/*
 LibraryLogic: Interface to the library data. Provides an entity-free interface to the outside.
 Responsibilities: Takes data from outside (e.g. via strings) and creates or updates the entities in the repository
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

        //Perist relation objects (if they exist get them -> add..() calls findOrInsert()) before persisting model itself
        var mediaItemId = addMediaItem(exemplarModel.getMediaItem());
        var languageId = addLanguage(exemplarModel.getLanguage());
        var publisherId = addPublisher(exemplarModel.getPublisher());
        var mediaTypeId = addMediaType(exemplarModel.getMediaType());

        mediaExemplarDB.setMediaItem((MediaItem) repository.getById(MediaItem.class, mediaItemId));
        mediaExemplarDB.setLanguage((Language) repository.getById(Language.class, languageId));
        mediaExemplarDB.setPublisher((Publisher) repository.getById(Publisher.class, publisherId));
        mediaExemplarDB.setMediaType((MediaType) repository.getById(MediaType.class, mediaTypeId));

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

        //Alternative to modelmapper not mapping correctly....
        //Add authors manually and get those entities again to add to MediaItem entity
        var authorsModels = mediaItemModel.getAuthors();
        var authorsDB = new HashSet<Author>();

        for(var a : mediaItemDB.getAuthors()) {
            var aId = addAuthor(mapper.map(a, AuthorModel.class));
            authorsDB.add((Author) repository.getById(Author.class, aId));
        }
        mediaItemDB.setAuthors(authorsDB);
        //--

        var genreId = addGenre(mapper.map(mediaItemDB.getGenre(), GenreModel.class));
        mediaItemDB.setGenre((Genre) repository.getById(Genre.class, genreId));

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

        var customerId = addCustomer(receiptModel.getCustomer());
        var employeeId = addEmployee(receiptModel.getEmployee());

        receiptDB.setCustomer((Customer) repository.getById(Customer.class, customerId));
        receiptDB.setEmployee((Employee) repository.getById(Employee.class, employeeId));

        var result =  repository.add(receiptDB);
        return result.getId();
    }

    @Transactional
    public Long addReservation(ReservationModel reservationModel){
        var reservationDB = mapper.map(reservationModel, Reservation.class);

        var customerId = addCustomer(reservationModel.getCustomer());
        var employeeId = addEmployee(reservationModel.getEmployee());

        reservationDB.setCustomer((Customer) repository.getById(Customer.class, customerId));
        reservationDB.setEmployee((Employee) repository.getById(Employee.class, employeeId));

        var result =  repository.add(reservationDB);
        return result.getId();
    }

    @Transactional
    public Long addSale(SaleModel saleModel){
        var saleDB = mapper.map(saleModel, Sale.class);

        var mediaExemplarId = addMediaExemplar(saleModel.getMediaExemplar());
        var receiptId = addReceipt(saleModel.getReceipt());

        saleDB.setMediaExemplar((MediaExemplar) repository.getById(MediaExemplar.class, mediaExemplarId));
        saleDB.setReceipt((Receipt) repository.getById(Receipt.class, receiptId));

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
    //TODO: Add special "manual mappings" for entities with n:m relations
    //TODO: Add setter for n:m relation Set<>'s
    public AuthorModel getAuthorById(long id) {
        Author a = (Author) repository.getById(Author.class, id);
        var result = mapper.map (a, AuthorModel.class);
        return result;
    }
    public CustomerModel getCustomerById(long id){
        //var temp = repository.getById(Customer.class, id);
        Customer cust = (Customer) repository.getById(Customer.class, id);
        return mapper.map(cust, CustomerModel.class);
    }
    public EmployeeModel getEmployeeById(long id){
        Employee emp = (Employee) repository.getById(Employee.class, id);
        return mapper.map(emp, EmployeeModel.class);
    }
    public GenreModel getGenreById(long id) {
        Genre gen = (Genre) repository.getById(Genre.class, id);
        return mapper.map(gen, GenreModel.class);
    }
    public LanguageModel getLanguageById(long id){
        Language lan = (Language) repository.getById(Language.class, id);
        return mapper.map(lan, LanguageModel.class);
    }
    public MediaExemplarModel getMediaExemplarById(long id) {
        MediaExemplar me = (MediaExemplar) repository.getById(MediaExemplar.class, id);
        var result = mapper.map(me, MediaExemplarModel.class);

        var authors = me.getMediaItem().getAuthors();
        var authorModels = authors.stream().map(a -> mapper.map(a, AuthorModel.class)).collect(Collectors.toSet());
        result.getMediaItem().setAuthors(authorModels);

        return result;
    }
    public MediaItemModel getMediaItemById(long id) {
        MediaItem mi = (MediaItem) repository.getById(MediaItem.class, id);
        return mapper.map(mi, MediaItemModel.class);
    }
    public MediaTypeModel getMediaTypeById(long id) {
        MediaType mt = (MediaType) repository.getById(MediaType.class, id);
        return mapper.map(mt, MediaTypeModel.class);
    }
    public PublisherModel getPublisherById(long id) {
        Publisher pub = (Publisher) repository.getById(Publisher.class, id);
        return mapper.map(pub, PublisherModel.class);
    }
    public ReceiptModel getReceiptById(long id) {
        Receipt rec = (Receipt) repository.getById(Receipt.class, id);
        return mapper.map(rec, ReceiptModel.class);
    }
    public ReservationModel getReservationById(long id) {
        Reservation res = (Reservation) repository.getById(Reservation.class, id);
        return mapper.map(res, ReservationModel.class);
    }
    public SaleModel getSaleById(long id) {
        Sale sale = (Sale) repository.getById(Sale.class, id);
        return mapper.map(sale, SaleModel.class);
    }
    public TopicModel getTopicById(long id) {
        Topic topic = (Topic) repository.getById(Topic.class, id);
        return mapper.map(topic, TopicModel.class);
    }

    //-------------------------------------------------------
    // Special getters - by BusinessKey
    //-------------------------------------------------------

    public GenreModel getGenreByKeyword(String keyword) {
        var result = repository.getGenreByKeyword(keyword);
        return mapper.map(result, GenreModel.class);
    }

    public TopicModel getTopicByKeyword(String keyword) {
        var result = repository.getTopicByKeyword(keyword);
        return mapper.map(result, TopicModel.class);
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
