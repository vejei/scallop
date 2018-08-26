package io.github.zeleven.scallop.ui.modules.main.pictures;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.ui.adapter.PictureAdapter;
import io.github.zeleven.scallop.ui.base.BaseFragment;
import io.github.zeleven.scallop.ui.listener.EndlessRecyclerViewScrollListener;

public class PicturesFragment extends BaseFragment implements PicturesContract.View {
    @Inject PicturesPresenter presenter;

    @BindView(R.id.refresh_layout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private PictureAdapter adapter;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_pictures;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter != null) {
            presenter.attachView(this);
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new PictureAdapter();
        recyclerView.setAdapter(adapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.getPictures("福利", page);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPictures("福利", 1);
                recyclerViewScrollListener.resetState();
                adapter.clear();
            }
        });
        refreshLayout.setRefreshing(true);
        presenter.getPictures("福利", 1);
    }

    @Override
    public void showList(List<GanHuo> data, int page) {
        if (page == 1) {
            refreshLayout.setRefreshing(false);
            adapter.setData(data);
        } else {
            adapter.appendItems(data);
        }
    }
}
