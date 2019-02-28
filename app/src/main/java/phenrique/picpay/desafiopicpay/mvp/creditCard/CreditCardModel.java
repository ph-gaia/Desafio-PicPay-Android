package phenrique.picpay.desafiopicpay.mvp.creditCard;


import phenrique.picpay.desafiopicpay.data.database.ManagerDatabase;
import phenrique.picpay.desafiopicpay.data.model.CreditCard;

public class CreditCardModel implements CreditCardMVP.ModelImpl {

    private CreditCardMVP.PresenterImpl presenter;
    private ManagerDatabase db;

    public CreditCardModel(CreditCardMVP.PresenterImpl presenter) {
        db = ManagerDatabase.getAppDatabase(presenter.getContext());
        this.presenter = presenter;
    }

    @Override
    public void saveCreditCard(CreditCard creditCard) {
        db.CreditCardDao().delete(creditCard);
        db.CreditCardDao().save(creditCard);
    }
}
