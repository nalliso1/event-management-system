import java.util.*;
public class Attendee
{

   	private String name;
   	private String email;
	private String password;
  	private ArrayList<Ticket> purchasedTickets;
	private Ticket t;
   
   	public Attendee(String nameIn, String emailIn, String passwordIn) 
	{
      		name = nameIn;
      		email = emailIn;
		password = passwordIn;
      		purchasedTickets = new ArrayList<Ticket>();
   	}
   
   
   	public String getName() 
	{
      		return name;
   	}
   
   
   	public String getEmail()
	{
      		return email;
   	}
   
   
   	public void updateEmail(String newEmail) 
	{
      		email = newEmail;
   	}
   
	public void buyTicket(Ticket ticket)
	{
		purchasedTickets.add(ticket);
	}
   
   	public void newTicket(int id) 
	{
      		for(int i=0;i<purchasedTickets.size();i++)
		{
			t = purchasedTickets.get(i);
			if(t.getID()==id)
			{
				t.changeType();
				purchasedTickets.set(i,t);
			}
		}
   	}

	public void removeTicket(int id)
	{
		for(int i=0;i<purchasedTickets.size();i++)
		{
			t = purchasedTickets.get(i);
			if(t.getID()==id)
			{
				purchasedTickets.remove(i);
			}
		}
	}

	public ArrayList<Ticket> viewTickets()
	{
		return purchasedTickets;
	}

	public String getPassword()
	{
		return password;
	}
}
