package model;

public class Room implements IRoom{
    public String roomNumber ;
    public double price;
    public RoomType roomType;

    @Override
    public String getRoomNumber() {return roomNumber;}

    @Override
    public Double getRoomPrice() {return price;}

    @Override
    public RoomType getRoomType() {return roomType;}

    @Override
    public boolean isFree() {return false;}

    @Override
    public String toString(){
        return String.format("Room\nroomNumber=%s\nprice=%s\nroomType=%s\n", roomNumber, price, roomType);
    }
}
