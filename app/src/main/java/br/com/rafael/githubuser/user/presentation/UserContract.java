package br.com.rafael.githubuser.user.presentation;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import br.com.rafael.githubuser.user.data.models.GithubUser;
import rx.Observable;

public interface UserContract {
    interface View {
        Observable<String> onFollowersClicked();

        void callFollowers(String username);

        void showUser();

        void setUser(GithubUser githubUser);

        void showPhoto(String photoUrl);

        void showLogin(String login);

        void showName(String name);

        void showLocation(String location);

        void showUserLoading();

        void showUserError();

        Observable<String> onRetryClicked();

        Observable<State> onSaveState();

        void saveState(@NonNull State state);
    }

    interface Presenter {
        void initialize(String username);

        void initializeFromState(State state);
    }

    @ParcelablePlease
    class State implements Parcelable {

        public GithubUser githubUser;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            UserContract$StateParcelablePlease.writeToParcel(this, dest, flags);
        }

        public static final Creator<State> CREATOR = new Creator<State>() {
            public State createFromParcel(Parcel source) {
                State target = new State();
                UserContract$StateParcelablePlease.readFromParcel(target, source);
                return target;
            }

            public State[] newArray(int size) {
                return new State[size];
            }
        };
    }
}
