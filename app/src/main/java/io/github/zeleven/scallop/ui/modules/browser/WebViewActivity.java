package io.github.zeleven.scallop.ui.modules.browser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.ui.base.BaseActivity;

public class WebViewActivity extends BaseActivity {
    @BindView(R.id.web_view) WebView webView;
    @BindView(R.id.web_view_progressbar) ProgressBar webViewProgressBar;

    private String url;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        title = intent.getStringExtra("TITLE");

        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        getSupportActionBar().setTitle(title != null ? title : "");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                webViewProgressBar.setProgress(newProgress);
                if (newProgress >= 100) {
                    webViewProgressBar.setVisibility(View.GONE);
                } else {
                    webViewProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.wed_view_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareText = getResources().getString(R.string.share_dialog_title_share);
                String shareContent = getResources().getString(R.string.share_content);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareText);
                shareIntent.putExtra(Intent.EXTRA_TEXT, url + shareContent);
                startActivity(Intent.createChooser(shareIntent, shareText));
                break;
            case R.id.refresh:
                webView.reload();
                break;
            case R.id.open_in_browser:
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent openBrowserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(openBrowserIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
