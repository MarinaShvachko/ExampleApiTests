package pojoClasses;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private String additionalneeds;

    @Setter(AccessLevel.PRIVATE)
    private int bookingId;

    //to create json in json
    BookingDates bookingdates;

    public Booking addBookingAllFieldsFilled(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds, String checkin, String checkout) {
        Booking booking = new Booking();
        booking.setFirstname(firstname);
        booking.setLastname(lastname);
        booking.setTotalprice(totalprice);
        booking.setDepositpaid(depositpaid);
        booking.setAdditionalneeds(additionalneeds);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin);
        bookingDates.setCheckout(checkout);
        booking.setBookingdates(bookingDates);

        return booking;
    }
}
