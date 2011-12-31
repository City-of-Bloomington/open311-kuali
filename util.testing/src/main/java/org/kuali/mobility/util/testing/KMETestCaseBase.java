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

package org.kuali.mobility.util.testing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class KMETestCaseBase {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(KMETestCaseBase.class);

	private static JettyServer js;
	
	@BeforeClass
	public static void startup() {
		js = new JettyServer();
		try {
			js.start();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	
	@AfterClass
	public static void shutdown() {
		if (js != null && js.isStarted()) {
			try {
				js.stop();
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}
	
	@Before
	public void startTest() {
		bootstrapTestData();
	}
	
	@After
	public void endTest() {
		cleanTestData();
	}
	
	public void bootstrapTestData() {}

	public void cleanTestData() {}
	
}
