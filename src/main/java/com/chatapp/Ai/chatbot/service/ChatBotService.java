package com.chatapp.Ai.chatbot.service;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.chatapp.Ai.chatbot.dto.Content;
import com.chatapp.Ai.chatbot.dto.GeminiRequest;
import com.chatapp.Ai.chatbot.dto.Part;

@Service
public class ChatBotService {
	

    private final WebClient webClient;
    private final String apiKey;

    public ChatBotService(@Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
            .baseUrl("https://generativelanguage.googleapis.com/v1beta")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-goog-api-key", apiKey)
            .build();
    }

	

	public String getResponseFromAi(String userMessage) {
	
	 // Build the request DTO
	    	Part part = new Part(userMessage);
	   	Content content = new Content("user", List.of(part));
	    	GeminiRequest requestDto = new GeminiRequest(List.of(content));

	     	String response = webClient.post()
	    		.uri("/models/gemini-2.0-flash:generateContent?key=" + apiKey)
	                .bodyValue(requestDto)
	                .retrieve()
	                .bodyToMono(String.class)
	                .block();
	     	return extractReply(response);
	      
	    }

	private String extractReply(String json) {
	    JSONObject obj = new JSONObject(json);
	    return obj
	        .getJSONArray("candidates")
	        .getJSONObject(0)
	        .getJSONObject("content")
	        .getJSONArray("parts")
	        .getJSONObject(0)
	        .getString("text");
	}
}
	
