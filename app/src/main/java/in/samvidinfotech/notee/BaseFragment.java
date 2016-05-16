package in.samvidinfotech.notee;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import in.samvidinfotech.notee.util.LifecycleListener;
import in.samvidinfotech.notee.util.LifecycleProvider;

/**
 * Created by samvidmistry on 15/5/16.
 */
public class BaseFragment extends Fragment implements LifecycleProvider {
    private List<LifecycleListener> mLifecycleListeners = new ArrayList<>();

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        mLifecycleListeners.add(listener);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        mLifecycleListeners.remove(listener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mLifecycleListeners.size() > 0){
            for (LifecycleListener listener :
                    mLifecycleListeners) {
                listener.onProviderStopped();
            }
        }
        mLifecycleListeners.clear();
    }
}
