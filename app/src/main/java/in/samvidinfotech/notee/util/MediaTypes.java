package in.samvidinfotech.notee.util;

import okhttp3.MediaType;

/**
 * Created by samvidmistry on 16/5/16.
 */
public class MediaTypes {

    public static MediaType getJsonMediaType(){
        return MediaType.parse("application/json; charset=utf-8");
    }

}
