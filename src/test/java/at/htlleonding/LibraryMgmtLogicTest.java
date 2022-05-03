package at.htlleonding;

import at.htlleonding.logic.LibraryLogic;
import at.htlleonding.persistence.entities.Customer;
import at.htlleonding.persistence.entities.MediaExemplar;
import at.htlleonding.persistence.entities.MediaItem;
import at.htlleonding.persistence.models.*;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;

@QuarkusTest
class LibraryMgmtLogicTest {

    @Inject
    LibraryLogic target;
    /*
    Helper methods for tests
    */
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

    private MediaExemplar createMediaExemplar(MediaItem item, LocalDate buyDate, String language, PublisherModel publisher, boolean forSale) {
        var result = new MediaExemplarModel();

        result.setMediaItem(item);
        result.setBuyDate(buyDate);
        result.setForSale(forSale);

        result.set
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
        target.addMediaItem(mediaItem);
        var mediaExemplar = createMediaExemplar(mediaItem);


        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addThreeCopiesOfPaperBookWithThreeAuthors_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addNewspaperWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addAudioBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addEBookWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
    }

    @Test
    @TestTransaction
    public void addJournalWithOneAuthor_makeRentable_canBeRented()
    {
        Assertions.fail("Not implemented yet");
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

        target.addCustomer(c);

        //Check if available in db
        var checkC = target.getCustomerById(c.getId());

        Assertions.assertNotNull(checkC);
        Assertions.assertEquals("Marcel", checkC.getFirstName());
        Assertions.assertEquals("Davis", checkC.getLastName());
    }

    @Test
    @TestTransaction
    public void addLibraryEmployee_isAvailable()
    {
        EmployeeModel c = new EmployeeModel();
        c.setFirstName("Marcel");
        c.setLastName("Davis");
        c.setSalary(1_500);

        target.addEmployee(c);

        //Check if available in db
        var checkC = target.getEmployeeById(c.getId());

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
    @TestTransaction
    public void setItemForSale_cannotBeRented()
    {

        Assertions.fail("Not implemented yet");
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