import java.io.*;
import java.util.*;
public class Event
{
	private String name;
	private String description;
	private Venue venue;
	private Schedule schedule;
	private String speaker;
	private boolean isOpen;
	private ArrayList<Ticket> ticketsSold;
	private ArrayList<Attendee> att;
	private static int nextEventNum = 1000;
	private int eventNum = 1000;
	private int canceledTickets;

	public Event(String nameIn, String descIn, Venue venIn, Schedule schIn, String speakerIn)
	{
		name = nameIn;
		description = descIn;
		venue = venIn;
		schedule = schIn;
		speaker = speakerIn;
		eventNum = nextEventNum;
		nextEventNum++;

		isOpen = true;
		ticketsSold = new ArrayList<Ticket>();
		att = new ArrayList<Attendee>();
		canceledTickets = 0;
	}

	public String getEventName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public Venue getVenue()
	{
		return venue;
	}

	public Schedule getSchedule()
	{
		return schedule;
	}

	public String getSpeaker()
	{
		return speaker;
	}

	public void addTicket(Ticket newT, Attendee newA)
	{
		ticketsSold.add(newT);
		att.add(newA);
	}

	public void removeTicket(Ticket removeT, Attendee removeA)
	{
		int count = 0;
		Ticket temp;
		System.out.println(ticketsSold.size());
		while(count<ticketsSold.size())
		{
			temp = ticketsSold.get(count);
			if(temp.getID() == removeT.getID())
			{
				ticketsSold.remove(count);
				att.remove(removeA);
				count = ticketsSold.size();
				canceledTickets++;
			}
			count++;
		}
	}

	public void closeEvent()
	{
		isOpen=false;
		boolean isPrem;
		int premSold = 0;
		for(int i=0;i<ticketsSold.size();i++)
		{
			isPrem = (ticketsSold.get(i)).getType();
			if(isPrem==true)
			{
				premSold++;
			}
		}
		System.out.println(name + " Event Report:\nPremium Tickets Sold: " + premSold + "\nStandard Tickets Sold " + (ticketsSold.size()-premSold) + "\nAttendance: " + ticketsSold.size() + " out of " + (ticketsSold.size()+canceledTickets) + " attendees arrived");
	}
	public boolean openingStatus()
	{
		return isOpen;
	}
	
	public void changeSchedule(String newDate, String newStartTime, String newEndTime)
	{
		Schedule newSchedule = new Schedule (newDate, newStartTime, newEndTime);
		schedule = newSchedule;
	}
	
	public int getEventNumber()
	{
		return eventNum;
	}

	public int getCancel()
	{
		return canceledTickets;
	}

	public void showAttendees()
	{
		System.out.println(name + " Attendees:\nName, Email\n");
		Attendee a;
		for(int i=0;i<att.size();i++)
		{
			a= att.get(i);
			System.out.println(a.getName() + ", " + a.getEmail());
		}
	}
	
}
