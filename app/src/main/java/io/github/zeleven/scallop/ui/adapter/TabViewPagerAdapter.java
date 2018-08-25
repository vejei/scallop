package io.github.zeleven.scallop.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import io.github.zeleven.scallop.utils.TabViewPagerAdapterItem;

public class TabViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<TabViewPagerAdapterItem> itemList;

    public TabViewPagerAdapter(FragmentManager fm, List<TabViewPagerAdapterItem> itemList) {
        super(fm);
        this.itemList = itemList;
    }

    @Override
    public Fragment getItem(int position) {
        return itemList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itemList.get(position).getTitle();
    }
}
