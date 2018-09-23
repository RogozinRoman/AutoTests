package ui.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.hamcrest.Matchers.*;
import static ui.WebDriverUtils.closeBrowserActiveTab;

/**
 * Created by Rogozin Roman on 22.09.2018.
 */
public class SearchYandexPage {

    @Step("Проверка пустого запроса")
    public SearchYandexPage checkEmptySearch() {
        String text = $(byXpath("//div[@class='misspell__message']")).getText();
        Assert.assertThat(text, is("Задан пустой поисковый запрос"));
        return this;
    }

    @Step("Проверка коллекции результатов")
    public SearchYandexPage checkResultSize() {
        int size = $$(".content__left li").size();
        Assert.assertThat(size, not(0));
        return this;
    }

    @Step("Проверка первой найденной ссылки")
    public SearchYandexPage checkFirstResult(String search) {
        $(".content__left li").shouldHave(text(search));
        return this;
    }

    @Step("Проверка слова в url")
    public void checkTextAllLink(String search) {
        List<String> items = new ArrayList<String>();
        for (SelenideElement item : $$(byXpath(".//*[@class='path organic__path']//b"))){
            items.add(item.getText());
        }
        for (String text : items) {
            /*
               По условию кейса, нужно искать слово в ссылке =>
               Если в поиске находится сайт где нет слова в ссылке то тест падает.
            */
            Assert.assertThat(text, containsString(search));
        }
    }

    @Step("Проверка перехода по ссылке")
    public void checkClickSearch(){
        $(byXpath("(.//*[contains(@class,'organic__title-wrapper')]/a)[1]")).click();
        ArrayList<String> tabs = new ArrayList<String> (WebDriverRunner.getWebDriver().getWindowHandles());
        Assert.assertThat(tabs, hasSize(2));
        if (tabs.size()>1)
            closeBrowserActiveTab();
    }

}