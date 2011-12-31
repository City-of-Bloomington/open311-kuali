package org.kuali.mobility.util.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kuali.mobility.configparams.entity.ConfigParam;
import org.kuali.mobility.configparams.service.ConfigParamService;
import org.springframework.beans.factory.annotation.Autowired;

public class ExampleTest extends KMETestCaseBase {

	@Autowired	
	private ConfigParamService configParamService;
	
	@Override
	public void bootstrapTestData() {
		super.bootstrapTestData();
		 
		ConfigParam configParam = new ConfigParam();
		configParam.setName("cp-a");
		configParam.setValue("a's value");
		configParamService.saveConfigParam(configParam);
	}

	@Test
	public void testMethodOne() {
		ConfigParam configParam = configParamService.findConfigParamByName("cp-a");
		
		assertNotNull("configParam should not be null", configParam);
		assertNotNull("configParam's value should not be null", configParam.getValue());
		
		assertEquals("configParam value should be [a's value]", configParam.getValue(), "a's value");
	}
	
	@Test
	public void testMethodTwo() {
		ConfigParam configParam = configParamService.findConfigParamByName("cp-a");
		
		assertNotNull("configParam should not be null", configParam);

		configParam.setValue("new value");		
		configParamService.saveConfigParam(configParam);

		ConfigParam configParam2 = configParamService.findConfigParamByName("cp-a");
		
		assertNotNull("configParam should not be null", configParam2);
		assertNotNull("configParam's value should not be null", configParam2.getValue());
		
		assertEquals("There should only be one ConfigParam", configParamService.findAllConfigParam().size(), 1);
		assertEquals("configParam value should be [new value]", configParam2.getValue(), "new value");
	}
	
	@Override
	public void cleanTestData() {
		super.cleanTestData();
		ConfigParam configParam = configParamService.findConfigParamByName("cp-a");
		configParamService.deleteConfigParamById(configParam.getConfigParamId());
	}
	
}
