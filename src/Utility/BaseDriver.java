package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chromium.ChromiumDriver;

import java.time.Duration;

public class BaseDriver {

        public static WebDriver driver;

        //public static void DriverBaslat()
        static // yukarıdakinin işini sadec static goruyor aslında
        {
            KalanOncekileriKapat();
            // Outputtaki gerekli olmayan logları kaldıralım
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");

            driver = new ChromeDriver();
            driver.manage().window().maximize();// ekranı max yapıyor
            
            Duration dr=Duration.ofSeconds(30);
            driver.manage().timeouts().pageLoadTimeout(dr);
            // 30 sn boyunca bekle eger sayfa yuklenmzse hata ver.
            // eger 2 sn de yuklerse 30 sn beklemez

            driver.manage().timeouts().implicitlyWait(dr);
            //web elementlerin aktif yani hazır hale gelebilmesi için beklenmesi
            // gereken sure. 2sn de hazır hale gelirse 30 sn beklemez

        }


        public static void BekleKapat(){
            MyFunc.bekle(2);
            driver.quit();
        }

        public static void KalanOncekileriKapat(){
            try {
                Runtime.getRuntime().exec("taskkill /F /IM choromedriver.exe /T");
            }catch (Exception ignored){

            }
        }
    }

