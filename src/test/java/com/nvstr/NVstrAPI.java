package com.nvstr;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NVstrAPI extends WebDriverSettings {




    @Test
    public void SignIn() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://www.nvstr.com/api/v1/auth/sign_in")
                .header("Content_Type", Content_Type)
                .header("Accept", Accept)
                .field("email", username)
                .field("password",pass)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());

        Assert.assertEquals(200,response.getStatus());
        Assert.assertEquals("{\"success\":true}","" + response.getBody());
        System.out.println("SignIn: Pass");
    }

    @Test
    public void GetAAPL() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://www.nvstr.com/api/v1/securities?symbols=TSLA,AAPL")
                .header("Content_Type", Content_Type)
                .header("Accept", Accept)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assert.assertEquals(200,response.getStatus());
        System.out.println("GetAAPL: Pass");
    }
    @Test
    public void PlaceOrder() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://www.nvstr.com/api/v1/order/place")
                .header("Content_Type", Content_Type)
                .header("Accept", Accept)
                .header("Uid", uid)
                .header("access-token",accesstoken)
                .header("client",client)
                .header("expiry",expiry)
                .body("{\n" +
                        "\"order\":{\n" +
                        "\"operation\":\"buy\",\n" +
                        "\"type\":\"market\",\n" +
                        "\"shares\":20,\n" +
                        "\"security_id\":286,\n" +
                        "\"limit_price\":null,\n" +
                        "\"stop_price\":null,\n" +
                        "\"Is_from_optimizer\":false\n" +
                        "},\n" +
                        "\"override_warnings\":[\"market_closed\"]\n" +
                        "}")
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assert.assertEquals(200,response.getStatus());
        System.out.println("GetOrders: Pass");
    }
    @Test
    public void GetOrders() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://www.nvstr.com/api/v1/orders")
                .header("Content_Type", Content_Type)
                .header("Accept", Accept)
                .header("Uid", uid)
                .header("access-token",accesstoken)
                .header("client",client)
                .header("expiry",expiry)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assert.assertEquals(200,response.getStatus());
        System.out.println("GetOrders: Pass");
    }
}

