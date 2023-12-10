package Vodafone;

import Utility.BaseDriver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class VodafoneShopping extends BaseDriver {

    @Test
    public void Wait1() {
        driver.get("https://vodafone.com.tr/");

        // Alert'i try catch içine alıyoruz, alett olmasa da program devam etsin diye
        try {
            // Alert'i handle ediyoruz
            Alert alert = driver.switchTo().alert();
            // Alert'i kapat
            alert.dismiss();
        } catch (Exception e) {
            // Eğer alert bulunamazsa veya başka bir hata oluşursa buraya düşer
            e.printStackTrace();
        }


        try {
            // Sayfa ilk açıldıgında çerez çıkıyor. Çerezi kapatmak için web element clickable olana kadar bekletip sonrasında kapatıyoruz.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cookiePopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'kabul et')]")));
            cookiePopup.click();
        }catch (Exception e){
            e.printStackTrace();
        }

        // Sayfalarda geçiş sırasında pop up lar çıkıyor aşağıdaki explicitly wait ile çıkan pop up ı handle ediyoruz.
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement handlePopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='İlgilenmiyorum']")));
            handlePopup.click();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Anasayfadaki alışveriş tagına gidip alt kategorileri gormek için tıklıyoruz
        WebElement shopping = driver.findElement(By.xpath( "//*[text()='Alışveriş']") );
        shopping.click();

        //Telefonlar tagına gelip tıklıyoruz. Web elementlerin locatorlarını xpath ile buluyoruz.
        WebElement phones = driver.findElement(By.xpath( "//*[text()='Telefonlar']"));
        phones.click();

        // SearchBox açıldıktan sonra yeni bir pop up çıkıyor. Onu handle ediyoruz.
  try{
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cookiePopup1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'İncele')]")));
        cookiePopup1.click();
  }catch (Exception e){
      e.printStackTrace();
  }

        // Çıkan sayfada searchBox ı bulup içine iphone metnini yazdırıyorum ve ENTERa basıyorum.
        WebElement searhcbox = driver.findElement(By.xpath( "//*[@id='search-input']"));
        searhcbox.sendKeys("iphone" + Keys.ENTER);

        // En populer Iphone telefonlar listelendikten sonra ilk sıradakine tıklıyorum
        WebElement iphonePopular = driver.findElement(By.xpath( "//img[@alt='Apple iPhone 12'][1]"));
        iphonePopular.click();

        // En populer telefonu sepete ekliyorum
        WebElement addChart = driver.findElement(By.xpath( "//button[@aria-label='Sepete Ekle']"));
        addChart.click();

        // Ürün sepete eklendikten sonra login sayfasına (gitmek istediğim sayfaya) gittiğini doğruluyorum
        String actualTitle = driver.getTitle();
        String expectedTitle = "Login";
        System.out.println("actualTitle = " + actualTitle);
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        // Sayfayı kapatıyorum
        BekleKapat();
    }
}
