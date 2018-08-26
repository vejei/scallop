package io.github.zeleven.scallop.ui.modules.main.xiandu;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.XianDuCategory;
import io.github.zeleven.scallop.ui.adapter.TabViewPagerAdapter;
import io.github.zeleven.scallop.ui.base.BaseFragment;
import io.github.zeleven.scallop.utils.TabViewPagerAdapterItem;

public class XianDuFragment extends BaseFragment implements XianDuContract.View {
    @Inject XianDuPresenter presenter;

    @BindView(R.id.tab) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager viewPager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.tab_viewpager;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter != null) {
            presenter.attachView(this);
        }

        presenter.getCategories();
    }

    @Override
    public void setupTab(List<XianDuCategory> data) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager(),
                        TabViewPagerAdapterItem.createXianDuFragments(data));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
