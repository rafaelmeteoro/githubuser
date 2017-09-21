package br.com.rafael.githubuser.followers.presentation.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.core.adapter.AdapterDelegate;
import br.com.rafael.githubuser.followers.data.models.Follower;
import br.com.rafael.githubuser.followers.presentation.data.RightFollowerClickData;
import br.com.rafael.githubuser.followers.presentation.listener.OnRightFollowerClickListener;
import br.com.rafael.githubuser.followers.presentation.viewmodel.FollowersViewModelHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RightFollowerAdapterDelegate implements AdapterDelegate<FollowersViewModelHolder> {

    private OnRightFollowerClickListener listener;

    @Override
    public boolean isViewForData(FollowersViewModelHolder data, int position) {
        return !getItem(data, position).isLeft();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.follower_item_right, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FollowersViewModelHolder data, int position, RecyclerView.ViewHolder holder) {
        Follower follower = getItem(data, position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textLogin.setText(follower.login());
        viewHolder.textUrl.setText(follower.url());
        viewHolder.itemView.setOnClickListener(
                view -> callOnFollowerClickIfNotNull(follower));

        Picasso.with(viewHolder.itemView.getContext())
                .load(follower.avatarUrl())
                .into(viewHolder.imageAvatar);
    }

    private Follower getItem(FollowersViewModelHolder holder, int position) {
        return holder.getViewModels().get(position);
    }

    private void callOnFollowerClickIfNotNull(Follower follower) {
        if (listener != null) {
            listener.onClick(
                    new RightFollowerClickData()
                            .url(follower.htmlUrl())
            );
        }
    }

    public void setOnFollowerClickListener(@Nullable OnRightFollowerClickListener listener) {
        this.listener = listener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_avatar)
        ImageView imageAvatar;

        @BindView(R.id.text_login)
        TextView textLogin;

        @BindView(R.id.text_url)
        TextView textUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
