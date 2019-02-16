package phenrique.picpay.desafiopicpay.mvp.contact;

import android.util.Log;

import phenrique.picpay.desafiopicpay.data.model.User;
import phenrique.picpay.desafiopicpay.data.model.UserResults;
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

            Call<UserResults> call = ApiManager.get().searchUsers();

            call.enqueue(new Callback<UserResults>() {
                @Override
                public void onFailure(Call<UserResults> call, Throwable t) {
                    t.printStackTrace();
                    presenter.showToast( t.getMessage() );
                }

                @Override
                public void onResponse(Call<UserResults> call, Response<UserResults> response) {
                    UserResults contacts = response.body();
                    Log.d("APIPlug", "Successfully response fetched");

                    presenter.showProgressBar(false);

                    if (contacts.results.size() > 0) {
                        presenter.updateListRecycler(contacts.results);
                    }
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
