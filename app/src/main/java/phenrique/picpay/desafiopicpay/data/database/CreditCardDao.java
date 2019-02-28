package phenrique.picpay.desafiopicpay.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import phenrique.picpay.desafiopicpay.data.model.CreditCard;

@Dao
public interface CreditCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(CreditCard creditCard);

    @Query("SELECT * FROM credit_card")
    List<CreditCard> getAll();

    @Query("DELETE FROM credit_card")
    void delete(CreditCard creditCard);

}
