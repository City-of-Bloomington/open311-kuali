/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
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
