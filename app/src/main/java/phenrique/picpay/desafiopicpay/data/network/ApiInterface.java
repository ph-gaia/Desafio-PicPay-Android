package phenrique.picpay.desafiopicpay.data.network;

import java.util.List;

import phenrique.picpay.desafiopicpay.data.model.Payment;
import phenrique.picpay.desafiopicpay.data.model.Transaction;
import phenrique.picpay.desafiopicpay.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("users")
    Call<List<User>> searchUsers();

    @POST("transaction")
    Call<Transaction> sendTransaction(@Body Payment payment);
}
