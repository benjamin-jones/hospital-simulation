/**
 * Module:      PriorityQueue
 *
 * Description: This module implements a priority queue utilizing a heap data structure implemented
 *              using an array. 
 *
 * Author:      Benjamin Jones
 * 
 * Revisions (committed, r# corresponds to SVN):
 *
 *              4/09/2012 r21: Fixed PQ element removal bug
 *              4/08/2012 r19: PQ is getting better, tracking element removal issue in *.getEvent()
 *              4/03/2012 r17: Created better tests. Still major bugs with PQ
 *              4/03/2012 r14: Wrote simple test case for PQ. Fixed minor bugs.
 *              4/03/2012 r13: PQ compiles.
 *              4/03/2012 r12: PQ implementation details.
 *              4/04/2012 r4: PQ initial revision. 
 */
public class PriorityQueue {
        int heapSize;
        int eventCount;
        Event[] heap;   
        boolean qFull;

        /**
         * Class constructor
         * @args integer n - size of the priority queue
         * @return none
         */
        public PriorityQueue(int n) {
                heapSize = n;
                eventCount = 0;
                heap = new Event[heapSize];
                qFull = false;  
        }

        /**
         * Accessor for queue full status
         * @args none
         * @return boolean - status of queue
         */
        public boolean queueFull() {
                return qFull;
        }

        /**
         * Swaps two elements in an array
         * @args integer child - index of child node
         *       integer parent - index of parent node
         * @return none
         */
        void swap(int child, int parent) {
                Event tmpEvent = heap[parent];
                heap[parent] = heap[child];
                heap[child] = tmpEvent;
        }

        /**
         * This implements the bubble-down routine to re-heapify
         * @args integer currentLoc - the node to start bubble-down with
         * @return boolean - if bubble-down complete
         */
        boolean fixHeapDown(int currentLoc)
        {
                int lChild = 2*currentLoc + 1;
                int rChild = 2*currentLoc + 2;
                int largest = currentLoc;       

                // If left child exists
                if (lChild < eventCount ) {
                        // If left child priority is less than parent, set left child to swap
                        if ( heap[lChild].getPriority() < heap[largest].getPriority()) {
                                largest = lChild;
                        }
                }

                // If right child exists
                if (rChild < eventCount ) {
                        // If right child priority is less than best node, set right child to swap
                        if (heap[rChild].getPriority() < heap[largest].getPriority()) {
                                largest = rChild;
                        }
                }

                // If we need to swap, then swap, and bubble down on next node
                if (largest != currentLoc) {
                        swap(currentLoc,largest);
                        return fixHeapDown(largest);
                }

                return true;
        
        }

        /**
         * Implements bubble-up routine
         * @args integer child - index of child node
         *       integer parent - index of parent node
         * @return boolean - if bubble-up finished
         */
        boolean fixHeap(int child, int parent) {
                if (heap[child].getPriority() < heap[parent].getPriority()) {
                        swap(child,parent);
                        return fixHeap(parent,getParent(parent));
                } 
                else {
                        return true;
                }
        }
        
        /**
         * Calculates parent index, given child index
         * @args integer child - index of child node
         * @return integer - index of parent node
         */
        int getParent(int child) {
                if (child == 0) {
                        return 0;
                }
                // Calculate the parent of the current node
                if (child == 1 || child == 2) {
                        return 0;
                }
                else {
                        if (child % 2 == 0) {
                                return (child-2)/2;
                        }
                        else {
                                return(child-1)/2;
                        }
                } // End calculation
        }

        // Utility function for debug purposes
        public void printQueue() {
                for (int i = 0; i < eventCount; i++) {
                        System.out.println(heap[i].getPriority());
                }
        }                       

        /**
         * Retrieves the next event in the queue
         * @args none
         * @return Event - the next event in the queue
         */
        public Event getEvent() {
                // If there are no events, send back an empty event
                if (eventCount == 0) {
                        //System.out.println("[INFO] No events in queue");
                        return new Event();
                }
                // Extract the next event
                Event root = heap[0];

                // Swap out the last event and remove old root
                swap(0,eventCount-1);
                
                //temporary bug fix
//              Event retrievedEvent = heap[eventCount-1];
///             int[] tmp = new int[heap.length-1];
//              System.arraycopy(heap,)
//              heap[eventCount-1]=null;
                
                eventCount--;
                heap[eventCount] = null;

                // If the queue was full, it no longer is
                if (qFull == true) {
                        qFull = false;
                }

                // If this was the last event, just return it
                if (eventCount == 0) {
                        return root;
                }
                // Else we need to reheapify
                else {
                        fixHeapDown(0);
                        return root;
                }
        }
        
        /**
         * Inserts a new Event into the queue
         * @args Event e - the new Event
         * @return boolean - if insertion was successful
         */
        public boolean insertEvent(Event e) {
                int currPos = eventCount;
                
                // If queue is not full, add new event to the end
                if (qFull != true) {
                        heap[currPos] = e;
                        eventCount++;
                        // If this is the last possible event we can hold, set queue full
                        if (eventCount == heapSize) {
                                qFull = true;
                        }
                }
                // Otherwise queue is full!
                else {
                        // change heapsize if queue is full for user added events
                        // there are more efficient ways to do this, but it was 
                        // implemented too late to do more efficiently
                        Event[] tmp = new Event[heap.length + 1];
                        System.arraycopy(heap, 0, tmp, 0, heap.length);
                        tmp[heap.length] = e;
                        eventCount++;
                        
                        heap = new Event[tmp.length];
                        System.arraycopy(tmp, 0, heap, 0, tmp.length);
                        heapSize = heap.length;         //update heapsize
                        
//                      return false;
                }
                // If this was an empty queue, just return
                if (currPos == 0) {
                        return true;
                }
                // Otherwise we need to reheapify
                return fixHeap(currPos,getParent(currPos));
                        
        }
        
}