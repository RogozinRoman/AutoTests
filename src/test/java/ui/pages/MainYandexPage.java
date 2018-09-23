package ui.pages;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Rogozin Roman on 22.09.2018.
 */
public class MainYandexPage {

    @Step("Открытие главной страницы Yandex")
    public MainYandexPage openMainPage(){
        open(baseUrl);
        $(byXpath("//*[@class='home-logo__default']")).shouldBe(visible);
        return this;
    }

    @Step("Поиск значения (ввод значения+Enter)")
    public SearchYandexPage yandexSearch(String search) {
        $("#text").setValue(search).pressEnter();
        return new SearchYandexPage();
    }

}