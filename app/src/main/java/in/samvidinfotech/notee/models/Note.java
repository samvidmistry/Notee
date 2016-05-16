package in.samvidinfotech.notee.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

/**
 * Created by samvidmistry on 15/5/16.
 */
public class Note extends SugarRecord {
    @SerializedName("note_id")
    @Ignore
    private long mNoteId;
    @SerializedName("note_title")
    private String mNoteTitle;
    @SerializedName("note_name")
    private String mNoteText;

    public Note(){

    }

    public Note(long id, String noteTitle, String noteText){
        setId(id);
        mNoteTitle = noteTitle;
        mNoteText = noteText;
    }

    public void setNoteId(){
        setId(mNoteId);
    }

    public String getNoteTitle() {
        return mNoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        mNoteTitle = noteTitle;
    }

    public String getNoteText() {
        return mNoteText;
    }

    public void setNoteText(String noteText) {
        mNoteText = noteText;
    }
}
