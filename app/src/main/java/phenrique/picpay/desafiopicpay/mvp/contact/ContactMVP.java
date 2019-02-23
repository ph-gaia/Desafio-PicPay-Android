package phenrique.picpay.desafiopicpay.mvp.contact;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import phenrique.picpay.desafiopicpay.data.model.User;

public interface ContactMVP {

    interface ViewImpl {
        public void showToast( String mensagem );
        public void showProgressBar( int visibilidade );
        public void updateListRecycler();
    }

    interface PresenterImpl {
        public void showToast( String mensagem );
        public void showProgressBar( boolean status );
        public void setView( ContactMVP.ViewImpl view );
        public ArrayList<User> getUsers();
        public void retrieveUsers();
        public void updateListRecycler(List<User> users);
    }

    interface ModelImpl {
        public void retrieveUsers();
    }
}
