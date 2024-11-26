package vkAPI;

import io.github.cdimascio.dotenv.Dotenv;


public class AccessToken {
    public static String getAccessToken() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("TOKEN");
    }
}