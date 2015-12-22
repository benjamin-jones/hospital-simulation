/* EventProcessor - The event processor is responsible for handling events and updating the game 
 * 
 * Author: Benjamin Jones
 * Creation Date: May 21, 2012
 *
 * Revisions
 * May 21, 2012: Initial rev.
 */

class EventProcessor
{
	public static PriorityQueue pq = new PriorityQueue(256);
	static boolean status;

	static public boolean getStatus()
	{
		return status;
	}
	// Processes events by pulling them from the PQ and calling the appropriate Game update method
	static public boolean processEvents()
	{
		int clock = Game.getTime();
		boolean process = true;
		Event e = pq.getEvent();
		while ( e.getPriority() <= clock)
		{
			if (e.getType() == EventType.NEW_PATIENT)
			{
				Game.newPatientEventHandler(e);
			}
			else if (e.getType() == EventType.SHOW_SYMPTOM)
			{
				Game.showSymptomEventHandler(e);
			}
			else if (e.getType() == EventType.DIAGNOSE_PATIENT)
			{
				Game.diagnosePatientEventHandler(e);
			}
			else if (e.getType() == EventType.CLOCK_TICK)
			{
				Game.clockTickEventHandler(e);
			}
			else
			{
				displayError(e);

				if (handleError(e))
				{
					continue;
				}
				else
				{
					break;
				}
			}
			e = pq.getEvent();
		}
		return getStatus();
	}

	// These haven't been necessary but could provide useful diagnostic information
 	// Errors in the PQ have not been observed.
	static void displayError(Event e)
	{

	}

	static boolean handleError(Event e)
	{
		if (e.getType() == EventType.GENERIC)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
