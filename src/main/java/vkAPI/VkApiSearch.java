package vkAPI;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import static vkAPI.AccessToken.getAccessToken;


public class VkApiSearch {

    private static final String ACCESS_TOKEN = getAccessToken();

    public static String getAgeFromStudent(String name) {
        try {
            // Кодируем имя для безопасной передачи через URL
            String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8);

            // Формируем URL для поиска
            String apiUrl = String.format(
                    "https://api.vk.com/method/users.search?q=%s&fields=bdate&access_token=%s&v=5.131",
                    encodedName, ACCESS_TOKEN
            );

            // Создаем HttpClient для отправки запроса
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            // Отправляем запрос и получаем ответ
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Парсим JSON-ответ
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            // Проверка, есть ли в ответе данные
            if (jsonResponse.has("response") &&
                    jsonResponse.getAsJsonObject("response").has("items")) {
                JsonArray itemsArray = jsonResponse
                        .getAsJsonObject("response")
                        .getAsJsonArray("items");

                // Проверка, есть ли результаты поиска
                if (!itemsArray.isEmpty()) {
                    JsonObject userInfo = itemsArray
                            .get(0)
                            .getAsJsonObject(); // Берем первого найденного пользователя

                    // Извлекаем дату рождения
                    if (userInfo.has("bdate")) {
                        String birthDate = userInfo.get("bdate").getAsString();
                        int age = calculateAge(birthDate);
                        if (age > 0) {
//                            String vkName = userInfo.getString("first_name") + " " + userInfo.getString("last_name");
                            return String.valueOf(age);
                        } else return "дата рождения не указана";
                    } else {
                        return "дата рождения не указана";
                    }
                } else {
                    return "пользователь не найден или не зарегистрирован в вк";
                }
            } else {
                return "Ошибка при получении данных";
            }

        } catch (Exception e) {
            return "Ошибка при получении данных";
        }
    }

    public static int calculateAge(String birthDate) {
        try {
            // Преобразуем строку с датой в объект LocalDate
            String[] dateParts = birthDate.split("\\.");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = (dateParts.length > 2) ? Integer.parseInt(dateParts[2]) : LocalDate.now().getYear();

            LocalDate birth = LocalDate.of(year, month, day);
            LocalDate now = LocalDate.now();

            // Вычисляем период между датами
            Period period = Period.between(birth, now);

            return period.getYears() - 1;
        } catch (Exception e) {
            System.out.println("Ошибка при вычислении возраста: " + e.getMessage());
            return -1;
        }
    }
}
