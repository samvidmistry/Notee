package in.samvidinfotech.notee.util;

/**
 * Created by samvidmistry on 16/5/16.
 */
public class UrlProvider {
    private static final String sBaseUrl = "http://192.168.0.103";
    private static final String sProjectUrl = "/notee";
    private static final String sGetNotes = "/getnotes.php";

    public static String getNotesUrl(){
        return sBaseUrl + sProjectUrl + sGetNotes;
    }
}
