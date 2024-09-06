package com.application.javafxtest.data;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ResetPasswordServer {
    private static final int PORT = 8080;
    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/reset-password", new ResetPasswordHandler());
        server.setExecutor(null);
        server.start();
        System.out.printf("Server started on port %s\n", PORT);
    }

    static class ResetPasswordHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());
            String token = params.get("token");

            String response = "<html><body>"
                            + "<h1>Reset Your Password</h1>"
                            + "<p>Token: " + token + "</p>"
                            + "<form action='/submit-reset' method='post'>"
                            + "<input type='hidden' name='token' value='" + token + "'>"
                            + "<input type='password' name='new_password' placeholder='New Password'><br>"
                            + "<input type='password' name='confirm_password' placeholder='Confirm Password'><br>"
                            + "<input type='submit' value='Reset Password'>"
                            + "</form>"
                            + "</body></html>";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private Map<String, String> queryToMap(String query) {
            Map<String, String> result = new HashMap<>();
            if (query != null) {
                for (String param : query.split("&")) {
                    String[] entry = param.split("=");
                    if (entry.length > 1) {
                        result.put(entry[0], entry[1]);
                    } else {
                        result.put(entry[0], "");
                    }
                }
            }
            return result;
        }
    }
}
