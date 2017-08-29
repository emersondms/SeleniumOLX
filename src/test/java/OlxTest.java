import builder.OlxUrl;
import http.Location;
import org.junit.Before;
import org.junit.Test;
import pageobject.OlxHomePage;
import pageobject.OlxSearchResultsPage;
import system.Connection;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OlxTest {

    private String defaultOlxUrl, localOlxUrl;
    private OlxHomePage olxHomePage;
    private int expectedVehiclesFound;

    @Before()
    public void setup() throws IOException {
        defaultOlxUrl = "http://www.olx.com.br/veiculos?q=";
        localOlxUrl = "http://sp.olx.com.br/veiculos?q=";
        olxHomePage = new OlxHomePage();
        expectedVehiclesFound = 5;
    }

    @Test()
    public void connectionTest() {
        assertTrue(Connection.isInternetConnected());
    }

    @Test()
    public void locationTest() {
        assertEquals(Location.getState(), "Sao Paulo");
    }

    @Test()
    public void defaultOlxUrlTest() {
        String defaultUrl = new OlxUrl.Builder().stateUrl(null).query("").build();
        assertEquals(defaultUrl, defaultOlxUrl);
    }

    @Test()
    public void urlBuilderTest() {
        assertEquals(olxHomePage.getUrlBuilder(""), localOlxUrl);
    }

    @Test()
    public void vehiclesFoundTest() throws IOException {
        assertEquals(new OlxSearchResultsPage(localOlxUrl)
            .getVehiclesList().size(), expectedVehiclesFound
        );
    }

    @Test()
    public void pricesFoundTest() throws IOException {
        assertEquals(new OlxSearchResultsPage(localOlxUrl)
            .getPricesList().size(), expectedVehiclesFound
        );
    }
}
