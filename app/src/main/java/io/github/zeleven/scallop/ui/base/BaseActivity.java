package io.github.zeleven.scallop.ui.base;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.Scallop;
import io.github.zeleven.scallop.di.component.ActivityComponent;
import io.github.zeleven.scallop.di.component.DaggerActivityComponent;
import io.github.zeleven.scallop.ui.modules.main.MainActivity;
import io.github.zeleven.scallop.utils.NetworkUtils;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {
    protected ActivityComponent activityComponent;

    @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;
    @Nullable @BindView(R.id.appbar_layout) protected AppBarLayout appbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (this instanceof MainActivity) {
            setTheme(R.style.AppTheme);
        }
        updateLanguage();
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

    @Override
    public void showError(String message) {
        Log.e(getClass().getName(), message);
        Toast.makeText(this, R.string.toast_message_error_occurred, Toast.LENGTH_SHORT).show();
    }

    public void enableAppBarElevation(boolean enable) {
        if (appbarLayout != null) {
            appbarLayout.setElevation(enable ? getResources().getDimension(R.dimen.four_dp) : 0.0f);
        }
    }

    public void updateLanguage() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String languageCode = sharedPreferences.getString("app_language", "zh_CN");
        Locale locale;
        if (languageCode.contains("_")) {
            String[] parts = languageCode.split("_");
            locale = new Locale(parts[0], parts[1]);
        } else {
            locale = new Locale(languageCode);
        }
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
