package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource=null;
    private CustomerService customerService;
    private ReservationService reservationService;
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(lastName,firstName,email);
    }
    public IRoom getRoom(String roomNumber){return reservationService.getARoom(roomNumber);}

    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date CheckOutDate){
        //Customer customer=new Customer(null,null,customerEmail);
        return reservationService.reserveARoom(customer,room,checkInDate,CheckOutDate);
    }
    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return reservationService.getCustomersReservation(customerEmail);
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);
    }


}
