package io.github.zeleven.scallop.ui.modules.main.xiandu.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.XianDu;
import io.github.zeleven.scallop.ui.adapter.XianDuAdapter;
import io.github.zeleven.scallop.ui.base.BaseActivity;
import io.github.zeleven.scallop.ui.listener.EndlessRecyclerViewScrollListener;

public class XianDuListActivity extends BaseActivity implements XianDuListContract.View {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.category_image) CircleImageView categoryImage;
    @BindView(R.id.category_name) TextView categoryNameTextView;
    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private String categoryId;
    private String imageUrl;
    private String categoryName;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private XianDuAdapter adapter;

    @Inject XianDuListPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_xiandu_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
        if (presenter != null) {
            presenter.attachView(this);
        }

        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        categoryId = intent.getStringExtra("CID");
        imageUrl = intent.getStringExtra("IMAGE_URL");
        categoryName = intent.getStringExtra("CATEGORY_NAME");

        Glide.with(this).load(imageUrl).apply(RequestOptions.centerCropTransform()).into(categoryImage);
        categoryNameTextView.setText(categoryName);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new XianDuAdapter();
        recyclerView.setAdapter(adapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getXianDu(categoryId, page);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            presenter.getXianDu(categoryId, 1);
            }
        });
        refreshLayout.setRefreshing(true);
        presenter.getXianDu(categoryId, 1);
    }

    @Override
    public void showXianDu(List<XianDu> dataList, int page) {
        if (page == 1) {
            refreshLayout.setRefreshing(false);
            adapter.setData(dataList);
        } else {
            adapter.appendItems(dataList);
        }
    }
}
