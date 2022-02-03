package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    static AdminResource adminResource=null;
    private CustomerService customerService;
    private ReservationService reservationService;
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void addRoom(IRoom room){
        reservationService.addRoom(room);
    }

    public Collection<IRoom> getAllRooms(){return reservationService.getRooms();}

    public Collection<Customer> getAllCustomers(){return customerService.getAllCustomers();}

    public void displayAllReservations(){
        for(Reservation reservation:reservationService.getReservations()){
            System.out.println(reservation);
        }
    }
}
