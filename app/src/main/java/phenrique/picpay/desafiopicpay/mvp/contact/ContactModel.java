package phenrique.picpay.desafiopicpay.mvp.contact;

import java.util.List;

import phenrique.picpay.desafiopicpay.data.model.User;
import phenrique.picpay.desafiopicpay.data.network.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactModel implements ContactMVP.ModelImpl {

    private ContactMVP.PresenterImpl presenter;

    public ContactModel(ContactMVP.PresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void retrieveUsers() {
        try {

            presenter.showProgressBar(true);

            Call<List<User>> call = ApiManager.get().searchUsers();

            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    t.printStackTrace();
                    presenter.showProgressBar(false);
                    presenter.showToast( t.getMessage() );
                }

                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    List<User> contacts = response.body();

                    presenter.showProgressBar(false);

                    presenter.updateListRecycler(contacts);
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
            presenter.showProgressBar(false);
        }

    }
}
