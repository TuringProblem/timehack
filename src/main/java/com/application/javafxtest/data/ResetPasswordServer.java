package com.application.javafxtest.data;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import dev.mccue.jdk.httpserver.Body;
import dev.mccue.jdk.httpserver.HttpExchangeUtils;
import dev.mccue.urlparameters.UrlParameters;

import java.io.IOException;
import java.net.InetSocketAddress;
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

            UrlParameters params = UrlParameters.parse(exchange.getRequestURI().getQuery());
            String token = params.firstValue("token").orElseThrow();
            String response =
                           /*language=HTML*/
                            """
                            <html><body>
                            <h1>Reset Your Password</h1>
                            <p>Token: {{token}} </p>
                            <form action='/submit-reset' method='post'>
                            <input type='hidden' name='token' value='{{token}}'>
                            <input type='password' name='new_password' placeholder='New Password'><br>
                            <input type='password' name='confirm_password' placeholder='Confirm Password'><br>
                            <input type='submit' value='Reset Password'>
                            </form>
                            </body></html>""";
            Template template = Mustache.compiler().compile(response);
            HttpExchangeUtils.sendResponse(exchange, 200, Body.of(template.execute(Map.of(
                    "token", token
            ))));
        }
    }
}
