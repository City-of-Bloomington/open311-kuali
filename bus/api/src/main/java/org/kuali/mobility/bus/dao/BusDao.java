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

package org.kuali.mobility.bus.dao;

import java.util.List;
import org.kuali.mobility.bus.entity.Bus;
import org.kuali.mobility.bus.entity.BusRoute;
import org.kuali.mobility.bus.entity.BusStop;

public interface BusDao {

    public List<BusRoute> getBusRoutes();
    public List<BusStop> getBusStops();
    public List<Bus> getBuses();
    
    public void loadRoutes();
    public void loadStops();
    public void loadBusLocations();
    
    public List<BusStop> findNearByStops(double lat1,double lon1, double radius);
}
