package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class DateFormTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
    }

    @Test
    void DataTest() {
        $("[placeholder='Город']").setValue("Омск");
        String requireDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder='Дата встречи']").setValue(requireDate);
        $("[name='name']").setValue("Попов Андрей");
        $("[name='phone']").setValue("+79997775555");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $("[data-test-id='notification']").shouldHave(Condition.text("Встреча успешно забронирована на " + requireDate), Duration.ofSeconds(15));
    }
}
