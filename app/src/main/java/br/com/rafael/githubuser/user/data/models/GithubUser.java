package br.com.rafael.githubuser.user.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Getter;
import lombok.Setter;

@ParcelablePlease
public class GithubUser implements Parcelable {

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
    @SerializedName("gravatar_id")
    String gravarId;

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

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String company;

    @Getter
    @Setter
    String blog;

    @Getter
    @Setter
    String location;

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    boolean hireable;

    @Getter
    @Setter
    String bio;

    @Getter
    @Setter
    @SerializedName("public_repos")
    int publicRepos;

    @Getter
    @Setter
    @SerializedName("public_gists")
    int publicGists;

    @Getter
    @Setter
    int followers;

    @Getter
    @Setter
    int following;

    @Getter
    @Setter
    @SerializedName("created_at")
    String createdAt;

    @Getter
    @Setter
    @SerializedName("updated_at")
    String updatedAt;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        GithubUserParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {
        public GithubUser createFromParcel(Parcel source) {
            GithubUser target = new GithubUser();
            GithubUserParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public GithubUser[] newArray(int size) {
            return new GithubUser[size];
        }
    };
}
