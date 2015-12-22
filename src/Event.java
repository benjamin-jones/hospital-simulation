/* Event - The Event module encapsulates the notion of an event in the simulation context
 * 
 * Author: Benjamin Jones
 * Creation Date: May 15, 2012
 *
 * Revisions
 * May 15, 2012: Initial Commit
 */

class Event {
        public EventType type;
        int PR;
        String INF;
        int patient;
        int disease;
        Event() {
                type = EventType.GENERIC;
                PR = Integer.MAX_VALUE;
                INF = this.setString();
        }
        Event(int p, int d) {
                type = EventType.GENERIC;
                PR = Integer.MAX_VALUE;
                INF = this.setString();
                patient = p;
                disease = d;
        }

        public int getPatient() {
                return patient;
        }
        
        public int getDisease() {
                return disease;
        }
        public EventType getType()
        {
                return type;
        }

        public int getPriority() {
                return PR;
        }

        public void setPriority(int pri) {
                PR = pri;
        }

        public String getString() {
                INF = this.setString();
                return INF;
        }

        String setString() {
                switch (this.type) {
                        case NEW_PATIENT:
                                return "A new patient was admitted.";
                        case SHOW_SYMPTOM:
                                return "A patient has developed a new symptom!";
                        case DIAGNOSE_PATIENT:
                                return "Applying diagnosis...";
                        case CLOCK_TICK:
                                return "The clock is ticking...";
                        case GENERIC:
                                return "Nothing happened.";
                        default:
                                return "ERROR: Bad EventType used.";
                }
        }
                
}
