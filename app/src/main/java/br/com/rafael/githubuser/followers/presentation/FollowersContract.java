package br.com.rafael.githubuser.followers.presentation;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;

public interface FollowersContract {
    interface View {
        void showLoadingState();

        void showEmptyState();

        void showErrorState();

        void showContentState();

        void showFollowers(FollowersViewModelHolder holder);
    }

    interface Presenter {
        void initialize(String username);

        void initializeFromState(State state);
    }

    @ParcelablePlease
    class State implements Parcelable {

        public FollowersViewModelHolder holder;
        public boolean isShowingFollowersLoadError;

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
