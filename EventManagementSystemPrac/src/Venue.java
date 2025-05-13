public class Venue {

   private String location;
   private int floor;
   private int roomNum;
   private String openingTime;
   private String closingTime;
   private String seatingType;
   private boolean isAvailable = true;
   
   public Venue (String locationIn, int floorIn, int roomNumIn, String openingTimeIn, String closingTimeIn, String seatingTypeIn) {
      location = locationIn;
      floor = floorIn;
      roomNum = roomNumIn;
      openingTime = openingTimeIn;
      closingTime = closingTimeIn;
      seatingType = seatingTypeIn;
      
   }
   
   
   public String getLocation () {
      return location;
   }
   
   
   public int getFloor () {
      return floor;
   }
   
   
   public int getRoomNum () {
      return roomNum;

   }
   
   
   public String getOpeningTime () {
      return openingTime;
   }
   
   
   public String getClosingTime () {
      return closingTime;
   }
   
   
   public String getSeatingType () {
      return seatingType;
   }
   
   
   public void changeSeatingType (String newSeating) {
      seatingType = newSeating;
   }
   
   
   public void changeVenue (String newLocation, int newFloor, int newRoom, String newOpening, String newClosing, String newSeating) {
      location = newLocation;
      floor = newFloor;
      roomNum = newRoom;
      openingTime = newOpening;
      closingTime = newClosing;
      seatingType = newSeating;
   }
   
   
   public void changeAvailability () {
      isAvailable = false;
   }
   
   
   
}
