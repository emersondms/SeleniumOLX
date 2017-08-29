package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OlxSearchResultsPage {

    private WebDriver driver;
    private List<WebElement> searchResultsList;

    public OlxSearchResultsPage(String url) throws IOException {
        this.driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        searchResultsList = getSearchResultsList();
    }

    public List<WebElement> getSearchResultsList() {
        try {
            return driver.findElements(By.xpath(
                "//*[@id=\"state_tab\"]/div[2]/div[4]/main/div/div/div[2]/div[2]/div[4]/ul/li/a"
            ));
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getVehiclesList() {
        List<String> vehiclesList = new ArrayList<>();
        for (int i=0; i < 5; i++)
            if (searchResultsList != null &&
                !searchResultsList.isEmpty() &&
                searchResultsList.get(i) != null)
                vehiclesList.add(searchResultsList.get(i).getAttribute("title"));

        return vehiclesList;
    }

    public List<String> getPricesList() {
        List<String> pricesList = new ArrayList<>();
        for (int i=0; i < 5; i++)
            if (searchResultsList != null &&
                !searchResultsList.isEmpty() &&
                searchResultsList.get(i) != null)
                pricesList.add(searchResultsList.get(i).findElement(
                    By.className("OLXad-list-price")).getText()
                );
            
        return pricesList;
    }
}
