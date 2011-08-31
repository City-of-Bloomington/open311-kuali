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
package org.kuali.mobility.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HttpUtil {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(HttpUtil.class);
	
	public static String stringFromUrl(String url) {
		String response = "";
		
		BufferedReader in = null;
        try {
            URL feed = new URL(url);
            in = new BufferedReader(new InputStreamReader(feed.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	response += inputLine;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {            
                in.close();
            } catch (Exception e) {}
        }
        
        return response;
	}
	
	public static boolean needsAuthenticated(String path) {
		if (path.startsWith("/oauth") 
				|| path.startsWith("/backdoor") 
				|| path.startsWith("/admin") 
				|| path.startsWith("/publishing") 
				|| path.startsWith("/myclasses") 
				|| path.startsWith("/calendar")
				|| path.startsWith("/classifieds")) {
			return true;
		}
		return false;
	}

	public static boolean backdoorRestricted(String path) {
		if (path.indexOf("/myclasses") >= 0 && 
				(path.indexOf("/assignments") >= 0 
				|| path.indexOf("/announcements") >= 0
				|| path.indexOf("/attachment") >= 0
				|| path.indexOf("/grades") >= 0
				|| path.indexOf("/roster") >= 0
				|| path.indexOf("/resources") >= 0
				|| path.indexOf("/forums") >= 0
				|| path.indexOf("/messages") >= 0)) {
			return true;
		}
		return false;
	}

}
