class Controller {
  
  public static void main(String argv[]) {
    double lambda = 60.0;                         //Initialize user-specified values.
    double Ts = 0.015;
    double simulationTime = 100.0;
    double clock = 0.0;
    Calendar eventCalendar = new Calendar();      //Initialize the calendar
    
    MM1 queue = new MM1(lambda,Ts,simulationTime,eventCalendar); //Create the MM1 queue, inputting the lambda, Ts and eventCalendar
    Event firstEvent = new Event("first");        //Initialize the first event
    queue.function(clock,firstEvent);             //Enter the first event in the function.
    
    // Loop until specified simulation time
    while (clock < simulationTime*2.0) {          //Loop for twice as long as simulation time for warm-up.
      Event evt = (Event) eventCalendar.getNext();//Get imminent event from calendar event creation
      clock = evt.getEventTime();                 //Advance simulation time
      queue.function(clock,evt);                  //Pass event to MM1 function to make it a death or birth event.
    }
    queue.statusReport();                         //Print results.
  }
}