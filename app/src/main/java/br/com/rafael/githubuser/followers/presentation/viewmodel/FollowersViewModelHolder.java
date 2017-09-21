package br.com.rafael.githubuser.followers.presentation.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.ArrayList;
import java.util.List;

import br.com.rafael.githubuser.followers.data.models.Follower;
import lombok.Getter;
import lombok.Setter;

@ParcelablePlease
public class FollowersViewModelHolder implements Parcelable {

    @Getter
    @Setter
    ArrayList<Follower> viewModels;

    public FollowersViewModelHolder() {
        this.viewModels = new ArrayList<>();
    }

    public FollowersViewModelHolder(@NonNull List<Follower> viewsModels) {
        this.viewModels = new ArrayList<>(viewsModels);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        FollowersViewModelHolderParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<FollowersViewModelHolder> CREATOR = new Creator<FollowersViewModelHolder>() {
        public FollowersViewModelHolder createFromParcel(Parcel source) {
            FollowersViewModelHolder target = new FollowersViewModelHolder();
            FollowersViewModelHolderParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public FollowersViewModelHolder[] newArray(int size) {
            return new FollowersViewModelHolder[size];
        }
    };
}
