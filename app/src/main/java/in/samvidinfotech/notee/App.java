package in.samvidinfotech.notee;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.orm.SugarApp;

/**
 * Created by samvidmistry on 15/5/16.
 */
public class App extends SugarApp {
    private static App sInstance;
    private JobManager mJobManager;

    public App(){
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configureJobManager();
    }

    private void configureJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .consumerKeepAlive(120).build();

        mJobManager = new JobManager(configuration);
    }

    public JobManager getJobManager(){
        return mJobManager;
    }

    public static App getInstance(){
        return sInstance;
    }
}
