package phenrique.picpay.desafiopicpay.mvp.creditCard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import phenrique.picpay.desafiopicpay.R;

public class CreditCardActivity extends AppCompatActivity {

    EditText register_card_number;
    EditText register_cardholder_name;
    EditText register_card_expiration;
    EditText register_card_cvv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_creditcard);

        bindView();
    }

    private void bindView() {
        register_card_number = findViewById(R.id.register_card_number);
        register_cardholder_name = findViewById(R.id.register_cardholder_name);
        register_card_expiration = findViewById(R.id.register_card_expiration);
        register_card_cvv = findViewById(R.id.register_card_cvv);
    }
}
