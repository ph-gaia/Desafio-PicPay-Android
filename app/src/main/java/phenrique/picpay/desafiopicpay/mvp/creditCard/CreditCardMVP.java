package phenrique.picpay.desafiopicpay.mvp.creditCard;

import android.content.Context;

import phenrique.picpay.desafiopicpay.data.database.ManagerDatabase;
import phenrique.picpay.desafiopicpay.data.model.CreditCard;

public interface CreditCardMVP {

    interface ViewImpl {
        public void showToast( String mensagem );
        public void showButton(int show);
    }

    interface PresenterImpl {
        public void setView( CreditCardMVP.ViewImpl view );
        public Context getContext();
        public void showButton();
        public void validateCardNumber(String value);
        public void validateCardName(String value);
        public void validateCardExpiration(String value);
        public void validateCardCvv(String value);
        public void saveCreditCard(String cardNumber, String holderName, String Expiration, String Cvv);
    }

    interface ModelImpl {
        public void saveCreditCard(CreditCard creditCard);
    }
}
