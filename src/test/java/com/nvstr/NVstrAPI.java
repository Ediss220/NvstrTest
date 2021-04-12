package com.nvstr;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NVstrAPI extends WebDriverSettings {




    @Test(priority = 1)
    public void POST_SignIn() throws UnirestException {
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

    @Test(priority = 2)
    public void GET_AAPL() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://www.nvstr.com/api/v1/securities?symbols=TSLA,AAPL")
                .header("Content_Type", Content_Type)
                .header("Accept", Accept)
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assert.assertEquals(200,response.getStatus());
        System.out.println("GetAAPL: Pass");
    }
    @Test(priority = 3)
    public void POST_PlaceOrder() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("https://www.nvstr.com/api/v1/order/place")
                .header("Content_Type", Content_Type)
                .header("Accept", Accept)
                .header("Uid", uid)
                .header("access-token",accesstoken)//Need to update
                .header("client",client)//Need to update
                .header("expiry",expiry)//Need to update
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
    @Test(priority = 1)
    public void GET_GetOrders() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://www.nvstr.com/api/v1/orders")
                .header("Content_Type", Content_Type)
                .header("Accept", Accept)
                .header("uid", uid)
                .header("access-token",accesstoken)//Need to update
                .header("client",client)//Need to update
                .header("expiry",expiry)//Need to update
                .asJson();
        System.out.println(response.getStatus());
        System.out.println(response.getBody());
        Assert.assertEquals(200,response.getStatus());
        System.out.println("GetOrders: Pass");
    }
}

