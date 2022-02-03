package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ReservationService {
    public static ReservationService reservationService=null;
    private ArrayList<IRoom> rooms;
    private ArrayList<Reservation> reservations;

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public ArrayList<IRoom> getRooms() {
        return rooms;
    }

    public void addRoom(IRoom room){
        rooms.add(room);
    }

    public IRoom getARoom(String roomId){
        for(IRoom room:rooms){
            if(room.getRoomNumber().equals(roomId)){
                return room;}
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation=new Reservation(customer,room,checkInDate,checkOutDate);
        reservations.add(reservation);
        return reservation;
    }


    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        ArrayList<IRoom> reservedRooms=new ArrayList<>();
        ArrayList<IRoom> availableRooms=new ArrayList<>();
        for(Reservation reservation:reservations){
            if(reservation.getCheckInDate().getTime()<=checkInDate.getTime()&&
                    reservation.getCheckOutDate().getTime() >= checkOutDate.getTime()){
                reservedRooms.add(reservation.getRoom());
            }
        }
        for(IRoom room:rooms){
            if(!reservedRooms.contains(room)){
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        ArrayList<Reservation> array=new ArrayList<>();
        for(Reservation reservation:reservations){
            if(reservation.getCustomer().equals(customer)){
                array.add(reservation);
            }
        }
        return array;
    }

    public Collection<Reservation> getCustomersReservation(String customerEmail){
        ArrayList<Reservation> array=new ArrayList<>();
        for(Reservation reservation:reservations){
            if(reservation.getCustomer().getEmail().equals(customerEmail)){
                array.add(reservation);
            }
        }
        return array;
    }

    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }



}
