package ui.tests;

import org.junit.Rule;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import ui.helpers.WebDriverRule;
import ui.pages.MainYandexPage;

/**
 * Created by Rogozin Roman on 22.09.2018.
 */
@Title("UI тесты 'поисковая строка' на главной странице Yandex")
public class SearchYandexTest {

    @Rule
    public WebDriverRule webDriverRule = new WebDriverRule();

    @Title("Поиск по пустому значению")
    @Test
    public void searchEmpty(){
        new MainYandexPage().
                openMainPage().
                yandexSearch("").
                checkEmptySearch();
    }

    @Title("Поиск и проверка результата")
    @Test
    public void yandexSearch(){
        String search = "selenide";
        new MainYandexPage().
                openMainPage().
                yandexSearch(search).
                checkResultSize().
                checkFirstResult(search);
    }

    @Title("Переход по ссылке из результата поиска")
    @Test
    public void yandexExampleSearch(){
        String search = "selenide";
        new MainYandexPage().
                openMainPage().
                yandexSearch(search).
                checkClickSearch();
    }

    @Title("Поиск слова в ссылке")
    @Test
    public void yandexSearchText(){
        String search = "selenide";
        new MainYandexPage().
                openMainPage().
                yandexSearch(search).
                checkFirstResult(search).
                checkTextAllLink(search);
    }
}