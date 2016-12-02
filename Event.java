class Event {
  //Event class.
  //Initialize all the variables
  public double eventTime;
  public double serviceTime;
  public boolean birth;
  public boolean death;
  public boolean first;
  
  
  //Constructor classes.
  //When an event is created, it is specified as a first event, a birth event, or a death event.
  //This way, the Calendar knows which type of event to schedule it as.
  public Event(String type) {
    if (type.equals("first")) {
      first = true;
      birth = true;
      death = false;
    }
    else if (type.equals("birth")) {
      first = false;
      birth = true;
      death = false;
    }
    else {
      first = false;
      birth = false;
      death = true;
    }
  }
  
  public Event(String type, double inputTime) {
    if (type.equals("birth")) {
      first = false;
      birth = true;
      death = false;
      eventTime = inputTime;
    }
    else {
      first = false;
      birth = false;
      death = true;
      eventTime = inputTime;
    }
  }
  
  public Event(String type,double interArrivalTime,double _serviceTime, double systemTime){
    if(type.equals("birth")){
      birth=true;
      death=false;
      first=false;
      eventTime = systemTime+interArrivalTime;
      serviceTime = _serviceTime;
    }
  }
  
  
  //Methods that return true or false to determine what kind of event we have.
  public Boolean isFirst() {
    return first;
  }
  
  public Boolean isBirth() {
    return birth;
  }
  
  public Boolean isDeath() {
    return death;
  }
  
  public double getEventTime() {
    return eventTime;
  }
  
  public double getServiceTime() {
    return serviceTime;
  }
  
}
