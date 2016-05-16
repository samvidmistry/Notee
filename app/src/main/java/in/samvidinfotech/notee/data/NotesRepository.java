package in.samvidinfotech.notee.data;

/**
 * Created by samvidmistry on 15/5/16.
 */
public interface NotesRepository {
    interface LoadNotesCallback {
        void onNotesLoaded();
    }

    void getNotes(LoadNotesCallback callback);
}
