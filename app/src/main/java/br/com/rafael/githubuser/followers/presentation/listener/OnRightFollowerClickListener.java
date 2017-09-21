package br.com.rafael.githubuser.followers.presentation.listener;

import android.support.annotation.NonNull;

import br.com.rafael.githubuser.followers.presentation.data.RightFollowerClickData;

public interface OnRightFollowerClickListener {
    void onClick(@NonNull RightFollowerClickData data);
}
