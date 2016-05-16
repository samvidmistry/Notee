package in.samvidinfotech.notee.jobs;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import in.samvidinfotech.notee.data.NotesRepository;
import in.samvidinfotech.notee.models.Note;
import in.samvidinfotech.notee.util.Injector;
import in.samvidinfotech.notee.util.MediaTypes;
import in.samvidinfotech.notee.util.Priority;
import in.samvidinfotech.notee.util.UrlProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by samvidmistry on 16/5/16.
 */
public class FetchNotesJob extends Job {
    private NotesRepository.LoadNotesCallback mCallback;
    private String mJson;

    public FetchNotesJob(NotesRepository.LoadNotesCallback callback, String json) {
        super(new Params(Priority.MID).requireNetwork().groupBy("Fetch-Feed").singleInstanceBy("Fetch-Feed"));
        mCallback = callback;
        mJson = json;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        OkHttpClient okHttpClient = Injector.getOkHttpClient();

        Request request = new Request.Builder()
                .url(UrlProvider.getNotesUrl())
                .post(RequestBody.create(MediaTypes.getJsonMediaType(), mJson))
                .build();

        Response response = okHttpClient.newCall(request).execute();
        Gson gson = Injector.getGson();
        if(response.body().contentLength() <= 0){
            mCallback.onNotesLoaded();
            return;
        }

        List<Note> notes = Arrays.asList(gson.fromJson(response.body().string(), Note[].class));
        for (Note n :
                notes) {
            n.save();
        }
        mCallback.onNotesLoaded();
    }

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        if(throwable instanceof NullPointerException){
            return RetryConstraint.CANCEL;
        }

        return null;
    }
}
