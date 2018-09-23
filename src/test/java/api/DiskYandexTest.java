package api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;

import static org.hamcrest.core.IsNot.not;

/**
 * Created by Rogozin Roman on 23.09.2018.
 */
@Title("API тесты в Yandex Disk")
public class DiskYandexTest {

    @Title("Отображение списка всех файлов")
    @Test
    public void showMeFiles() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://cloud-api.yandex.net/v1/disk/resources/files")
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "AQAAAAAQSJGtAADLW72eHsFpPU0nqDWYuBurU7Q")
                .addHeader("Cache-Control", "no-cache")
                .build();

        Response response;
        String responseText = "";
        try {
            response = client.newCall(request).execute();
            responseText = response.body().string();
            System.out.println("Body: " + responseText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertThat(responseText, not(""));
    }
}
