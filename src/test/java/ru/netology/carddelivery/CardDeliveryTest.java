package ru.netology.carddelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.xml.crypto.KeySelector;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");

    }

    public String generateDate(int days) {

        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    @Test
    void shouldSendSuccessfully() {
        String planningDate = generateDate(4);
        $("[data-test-id='city'] [placeholder=\"Город\"]").sendKeys("Москва");
        $("[data-test-id=\"date\"]").click();
        $x("//*[@placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.BACK_SPACE);
        $x("//*[@placeholder=\"Дата встречи\"]").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов-Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+79999999999");
        $("[data-test-id=\"agreement\"]").click();
        $(".button__text").click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));

    }

    @Test
    void shouldSendSuccessfullyChoosingCity() {
        String planningDate = generateDate(4);
        $("[data-test-id='city'] [placeholder=\"Город\"]").sendKeys("Ка");
        $x("//span[contains(text(), 'Казань')]").hover().click();
        $("[data-test-id=\"date\"]").click();
        $x("//*[@placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.BACK_SPACE);
        $x("//*[@placeholder=\"Дата встречи\"]").setValue(planningDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов-Иванов");
        $("[data-test-id=\"phone\"] .input__control").setValue("+79999999999");
        $("[data-test-id=\"agreement\"]").click();
        $(".button__text").click();
        $("[data-test-id=notification]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));

    }

    @Test
    void shouldSendSuccessfullyChoosingDate() {
        $("[data-test-id='city'] [placeholder=\"Город\"]").sendKeys("Москва");
        $("[data-test-id='city'] [placeholder=\"Город\"]").click();
        $x("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span/span").click();
        $("[data-test-id=\"date\"] .calendar-input__native-control").click();
      //  String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
     //   $x("//*[@placeholder=\"Дата встречи\"]").sendKeys(Keys.chord(Keys.CONTROL + "a"), Keys.BACK_SPACE);
      //  $x("//*[@placeholder=\"Дата встречи\"]").setValue(date);
     //   $("[data-test-id=\"name\"] .input__control").setValue("Иван Иванов-Иванов");
    //    $("[data-test-id=\"phone\"] .input__control").setValue("+79999999999");
      //  $("[data-test-id=\"agreement\"]").click();
     //   $(".button__text").click();
      //  $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
     //   $("[data-test-id='notification'] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));

    }
}