package phenrique.picpay.desafiopicpay.mvp.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import phenrique.picpay.desafiopicpay.R;

public class ContactActivity extends AppCompatActivity implements ContactMVP.ViewImpl {

    private static ContactMVP.PresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
    }
}
