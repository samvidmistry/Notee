package in.samvidinfotech.notee.data;

import com.birbit.android.jobqueue.JobManager;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import in.samvidinfotech.notee.App;
import in.samvidinfotech.notee.jobs.FetchNotesJob;
import in.samvidinfotech.notee.models.Note;
import in.samvidinfotech.notee.util.Injector;

/**
 * Created by samvidmistry on 15/5/16.
 */
public class NotesRepositoryImpl implements NotesRepository {

    @Override
    public void getNotes(LoadNotesCallback callback) {
        JobManager jobManager = App.getInstance().getJobManager();
        long noteId = 0;
        if(SugarRecord.last(Note.class) != null){
            noteId = SugarRecord.last(Note.class).getId();
        }
        jobManager.addJobInBackground(new FetchNotesJob(callback, Injector.getGson().toJson(new FetchNotesReqModel(noteId))));
    }

    private class FetchNotesReqModel{
        @SerializedName("note_id")
        private long mNoteId;

        private FetchNotesReqModel(long noteId) {
            mNoteId = noteId;
        }
    }

}
