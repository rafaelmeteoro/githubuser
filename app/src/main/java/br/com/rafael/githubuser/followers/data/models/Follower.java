package br.com.rafael.githubuser.followers.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Getter;
import lombok.Setter;

@ParcelablePlease
public class Follower implements Parcelable {

    @Getter
    @Setter
    String login;

    @Getter
    @Setter
    int id;

    @Getter
    @Setter
    @SerializedName("avatar_url")
    String avatarUrl;

    @Getter
    @Setter
    @SerializedName("gravatar_url")
    String gravatarId;

    @Getter
    @Setter
    String url;

    @Getter
    @Setter
    @SerializedName("html_url")
    String htmlUrl;

    @Getter
    @Setter
    @SerializedName("followers_url")
    String followersUrl;

    @Getter
    @Setter
    @SerializedName("following_url")
    String followingUrl;

    @Getter
    @Setter
    @SerializedName("gists_url")
    String gistsUrl;

    @Getter
    @Setter
    @SerializedName("starred_url")
    String starredUrl;

    @Getter
    @Setter
    @SerializedName("subscriptions_url")
    String subscriptionsUrl;

    @Getter
    @Setter
    @SerializedName("organizations_url")
    String organizationsUrl;

    @Getter
    @Setter
    @SerializedName("repos_url")
    String reposUrl;

    @Getter
    @Setter
    @SerializedName("events_url")
    String eventsUrl;

    @Getter
    @Setter
    @SerializedName("received_events_url")
    String receivedEventsUrl;

    @Getter
    @Setter
    String type;

    @Getter
    @Setter
    @SerializedName("site_admin")
    boolean siteAdmin;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        FollowerParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Follower> CREATOR = new Creator<Follower>() {
        public Follower createFromParcel(Parcel source) {
            Follower target = new Follower();
            FollowerParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Follower[] newArray(int size) {
            return new Follower[size];
        }
    };
}
