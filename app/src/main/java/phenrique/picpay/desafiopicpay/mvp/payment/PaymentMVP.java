package phenrique.picpay.desafiopicpay.mvp.payment;

import android.content.Context;
import android.widget.EditText;

import phenrique.picpay.desafiopicpay.data.model.User;

public interface PaymentMVP {

    interface ViewImpl {
        public void showToast( String mensagem );
        public void showProgressBar( int visibilidade );
        public void populateView(User user);
        public void setTransactionValue(String value, int color, boolean enableButton);
    }

    interface PresenterImpl {
        public void showToast( String mensagem );
        public void showProgressBar( boolean status );
        public void setView( PaymentMVP.ViewImpl view );
        public Context getContext();
        public void transactionValuesListener(String text);
        public void initSettings();
    }

    interface ModelImpl {

    }
}
