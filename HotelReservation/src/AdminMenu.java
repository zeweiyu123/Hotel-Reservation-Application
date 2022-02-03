import api.AdminResource;
import api.HotelResource;
import model.Room;
import model.RoomType;

import java.util.Scanner;

public class AdminMenu {
    private static AdminResource adminResource;
    private static Scanner scanner;
    public static void adminStart() {
        scanner = new Scanner(System.in);
        try {
            boolean exit = false;
            while (!exit) {
                String selection = showAdminMeun();
                switch (selection) {
                    case "1" -> seeCustomers();
                    case "2" -> seeRooms();
                    case "3" -> seeReservations();
                    case "4" -> addRoom();
                    case "5" -> exit = true;
                    default -> showAdminMeun();
                }
            }
            System.exit(0);
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        } finally {
            scanner.close();
        }
    }
    private static String showAdminMeun(){
        System.out.println("__________________________________________________");
        System.out.println("   ADMIN MENU");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main menu");
        System.out.println("__________________________________________________");
        System.out.println("Enter menu:");
        return scanner.nextLine();
    }

    private static void seeCustomers(){
        System.out.println("Here are the customers:");
        System.out.println(adminResource.getAllCustomers());
    }

    private static void seeRooms(){
        System.out.println("Here are the rooms:");
        System.out.println(adminResource.getAllRooms());
    }

    private static void seeReservations(){
        System.out.println("Here are the reservations:");
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        Room room = new Room();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter room number");
        room.roomNumber = scanner.nextLine().trim();
        System.out.println("Enter price per night");
        room.price = scanner.nextDouble();
        System.out.println("Enter room type: 1 for single,  2 for double bed");
        int type = scanner.nextInt();
        if (type == 1) {
            room.roomType = RoomType.SINGLE;
        } else {
            room.roomType = RoomType.DOUBLE;
        }
        adminResource.addRoom(room);
    }

    public static void setAdminResource(AdminResource adminResource) {
        AdminMenu.adminResource = adminResource;
    }



}
