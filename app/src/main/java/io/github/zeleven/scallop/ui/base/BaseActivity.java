package io.github.zeleven.scallop.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.Scallop;
import io.github.zeleven.scallop.di.component.ActivityComponent;
import io.github.zeleven.scallop.di.component.DaggerActivityComponent;
import io.github.zeleven.scallop.utils.NetworkUtils;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {
    protected ActivityComponent activityComponent;

    @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;
    @Nullable @BindView(R.id.appbar_layout) protected AppBarLayout appbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((Scallop) getApplication()).getApplicationComponent())
                .build();
    }

    public abstract int getLayout();

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void enableAppBarElevation(boolean enable) {
        if (appbarLayout != null) {
            appbarLayout.setElevation(enable ? 4.0f : 0.0f);
        }
    }
}
