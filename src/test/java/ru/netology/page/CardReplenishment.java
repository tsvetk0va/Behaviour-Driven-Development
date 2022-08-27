
package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardReplenishment {
    private SelenideElement sumField = $("[class=input__control]");
    private SelenideElement cardField = $("[type=tel]");
    private SelenideElement replenishButton = $("[class=button__text]");


    public CardReplenishment replenish(String sum, DataHelper.CardsInfo cardsInfo, int id) {
        sumField.setValue(sum);
        if (id != 1) {
            cardField.setValue(cardsInfo.getFirst());
        } else {
            cardField.setValue(cardsInfo.getSecond());
        }
        replenishButton.click();
        return new CardReplenishment();
    }

    public void errorIfTransferIsMoreThenBalance() {
        SelenideElement form = $(".form");
        $(byText("Сумма перевода не может превышать допустимый баланс по карте")).shouldBe(visible, Duration.ofSeconds(10));
    }
}
