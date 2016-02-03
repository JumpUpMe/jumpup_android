package jumpup.imi.fb4.htw.de.jumpupandroid.util.task;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;


/**
 * Project: jumpup_android
 * <p/>
 * This wrapper class extends AsyncTasks to contain an Obserable object to allow for the Observer pattern.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public abstract class ObservableAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
    private class ObserverImpl extends Observable {
        public void triggerChanged() {
            this.setChanged();
        }
    }

    private ObserverImpl observable = new ObserverImpl();


    public ObservableAsyncTask(Observer observer) {
        super();

        this.getObservable().addObserver(observer);
    }

    public ObservableAsyncTask()
    {
        super();
    }

    protected void triggerChangedAndNotifyObservers(Object o) {
        observable.triggerChanged();

        getObservable().notifyObservers(o);
    }

    public Observable getObservable() {
        return this.observable;
    }

    /**
     * Get tag for logging.
     * @return
     */
    protected abstract String getTag();

    @Override
    /**
     * Default implementation of onPostExecvute: will notify each observer.
     */
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);

        // notify observers about the completion of the task
        Log.v(getTag(), "onPostExecute(): notifying " + this.getObservable().countObservers() + " observers...");

        this.triggerChangedAndNotifyObservers(this);
    }
}
