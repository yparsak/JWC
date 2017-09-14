package name.parsak.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Tcpip {
	public static final String NL = System.getProperty("line.separator");
	public static final String XML = "text/xml";
	private static final String POST = "POST";
	private static final String GET = "GET";
	
	private static URL url = null;
	private static HttpURLConnection conn = null;
	private static OutputStreamWriter writer = null;
	

	private static void setURL(final String url_str) throws MalformedURLException {
		url = new URL(url_str);
	}
	//
	private static void connect(final String method) throws IOException {
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		if (method.equals(POST)) {
			conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.addRequestProperty("accept", XML);
            conn.addRequestProperty("content-type", XML);
		}
		conn.connect();	
	}
    private static void disconnect() {
    	conn.disconnect();
    }
	//
	private static void setWriter() throws IOException {
		writer = new OutputStreamWriter(conn.getOutputStream());
	}
	private static String readResponse() throws IOException {
		InputStream inputstream = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
		StringBuilder response = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) { response.append(line + NL); }
		return response.toString();
	}
	//   
    public String post(String url_str, String request) {
    	try {
			setURL(url_str);
			try {
				connect(POST);
				setWriter();
				writer.write(request);
				writer.flush();
				String response = readResponse();
				disconnect();
				return response;
			} catch (IOException e) {
				e.printStackTrace();
				return e.getMessage();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
    }
    //
    public String get(String url_str) {
    	try {
			setURL(url_str);
			try {
				connect(GET);
				String response = readResponse();
				disconnect();
				return response;
			} catch (IOException e) {
				e.printStackTrace();
				return e.getMessage();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
    }
}
