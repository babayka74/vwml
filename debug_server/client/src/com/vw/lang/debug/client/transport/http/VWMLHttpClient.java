package com.vw.lang.debug.client.transport.http;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.vw.lang.debug.common.VWMLDebugCommand;
import com.vw.lang.debug.common.VWMLDebugCommandResult;
import com.vw.lang.debug.common.VWMLDebugCommandTranscoder;

/**
 * Used as transport for sending commands to VWMLHttpServer
 * @author Oleg
 *
 */
public class VWMLHttpClient {
	/**
	 * Client's properties
	 * @author Oleg
	 *
	 */
	public static class VWMLHttpClientProps {
		// server's URL
		private String targetUrl;

		public String getTargetUrl() {
			return targetUrl;
		}

		public void setTargetUrl(String targetUrl) {
			this.targetUrl = targetUrl;
		}
	}
	
	private CloseableHttpClient httpClient = null;
	private VWMLHttpClientProps props = null;
	
	private VWMLHttpClient() {
		
	}
	
	public static VWMLHttpClient instance() {
		return new VWMLHttpClient();
	}
	
	public void init(VWMLHttpClientProps props) throws Exception {
		if (props == null || props.getTargetUrl() == null) {
			throw new Exception("Http client's property must be set before");
		}
		httpClient = HttpClients.createDefault();
		this.props = props;
	}
	
	/**
	 * Synchronously sends command to server and processes response (processed response is encapsulated into VWMLDebugCommandResult)
	 * @param cmd
	 * @return
	 * @throws Exception
	 */
	public VWMLDebugCommandResult send(VWMLDebugCommand cmd) throws Exception {
		VWMLDebugCommandResult cmdResult = null;
		String fullCmdUrl = props.getTargetUrl() + "/" + cmd.getName();
		HttpPost httpPost = new HttpPost(fullCmdUrl);
		String data = VWMLDebugCommandTranscoder.toJSON(cmd);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair(VWMLDebugCommand.getCommandTag(), data));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response = httpClient.execute(httpPost);
		String status = null;
		try {
			status = response.getStatusLine().getStatusCode() + "/" + response.getStatusLine().getReasonPhrase();
			HttpEntity e = response.getEntity();
			InputStream is = e.getContent();
			if (is == null) {
				throw new Exception("Failed to process response of command '" + data + "'; response body is 'null'");
			}
			cmdResult = (VWMLDebugCommandResult)VWMLDebugCommandTranscoder.fromJSONEx(is, VWMLDebugCommandResult.class);
		}
		finally {
			response.close();
		}
		cmdResult.setDetailedResponse(status);
		return cmdResult;
	}
}
