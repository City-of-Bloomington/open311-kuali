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
 
package org.kuali.mobility.bus.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.kuali.mobility.bus.dao.BusDao;
import org.kuali.mobility.bus.entity.BusRoute;
import org.kuali.mobility.bus.entity.BusStop;
import org.springframework.stereotype.Service;

@Service
public class BusServiceImpl implements BusService {
    
    private static Logger LOG = Logger.getLogger( BusServiceImpl.class );
    
    private BusDao dao;
    
    @Override
    public List<BusRoute> getRoutes(String campus) {
        if( getDao().getBusRoutes() == null || getDao().getBusRoutes().size() == 0 ) {
            getDao().loadRoutes();
        }
        return getDao().getBusRoutes();
    }
    
    @Override
    public BusRoute getRoute(String campus, long id) {
        BusRoute route = null;
        if( getDao().getBusRoutes() == null || getDao().getBusRoutes().size() == 0 ) {
            getDao().loadRoutes();
        }
        for( BusRoute r : getDao().getBusRoutes() ) {
            if( route != null ) break;
            if( r.getId() == id ) {
                route = r;
            }
        }
        return route;
    }

    @Override
    public List<BusStop> getStops(String campus) {
        if( getDao().getBusStops() == null || getDao().getBusStops().size() == 0 ) {
            getDao().loadRoutes();
        }
        return getDao().getBusStops();
    }

    @Override
    public BusStop getStop(String campus, long id) {
        BusStop stop = null;
        if( getDao().getBusStops() == null || getDao().getBusStops().size() == 0 ) {
            getDao().loadRoutes();
        }
        for( BusStop s : getDao().getBusStops() ) {
            if( stop != null ) break;
            if( s.getId() == id ) {
                stop = s;
            }
        }
        return stop;
    }

    public void setDao(BusDao dao) {
        this.dao = dao;
    }
    public BusDao getDao() {
        return dao;
    }

}
