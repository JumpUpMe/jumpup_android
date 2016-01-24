package jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.task.ObservableAsyncTask;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.01.2016
 */
public class ProfileTask extends ObservableAsyncTask<User, Void, Void> {

    private static final String TAG = ProfileTask.class.getName();
    private User user;

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected Void doInBackground(User... users) {
        this.validate(users);

        this.updateProfile();

        return null;
    }

    private void updateProfile() {

    }

    private void validate(User[] users) {
        if (users == null || users.length != 1) {
            throw new IllegalArgumentException("Please provide a single argument of type User.");
        }

        this.user = users[0];
    }
}
