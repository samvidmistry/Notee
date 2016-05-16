package in.samvidinfotech.notee.noteslist;

import com.orm.dsl.NotNull;

import in.samvidinfotech.notee.data.NotesRepository;

/**
 * Created by samvidmistry on 15/5/16.
 */
public class NotesListPresenter implements NotesListContract.Presenter{
    private final NotesRepository mNotesRepository;
    private final NotesListContract.View mView;

    public NotesListPresenter(@NotNull NotesRepository notesRepository, NotesListContract.View view){
        mNotesRepository = notesRepository;
        mView = view;
    }


    @Override
    public void loadNotes() {
        mView.setProgressState(true);
        mNotesRepository.getNotes(new NotesRepository.LoadNotesCallback() {
            @Override
            public void onNotesLoaded() {
                mView.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.setProgressState(false);
                        mView.refreshNotes();
                    }
                });
            }
        });
    }
}
