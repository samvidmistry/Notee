package in.samvidinfotech.notee.noteslist;

import in.samvidinfotech.notee.BaseView;

/**
 * Created by samvidmistry on 15/5/16.
 */
public interface NotesListContract {
    interface View extends BaseView {
        void setProgressState(boolean active);

        void refreshNotes();
    }

    interface Presenter{
        void loadNotes();
    }
}
