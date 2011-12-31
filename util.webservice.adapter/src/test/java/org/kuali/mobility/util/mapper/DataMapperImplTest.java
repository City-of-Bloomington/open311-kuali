package org.kuali.mobility.util.mapper;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.kuali.mobility.util.mapper.domain.Seat;

public class DataMapperImplTest {

	private static final Logger logger = Logger.getLogger( DataMapperImplTest.class );
	
	public static final String DATA_URL = "http://ulib.iupui.edu/utility/seats.php?show=locations&type=data";

	public static final String DATA_FILE = "seatSampleData.xml";
	public static final String MAPPING_FILE = "seatMapping.xml";
	
	public static final String JSON_DATA_FILE = "sampleJsonData.json";
	public static final String JSON_MAPPING_FILE = "sampleJsonMapping.xml";
	
	@Test
	public void testMapData() {
		
		List<Seat> seats = new ArrayList<Seat>();
		try
		{
			DataMapperImpl mapper = new DataMapperImpl();
			seats = mapper.mapData( seats, DATA_FILE, MAPPING_FILE);
		}
		catch( ClassNotFoundException cnfe )
		{
			logger.error( cnfe );
		}
		
		assertTrue( "failed to parse file.", seats != null && seats.size() == 16 );

		for( Seat s : seats )
		{
			logger.debug( s.getLab() );
			logger.debug( "\t"+ s.getFloor() );
			logger.debug( "\t"+ s.getBuildingCode() );
			logger.debug( "\t"+ s.getAvailability() );
			logger.debug( "\t"+ s.getWindowsAvailability() );
			logger.debug( "\t"+ s.getMacAvailability() );
		}
	}

	@Test
	public void testMapJsonData() {
		
		List<Seat> seats = new ArrayList<Seat>();
		try
		{
			DataMapperImpl mapper = new DataMapperImpl();
			seats = mapper.mapData( seats, JSON_DATA_FILE, JSON_MAPPING_FILE);
		}
		catch( ClassNotFoundException cnfe )
		{
			logger.error( cnfe );
		}
		
		assertTrue( "failed to parse file.", seats != null && seats.size() == 3 );

		for( Seat s : seats )
		{
			logger.debug( s.getLab() );
			logger.debug( "\t"+ s.getFloor() );
			logger.debug( "\t"+ s.getBuildingCode() );
			logger.debug( "\t"+ s.getAvailability() );
			logger.debug( "\t"+ s.getWindowsAvailability() );
			logger.debug( "\t"+ s.getMacAvailability() );
		}
	}
}
