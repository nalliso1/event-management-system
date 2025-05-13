import java.util.*;
import java.io.*;
public class EventProgram
{
	public static void main(String[] args)
	{
		ArrayList<Attendee> aList = new ArrayList<Attendee>();
		Attendee att;
		ArrayList<Ticket> tList = new ArrayList<Ticket>();
		ArrayList<Event> eventList = new ArrayList<Event>();
		Venue v;
		Schedule sch;
		Event eTemp;
		Ticket tTemp;
		int index, row, num, eID, tID, venueFloor, venueRoom, eventNum;
		Double price;
		boolean prem, eCheck, pCheck, found, looping;
		String email, p, eventName, description, speaker, venueLocation, venueOpening, venueClosing, venueSeating, scheduleDate, scheduleStart, scheduleEnd, newBreak;
		Attendee aTemp;

		String password = "password123";
		boolean loop = true;
		char in;

		Scanner sc = new Scanner(System.in);
		System.out.println("Are you an attendee (A) or staff (S)?");
		in = sc.next().charAt(0);
		in = Character.toUpperCase(in);
		boolean closeProgram = false;
		boolean passKill = false;
		ArrayList<Ticket> allT;
		int attInd = 0;

		if((in!='S')&&(in!='A'))
		{
			while((in!='S')&&(in!='A'))
			{
				System.out.println("Invalid input. Please enter A or S.");
				in = sc.next().charAt(0);
				in = Character.toUpperCase(in);
			}
		}
		while(closeProgram==false)
		{
			if(in=='A')
			{
				System.out.println("Do you already have an account? Y or N");
				in = sc.next().charAt(0);
				in = Character.toUpperCase(in);
				while((in!='Y')&&(in!='N'))
				{
					System.out.println("Invalid input. Please type a Y or N.");
					in = sc.next().charAt(0);
					in = Character.toUpperCase(in);
				}
				if(in=='Y')
				{
					eCheck=false;
					System.out.println("What is your email?");
					email = sc.next();
					for(int i=0;i<aList.size();i++)
					{
						if(email.equals((aList.get(i)).getEmail()))
						{
							attInd = i;
							i = aList.size();
							eCheck=true;
							pCheck=false;
							System.out.println("Please enter the password.");
							p = sc.nextLine();
							p = sc.nextLine();
							if(p.equals((aList.get(attInd)).getPassword())==false)
							{
								System.out.println("Incorrect password. 4 tries left.");
								p = sc.next();
								for(int j =0;(j<3)&&(p.equals((aList.get(attInd)).getPassword())==false);j++)
								{
									System.out.println("Incorrect password. " + (3-j) + " tries left.");
									p = sc.next();
								}
							}
							if(p.equals((aList.get(attInd)).getPassword())==false)
							{
								System.out.println("Incorrect password. 0 tries left. CLosing program.");
								passKill = true;
								closeProgram = true;
								loop = false;
							}
							else
							{
								System.out.println("Password accepted.");
							}
						}
					}
					if(eCheck==false)
					{
						System.out.println("That email is not registered.");
						loop = false;
					}
				}
				else
				{
					System.out.println("What is your full name?");
					sc.nextLine();
					String aName = sc.nextLine();
					System.out.println("What is your email?");
					email = sc.next();
					System.out.println("What is your password?");
					p = sc.next();
					att = new Attendee(aName,email,p);
					aList.add(att);
					attInd = (aList.size()-1);
				}

				while(loop==true)
				{
					System.out.println("\nWhat would you like to do?\n1) Purchase ticket\n2) Remove ticket" +
							"\n3) View ticket information\n4) Upgrade/downgrade ticket\n5) Log out");
					in = sc.next().charAt(0);
					if(in=='1')
					{
						System.out.println("\nWhich event will you be attending?\n");
						for(int i=0;i<eventList.size();i++)
						{
							eTemp = eventList.get(i);
							System.out.println((i+1) + ") " + eTemp.getEventName());
						}
						index = sc.nextInt();
						index = index-1;
						if((index>=0)&&(index<eventList.size()))
						{
							eTemp = eventList.get(index);
							eID = (eventList.get(index)).getEventNumber();
							System.out.println("Do you want premium? Y for yes, N for no.");
							in = sc.next().charAt(0);
							in = Character.toUpperCase(in);
							while((in!='Y')&&(in!='N'))
							{
								System.out.println("Invalid input. Please type a Y or N.");
								in = sc.next().charAt(0);
								in = Character.toUpperCase(in);
							}
							if(in=='Y')
							{
								prem = true;
							}
							else
							{
								prem = false;
							}
							System.out.println("What row are you in?");
							row = sc.nextInt();
							System.out.println("What column?");
							num = sc.nextInt();
							tTemp = new Ticket(eTemp.getEventName(), num, row, prem, eID);
							tList.add(tTemp);
							(aList.get(attInd)).buyTicket(tTemp);
							(eventList.get(index)).addTicket(tTemp, aList.get(attInd));

						}
						else
						{
							System.out.println("Invalid input.");	
						}
					}
					else if(in=='2')
					{
						System.out.println("\nWhat is the ID of the ticket you wish to cancel?");
						tID = sc.nextInt();
						allT = (aList.get(attInd)).viewTickets();
						for(int j=0;j<allT.size();j++)
						{
							if((allT.get(j)).getID()==tID)
							{
								eID = (allT.get(j)).getEID();
								for(int i=0;i<eventList.size();i++)
								{
									if((eventList.get(i)).getEventNumber()==eID)
									{
										(eventList.get(i)).removeTicket(allT.get(j), aList.get(attInd));
										i = eventList.size();
										System.out.println("The ticket has been removed.");
									}
								}
								j = allT.size();
							}
						}
						(aList.get(attInd)).removeTicket(tID);
					}
					else if(in=='3')
					{
						System.out.println("\nTicket ID, Ticket Type, Event Name, Event ID\n");
						allT = (aList.get(attInd)).viewTickets();
						for(int j=0;j<allT.size();j++)
						{
							System.out.print(allT.get(j).getID() + ", ");
							if(allT.get(j).getType()==true)
							{
								System.out.print("Premium, ");
							}
							else
							{
								System.out.print("Standard, ");
							}
							System.out.println(allT.get(j).getEName() + ", " + allT.get(j).getEID());
						}
					}
					else if(in=='4')
					{
						System.out.println("What is the ID of the ticket you wish to alter?");
						tID = sc.nextInt();
						(aList.get(attInd)).newTicket(tID);
					}
					else if(in=='5')
					{
						loop=false;
						System.out.println("Logging out...");
					}
					else
					{
						System.out.println("Invalid input. Please type a number corresponding to a command.\n");
					}
				}
			}

			if(in=='S')
			{
				System.out.println("Please enter the password.");
				p = sc.nextLine();
				p = sc.nextLine();
				if(p.equals(password)==false)
				{
					System.out.println("Incorrect password. 4 tries left.");
					p = sc.next();
					for(int i =0;(i<3)&&(p.equals(password)==false);i++)
					{
						System.out.println("Incorrect password. " + (3-i) + " tries left.");
						p = sc.next();
					}
				}
				if(p.equals(password))
				{
					System.out.println("Password accepted.\n");
					while(loop==true)
					{
						System.out.println("\nWhat would you like to do?\n1)Register an event\n2)Close an event\n3)Reschedule an event" +
							 	"\n4)Add a break to an event\n5)Change venue details\n6)View Attendee List\n7)View Event List\n8)Log out");
						in = sc.next().charAt(0);
						if(in=='1')
						{
							System.out.println("\nPlease enter the name of your event, provide a short description, the venue where the event will be held, and the schedule of your event.");
							System.out.print("Name of event: ");
							sc.nextLine();
							eventName = sc.nextLine();
						
							System.out.print("Short description: ");
							description = sc.nextLine();

							System.out.print("Name of speaker: ");
							speaker = sc.nextLine();
						
							System.out.print("\nMoving onto the venue you wish to hold your event at. \nPlease provide the name of the location: ");
							venueLocation = sc.nextLine();
							System.out.print("The floor your event will be held on: ");
							venueFloor = sc.nextInt();
						
							System.out.print("The room number: ");
							venueRoom = sc.nextInt();
							sc.nextLine();
						
							System.out.print("The opening time of your chosen venue: ");
							venueOpening = sc.nextLine();
						
							System.out.print("The closing time of your chosen venue: ");
							venueClosing = sc.nextLine();
						
							System.out.print("The way you would like the seating to be arranged (ex: circular tables in a star formation): ");
							venueSeating = sc.nextLine();
						
							v = new Venue (venueLocation, venueFloor, venueRoom, venueOpening, venueClosing, venueSeating);
						
							System.out.print("\nNow, please enter the schedule of your event. \nThe date your event will be held on: ");
							scheduleDate = sc.nextLine();
						
							System.out.print("The starting time of your event: ");
							scheduleStart = sc.nextLine();
						
							System.out.print("The time your event will end: ");
							scheduleEnd = sc.nextLine();
						
							sch = new Schedule (scheduleDate, scheduleStart, scheduleEnd);
						
							eTemp = new Event (eventName, description, v, sch, speaker);
							System.out.println("\nPlease make sure that the following information is correct.\nName of event: " + eTemp.getEventName() + "\nDescription: " + eTemp.getDescription());
							System.out.println("Location name: " + v.getLocation() + "\nFloor " + v.getFloor() + ", room " + v.getRoomNum() + "\nSeating arrangement: " + v.getSeatingType() + "\nVenue opens at " + v.getOpeningTime() + ", closes at " + v.getClosingTime());
							System.out.println("Event date: " + sch.getDate() + "\nDuration: " + sch.getDuration());
						
							System.out.println("\nDoes the information above look correct? (y or n)");
							in = sc.next().charAt(0);
							in = Character.toUpperCase(in);

							if((in!='Y')&&(in!='N'))
							{
								while((in!='Y')&&(in!='N'))
								{
									System.out.println("Invalid input. Please enter Y or N.");
									in = sc.next().charAt(0);
									in = Character.toUpperCase(in);
								}
							}
						
							if (in=='Y') 
							{
						   		System.out.println("Perfect! Your event has been booked. Thank you for booking with us! Your designated event number is given below, please use it when you want to close your event, or to change any details of the event.");
						   		System.out.println("Your event number: " + eTemp.getEventNumber() + "\n");
						   		eventList.add(eTemp);
							}
						
							else 
							{
						   		System.out.println("Please re-enter your booking information.\n");
							}
						}
						else if(in=='2')
						{
							System.out.println("Please enter your event number.");
							sc.nextLine();
							eventNum = sc.nextInt();
						
							Iterator<Event> iterator = eventList.iterator();
							found = false;
						
							while (iterator.hasNext()) 
							{
						   		eTemp = iterator.next();
						   
						   		if (eTemp.getEventNumber() == eventNum) 
								{
						      			found = true;
						      			System.out.println("\nAre you sure you would like to close " + eTemp.getEventName() + "? (y or n)");
						      			sc.nextLine();
						      			in = sc.next().charAt(0);
									in = Character.toUpperCase(in);

									if((in!='Y')&&(in!='N'))
									{
										while((in!='Y')&&(in!='N'))
										{
											System.out.println("Invalid input. Please enter Y or N.");
											in = sc.next().charAt(0);
											in = Character.toUpperCase(in);
										}
									}
							
						      			if (in=='Y') 
									{
						         			System.out.println("Closing " + eTemp.getEventName() + "...\n");
						         			eTemp.closeEvent();
						         			iterator.remove();
						         			System.out.println("Event closed.\n");
						      			}
						      
						      			else 
									{
						         			System.out.println("Please try again and re-enter your event number.\n");
						      			}
								}
							}
							if (!found) 
							{
								System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.\n");
							}
						}

						else if(in=='3')
						{
							System.out.println("\nPlease enter your event number.");
							sc.nextLine();
							eventNum = sc.nextInt();
						
							found = false;
							Iterator<Event> iterator = eventList.iterator();
						
							while(iterator.hasNext()) 
							{
						   		eTemp = iterator.next();
						   
						
				                   		if(eTemp.getEventNumber() == eventNum) 
								{
				                      			found = true;
				                      			System.out.print("\nNow input the new scheduling information. \nNew date: ");
						      			sc.nextLine();
						      			scheduleDate = sc.nextLine();
						
						      			System.out.print("New start time: ");
						      			scheduleStart = sc.nextLine();
						
						      			System.out.print("New end time: ");
						      			scheduleEnd = sc.nextLine();
						
				                      			eTemp.changeSchedule(scheduleDate, scheduleStart, scheduleEnd);
				                      			System.out.println("\nYour event schedule has been changed. Please check the information below, and make sure it is correct. Otherwise, please try again.");
				                			System.out.print("\nNew date: " + eTemp.getSchedule().getDate() + "\nNew duration: " + eTemp.getSchedule().getDuration() + "\n");
				                   		}
				                	}

				                	if (!found) 
							{
						   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.\n");
							}
						}
					
						else if(in=='4')
						{
							System.out.println("\nPlease enter your event number.");
							sc.nextLine();
							eventNum = sc.nextInt();
						
							Iterator<Event> iterator = eventList.iterator();
							found = false;
						
							while (iterator.hasNext()) 
							{
						   		eTemp = iterator.next();
						   		if (eTemp.getEventNumber() == eventNum) 
								{
						      			found = true;
						      			System.out.println("\nEnter the duration of the break you would like to add to " + eTemp.getEventName() + ". (Ex: 11:00am-11:30am)");
						      			sc.nextLine();
						      			newBreak = sc.nextLine();
					              			eTemp.getSchedule().addBreak(newBreak);
						      			System.out.println("Break added.\n");
						   		}
							}
						
							if (!found) 
							{
						   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.\n");
							}	
						}
					
						else if(in=='5')
						{
							looping = true;
							while (looping) 
							{
								System.out.println("\nWelcome to the venue management system. What would you like to do?" + "\n1)Change an event's venue" + "\n2)Change seating arrangement" + "\n3)Back to event menu");
							
						        	in = sc.next().charAt(0);
						        
						        	if (in=='1') 
								{
						        		System.out.println("Please enter your event number.");
									sc.nextLine();
									eventNum = sc.nextInt();
								
									Iterator<Event> iterator = eventList.iterator();
									found = false;
						
									while (iterator.hasNext()) 
									{
						   		   		eTemp = iterator.next();
						   
						   		   		if (eTemp.getEventNumber() == eventNum) 
										{
						      		      			found = true;
						      		      			System.out.print("Please enter the name of the new venue: ");
						      		      			sc.nextLine();
						      		      			venueLocation = sc.nextLine();
						      		      
						      		      			System.out.print("The new floor your event will be held on: ");
						      		      			venueFloor = sc.nextInt();
						      		      			System.out.print("The new room your event will be held in: ");
						      		      			venueRoom = sc.nextInt();
						      		      
						      		      			System.out.print("The opening time of your new venue: ");
						      		      			sc.nextLine();
						      		      			venueOpening = sc.nextLine();
						      		      			System.out.print("The closing time of your new venue: ");
						      		      			venueClosing = sc.nextLine();
						      		      
						      		      			System.out.print("The way the seating will be arranged: ");
						      		      			venueSeating = sc.nextLine();
						      		      
						      		      			v = new Venue (venueLocation, venueFloor, venueRoom, venueOpening, venueClosing, venueSeating);
						      		      
						      		      			eTemp.getVenue().changeVenue(venueLocation, venueFloor, venueRoom, venueOpening, venueClosing, venueSeating);
						      		      
						      		      			System.out.println("\nVenue changed. Please check that the information below looks correct. Otherwise, return to the venue menu and try again.\nVenue location: " + v.getLocation() + "\non floor " + v.getFloor() + ", room " + v.getRoomNum() + "\nOpens at " + v.getOpeningTime() + ", closes at " + v.getClosingTime() + "\nSeating arrangement: " + v.getSeatingType() + "\n");
						      		   		}
						      			}
						      		
						      			if (!found) 
									{
						                   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.\n");
									}
						      		      
								
						        	}
						        	else if (in=='2') 
								{
						        		System.out.println("Please enter your event number.");
									sc.nextLine();
									eventNum = sc.nextInt();
								
									Iterator<Event> iterator = eventList.iterator();
									found = false;
						
									while (iterator.hasNext()) 
									{
						   		   		eTemp = iterator.next();
						   
						   		   		if (eTemp.getEventNumber() == eventNum) 
										{
						      		      			found = true;
						      		      			System.out.print("Enter the new way you would like your seats to be arranged: ");
						      		      			sc.nextLine();
						      		      			venueSeating = sc.nextLine();
						      		      
						      		      			eTemp.getVenue().changeSeatingType(venueSeating);
						      		      			System.out.println("Seating arrangement successfully changed.");
						      		   		}
						      			}
						      		
						      			if (!found) 
									{
						                   		System.out.println("\nSeems like we couldn't find your event in the system. Please re-enter your event number, and try again.\n");
									}
						      		      
						      		      
						        	}
						        
						        	else if (in=='3') 
								{
						        		looping = false;
						        	}
						        
						        	else 
								{
						        		System.out.println("Invalid answer, please type one of the numbers provided.");
						        
						        	}
							}
						}

						else if(in=='6')
						{
							System.out.println("Please enter your event number.");
							sc.nextLine();
							eventNum = sc.nextInt();

							for(int i=0;i<eventList.size();i++)
							{
								if(eventNum==(eventList.get(i)).getEventNumber())
								{
									(eventList.get(i)).showAttendees();
								}
							}
						}

						else if(in=='7')
						{
							System.out.println("\nEvent Name, Event Number\n");
							for(int i=0;i<eventList.size();i++)
							{
								System.out.println((eventList.get(i)).getEventName() + ", " + (eventList.get(i)).getEventNumber());
							}
							System.out.println("Would you like to view the details of an event? Y or N");
							in = sc.next().charAt(0);
							in = Character.toUpperCase(in);
							while((in!='Y')&&(in!='N'))
							{
								System.out.println("Invalid input. Please type a Y or N.");
								in = sc.next().charAt(0);
								in = Character.toUpperCase(in);
							}
							if(in=='Y')
							{
								System.out.println("Enter the event number.");
								eventNum = sc.nextInt();
								for(int i=0;i<eventList.size();i++)
								{
									if(eventNum==(eventList.get(i)).getEventNumber())
									{
										System.out.print("\nName: " + (eventList.get(i)).getEventName() + "\nEvent Number: " + (eventList.get(i)).getEventNumber() + "\nSpeaker: " + (eventList.get(i)).getSpeaker() + "\n\nVenue: ");
										v = (eventList.get(i)).getVenue();
										sch = (eventList.get(i)).getSchedule();
										System.out.print(v.getLocation() + "\nFloor: " + v.getFloor() + "\nRoom Number: " + v.getRoomNum() + "\n\nDate: " + sch.getDate() + "\nDuration: " + sch.getDuration() + "\n");
									}
								}
							}
						}
					
						else if(in=='8')
						{
							loop=false;
							System.out.println("Logging out...");
						}
						else
						{
							System.out.println("Invalid input. Please type a number corresponding to a command.\n");
						}
					}
				}
				else
				{
					System.out.println("Incorrect password. 0 tries left. CLosing program.");
					passKill = true;
					closeProgram = true;
				}
			}

			if(passKill==false)
			{
				System.out.println("\nWould you like to log in as a staff member or another attendee? Y or N");
				in = sc.next().charAt(0);
				in = Character.toUpperCase(in);
				while((in!='Y')&&(in!='N'))
				{
					System.out.println("Invalid input. Please type a Y or N.");
					in = sc.next().charAt(0);
					in = Character.toUpperCase(in);
				}
				if(in=='Y')
				{
					loop = true;
					System.out.println("Are you an attendee (A) or staff (S)?");
					in = sc.next().charAt(0);
					in = Character.toUpperCase(in);

					if((in!='S')&&(in!='A'))
					{
						while((in!='S')&&(in!='A'))
						{
							System.out.println("Invalid input. Please enter A or S.");
							in = sc.next().charAt(0);
							in = Character.toUpperCase(in);
						}

					}
				}
				else
				{
					closeProgram = true;
				}
			}
		}
		System.out.println("Goodbye.");
	}
}