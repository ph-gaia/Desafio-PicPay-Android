package phenrique.picpay.desafiopicpay.mvp.payment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import phenrique.picpay.desafiopicpay.R;
import phenrique.picpay.desafiopicpay.data.model.User;


public class PaymentActivity extends AppCompatActivity implements PaymentMVP.ViewImpl {

    private static PaymentMVP.PresenterImpl presenter;
    private ImageView imgTransaction;
    private TextView txtUsername;
    private EditText transaction_value;
    private TextView transaction_currency;
    private Button transaction_pay_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        if( presenter == null ) {
            presenter = new PaymentPresenter();
        }

        presenter.setView( this );
        presenter.initSettings();

        User user = (User) getIntent().getSerializableExtra("user_data");

        bindView();
        populateView(user);
        listenerTransactionValue();
    }

    @Override
    public void showToast(String mensagem ) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar( int visibilidade ) {
        findViewById(R.id.transaction_loading_screen).setVisibility( visibilidade );
    }

    private void bindView() {
        imgTransaction = findViewById(R.id.transaction_image);
        txtUsername = findViewById(R.id.transaction_username);

        transaction_value = findViewById(R.id.transaction_value);
        transaction_currency = findViewById(R.id.transaction_currency);
        transaction_pay_button = findViewById(R.id.transaction_pay_button);
    }

    private void listenerTransactionValue() {
        transaction_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                transaction_value.removeTextChangedListener(this);
                presenter.transactionValuesListener(s.toString());
                transaction_value.addTextChangedListener(this);
            }
        });
    }

    public void populateView(User user) {
        txtUsername.setText(user.getUsername());

        Picasso.get()
            .load(user.getImg())
            .placeholder(R.drawable.ic_account_default)
            .into(imgTransaction);
    }

    public void setTransactionValue(String value, int color, boolean enableButton) {
        transaction_value.setText(value);
        transaction_value.setSelection(value.length());

        transaction_currency.setTextColor(color);
        transaction_value.setTextColor(color);
        transaction_pay_button.setEnabled(enableButton);
    }

}
