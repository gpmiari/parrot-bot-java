package chatbot.santander.testbot.controller;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.json.JSONObject;
import org.limeprotocol.EnvelopeId;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chatbot.santander.testbot.helper.OkHttpHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class MessagesController {

	private Client client;
	private WebTarget messagesEndPoint;

	public MessagesController() {
		// this.client = ClientBuilder.newClient();
		// this.client.property("Content-Type",
		// "application/json").property("Authorization",
		// "Key aXBlcm95Z3dlYnRlc3Q6YnRRM1RRcmNIQmdQdkxoZE1ESzA=");
		//
		// this.messagesEndPoint = client.target("https://msging.net/messages");

	}

	@RequestMapping(value = "/echo", method = RequestMethod.POST)
	public int echoPost(@RequestBody String msg) throws Exception {

		try {
			JSONObject j = new JSONObject(msg);
			j.remove("sender");
			j.remove("metadata");

			String responseBody;

			OkHttpClient client = OkHttpHelper.getClient();
			Request req = OkHttpHelper.requestPOST(j.toString());

			Response response = client.newCall(req).execute();
			responseBody = response.body().string();

			if (response.code() == 200) {
				System.out.println(responseBody);
			} else {
				System.out.println(response.toString());
			}

			JSONObject jresp = new JSONObject();
			jresp.put("to", j.get("from"));
			jresp.put("from", j.get("to"));
			jresp.put("type", j.get("type"));
			jresp.put("content", j.get("content"));
			jresp.put("id", EnvelopeId.newId());
			
			
			client = OkHttpHelper.getClient();
			req = OkHttpHelper.requestPOST(jresp.toString());

			response = client.newCall(req).execute();
			responseBody = response.body().string();

			if (response.code() == 200) {
				System.out.println(responseBody);
			} else {
				System.out.println(response.toString());
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		

		return 0;
	}

	@RequestMapping(value = "/echo2", method = RequestMethod.POST)
	public int echoGetter(@RequestBody String msg) throws Exception {
		System.out.println(msg);
		return 0;
	}

	@RequestMapping(value = "/echo", method = RequestMethod.GET)
	public String echo(@RequestParam(value = "msg") String message) {
		return message;
	}

}
