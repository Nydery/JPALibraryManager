package at.htlleonding;

import at.htlleonding.logic.LibraryLogic;
import at.htlleonding.persistence.entities.Genre;
import at.htlleonding.persistence.entities.MediaExemplar;
import at.htlleonding.persistence.enums.MediaTypes;
import at.htlleonding.persistence.models.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

@QuarkusTest
class LibraryMgmtLogicTest {

    @Inject
    LibraryLogic target;

    //-------------------------------------------------------
    // Helper methods for tests
    //-------------------------------------------------------
    private void flushAndClear() {
        target.flushAndClear();
    }

    private MediaItemModel createMediaItem(String title, String genre, String[] topics) {
        var result = new MediaItemModel();

        result.setTitle(title);
        var genr = new GenreModel();
        genr.setKeyword(genre);

        result.setGenre(genr);

        for (var t : topics) {
            var topic = new TopicModel();
            topic.setKeyword(t);
            result.getTopics().add(topic);
        }

        return result;
    }

    private AuthorModel createAuthor(String firstName, String lastName) {
        var result = new AuthorModel();

        result.setFirstName(firstName);
        result.setLastName(lastName);

        return result;
    }

    private MediaExemplarModel createMediaExemplar(MediaItemModel item, MediaTypes mediatype, LocalDate buyDate, String language, String publisherName, boolean forSale, boolean forRent) {
        var result = new MediaExemplarModel();

        result.setMediaItem(item);
        result.setBuyDate(buyDate);
        result.setForSale(forSale);
        result.setForRent(forRent);

        var pub = new PublisherModel();
        pub.setName(publisherName);

        result.setPublisher(pub);

        var lang = new LanguageModel();
        lang.setKeyword(language);
        result.setLanguage(lang);

        var type = new MediaTypeModel();
        type.setType(mediatype);
        type.setPrice(0);
        result.setMediaType(type);

        return result;
    }

    /*
    Add rentable items to the library, of each media type, with multiple authors and attributes.
    Verify that these items can be rented.
    */
    @Test
    @TestTransaction
    public void addPaperBookWithOneAuthor_makeRentable_canBeRented()
    {
        var mediaItem = createMediaItem("1984", "dystopian", new String[] {"idktopic"});
        var author1 = createAuthor("Amel", "Sarvan");

        var author1Id = target.addAuthor(author1);
        var eAuthor = target.getAuthorById(author1Id);
        mediaItem.getAuthors().add(eAuthor);

        var mediaExemplar =
                createMediaExemplar(mediaItem, MediaTypes.Book, LocalDate.now(), "Deutsch", "HTL Leonding", false, true);

        //Modelmapper in addMediaExemplar() doesnt map Hashmap of authormodels to hashmap of authors => authors get lost: HOW DO WE FIX THIS??? HELP
        //Fixed: see implementation of addMediaExemplar() below
        var meId = target.addMediaExemplar(mediaExemplar);

        //Check if 1 author is set
        var meModel = target.getMediaExemplarById(meId);
        var miModel = target.getMediaItemById(meModel.getMediaItem().getId());

        Assertions.assertEquals(1, miModel.getAuthors().size());

        //Check if rentable
        var actual = target.isMediaExemplarRentable(meId);
        Assertions.assertTrue(actual);
    }

    @Test
    @TestTransaction
    public void addPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        var mediaItem = createMediaItem("Benjamin Blümchen", "comic", new String[] {"fiction", "horror"});

        var author1 = createAuthor("Benjamin", "Imsirovic");
        var author2 = createAuthor("Simon", "Rausch-Schott");
        var author3 = createAuthor("Jakob", "Lehner");

        var a1Id = target.addAuthor(author1);
        var a2Id = target.addAuthor(author2);
        var a3Id = target.addAuthor(author3);

        var eAuthor1 = target.getAuthorById(a1Id);
        var eAuthor2 = target.getAuthorById(a2Id);
        var eAuthor3 = target.getAuthorById(a3Id);

        mediaItem.getAuthors().add(eAuthor1);
        mediaItem.getAuthors().add(eAuthor2);
        mediaItem.getAuthors().add(eAuthor3);

        var mediaExemplar =
                createMediaExemplar(mediaItem, MediaTypes.Book, LocalDate.now(), "Deutsch", "HTL Leonding", true, true);

        var meId = target.addMediaExemplar(mediaExemplar);


        //Check if 3 authors are set
        var meModel = target.getMediaExemplarById(meId);
        var miModel = target.getMediaItemById(meModel.getMediaItem().getId());

        Assertions.assertEquals(3, miModel.getAuthors().size());

        //Check if rentable
        var actual = target.isMediaExemplarRentable(meId);
        Assertions.assertTrue(actual);
    }

    @Test
    @TestTransaction
    public void addThreeCopiesOfPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        var mediaItem = createMediaItem("Benjamin Blümchen", "comic", new String[] {"fiction", "horror"});

        var author1 = createAuthor("Benjamin", "Imsirovic");
        var author2 = createAuthor("Simon", "Rausch-Schott");
        var author3 = createAuthor("Jakob", "Lehner");

        var a1Id = target.addAuthor(author1);
        var a2Id = target.addAuthor(author2);
        var a3Id = target.addAuthor(author3);

        var eAuthor1 = target.getAuthorById(a1Id);
        var eAuthor2 = target.getAuthorById(a2Id);
        var eAuthor3 = target.getAuthorById(a3Id);

        mediaItem.getAuthors().add(eAuthor1);
        mediaItem.getAuthors().add(eAuthor2);
        mediaItem.getAuthors().add(eAuthor3);

        var mediaExemplar =
                createMediaExemplar(mediaItem, MediaTypes.Book, LocalDate.now(), "Deutsch", "HTL Leonding", false, true);

        var mediaExemplar2 =
                createMediaExemplar(mediaItem, MediaTypes.Book, LocalDate.now(), "Englisch", "HTL Leonding", false, true);

        var mediaExemplar3 =
                createMediaExemplar(mediaItem, MediaTypes.Book, LocalDate.now(), "Französisch", "HTL Leonding", false, true);

        var meId = target.addMediaExemplar(mediaExemplar);
        var meId2 = target.addMediaExemplar(mediaExemplar2);
        var meId3 = target.addMediaExemplar(mediaExemplar3);

        //Check if 3 authors are set
        var meModel = target.getMediaExemplarById(meId);
        var meModel2 = target.getMediaExemplarById(meId2);
        var meModel3 = target.getMediaExemplarById(meId3);

        var miModel = target.getMediaItemById(meModel.getMediaItem().getId());
        var miModel2 = target.getMediaItemById(meModel2.getMediaItem().getId());
        var miModel3 = target.getMediaItemById(meModel3.getMediaItem().getId());

        Assertions.assertEquals(3, miModel.getAuthors().size());
        Assertions.assertEquals(3, miModel2.getAuthors().size());
        Assertions.assertEquals(3, miModel3.getAuthors().size());

        //Check if rentable
        var actual = target.isMediaExemplarRentable(meId);
        var actual2 = target.isMediaExemplarRentable(meId2);
        var actual3 = target.isMediaExemplarRentable(meId3);

        Assertions.assertTrue(actual);
        Assertions.assertTrue(actual2);
        Assertions.assertTrue(actual3);
    }

    @Test
    @TestTransaction
    public void addNewspaperWithOneAuthor_makeRentable_canBeRented()
    {
        var mediaItem = createMediaItem("Bravo", "boulevard", new String[] {"fiction", "horror"});

        var author1 = createAuthor("Amel", "Sarvan");

        var a1Id = target.addAuthor(author1);

        var eAuthor1 = target.getAuthorById(a1Id);

        mediaItem.getAuthors().add(eAuthor1);

        var mediaExemplar =
                createMediaExemplar(mediaItem, MediaTypes.Newspaper, LocalDate.now(), "Deutsch", "HTL Leonding", false, true);

        var meId = target.addMediaExemplar(mediaExemplar);

        //Check if 1 authors are set
        var meModel = target.getMediaExemplarById(meId);

        var miModel = target.getMediaItemById(meModel.getMediaItem().getId());

        Assertions.assertEquals(1, miModel.getAuthors().size());

        //Check if rentable
        var actual = target.isMediaExemplarRentable(meId);

        Assertions.assertTrue(actual);

        var mediumType = target.getMediaTypeById(meId);
        var mediaTypes = mediumType.getType();

        Assertions.assertEquals(MediaTypes.Newspaper, mediaTypes);
    }

    @Test
    @TestTransaction
    public void addAudioBookWithOneAuthor_makeRentable_canBeRented()
    {
        var mediaItem = createMediaItem("IT", "Thriller", new String[] {"fiction", "horror"});

        var author1 = createAuthor("Steven", "King");

        var a1Id = target.addAuthor(author1);

        var eAuthor1 = target.getAuthorById(a1Id);

        mediaItem.getAuthors().add(eAuthor1);

        var mediaExemplar =
                createMediaExemplar(mediaItem, MediaTypes.AudioBook, LocalDate.now(), "Deutsch", "Apress", false, true);

        var meId = target.addMediaExemplar(mediaExemplar);

        //Check if 1 authors are set
        var meModel = target.getMediaExemplarById(meId);

        var miModel = target.getMediaItemById(meModel.getMediaItem().getId());

        Assertions.assertEquals(1, miModel.getAuthors().size());

        //Check if rentable
        var actual = target.isMediaExemplarRentable(meId);

        Assertions.assertTrue(actual);

        var mediumType = target.getMediaTypeById(meId);
        var mediaTypes = mediumType.getType();

        Assertions.assertEquals(MediaTypes.AudioBook, mediaTypes);
    }

    @Test
    @TestTransaction
    public void addEBookWithOneAuthor_makeRentable_canBeRented()
    {
        var mediaItem = createMediaItem("IT", "Thriller", new String[] {"fiction", "horror"});

        var author1 = createAuthor("Steven", "King");

        var a1Id = target.addAuthor(author1);

        var eAuthor1 = target.getAuthorById(a1Id);

        mediaItem.getAuthors().add(eAuthor1);

        var mediaExemplar =
                createMediaExemplar(mediaItem, MediaTypes.EBook, LocalDate.now(), "Deutsch", "Tor", false, true);

        var meId = target.addMediaExemplar(mediaExemplar);

        //Check if 1 authors are set
        var meModel = target.getMediaExemplarById(meId);

        var miModel = target.getMediaItemById(meModel.getMediaItem().getId());

        Assertions.assertEquals(1, miModel.getAuthors().size());

        //Check if rentable
        var actual = target.isMediaExemplarRentable(meId);

        Assertions.assertTrue(actual);

        var mediumType = target.getMediaTypeById(meId);
        var mediaTypes = mediumType.getType();

        Assertions.assertEquals(MediaTypes.EBook, mediaTypes);
    }

    @Test
    @TestTransaction
    public void addJournalWithOneAuthor_makeRentable_canBeRented()
    {
        var mediaItem = createMediaItem("IT", "Thriller", new String[] {"fiction", "horror"});

        var author1 = createAuthor("Steven", "King");

        var a1Id = target.addAuthor(author1);

        var eAuthor1 = target.getAuthorById(a1Id);

        mediaItem.getAuthors().add(eAuthor1);

        var mediaExemplar =
                createMediaExemplar(mediaItem, MediaTypes.Magazine, LocalDate.now(), "Englisch", "Tor", false, true);

        var meId = target.addMediaExemplar(mediaExemplar);

        //Check if 1 authors are set
        var meModel = target.getMediaExemplarById(meId);

        var miModel = target.getMediaItemById(meModel.getMediaItem().getId());

        Assertions.assertEquals(1, miModel.getAuthors().size());

        //Check if rentable
        var actual = target.isMediaExemplarRentable(meId);

        Assertions.assertTrue(actual);

        var mediumType = target.getMediaTypeById(meId);
        var mediaTypes = mediumType.getType();

        Assertions.assertEquals(MediaTypes.Magazine, mediaTypes);
    }


    /*
     Add a library customer.
     Add a library employee.
     */
    @Test
    @TestTransaction
    public void addLibraryCustomer_isAvailable()
    {
        CustomerModel c = new CustomerModel();
        c.setFirstName("Marcel");
        c.setLastName("Davis");
        c.setEmail("m.davis@1und1.com");
        c.setEmployee(false);
        c.setPhoneNumber("0123456789");

        var cId = target.addCustomer(c);

        //Check if available in db
        var checkC = target.getCustomerById(cId);

        Assertions.assertNotNull(checkC);
        Assertions.assertEquals("Marcel", checkC.getFirstName());
        Assertions.assertEquals("Davis", checkC.getLastName());
    }

    @Test
    @TestTransaction
    public void addLibraryEmployee_isAvailable()
    {
        EmployeeModel e = new EmployeeModel();
        e.setFirstName("Marcel");
        e.setLastName("Davis");

        e.setSalary(1_500);

        var eId = target.addEmployee(e);

        //Check if available in db
        var checkC = target.getEmployeeById(eId);

        Assertions.assertNotNull(checkC);
        Assertions.assertEquals("Marcel", checkC.getFirstName());
        Assertions.assertEquals("Davis", checkC.getLastName());
        Assertions.assertEquals(1500, checkC.getSalary());
    }

    /*
     Rent out, bring back, reserve and prolong.
     Verify state of rented items and customer's rent list.
    */
    @Test
    @TestTransaction
    public void customerRentsRentableItem_ItemIsRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsOneOfThreeCopiesOfRentableItem_TwoRentableItemsRemain_CustomerHasRent()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsThreeOfThreeCopiesOfRentableItem_TryRentAnother_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerOneItemOfEachMediaType_ItemsAreRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForSale_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setItemForOnDisplay_customerTriesToRent_RentNotPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsSingleAvailableItem_RentNotPossible_BringsBackItem_RentPossible()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void rentOutItemToCustomerA_customerBmakesReservation_CustomerAreturnsItem_RentPossibleOnlyForCustomerB()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentThreeTimes_customerCanOnlyProlongTwoTimes_rentalEndDate6weeksAhead()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void customerRentsItem_prolongsRentTwoTimes_EmployeeProlongsOneTime_rentalEndDate8weeksAhead()
    {
        Assertions.fail("Not implemented yet");
    }

    /*
      - Declare a library item to be for sale, it cannot be rented anymore.
      - Sell one library item to a customer, create invoice. Item cannot be rented anymore.
      - Sell some items of multiple books.
     */
    @Test
    @Transactional
    public void setItemForSale_cannotBeRented()
    {
        GenreModel g = new GenreModel();
        g.setKeyword("Fictional");

        MediaItemModel model = new MediaItemModel();
        model.setGenre(g);
        model.setTitle("Der Sacklschupfer im Lagerhaus");
        var itemID = target.addMediaItem(model);

        MediaExemplarModel exemplarModel = createMediaExemplar(model, MediaTypes.Book, LocalDate.now(), "English", "Sony", true, false);
        var id = target.addMediaExemplar(exemplarModel);

        Assertions.assertEquals(exemplarModel.isForRent(), false);
    }

    @Test
    @TestTransaction
    public void setOneOfTwoItemsForSale_onlyOneCanBeRented()
    {


        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void setThreeDifferentItemsForSale_CustomerBuys2_InvoiceHasTwoItems_OnlyOneItemForRent()
    {


        Assertions.fail("Not implemented yet");
    }
}