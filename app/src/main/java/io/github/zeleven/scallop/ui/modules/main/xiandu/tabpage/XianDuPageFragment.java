package io.github.zeleven.scallop.ui.modules.main.xiandu.tabpage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.XianDuSubCategory;
import io.github.zeleven.scallop.ui.adapter.XianDuSubCategoryAdapter;
import io.github.zeleven.scallop.ui.base.BaseFragment;

public class XianDuPageFragment extends BaseFragment implements XianDuPageContract.View {
    @Inject XianDuPagePresenter presenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private String parentCategory;
    private XianDuSubCategoryAdapter adapter;

    public static XianDuPageFragment newInstance(String tabTitle) {
        Bundle args = new Bundle();
        args.putString("TITLE", tabTitle);
        XianDuPageFragment fragment = new XianDuPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.recyclerview;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter != null) {
            presenter.attachView(this);
        }

        parentCategory = getArguments().getString("TITLE");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new XianDuSubCategoryAdapter();
        recyclerView.setAdapter(adapter);

        presenter.getSubCategories(parentCategory);
    }

    @Override
    public void showList(List<XianDuSubCategory> data) {
        adapter.setData(data);
    }
}
