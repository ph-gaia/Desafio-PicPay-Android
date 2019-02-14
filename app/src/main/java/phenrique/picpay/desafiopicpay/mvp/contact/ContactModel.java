package phenrique.picpay.desafiopicpay.mvp.contact;

public class ContactModel implements ContactMVP.ModelImpl {

    private ContactMVP.PresenterImpl presenter;

    public ContactModel(ContactMVP.PresenterImpl presenter) {
        this.presenter = presenter;
    }
}
