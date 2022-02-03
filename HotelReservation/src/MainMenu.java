import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static AdminResource adminResource;
    private static HotelResource hotelResource;
    private static Scanner scanner;
    public static void main(String[] args){
        scanner=new Scanner(System.in);
        try{
            boolean exit=false;
            while(!exit){
                String selection=showMeun();
                switch(selection){
                    case "1" -> reserveRoom();
                    case "2" -> seeReservation();
                    case "3" -> createAccount();
                    case "4" ->{
                        AdminMenu.setAdminResource(adminResource);
                        AdminMenu.adminStart();
                    }
                    case "5" -> exit=true;
                    default -> showMeun();
                }
            }
            System.exit(0);
        }
        catch(Exception ex){ex.getLocalizedMessage();}

        finally{scanner.close();}
    }

    private static String showMeun(){
        System.out.println("__________________________________________________");
        System.out.println("   MAIN MENU");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("__________________________________________________");
        System.out.println("Enter menu:");
        return scanner.nextLine();
    }

    private static void reserveRoom() throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter CheckIn Date dd/mm/yyyy example (01/02/2021)");
        Date checkInDate=dateFormat.parse(scanner.nextLine());
        System.out.println("Enter CheckOut Date dd/mm/yyyy example (01/02/2021)");
        Date checkOutDate=dateFormat.parse(scanner.nextLine());
        Collection<IRoom> rooms=hotelResource.findARoom(checkInDate,checkOutDate);

        if(!rooms.isEmpty()) {
            Customer customer;
            System.out.println("Would you like to book a room? y/n");
            char choice = scanner.next().trim().charAt(0);
            if (choice == 'y') {
                System.out.println("Do you have an account with us? y/n");
                char optionHasAccount = scanner.next().trim().charAt(0);
                if (optionHasAccount == 'y') {
                    System.out.println("Enter Email format:name@domain.com");
                    String email = scanner.next();
                    customer = hotelResource.getCustomer(email);

                    if (customer == null) {
                        System.out.println("Customer was not found.");
                        return;
                    }
                } else {
                    System.out.println("Enter Email format:name@domain.com");
                    String email = scanner.next();
                    System.out.println("Enter First Name");
                    String firstName = scanner.next();
                    System.out.println("Enter Last Name");
                    String lastName = scanner.next();
                    hotelResource.createACustomer(email, firstName, lastName);
                    customer=new Customer(lastName,firstName,email);
                }

                boolean isRoomAvailable = false;
                while (!isRoomAvailable) {
                    System.out.println("Available rooms:");
                    System.out.println(rooms);
                    System.out.println("Please enter room number from the available rooms:");
                    String roomNumber = scanner.next();
                    IRoom selectedRoom = hotelResource.getRoom(roomNumber);
                    if (!rooms.contains(selectedRoom)) {
                        System.out.println("Sorry, room number '" + roomNumber + "' is not available.");
                    } else {
                        hotelResource.bookARoom(customer, selectedRoom, checkInDate, checkOutDate);
                        System.out.println("Reservation was successfully created");
                        scanner.next();
                        isRoomAvailable = true;
                    }
                }
            }
        }
        else{
            System.out.println("Sorry there are no available rooms");
        }
    }

    private static void seeReservation(){
        System.out.println("Please tell us your email:");
        String email=scanner.next();

        System.out.println("Here are your reservations!");
        System.out.println(hotelResource.getCustomersReservations(email));
    }

    private static void createAccount(){
        boolean newEmail=false;

        System.out.println("Please tell us your email:");
        String email=scanner.next();
        System.out.println("Please tell us your lastname:");
        String lastName=scanner.next();
        System.out.println("Please tell us your firstname:");
        String firstName=scanner.next();

        try{
            hotelResource.createACustomer(email,firstName,lastName);}
        catch(IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }


}

