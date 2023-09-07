package com.example.rideservice.tool;

import com.example.rideservice.dto.CommentDTO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;



public class RestClient<T,V> {
    private String urlApiUser = "http://localhost:8081/api/";

    private String urlApiComment = "http://localhost:8083/api/";

    private String urlApiToken = "http://localhost:8080/api/test";
    private RestTemplate template;
    private HttpHeaders httpHeaders;
    private HttpStatus httpStatus;

    public RestClient() {
        template = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "*/*");
        httpHeaders.add("content-type", "application/json");
    }

    public T getUser(String uri, Class<T> type) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<T> response = template.exchange(urlApiUser+uri, HttpMethod.GET, requestEntity, type);
        return response.getBody();
    }

    public CommentDTO getComment(String uri, Class<CommentDTO> type) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<CommentDTO> response = template.exchange(urlApiComment+uri, HttpMethod.GET, requestEntity, type);
        return response.getBody();
    }


    public boolean testToken(String token, Class<T> type) {
        httpHeaders.add("Authorization", token);
        HttpEntity<String> requestEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<T> response = template.exchange(urlApiToken, HttpMethod.GET, requestEntity, type);
        if(response.hasBody()) {
            return response.getStatusCode().is2xxSuccessful();
        }
        return false;
    }
}
