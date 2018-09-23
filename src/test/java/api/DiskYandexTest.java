package api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Rogozin Roman on 23.09.2018.
 */
@Title("API тесты в Yandex Disk")
public class DiskYandexTest {

    @Title("Отображение списка всех файлов")
    @Test
    public void showMeFiles() {
        try {
            HttpResponse<String> response = Unirest.get("https://cloud-api.yandex.net/v1/disk/resources/files")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "AQAAAAAQSJGtAADLW72eHsFpPU0nqDWYuBurU7Q")
                    .header("Cache-Control", "no-cache")
                    .asString();
            System.out.println("Body: " + response.getBody());
            Assert.assertThat(response.getBody(), not(""));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Title("Проверка существования файла Зима.jpg")
    @Test
    public void fileExists() {
        try {
            HttpResponse<String> response = Unirest.get("https://cloud-api.yandex.net/v1/disk/resources/files")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "AQAAAAAQSJGtAADLW72eHsFpPU0nqDWYuBurU7Q")
                    .header("Cache-Control", "no-cache")
                    .asString();
            System.out.println("Body: " + response.getBody());
            Assert.assertThat(response.getBody(), containsString("Зима.jpg"));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Title("Создать и удалить папку Test")
    @Test
    public void createAndDeleteFolder(){
        try {
            // Create
            HttpResponse<String> responseCreate = Unirest.put("https://cloud-api.yandex.net/v1/disk/resources?path=disk%3A%2FTest")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "AQAAAAAQSJGtAADLW72eHsFpPU0nqDWYuBurU7Q")
                    .header("Cache-Control", "no-cache")
                    .asString();
            System.out.println("Body: " + responseCreate.getBody());
            Assert.assertThat(responseCreate.getBody(), containsString("https://cloud-api.yandex.net/v1/disk/resources?path=disk%3A%2FTest"));

            // Delete
            HttpResponse<String> responseDelete = Unirest.delete("https://cloud-api.yandex.net/v1/disk/resources?path=disk%3A%2FTest")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "AQAAAAAQSJGtAADLW72eHsFpPU0nqDWYuBurU7Q")
                    .header("Cache-Control", "no-cache")
                    .asString();
            System.out.println("Body: " + responseDelete.getBody());

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


}
