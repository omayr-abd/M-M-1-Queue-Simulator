import java.util.*;

class Queue extends LinkedList {
    
    public void enqueue(Object eventObj) {
        add(eventObj);        
    }
    
    public Object dequeue() {
        return removeFirst();
    }
}