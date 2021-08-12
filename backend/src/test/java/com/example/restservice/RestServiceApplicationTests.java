package com.example.restservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@SpringBootTest
class RestServiceApplicationTests {

    @Test
    void hello() {
        System.out.println("Hello Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/hello");
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

    @Test
    void put() {
        System.out.println("put Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            for (int i = 4; i < 7; i++) {
                HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/items/");
                httpPut.addHeader(new BasicHeader("Content-Type", ContentType.APPLICATION_JSON.toString()));

                ObjectMapper objectMapper = new ObjectMapper();
                Item item = new Item(0, "Item" + i, i * 100.0);
                String tmp = objectMapper.writeValueAsString(item);

                httpPut.setEntity(new StringEntity(tmp));

                try (CloseableHttpResponse response = httpclient.execute(httpPut)) {
                    System.out.println("response code: " + response.getStatusLine().getStatusCode());
                    HttpEntity entity = response.getEntity();
                    try (final InputStream inStream = entity.getContent()) {
                        System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

//    @Test
    void invalidPut() {
        System.out.println("invalidPut Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut("http://127.0.0.1:8080/items/");
            httpPut.addHeader(new BasicHeader("Content-Type", ContentType.APPLICATION_JSON.toString()));
            httpPut.setEntity(new StringEntity(""));

            try (CloseableHttpResponse response = httpclient.execute(httpPut)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }



    @Test
    void getAll() {
        System.out.println("getAll Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/items/");
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

    @Test
    void get() {
        System.out.println("get Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/items/2/");
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

//    @Test
    void invalidGet() {
        System.out.println("invalidGet Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://127.0.0.1:8080/items/321/");
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

    @Test
    void post() {
        System.out.println("post Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/items/update");

            httpPost.addHeader(new BasicHeader("Content-Type", ContentType.APPLICATION_JSON.toString()));

            ObjectMapper objectMapper = new ObjectMapper();
            Item item = new Item(2, "Item3", 3000.0);
            String tmp = objectMapper.writeValueAsString(item);

            httpPost.setEntity(new StringEntity(tmp));

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

//    @Test
    void invalidPost() {
        System.out.println("invalidPost Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8080/items/update");

            httpPost.addHeader(new BasicHeader("Content-Type", ContentType.APPLICATION_JSON.toString()));
            httpPost.setEntity(new StringEntity(""));

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

    @Test
    void delete() {
        System.out.println("delete Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete("http://127.0.0.1:8080/items/1");
            try (CloseableHttpResponse response = httpclient.execute(httpDelete)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }

//    @Test
    void invalidDelete() {
        System.out.println("invalidDelete Test");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete("http://127.0.0.1:8080/items/87423");
            try (CloseableHttpResponse response = httpclient.execute(httpDelete)) {
                System.out.println("response code: " + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                try (final InputStream inStream = entity.getContent()) {
                    System.out.println(new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n")));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
    }
}
