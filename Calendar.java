import java.util.*;

//Implement the calendar class as 3 linked lists. 1 for the actual events, and the other two for the times of deaths and births.
class Calendar {
  double systemTime;
  LinkedList<Event> EventList = new LinkedList<Event> ();
  LinkedList<Double> deathList = new LinkedList<Double>();
  LinkedList<Double> birthList = new LinkedList<Double>();
  
  //Constructor method
  public Calendar() {
    super();
  }

  //Inserts an event into the calendar
  public void insert(Event e){
    EventList.add(e);
  }
  
  //Gets size of calendar
  public int getSize(){
    return EventList.size();
  }
 //Gets next event in Calendar.
  public Event getNext(){
    if(EventList.size()==0){                                //If it's the first event
      double temp=birthList.get(0);                         //Get the predicted time until next birth
      systemTime+=temp;                                     //Add it to the systemTime
      return new Event("birth",systemTime);                 //return the birth event
    }
    else if(birthList.get(0)<deathList.get(0)){             //If there is a birth event coming before a death event
      deathList.set(0,(deathList.get(0)-birthList.get(0))); //Set the predicted time until next death to the current one - the predicted time until next birth
      double temp = birthList.get(0);                       //Get the predicted time until next birth
      systemTime+=temp;                                     //Add it to the systemTime
      return new Event("birth",systemTime);                 //return the birth event
    }
    else{                                                   //If it's a death event
      birthList.set(0,(birthList.get(0)-deathList.get(0))); //Set the predicted time until next birth to the current one - the predicted time until next death
      double temp = deathList.get(0);                       //Get the predicted time until next death
      systemTime+=temp;                                     //Add it to the systemTime
      
      return new Event("death",systemTime);                 //return the death event.
    }
    
  }
}
