package com.Movie.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class MovieTesting {
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void saveMovie() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "    \"title\":\"Pengabdi Setan\",\n" +
                "    \"description\":\"adalah film horor\",\n" +
                "    \"rating\":\"7.0\"\n" +                
                "    \"image\":\"\"\n" +
                "    \n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);
        ResponseEntity<String> exchange = restTemplate.exchange
                ("http://localhost:8081/Movies", HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response =" + exchange.getBody());
    }

    @Test
    public void updateMovie() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{   \n" +
                "    \"id\":\"1\",\n" +
                "    \"title\":\"Iblis\",\n" +
                "    \"description\":\"adalah film Komedi\",\n" +
                "    \"rating\":\"8.0\"\n" +                
                "    \"image\":\"\"\n" +
                "    \n" +
                "}";
        Long id = 1L;
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/Movies/" + id, HttpMethod.PUT, entity, String.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response =" + exchange.getBody());
    }

    @Test
    public void deleteMovie() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        Long id = 1L;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/Movies" + id, HttpMethod.DELETE, entity, String.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response =" + exchange.getBody());
    }

    @Test
    public void listMovie() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/Movies", HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response =" + exchange.getBody());
    }

    @Test
    public void listMovieId() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        Long id = 1L;
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/Movies/" + id, HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response =" + exchange.getBody());
    }
}

