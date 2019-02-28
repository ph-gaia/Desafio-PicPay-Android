package phenrique.picpay.desafiopicpay.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import phenrique.picpay.desafiopicpay.data.model.CreditCard;

@Database(entities = {CreditCard.class}, version = 1, exportSchema = false)
public abstract class ManagerDatabase extends RoomDatabase {

    private static ManagerDatabase INSTANCE;

    public abstract CreditCardDao CreditCardDao();

    public static ManagerDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), ManagerDatabase.class, "picpay-database")
                        .allowMainThreadQueries()
                        .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
