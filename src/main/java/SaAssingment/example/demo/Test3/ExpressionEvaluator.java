package SaAssingment.example.demo.Test3;

import okhttp3.*;
import java.io.IOException;

import java.io.IOException;

public class ExpressionEvaluator {
    private static final String API_URL = "https://api.mathjs.org/v4/";

    private OkHttpClient client;
    private RateLimiter rateLimiter;

    public ExpressionEvaluator(int maxRequests) {
        this.client = new OkHttpClient();
        this.rateLimiter = new RateLimiter(maxRequests);
    }

    public String evaluateExpression(String expression) {
        Request request = new Request.Builder()
                .url(API_URL + "?expr=" + expression)
                .build();

        try {
            rateLimiter.acquire();
            Response response = client.newCall(request).execute();
            rateLimiter.release();

            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "Error: " + response.code();
            }
        } catch (IOException | InterruptedException e) {
            return "Error: " + e.getMessage();
        }
    }
}
