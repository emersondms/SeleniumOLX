package pageobject;

import builder.OlxUrl;
import http.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.text.Normalizer;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OlxHomePage {

    private WebDriver driver;

    public OlxHomePage() {
        this.driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.olx.com.br/");
    }

    private List<WebElement> getStatesList() {
        return driver.findElements(By.xpath(
            "/html/body/div[2]/main/div/div[2]/div[1]/ul/li/a"
        ));
    }

    private String getStateURL() {
        List<WebElement> statesList = getStatesList();
        for (int i = 0; i < statesList.size()-1; i++) {
            WebElement state = statesList.get(i);
            if (removeAccents(state.getAttribute("title")).equals(Location.getState()))
                return state.getAttribute("href");
        }
        
        return null;
    }

    public String getUrlBuilder(String query) {
        return new OlxUrl.Builder().stateUrl(getStateURL()).query(query).build();
    }

    private String removeAccents(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
