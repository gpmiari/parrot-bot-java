package chatbot.santander.testbot.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.limeprotocol.EnvelopeId;
import org.limeprotocol.Message;
import org.limeprotocol.Node;
import org.limeprotocol.serialization.JacksonEnvelopeSerializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessagesController {

	private Client client;
	private WebTarget messagesEndPoint;

	public MessagesController() {
		this.client = ClientBuilder.newClient();
		this.client.property("Content-Type", "application/json").property("Authorization",
				"Key aXBlcm95Z3dlYnRlc3Q6YnRRM1RRcmNIQmdQdkxoZE1ESzA=");

		this.messagesEndPoint = client.target("https://msging.net/messages");

	}

	@RequestMapping(value = "/echo", method = RequestMethod.POST)
	public int echoPost(@RequestBody String msg) throws Exception {

		JacksonEnvelopeSerializer sr = new JacksonEnvelopeSerializer();
		Message message = (Message) sr.deserialize(msg);

		Node from = message.getFrom();
		message.setFrom(message.getTo());
		message.setTo(from);
		message.setId(EnvelopeId.newId());
		message.setMetadata(null);
		String input = sr.serialize(message);
		
		Entity<String> a = Entity.entity(input, "application/json");
		Response response = this.messagesEndPoint.request().post(a);
		int status = response.getStatus();
		String ent = response.readEntity(String.class);
		///
		// URL url = new URL("https://msging.net/messages");
		// HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		// conn.setDoOutput(true);
		// conn.setRequestMethod("POST");
		// conn.setRequestProperty("Content-Type", "application/json");
		// conn.setRequestProperty("Authorization", "Key
		// aXBlcm95Z3dlYnRlc3Q6YnRRM1RRcmNIQmdQdkxoZE1ESzA=");
		//
		//
		// OutputStream os = conn.getOutputStream();
		// os.write(input.getBytes());
		// os.flush();
		//
		// int gambi= conn.getResponseCode();
		//

		// RestTemplate restTemplate = new RestTemplate();
		// HttpHeaders responseHeaders = new HttpHeaders();
		// responseHeaders.set("Authorization", "Key
		// aXBlcm95Z3dlYnRlc3Q6YnRRM1RRcmNIQmdQdkxoZE1ESzA=");
		// HttpEntity<String> et = new HttpEntity<String>(sr.serialize(message),
		// responseHeaders);
		//

		// Object quote =
		// restTemplate.postForLocation("https://msging.net/messages",et);

		// String batata = restTemplate.postForObject("https://msging.net/messages", et,
		// String.class);

		return status;
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
