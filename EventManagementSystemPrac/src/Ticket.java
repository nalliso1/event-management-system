public class Ticket {

   private Double price;
   private int seatNum;
   private int row;
   private boolean isPremium;
   private int id;
   private static int count = 2000;
   private int eventID;
   private String eventName;
   
   public Ticket (String eNameIn, int seatNumIn, int rowIn, boolean isPremiumIn, int eventIDin) {
      eventName = eNameIn;
      seatNum = seatNumIn;
      row = rowIn;
      isPremium = isPremiumIn;
      eventID = eventIDin;
      id = ++count;
   }   
   
   public String getEName () {
      return eventName;
   }

   public int getSeat () {
      return seatNum;
   }

   public int getRow () {
      return row;
   }

   public boolean getType () {
      return isPremium;
   }

   public int getEID () {
      return eventID;
   }

   public int getID () {
      return id;
   }

	public void changeType()
	{
		if(isPremium==true)
		{
			isPremium=false;
		}
		else
		{
			isPremium=true;
		}
	}
}