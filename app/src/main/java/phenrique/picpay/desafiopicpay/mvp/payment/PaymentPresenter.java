package phenrique.picpay.desafiopicpay.mvp.payment;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Currency;
import java.util.Locale;

import phenrique.picpay.desafiopicpay.R;
import phenrique.picpay.desafiopicpay.data.model.User;

public class PaymentPresenter implements PaymentMVP.PresenterImpl {

    private char groupDivider;
    private char monetaryDivider;

    private int fractionDigit;
    private String currencySymbol;

    private Locale locale;
    private DecimalFormat numberFormat;

    private String value;

    private PaymentMVP.ModelImpl model;
    private PaymentMVP.ViewImpl view;
    private User user = new User();

    public PaymentPresenter() {
        model = new PaymentModel( this );
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
    public void setView( PaymentMVP.ViewImpl view ) {
        this.view = view;
    }

    @Override
    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void transactionValuesListener(String text) {
        try {
            if (!text.equals(value)) {
                text = text.replace(groupDivider, '\u0020').replace(monetaryDivider, '\u0020')
                        .replace(".", "").replace(" ", "")
                        .replace(currencySymbol, "").trim();

                value = format(text, false);

                if (!value.equals("0,00"))
                    view.setTransactionValue(value, getContext().getResources().getColor(R.color.colorAccent), true);
                else
                    view.setTransactionValue(value, getContext().getResources().getColor(R.color.color_white), false);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void initSettings() {
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
            return getContext().getResources().getConfiguration().getLocales().get(0);
        else
            return getContext().getResources().getConfiguration().locale;
    }

    private String format(String text, boolean showSymbol) throws ParseException {
        if (showSymbol)
            return numberFormat.format(Double.parseDouble(text) / Math.pow(10, fractionDigit));
        else
            return numberFormat.format(Double.parseDouble(text) / Math.pow(10, fractionDigit)).replace(currencySymbol, "");
    }

}
