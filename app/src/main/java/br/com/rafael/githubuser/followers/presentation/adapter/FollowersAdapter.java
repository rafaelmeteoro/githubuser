package br.com.rafael.githubuser.followers.presentation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import javax.inject.Inject;

import br.com.rafael.githubuser.core.adapter.AdapterDelegateManager;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;

public class FollowersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FollowersViewModelHolder data;

    private AdapterDelegateManager<FollowersViewModelHolder> delegateManager;

    private LeftFollowerAdapterDelegate leftFollowerAdapterDelegate;
    private RightFollowerAdapterDelegate rightFollowerAdapterDelegate;

    @Inject
    public FollowersAdapter() {
        delegateManager = new AdapterDelegateManager<>();

        leftFollowerAdapterDelegate = new LeftFollowerAdapterDelegate();
        rightFollowerAdapterDelegate = new RightFollowerAdapterDelegate();
        delegateManager.addDelegate(leftFollowerAdapterDelegate);
        delegateManager.addDelegate(rightFollowerAdapterDelegate);
    }

    public void setData(@NonNull FollowersViewModelHolder data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegateManager.onBindViewHolder(data, position, holder);
    }

    @Override
    public int getItemCount() {
        return data.getViewModels().size();
    }

    @Override
    public int getItemViewType(int position) {
        return delegateManager.getItemViewType(data, position);
    }
}
