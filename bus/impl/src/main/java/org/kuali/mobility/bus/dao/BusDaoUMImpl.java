/*
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.kuali.mobility.bus.entity.Bus;
import org.kuali.mobility.bus.entity.BusRoute;
import org.kuali.mobility.bus.entity.BusStop;
import org.kuali.mobility.bus.entity.UMBus;
import org.kuali.mobility.bus.entity.UMBusReader;
import org.kuali.mobility.bus.entity.UMBusRoute;
import org.kuali.mobility.bus.entity.UMBusRouteReader;
import org.kuali.mobility.bus.entity.UMBusStop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.thoughtworks.xstream.XStream;

/**
 *
 * @author Joe Swanson <joseswan@umich.edu>
 */
public class BusDaoUMImpl implements BusDao, ApplicationContextAware {
    
    public static Logger LOG = Logger.getLogger( BusDaoUMImpl.class );

    private ApplicationContext applicationContext;

    private List<BusStop>   busStops;
    private List<BusRoute>  busRoutes;
    private List<Bus>       buses;
     
    private String busStopUrl;
    private String busRouteUrl;
    private String busLocationUrl;
    
    public void loadRoutes() {
        
        XStream xstream = new XStream();
        xstream.processAnnotations(UMBusRouteReader.class);
        xstream.processAnnotations(UMBusRoute.class);
        xstream.processAnnotations(UMBusStop.class);
        xstream.addImplicitCollection( UMBusRouteReader.class, "routes" );
        xstream.addImplicitCollection( UMBusRoute.class, "stops");
        
        UMBusRouteReader routeReader = null;
        try {
            routeReader = (UMBusRouteReader)xstream.fromXML( new URL(getBusRouteUrl()) );
        } catch (MalformedURLException ex) {
            LOG.error(ex);
        }

        List<BusRoute> routes = new ArrayList<BusRoute>();
        if( routeReader != null )
        {
            for( UMBusRoute r : routeReader.getRoutes() ) {

                BusRoute route = (BusRoute)getApplicationContext().getBean( "busRoute" );
                route.setId( Long.parseLong( r.getId() ) );
                route.setName( r.getName() );
                
                if( null == getBusStops() ) {
                    setBusStops( new ArrayList<BusStop>() );
                }
                
                for( UMBusStop s : r.getStops() ) {
                    BusStop stop = (BusStop)getApplicationContext().getBean("busStop");
                    stop.setName( s.getName() );
                    stop.setId( s.getName().hashCode() );
                    stop.setLatitude( s.getLatitude() );
                    stop.setLongitude( s.getLongitude() );
                    
                    Map<Double, String> schedule = new TreeMap<Double,String>();
                    // TODO: Fix this to dynamically utilize the toacount variable.
                    //       This is functional but potentially will break.
                    if( s.getId1() != null ) {
                        LOG.debug( "Looking up bus "+s.getId1() );
                        Bus tBus = getBus( Long.parseLong( s.getId1() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa1() ), tBus.getName() );
                    }
                    if( s.getId2() != null ) {
                        LOG.debug( "Looking up bus "+s.getId2() );
                        Bus tBus = getBus( Long.parseLong( s.getId2() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa2() ), tBus.getName() );
                    }
                    if( s.getId3() != null ) {
                        LOG.debug( "Looking up bus "+s.getId3() );
                        Bus tBus = getBus( Long.parseLong( s.getId3() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa3() ), tBus.getName() );
                    }
                    if( s.getId4() != null ) {
                        LOG.debug( "Looking up bus "+s.getId4() );
                        Bus tBus = getBus( Long.parseLong( s.getId4() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa4() ), tBus.getName() );
                    }
                    if( s.getId5() != null ) {
                        LOG.debug( "Looking up bus "+s.getId5() );
                        Bus tBus = getBus( Long.parseLong( s.getId5() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa5() ), tBus.getName() );
                    }
                    if( s.getId6() != null ) {
                        LOG.debug( "Looking up bus "+s.getId6() );
                        Bus tBus = getBus( Long.parseLong( s.getId6() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa6() ), tBus.getName() );
                    }
                    if( s.getId7() != null ) {
                        LOG.debug( "Looking up bus "+s.getId7() );
                        Bus tBus = getBus( Long.parseLong( s.getId7() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa7() ), tBus.getName() );
                    }
                    if( s.getId8() != null ) {
                        LOG.debug( "Looking up bus "+s.getId8() );
                        Bus tBus = getBus( Long.parseLong( s.getId8() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa8() ), tBus.getName() );
                    }
                    if( s.getId9() != null ) {
                        LOG.debug( "Looking up bus "+s.getId9() );
                        Bus tBus = getBus( Long.parseLong( s.getId9() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa9() ), tBus.getName() );
                    }
                    if( s.getId10() != null ) {
                        LOG.debug( "Looking up bus "+s.getId10() );
                        Bus tBus = getBus( Long.parseLong( s.getId10() ) );
                        LOG.debug( "Bus was "+(tBus == null ? "not " : "" )+"found." );
                        schedule.put( new Double( s.getToa10() ), tBus.getName() );
                    }
                    
                    
                    
                    stop.setSchedule(schedule);
                    
                    route.addStop(stop);

                    if( !getBusStops().contains(stop) ) {
                        getBusStops().add(stop);
                    }
                }
                
                routes.add(route);
            }
            setBusRoutes( routes );
        }
        
        LOG.debug( (null == getBusRoutes() ? "Failed to load" :"Loaded" )+" routes." );
    }
    
    @Override
    public void loadStops() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void loadBusLocations() {
        XStream xstream = new XStream();
        xstream.processAnnotations(UMBusReader.class);
        xstream.processAnnotations(UMBus.class);
        xstream.addImplicitCollection( UMBusReader.class, "items" );
        
        UMBusReader busReader = null;
        try {
            busReader = (UMBusReader)xstream.fromXML( new URL(getBusLocationUrl()) );
        } catch (MalformedURLException ex) {
            LOG.error(ex);
        }

        List<Bus> busData = new ArrayList<Bus>();
        if( busReader != null )
        {
            for( UMBus b : busReader.getItems() ) {
                Bus bus = (Bus)getApplicationContext().getBean("bus");
                bus.setName( b.getRouteName() );
                bus.setId( b.getId() );
                bus.setRouteId( b.getRouteId() );
                bus.setRouteName( b.getRouteName() );
                bus.setHeading( b.getHeading() );
                bus.setColor( b.getColor() );
                bus.setLatitude( b.getLatitude() );
                bus.setLongitude( b.getLongitude() );
                
                busData.add(bus);
            }
            this.setBuses(busData);
        }
    }
    
    /**
     * @return the applicationContext
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param applicationContext the applicationContext to set
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @return the busStops
     */
    public List<BusStop> getBusStops() {
        return busStops;
    }

    /**
     * @param busStops the busStops to set
     */
    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    /**
     * @return the busRoutes
     */
    public List<BusRoute> getBusRoutes() {
        return busRoutes;
    }

    /**
     * @param busRoutes the busRoutes to set
     */
    public void setBusRoutes(List<BusRoute> busRoutes) {
        this.busRoutes = busRoutes;
    }

    /**
     * @return the buses
     */
    public List<Bus> getBuses() {
        return buses;
    }

    /**
     * @param buses the buses to set
     */
    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }
    
    public Bus getBus( long id ) {
        Bus bus = null;
        if( null == getBuses() ) {
            loadBusLocations();
        }
        for( Bus b : getBuses() ) {
            if( bus != null ) {
                break;
            }
            if( b.getId() == id ) {
                bus = b;
                LOG.debug( "Found bus "+id+" in list." );
            }
        }
        return bus;
    }

    /**
     * @return the busStopUrl
     */
    public String getBusStopUrl() {
        return busStopUrl;
    }

    /**
     * @param busStopUrl the busStopUrl to set
     */
    public void setBusStopUrl(String busStopUrl) {
        this.busStopUrl = busStopUrl;
    }

    /**
     * @return the busRouteUrl
     */
    public String getBusRouteUrl() {
        return busRouteUrl;
    }

    /**
     * @param busRouteUrl the busRouteUrl to set
     */
    public void setBusRouteUrl(String busRouteUrl) {
        this.busRouteUrl = busRouteUrl;
    }

    /**
     * @return the busLocationUrl
     */
    public String getBusLocationUrl() {
        return busLocationUrl;
    }

    /**
     * @param busLocationUrl the busLocationUrl to set
     */
    public void setBusLocationUrl(String busLocationUrl) {
        this.busLocationUrl = busLocationUrl;
    }

}
