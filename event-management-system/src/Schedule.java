import java.io.*;
import java.util.*;
public class Schedule
{
	private String date;
	private String startTime;
	private String endTime;
	private ArrayList<String> breaks;
	private String[] months;
	private int[] days;

	public Schedule(String dateIn, String startIn, String endIn)
	{
		date = dateIn;
		startTime = startIn;
		endTime = endIn;
		breaks = new ArrayList<String>();
		months = new String[]{"january","february","march","april","may","june","july","august","september","october","november","december"};
		days = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};
	}

	public String getDate()
	{
		return date;
	}

	public String getDuration()
	{
		String duration = (startTime + " to " + endTime);
		return duration;
	}

	public ArrayList<String> getBreaks()
	{
		return breaks;
	}

	public void changeDate(String newDate)
	{
		date = newDate;
	}
	
	public void changeStart(String newStart)
	{
		startTime = newStart;
	}

	public void changeEnd(String newEnd)
	{
		endTime = newEnd;
	}

	public void changeBreaks(ArrayList<String> updateBreaks)
	{
		for(int i=0;i<breaks.size();i++)
		{
			breaks.set(i, updateBreaks.get(i));
		}
	}

	public void addBreak(String newBreak)
	{
		breaks.add(newBreak);
	}

	public void removeBreak(String oldBreak)
	{
		int count = 0;
		while(count<breaks.size())
		{
			if(breaks.get(count).equals(oldBreak))
			{
				breaks.remove(count);
				count = breaks.size();
			}
			count++;
		}
	}

	public boolean monthValidation(String m)
	{
		boolean vm = false;
		String m2 = m.toLowerCase();

		for(int i=0;i<12;i++)
		{
			if(months[i].equals(m2))
			{
				vm = true;
			}
		}
		return vm;
	}

	public boolean dayValidation(int d, String m)
	{
		boolean vd = false;
		String m2 = m.toLowerCase();

		for(int i=0;i<12;i++)
		{
			if(months[i].equals(m2))
			{
				if((days[i]>=d)&&(d>=1))
				{
					vd = true;
				}
			}
		}
		return vd;
	}

	public String convertTime(String t)
	{
		String period;
		String hour = t.substring(0,2);
		int numHour = Integer.parseInt(hour);

		if(numHour>12)
		{
			numHour = numHour-12;
			period = "PM";
		}
		else
		{
			period = "AM";
		}
		
		String newTime;
		if(numHour<10)
		{
			newTime = "0" + numHour + t.substring(2) + period;
		}
		else
		{
			newTime = numHour + t.substring(2) + period;
		}
		return newTime;
	}
}
		