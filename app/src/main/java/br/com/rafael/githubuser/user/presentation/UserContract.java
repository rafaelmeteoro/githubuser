package br.com.rafael.githubuser.user.presentation;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import br.com.rafael.githubuser.user.data.models.GithubUser;

public interface UserContract {
    interface View {
        void showLoadingState();

        void showEmptySate();

        void showErrorState();

        void showContentState();

        void showUser(GithubUser githubUser);

        void launchFollowersActivity(String username);
    }

    interface Presenter {
        void initialize(String username);

        void initializeFromState(State state);

        void clickFollowers(String username);
    }

    @ParcelablePlease
    class State implements Parcelable {

        public GithubUser githubUser;
        public boolean isShowingUserLoadError;

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
