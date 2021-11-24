package unit3.ex3;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SecondTest extends CoreTestCase {

    @Test
    public void testEx3() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResultList();

        int amount_of_articles = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("No articles found", amount_of_articles > 1);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultsOfSearch();
    }
}