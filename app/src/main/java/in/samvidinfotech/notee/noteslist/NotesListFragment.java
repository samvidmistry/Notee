package in.samvidinfotech.notee.noteslist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orm.SugarRecord;

import java.util.List;

import in.samvidinfotech.notee.R;
import in.samvidinfotech.notee.models.Note;
import in.samvidinfotech.notee.util.Injector;
import in.samvidinfotech.notee.util.SpaceItemDecorator;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesListFragment extends Fragment implements NotesListContract.View {
    private NotesListContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private NotesAdapter mNotesAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public NotesListFragment() {
        // Required empty public constructor
    }

    public static NotesListFragment newInstance(){
        return new NotesListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_notes_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.notesList_notesList);
        mRecyclerView.addItemDecoration(new SpaceItemDecorator(5));
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh_notesList);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadNotes();
            }
        });

        setRetainInstance(true);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new NotesListPresenter(Injector.getNotesRepository(), this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadNotes();
    }

    @Override
    public void setProgressState(final boolean active) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void refreshNotes() {
        if(mNotesAdapter == null){
            mNotesAdapter = new NotesAdapter();
            mRecyclerView.setAdapter(mNotesAdapter);
        }

        mNotesAdapter.setNotes(SugarRecord.find(Note.class, "1"));
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }

    public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder>{
        private List<Note> mNotes;

        @Override
        public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_note_list, parent, false));
        }

        @Override
        public void onBindViewHolder(NoteViewHolder holder, int position) {
            Note note = mNotes.get(position);
            holder.mTitleTextView.setText(note.getNoteTitle());
            holder.mDescTextView.setText(note.getNoteText());
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        public void setNotes(List<Note> notes) {
            if(notes == mNotes) return;

            if(notes == null){
                mNotes.clear();
            }else{
                mNotes = notes;
            }
            notifyDataSetChanged();
        }
    }

    private class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView, mDescTextView;
        public NoteViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.noteTitle_noteList);
            mDescTextView = (TextView) itemView.findViewById(R.id.noteDesc_noteList);
        }
    }
}
