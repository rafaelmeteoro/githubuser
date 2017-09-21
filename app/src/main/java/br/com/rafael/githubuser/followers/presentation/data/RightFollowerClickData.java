package br.com.rafael.githubuser.followers.presentation.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ParcelablePlease
@Accessors(fluent = true, chain = true)
public class RightFollowerClickData implements Parcelable {

    @Getter
    @Setter
    String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        RightFollowerClickDataParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<RightFollowerClickData> CREATOR = new Creator<RightFollowerClickData>() {
        public RightFollowerClickData createFromParcel(Parcel source) {
            RightFollowerClickData target = new RightFollowerClickData();
            RightFollowerClickDataParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public RightFollowerClickData[] newArray(int size) {
            return new RightFollowerClickData[size];
        }
    };
}
