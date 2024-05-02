import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class dataScrape {
    private static ChromeDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void openLandP() {
        String url = "https://www.cvmarket.lt/darbo-skelbimai?op=search&search%5Bjob_salary%5D=3&ga_track=homepage&search%5Blocations%5D%5B%5D=134&search%5Bcategories%5D%5B%5D=8&search%5Bkeyword%5D=";
        driver.get(url);
    }
    @Test
    public void scrape() {
        String url = "https://www.cvmarket.lt/darbo-skelbimai?op=search&search%5Bjob_salary%5D=3&search%5Blocations%5D%5B0%5D=134&search%5Bcategories%5D%5B0%5D=8&search%5Bkeyword%5D=&ga_track=homepage&start=";
        //Loop through every page
        for (int i = 0; i <= 201; i += 3) {
            String url1 = url + i + "0";
            driver.get(url1);

            //Wait
            try {
                Thread.sleep(5000); // Wait for 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Find all listed elements
            List<WebElement> h3Elements = driver.findElements(By.xpath("//article"));

            if (h3Elements.isEmpty()) {
                break;
            }

            //Run it through condition
            for (WebElement h2 : h3Elements) {
                String h3Text = h2.getText();
                if (h3Text.contains("QA") || h3Text.contains("automation") || h3Text.contains("engineer") || h3Text.contains("testuotojas") || h3Text.contains("Junior") || h3Text.contains("Tester") || h3Text.contains("Quality") || h3Text.contains("Assurance") || h3Text.contains("Manual") || h3Text.contains("Rankinis")) {
                    System.out.println("");
                    System.out.println("Pozicija: " + h2.findElement(By.xpath(".//h2")).getText()); // Print position
                    try {
                        String salary = h2.findElement(By.xpath("//div[1]/div[2]/div[2]/div[2]")).getText(); //Print salary
                        System.out.println("Darbo uždarbis: " + salary);
                    } catch (NoSuchElementException e) {
                        System.out.println("Darbo uždarbis nematomas");
                    }
                    System.out.println("Skelbimo URL: " + h2.findElement(By.xpath(".//a")).getAttribute("href")); //Print URL
                }

            }

        }
    }
}


