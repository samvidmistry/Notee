package in.samvidinfotech.notee.util;

import com.google.gson.Gson;

import in.samvidinfotech.notee.data.NotesRepository;
import in.samvidinfotech.notee.data.NotesRepositoryImpl;
import okhttp3.OkHttpClient;

/**
 * Created by samvidmistry on 15/5/16.
 */
public class Injector {
    private static OkHttpClient sOkHttpClient;
    private static Gson sGson;

    public static NotesRepository getNotesRepository(){
        return new NotesRepositoryImpl();
    }

    public static synchronized OkHttpClient getOkHttpClient() {
        if(sOkHttpClient == null){
            sOkHttpClient = new OkHttpClient();
        }

        return sOkHttpClient;
    }

    public static synchronized Gson getGson(){
        if(sGson == null){
            sGson = new Gson();
        }

        return sGson;
    }
}
