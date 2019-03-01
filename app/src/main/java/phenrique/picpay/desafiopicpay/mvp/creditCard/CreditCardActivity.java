package phenrique.picpay.desafiopicpay.mvp.creditCard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import phenrique.picpay.desafiopicpay.R;
import phenrique.picpay.desafiopicpay.utils.CreditCardTextWatcher;

public class CreditCardActivity extends AppCompatActivity implements CreditCardMVP.ViewImpl {

    private static CreditCardMVP.PresenterImpl presenter;

    EditText register_card_number;
    EditText register_cardholder_name;
    EditText register_card_expiration;
    EditText register_card_cvv;
    Button register_save_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_creditcard);

        if( presenter == null ) {
            presenter = new CreditCardPresenter();
        }

        presenter.setView( this );

        bindView();
        listenerFields();
    }

    private void bindView() {
        register_card_number = findViewById(R.id.register_card_number);
        register_cardholder_name = findViewById(R.id.register_cardholder_name);
        register_card_expiration = findViewById(R.id.register_card_expiration);
        register_card_cvv = findViewById(R.id.register_card_cvv);
        register_save_button = findViewById(R.id.register_save_button);
    }

    private void listenerFields() {
        register_card_number.addTextChangedListener(new CreditCardTextWatcher(register_card_number));

        register_card_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validateCardNumber(s.toString());
            }
        });
        register_cardholder_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validateCardName(s.toString());
            }
        });
        register_card_expiration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validateCardExpiration(s.toString());
            }
        });
        register_card_cvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validateCardCvv(s.toString());
            }
        });
    }

    @Override
    public void showButton(int show) {
        register_save_button.setVisibility(show);
    }

    @Override
    public void showToast(String mensagem ) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void saveCreditCard(View view) {
        presenter.saveCreditCard(register_card_number.getText().toString().trim(),
                register_cardholder_name.getText().toString().trim(),
                register_card_expiration.getText().toString(),
                register_card_cvv.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
