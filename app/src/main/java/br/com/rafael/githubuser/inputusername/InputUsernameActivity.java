package br.com.rafael.githubuser.inputusername;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.widget.EditText;

import br.com.rafael.githubuser.R;
import br.com.rafael.githubuser.core.view.BaseActivity;
import br.com.rafael.githubuser.user.presentation.UserActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InputUsernameActivity extends BaseActivity {

    @BindView(R.id.input_username)
    EditText inputUsername;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_username);

        bindViews();
        initEdit();
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void initEdit() {
        inputUsername.setText("rafaelmeteoro");
    }

    @OnClick(R.id.confirmar)
    public void confirmar() {
        String username = inputUsername.getText().toString();
        if (username.isEmpty()) {
            Snackbar.make(inputUsername, "Preencha o campo", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent intent = UserActivity.IntentBuilder.builder(this)
                    .username(username)
                    .create();
            startActivity(intent);
        }
    }
}
