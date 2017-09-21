package br.com.rafael.githubuser.followers.presentation.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ParcelablePlease
@Accessors(fluent = true, chain = true)
public class LeftFollowerClickData implements Parcelable {

    @Getter
    @Setter
    String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        LeftFollowerClickDataParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<LeftFollowerClickData> CREATOR = new Creator<LeftFollowerClickData>() {
        public LeftFollowerClickData createFromParcel(Parcel source) {
            LeftFollowerClickData target = new LeftFollowerClickData();
            LeftFollowerClickDataParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public LeftFollowerClickData[] newArray(int size) {
            return new LeftFollowerClickData[size];
        }
    };
}
