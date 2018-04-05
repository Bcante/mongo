package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Tools for HTTP
 * 
 * @author Bonnin
 *
 */
public class HTTPTools {
	
	// Minimum time between HTTP requests in ms (set to 4 seconds)
	private int mt = 4000;
	
	// Last HTTP request
	private long last;

	/**
	 * Send a GET request
	 * @param url the URL of the request
	 * @return the response
	 */
	public String sendGet(String url) {
		// Check the elapsed time since the previous request 
		while (System.currentTimeMillis() - last < mt);
		last = System.currentTimeMillis();
		
		try {
			// Prepare the request
			StringBuilder result = new StringBuilder();
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF8");
			BufferedReader br = new BufferedReader(isr);
			
			// Fetch the response
			String line;
			while ((line = br.readLine()) != null) {
				result.append(line);
			}
			
			// Close readers and return
			br.close();
			isr.close();
			return result.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
