package in.samvidinfotech.notee.noteslist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import in.samvidinfotech.notee.R;

public class NotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_notesList);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Notes");

        if(savedInstanceState == null){
            addFragment();
        }
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction().add(R.id.container_notesList,
                NotesListFragment.newInstance()).commit();
    }
}
