import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class BaseStep extends BaseTest {
    final static Logger logger = Logger.getLogger(BaseStep.class.getName());

    @Step("<second> kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);
    }

    @Step("<Key> İd'li elemente tıkla")
    public void clickElementByid(String Key) {
        appiumDriver.findElement(By.id(Key)).click();
        logger.info("Id'si girilen Elemente tiklandi");

    }

    @Step("<Key> İd'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByid(String Key, String keywordc) {
        appiumDriver.findElement(By.id(Key)).sendKeys(keywordc);
        logger.info(keywordc + "kelimesi box'a gonderildi");

    }

    @Step("<Key> xpath'li elemente tıkla")
    public void clickElementByxpath(String Key) {
        appiumDriver.findElement(By.xpath(Key)).click();
        logger.info("Xpath'i girilen Elemente tiklandi");
    }

    @Step("<Key> xpath'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByxpath(String Key, String keywordc) {
        appiumDriver.findElement(By.xpath(Key)).sendKeys(keywordc);
        logger.info(keywordc + "kelimesi box'a gonderildi");

    }

    @Step("Sayfayı yukarı kaydır")
    public void swipeUpI() {
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Boyutu " + dims);
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder = 25;
        final int PRESS_TIME = 200; // ms
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        logger.info(  "baslangic noktasi"+ pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        logger.info(  "baslangic noktasi"+ pointOptionEnd);
        new TouchAction(appiumDriver).press(pointOptionStart).waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME))).moveTo(pointOptionEnd).release().perform();
    }

    @Step("Elementi <xpath> bul ve <keyword> değerini kontrol et")
    public void textControl(String xpath, String keyword) {
        logger.info(  "keyword"+ appiumDriver.findElement(By.xpath(xpath)).getText());
        Assert.assertTrue("Text degeri bulunmamadi ", appiumDriver.findElement(By.xpath(xpath)).getText().equals(keyword));
    }

    public List<MobileElement> listElements() {
        return appiumDriver.findElements(By.xpath("//*[@class='androidx.cardview.widget.CardView']"));
    }

    @Step("Rastgele ürün seç ve <wait> saniye bekle")
    public void randomClick(int wait) throws InterruptedException {
        logger.info("Rastgele bir urun seciliyor");
        Random random = new Random();
        listElements().get(random.nextInt(listElements().size())).click();
        Thread.sleep(1000 * wait);
        logger.info("Rastgele bir urun secildi");
    }


}








