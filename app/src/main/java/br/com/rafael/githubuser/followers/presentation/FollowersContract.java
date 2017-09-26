package br.com.rafael.githubuser.followers.presentation;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import rx.Observable;

public interface FollowersContract {
    interface View {
        void showFollowers(FollowersViewModelHolder holder);

        void showFollowersLoading();

        void showFollowersError();

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

        public FollowersViewModelHolder holder;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            FollowersContract$StateParcelablePlease.writeToParcel(this, dest, flags);
        }

        public static final Creator<State> CREATOR = new Creator<State>() {
            public State createFromParcel(Parcel source) {
                State target = new State();
                FollowersContract$StateParcelablePlease.readFromParcel(target, source);
                return target;
            }

            public State[] newArray(int size) {
                return new State[size];
            }
        };
    }
}
