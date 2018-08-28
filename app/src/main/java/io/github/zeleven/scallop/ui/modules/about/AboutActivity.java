package io.github.zeleven.scallop.ui.modules.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.OnClick;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.ui.base.BaseActivity;
import io.github.zeleven.scallop.utils.Constants;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(R.string.toolbar_title_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_about;
    }

    @OnClick(R.id.action_button_1)
    public void openProjectPage() {
        Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.PROJECT_PAGE_URL));
        startActivity(openBrowserIntent);
    }

    @OnClick(R.id.action_button_2)
    public void openIssuesPage() {
        Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.ISSUE_PAGE_URL));
        startActivity(openBrowserIntent);
    }
}
