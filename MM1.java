import java.util.*;

public class MM1 {
  //Initialize all the variables 
  Calendar eventCalendar;
  int count;
  double lambda;
  double Ts;
  double Q;
  double W;
  double Tq;
  double Tw;
  double everytwenty;
  double simulationTime;
  Random rng = new Random( System.nanoTime() );
  
  
  //Constructor methods
  public MM1(){
  }
  public MM1(double arrivalRate, double serviceTime, double _simulationTime, Calendar _eventCalendar) {
    lambda = arrivalRate;
    Ts = serviceTime;
    eventCalendar = _eventCalendar;
    simulationTime = _simulationTime;
  }
  
  
  //Print method. Prints results found 
  public void statusReport(){
    System.out.println(count);
    System.out.println("Q:  " + (Q/(count)));
    System.out.println("Tq: " + (Tq/(count)));
    System.out.println("W:  " + (W/(count)));
    System.out.println("Tw  " + (Tw/(count)));
  }
  
  
  //Function of the event. Determines if it is a First event, a Birth event or a Death event and acts accordingly.
public void function(double time,Event e){
    if(e.isFirst()){                                  //If Event e is the first event
      
      double r = rng.nextDouble();                    //Get a random number
      double numerator = Math.log(1 - r);             //Top half of exponential distribution equation
      double x = numerator / (-lambda);               //Inter-arrival time for birth event
      double y = numerator/(1/-Ts);                   //Service time for birth event
      
      Event next = new Event("birth",x,y,time);       //Create the event 
      eventCalendar.insert(next);                     //Insert it into the calendar
      
      r = rng.nextDouble();                           //Get a new random number
      numerator = Math.log(1 - r);                    
      x = numerator / (-lambda);                      //Calculate a new x
      
      eventCalendar.birthList.add(x);                 //Add this new x to the birthList (This is the predicted time until next birth.) 
      eventCalendar.deathList.add(y);                 //Add the Ts(time till next death) to the deathList
      
    }else if(e.isBirth()){                            //If Event e is any other birth Event
      
      eventCalendar.birthList.removeFirst();          //Remove the first event (The predicted time until this birth) from the birthList
      double r = rng.nextDouble();                    //Repeat inter-arrival time and service time calculations
      double numerator = Math.log(1 - r);             
      double x = numerator / (-lambda);
      double y = numerator/(1/-Ts);
      
      Event next = new Event("birth",x,y,time);       //Create event
      eventCalendar.insert(next);                     //Insert it
      eventCalendar.deathList.add(y);                 //Save its deathtime
      
      r = rng.nextDouble();                           //Get a new random number
      numerator = Math.log(1 - r);
      x = numerator / (-lambda);                      //Calculate a new x
      eventCalendar.birthList.add(x);                 //Add this new x to the birthList (This is the predicted time until next birth.)
      
      if(eventCalendar.systemTime>=simulationTime){   //Wait until the system is warmed up. (Have it running for 2*simulationTime, so simulationTime is half)
        if(everytwenty<20){                           //Only look at state of System at discreet points, i.e. every 20 iterations
          everytwenty++;
        }else if(everytwenty==20){
          count++;
          
          if(eventCalendar.EventList.size()>0){       //If EventList isn't empty
            if(eventCalendar.EventList.size()>eventCalendar.birthList.size()){ //If EventList>birthList
              Q=Q+eventCalendar.EventList.size();     //Add values to Q 
              W=W+eventCalendar.EventList.size()-1;   //Add values to W
            }else if(eventCalendar.birthList.size()>eventCalendar.EventList.size()){ //if birthList>EventList
              Q=Q+eventCalendar.birthList.size();     //Add values to Q
              W=W+eventCalendar.birthList.size()-1;   //Add values to W
            }
          }
          
          if(eventCalendar.EventList.size()!=0){      //If EventList isn't Empty, add values to Tq and Tw.
            Tq +=eventCalendar.deathList.get(0)+(eventCalendar.systemTime-(eventCalendar.EventList.get(0).getEventTime()));
            Tw +=eventCalendar.birthList.get(0);
          }
          everytwenty=0;
        }
      }
      
    }else if(e.isDeath()){                            //If the event is a deathEvent
      eventCalendar.deathList.removeFirst();          //Remove the first element in the deathList
      eventCalendar.EventList.removeFirst();          //Remove the first element in the EventList
    }
    
  }
  }
