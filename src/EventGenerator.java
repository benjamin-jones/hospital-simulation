/* EventGenerator - The event generator is responsible for creating events and putting them into the queue 
 * 
 * Author: Benjamin Jones
 * Creation Date: May 21, 2012
 *
 * Revisions
 * May 21, 2012: Initial rev.
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class EventGenerator
{
        static boolean started;
        static Queue<Event> outq = new LinkedList<Event>();

        static public boolean getStatus() 
        {
                return started;
        }

	// Stop/Start is deprecated because they are singletons and have no state
        static public boolean startEventGenerator()
        {
                //TODO: Init code
                return getStatus();
        }

        static public boolean stopEventGenerator()
        {
                //TODO: Cleanup code
                return getStatus();
        }
	// pushQ flushes the local queue of events into the global PriorityQueue
        static void pushQ()
        {
                int size = outq.size();
                Event e;
                while (size > 0)
                {
                        e = outq.poll();
                        EventProcessor.pq.insertEvent(e);
                        size--;

                }
        }
	// Creates a generic event
        static void createEvent(EventType t, int time) 
        {
                Event e = new Event();
                e.type = t;
                e.setString();
                e.setPriority(time);
                outq.add(e);
        }
	// Creates an event associated with a patient and disease
        static void createEvent(EventType t, int time, int p, int d)
        {
                Event e = new Event(p,d);
                e.type = t;
                e.setString();
                e.setPriority(time);
                outq.add(e);    
        }
        static public void generateDiagnoseEvent(int p, int d)
        {
                createEvent(EventType.DIAGNOSE_PATIENT,Game.getTime(),p,d);
                pushQ();                
        }

        static public void generateSymptomEvent()
        {
                createEvent(EventType.SHOW_SYMPTOM,Game.getTime());
        }

        static public void generatePatientEvent()
        {
                createEvent(EventType.NEW_PATIENT,Game.getTime());
        }

        static public void generateClockEvent()
        {
                createEvent(EventType.CLOCK_TICK,Game.getTime());
                pushQ();
        }
}
