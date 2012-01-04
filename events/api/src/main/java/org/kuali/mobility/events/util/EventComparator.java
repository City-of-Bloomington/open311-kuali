/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kuali.mobility.events.util;

import java.util.Comparator;
import org.kuali.mobility.events.entity.Event;

/**
 *
 * @author swansje
 */
public class EventComparator implements Comparator<Event> {
    public int compare( final Event a, final Event b )
    {
        if( a.getDisplayStartDate() == null )
        {
            return -5;
        }
        
        if( b.getDisplayStartDate() == null )
        {
            return 8;
        }
        
        return (a.getDisplayStartDate()).compareToIgnoreCase(b.getDisplayStartDate());
    }
    
}
