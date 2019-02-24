package phenrique.picpay.desafiopicpay.mvp.payment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;
import java.util.regex.Pattern;

import phenrique.picpay.desafiopicpay.R;
import phenrique.picpay.desafiopicpay.data.model.User;

public class PaymentActivity extends AppCompatActivity implements PaymentMVP.ViewImpl {

    private static PaymentMVP.PresenterImpl presenter;
    private String value;
    private EditText transaction_value;
    private TextView transaction_currency;
    private Button transaction_pay_button;

    private char groupDivider;
    private char monetaryDivider;

    private int fractionDigit;
    private String currencySymbol;

    private Locale locale;
    private DecimalFormat numberFormat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        if( presenter == null ) {
            presenter = new PaymentPresenter();
        }

        presenter.setView( this );

        User user = (User) getIntent().getSerializableExtra("user_data");

        initSettings();

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

    private void listenerTransactionValue() {
        transaction_value = findViewById(R.id.transaction_value);
        transaction_currency = findViewById(R.id.transaction_currency);
        transaction_pay_button = findViewById(R.id.transaction_pay_button);

        transaction_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (s.toString() != value) {
                        transaction_value.removeTextChangedListener(this);

                        String text = s.toString();

                        text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                                .replace(".", "").replace(" ", "")
                                .replace(currencySymbol, "").trim();

                        value = format(text, false);
                        transaction_value.setText(value);
                        transaction_value.setSelection(value.length());
                        transaction_value.addTextChangedListener(this);

                        if (!value.equals("0,00")) {
                            transaction_currency.setTextColor(getResources().getColor(R.color.colorAccent));
                            transaction_value.setTextColor(getResources().getColor(R.color.colorAccent));
                            transaction_pay_button.setEnabled(true);
                        } else {
                            transaction_currency.setTextColor(getResources().getColor(R.color.color_white));
                            transaction_value.setTextColor(getResources().getColor(R.color.color_white));
                            transaction_pay_button.setEnabled(false);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void populateView(User user) {
        ImageView transaction = findViewById(R.id.transaction_image);
        TextView username = findViewById(R.id.transaction_username);

        username.setText(user.getUsername());

        Picasso.get()
            .load(user.getImg())
            .placeholder(R.drawable.ic_account_default)
            .into(transaction);
    }

    private void initSettings() {
        locale = getDefaultLocale();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);

        fractionDigit = Currency.getInstance(getDefaultLocale()).getDefaultFractionDigits();
        groupDivider = symbols.getGroupingSeparator();
        monetaryDivider = symbols.getMonetaryDecimalSeparator();
        currencySymbol = symbols.getCurrencySymbol();

        numberFormat = new DecimalFormat("#,###,##0.00", symbols);
    }

    private Locale getDefaultLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            return getApplicationContext().getResources().getConfiguration().getLocales().get(0);
        else
            return getApplicationContext().getResources().getConfiguration().locale;
    }

    private String format(String text, boolean showSymbol) throws ParseException {
        if (showSymbol)
            return numberFormat.format(Double.parseDouble(text) / Math.pow(10, fractionDigit));
        else
            return numberFormat.format(Double.parseDouble(text) / Math.pow(10, fractionDigit)).replace(currencySymbol, "");
    }

}
