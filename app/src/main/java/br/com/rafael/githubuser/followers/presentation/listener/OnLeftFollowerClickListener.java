package br.com.rafael.githubuser.followers.presentation.listener;

import android.support.annotation.NonNull;

import br.com.rafael.githubuser.followers.presentation.data.LeftFollowerClickData;

public interface OnLeftFollowerClickListener {
    void onClick(@NonNull LeftFollowerClickData data);
}
