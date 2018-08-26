package io.github.zeleven.scallop.ui.modules.imageviewer;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.ui.adapter.ImageViewPagerAdapter;
import io.github.zeleven.scallop.ui.base.BaseActivity;
import io.github.zeleven.scallop.utils.StringUtils;

public class ImageViewerActivity extends BaseActivity implements ImageViewerContract.View {
    @Inject ImageViewerPresenter presenter;

    @BindView(R.id.image_viewpager) ViewPager imageViewPager;
    @BindView(R.id.current_position_tv) TextView currentPosTV;
    @BindView(R.id.total_tv) TextView totalTV;

    private int currentPosition;
    private List<String> imageUrlList;
    private ImageViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);

        if (presenter == null) {
            return;
        }
        presenter.attachView(this);

        Intent intent = getIntent();
        currentPosition = intent.getIntExtra("CURRENT_POSITION", 0);
        imageUrlList = intent.getStringArrayListExtra("IMAGE_URL_LIST");

        currentPosTV.setText(currentPosition + 1 + "");
        totalTV.setText(imageUrlList.size() + "");

        adapter = new ImageViewPagerAdapter(imageUrlList);
        imageViewPager.getCurrentItem();
        imageViewPager.setAdapter(adapter);
        imageViewPager.setCurrentItem(currentPosition);
        imageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                currentPosTV.setText(position + 1 + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_image_viewer;
    }

    @OnClick(R.id.share_button)
    public void openShare() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.share_dialog_title_share);
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                imageUrlList.get(imageViewPager.getCurrentItem()));
        startActivity(Intent.createChooser(shareIntent,
                this.getResources().getString(R.string.share_dialog_title_share)));
    }

    @OnClick(R.id.download_button)
    public void downloadImage() {
        String imageUrl = imageUrlList.get(imageViewPager.getCurrentItem());
        String fileName = StringUtils.getImageNameFromUrl(imageUrl);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .getAbsolutePath() + "/scallop";
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imageUrl));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI
                | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(fileName)
                .setNotificationVisibility(
                        DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(path, fileName);
        downloadManager.enqueue(request);
    }
}
