package jumpup.imi.fb4.htw.de.jumpupandroid.util.task;

import android.os.AsyncTask;

import java.util.Observable;


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

    protected void triggerChangedAndNotifyObservers(Object o) {
        observable.triggerChanged();

        getObservable().notifyObservers(o);
    }

    public Observable getObservable() {
        return this.observable;
    }
}

