package com.ecspresso.catfact;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CatFact {
    public static Fact getFact() {
        Logger logger = LoggerFactory.getLogger(CatFact.class);
        logger.info("Hämtar lite fakta om katter.");
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicHttpRequest httpPost = new BasicClassicHttpRequest(Method.GET, "https://catfact.ninja/fact");
            BasicHttpClientResponseHandler httpHandler = new BasicHttpClientResponseHandler();
            String response = httpclient.execute(httpPost, httpHandler);
            ObjectMapper objectMapper = new ObjectMapper();
            Fact fact = objectMapper.readValue(response, Fact.class);
            logger.info("Lite fin fakta om katter är " + fact.fact());
            return fact;
        } catch(IOException e) {
            Fact fact = new Fact("No cat fact for you.", 20);
            logger.error("Det blev ingen kattfakta den är gången.");
            return fact;
        }
    }

    public record Fact(String fact, int length) {
        @Override
        public String toString() {
            return fact;
        }
    }
}
