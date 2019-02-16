package phenrique.picpay.desafiopicpay.data.network;

import phenrique.picpay.desafiopicpay.data.model.UserResults;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Call<UserResults> searchUsers();

}
