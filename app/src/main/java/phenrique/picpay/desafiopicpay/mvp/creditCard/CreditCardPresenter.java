package phenrique.picpay.desafiopicpay.mvp.creditCard;

import android.content.Context;
import android.view.View;

import phenrique.picpay.desafiopicpay.data.model.CreditCard;

public class CreditCardPresenter implements CreditCardMVP.PresenterImpl {

    private CreditCardMVP.ModelImpl model;
    private CreditCardMVP.ViewImpl view;
    private boolean validateCardNumber = false;
    private boolean validateCardName = false;
    private boolean validateCardExpiration = false;
    private boolean validateCardCvv = false;

    public CreditCardPresenter() {
        model = new CreditCardModel( this );
    }

    @Override
    public void setView(CreditCardMVP.ViewImpl view) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void showButton() {
        if (validateCardNumber && validateCardName && validateCardExpiration && validateCardCvv) {
            view.showButton(View.VISIBLE);
        } else {
            view.showButton(View.INVISIBLE);
        }
    }

    @Override
    public void validateCardNumber(String value) {
        validateCardNumber = (value.length() == 16);
        showButton();
    }

    @Override
    public void validateCardName(String value) {
        validateCardName = value.isEmpty();
        showButton();
    }

    @Override
    public void validateCardExpiration(String value) {
        validateCardExpiration = (value.length() != 5);
        showButton();
    }

    @Override
    public void validateCardCvv(String value) {
        validateCardCvv = (value.length() != 3);
        showButton();
    }

    @Override
    public void saveCreditCard(String cardNumber, String holderName, String Expiration, String Cvv) {
        CreditCard card = new CreditCard();
        card.setCardNumber(cardNumber);
        card.setCardholderName(holderName);
        card.setCvv(Integer.parseInt(Cvv));
        card.setExpiryDate(Expiration);

        model.saveCreditCard(card);
    }

}
