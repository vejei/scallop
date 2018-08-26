package io.github.zeleven.scallop.ui.modules.main.home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.ui.adapter.TabViewPagerAdapter;
import io.github.zeleven.scallop.ui.base.BaseFragment;
import io.github.zeleven.scallop.utils.TabViewPagerAdapterItem;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @Inject HomePresenter presenter;

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

        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager(),
                TabViewPagerAdapterItem.createHomeFragments(getContext()));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
