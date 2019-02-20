package phenrique.picpay.desafiopicpay.data.network;

import java.util.List;

import phenrique.picpay.desafiopicpay.data.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Call<List<User>> searchUsers();

}
