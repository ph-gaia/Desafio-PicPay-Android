package phenrique.picpay.desafiopicpay.mvp.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import phenrique.picpay.desafiopicpay.R;
import phenrique.picpay.desafiopicpay.adapter.ContactAdapter;

public class ContactActivity extends AppCompatActivity implements ContactMVP.ViewImpl {

    private static ContactMVP.PresenterImpl presenter;

    private ContactAdapter adapter;

    private EditText contact_search;
    private RecyclerView contact_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( presenter == null ) {
            presenter = new ContactPresenter();
        }

        presenter.setView( this );
        presenter.retrieveUsers();
    }

    @Override
    protected void onStart() {
        super.onStart();

        contact_list = findViewById(R.id.contact_list);
        contact_list.setHasFixedSize(true);

        adapter = new ContactAdapter(presenter.getUsers());
        contact_list.setAdapter(adapter);

    }

    public void updateListRecycler() {
        adapter.notifyDataSetChanged();
    }

    public void showToast(String mensagem ) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void showProgressBar( int visibilidade ) {
        findViewById(R.id.contact_loading_screen).setVisibility( visibilidade );
    }
}
