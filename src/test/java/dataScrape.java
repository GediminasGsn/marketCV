import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class dataScrape {

    @Test
    public void openLandP(){
        String url = "https://www.cvmarket.lt/darbo-skelbimai?op=search&search%5Bjob_salary%5D=3&ga_track=homepage&search%5Blocations%5D%5B%5D=134&search%5Bcategories%5D%5B%5D=8&search%5Bkeyword%5D=";
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.quit();
    }

    @Test
    public void scrape(){
        String url = "https://www.cvmarket.lt/darbo-skelbimai?op=search&search%5Bjob_salary%5D=3&search%5Blocations%5D%5B0%5D=134&search%5Bcategories%5D%5B0%5D=8&search%5Bkeyword%5D=&ga_track=homepage&start=00";
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        //Loop through every page
        int i = 0;
        String url1 = "https://www.cvmarket.lt/darbo-skelbimai?op=search&search%5Bjob_salary%5D=3&search%5Blocations%5D%5B0%5D=134&search%5Bcategories%5D%5B0%5D=8&search%5Bkeyword%5D=&ga_track=homepage&start=" + i + "0";
        driver.get(url1);
        while (i <= 201){
            i += 3;
        }

            //Wait
            try {
                Thread.sleep(5000); // Wait for 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        List<WebElement> h3Elements = driver.findElements(By.xpath("//article"));
    }
}
