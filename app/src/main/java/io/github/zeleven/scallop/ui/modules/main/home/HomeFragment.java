package io.github.zeleven.scallop.ui.modules.main.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import javax.inject.Inject;

import butterknife.BindView;
import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.utils.FragmentPagerAdapterItem;
import io.github.zeleven.scallop.ui.adapter.FragmentPagerAdapter;
import io.github.zeleven.scallop.ui.base.BaseFragment;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @Inject HomePresenter presenter;

    @BindView(R.id.tab) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager viewPager;

    @Override
    public int getLayout() {
        return R.layout.tab_viewpager;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        // 依赖注入
        fragmentComponent.inject(this);

        if (presenter != null) {
            presenter.attachView(this);
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager(),
                FragmentPagerAdapterItem.createHomeFragments(getContext()));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
