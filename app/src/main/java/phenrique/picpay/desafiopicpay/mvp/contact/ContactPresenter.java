package phenrique.picpay.desafiopicpay.mvp.contact;

public class ContactPresenter implements ContactMVP.PresenterImpl {

    private ContactMVP.ModelImpl model;
    private ContactMVP.ViewImpl view;

    public ContactPresenter() {
        model = new ContactModel( this );
    }
}
