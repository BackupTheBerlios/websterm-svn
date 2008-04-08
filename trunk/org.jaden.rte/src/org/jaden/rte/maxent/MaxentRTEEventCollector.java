

/*
 * MaxentRTEEventCollector.java 1.00 Fri Feb 22 01:35:09 +0000 2008
 *
 */

package org.jaden.rte.maxent;
import opennlp.maxent.EventCollector;
import org.jaden.rte.RTEDataIterator;
import opennlp.maxent.Event;
import java.util.List;
import java.util.ArrayList;
import opennlp.maxent.ContextGenerator;
import org.jaden.rte.THPair;


/**
 * TODO: Class description
 *
 * @version 1.00 
 * @author 
 */
public class MaxentRTEEventCollector implements EventCollector {
    private RTEDataIterator iterator;

    public MaxentRTEEventCollector(RTEDataIterator iterator) {
        this.iterator = iterator;
    }
    
    public Event[] getEvents() {
        return getEvents(false);
    }

    public Event[] getEvents(boolean mode) {
        iterator.reset();
        List<Event> events = new ArrayList<Event>();

        ContextGenerator cg = new MaxentRTEContextGenerator();
        for(THPair pair : iterator) {
            String context[] = cg.getContext(pair);
            Event event = new Event(Boolean.toString(pair.getEntailment()),
                    context);
            events.add(event);
        }
        System.out.println(events.size());
        return events.toArray(new Event[2]);
    }
}


