package chatbot.santander.testbot.helper;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpHelper {
	 private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	  
	    public static OkHttpClient getClient() {

	    	OkHttpClient client = new OkHttpClient.Builder()
	                .connectTimeout(180, TimeUnit.SECONDS)
	                .writeTimeout(180, TimeUnit.SECONDS)
	                .readTimeout(180, TimeUnit.SECONDS)
	                .build();
	    	
	    	return client;
	    }

	    public static Request requestPOST(String json) {
	        Request request = post(json);

	        return request;
	    }

	    private static Request post(String json) {
	        Request request = null;

	        RequestBody body = RequestBody.create(JSON, json);

	        request = new Request.Builder()
	                .url("https://msging.net/messages")
	                .addHeader("Content-Type", "application/json")
	                .addHeader("Authorization", "Key d2ViaG9va2phdmFnbXA6NFNGTEJaQ0NNQjh5c2pqV0tRM0s=")
	                .post(body)
	                .build();
	        return request;
	    }

	    public static Request requestGET() {
	        Request request = get();

	        return request;
	    }

	    private static Request get() {
	        Request request = null;

	        request = new Request.Builder()
	                .url("https://msging.net/messages")
	                .addHeader("Content-Type", "application/json")
	                .build();

	        return request;
	    }
}
