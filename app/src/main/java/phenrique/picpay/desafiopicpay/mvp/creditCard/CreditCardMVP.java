package phenrique.picpay.desafiopicpay.mvp.creditCard;

import android.content.Context;

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
    }

    interface ModelImpl {

    }
}
