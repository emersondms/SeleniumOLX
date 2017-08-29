import http.Location;
import org.openqa.selenium.WebElement;
import pageobject.OlxHomePage;
import pageobject.OlxSearchResultsPage;
import system.Connection;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.print("Enter a vehicle name: ");
        String vehicleName = new Scanner(System.in).nextLine();

        if (checkInternetConnection()) {
            getCurrentState();

            System.out.println("\nBuilding the search URL...");
            String searchUrl = buildSearchUrl(vehicleName);
            System.out.println(searchUrl);

            System.out.println("\nGetting search results...");
            OlxSearchResultsPage resultsPage = new OlxSearchResultsPage(searchUrl);
            List<WebElement> searchResults = resultsPage.getSearchResultsList();

            if (searchResults != null && !searchResults.isEmpty()) {
                List<String> vehiclesFound = resultsPage.getVehiclesList();
                List<String> pricesFound = resultsPage.getPricesList();
                for (int i=0; i < vehiclesFound.size(); i++) {
                    System.out.println("Vehicle: " + vehiclesFound.get(i));
                    System.out.println("Price: " + pricesFound.get(i) + "\n");
                }
            } else {
                System.err.println("No results found.");
            }
        }
    }

    private static boolean checkInternetConnection() {
        System.out.println("\nChecking the internet connection...");
        if (Connection.isInternetConnected()) {
            System.out.println("Connection status: OK");
            return true;
        } else {
            System.err.println("Connection status: Fail");
        }
        
        return false;
    }

    private static void getCurrentState() {
        System.out.println("\nGetting the current state(location)...");
        String state = Location.getState();
        if (state != null)
            System.out.println("State: " + state);
        else
            System.err.println("Error getting the state.\n" +
                "The search will be done in the whole country."
            );
    }

    private static String buildSearchUrl(String query) {
        OlxHomePage olxHomePage = new OlxHomePage();
        return olxHomePage.getUrlBuilder(query);
    }
}
