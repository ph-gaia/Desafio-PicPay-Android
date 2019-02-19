package phenrique.picpay.desafiopicpay.mvp.contact;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import phenrique.picpay.desafiopicpay.R;
import phenrique.picpay.desafiopicpay.adapter.ContactAdapter;
import phenrique.picpay.desafiopicpay.data.model.User;

public class ContactActivity extends AppCompatActivity implements ContactMVP.ViewImpl,
        ContactAdapter.UserAdapterListener {

    private static ContactMVP.PresenterImpl presenter;

    private ContactAdapter adapter;

    private RecyclerView contact_list;
    private SearchView contact_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

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

        adapter = new ContactAdapter(presenter.getUsers(),this);
        contact_list.setAdapter(adapter);

        searchListener();
    }

    private void searchListener() {
        contact_search = findViewById(R.id.contact_search);

        contact_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
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

    @Override
    public void onUserSelected(User user) {
        Toast.makeText(getApplicationContext(), "Selected: " + user.getName(), Toast.LENGTH_LONG).show();
    }
}
