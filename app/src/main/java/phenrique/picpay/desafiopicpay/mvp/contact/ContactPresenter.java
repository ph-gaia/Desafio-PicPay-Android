package phenrique.picpay.desafiopicpay.mvp.contact;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import phenrique.picpay.desafiopicpay.data.model.User;

public class ContactPresenter implements ContactMVP.PresenterImpl {

    private ContactMVP.ModelImpl model;
    private ContactMVP.ViewImpl view;
    private ArrayList<User> users = new ArrayList<>();

    public ContactPresenter() {
        model = new ContactModel( this );
    }

    @Override
    public void showToast(String mensagem) {
        view.showToast( mensagem );
    }

    @Override
    public void showProgressBar(boolean status) {
        int visibilidade = status ? View.VISIBLE : View.GONE;
        view.showProgressBar( visibilidade );
    }

    @Override
    public void setView( ContactMVP.ViewImpl view ) {
        this.view = view;
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public void retrieveUsers() {
        model.retrieveUsers();
    }

    @Override
    public void updateListRecycler(List<User> user) {
        users.clear();
        users.addAll(user);
        view.updateListRecycler();
    }
}
