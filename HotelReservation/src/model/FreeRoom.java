package model;

public class FreeRoom extends Room{
    public FreeRoom() {this.price= 0.0;}

    @Override
    public String toString(){
        return String.format("FreeRoom\nroomNumber=%s\nprice=%s\nroomType=%s\n", roomNumber, price, roomType);

    }

}
