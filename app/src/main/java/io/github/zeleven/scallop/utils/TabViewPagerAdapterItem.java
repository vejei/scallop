package io.github.zeleven.scallop.utils;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import io.github.zeleven.scallop.R;
import io.github.zeleven.scallop.data.model.XianDuCategory;
import io.github.zeleven.scallop.ui.modules.main.home.tabpage.GanHuoPageFragment;
import io.github.zeleven.scallop.ui.modules.main.xiandu.tabpage.XianDuPageFragment;

public class TabViewPagerAdapterItem {
    private String title;
    private Fragment fragment;

    public TabViewPagerAdapterItem(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public static List<TabViewPagerAdapterItem> createHomeFragments(Context context) {
        String[] titleList = new String[] {
                context.getString(R.string.tab_title_all),
                context.getString(R.string.tab_title_android),
                context.getString(R.string.tab_title_ios),
                context.getString(R.string.tab_title_funny_video),
                context.getString(R.string.tab_title_res),
                context.getString(R.string.tab_title_front_end),
                context.getString(R.string.tab_title_random),
                context.getString(R.string.tab_title_app)
        };
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (int i = 0; i < titleList.length; i++) {
            adapterItems.add(new TabViewPagerAdapterItem(titleList[i],
                    GanHuoPageFragment.newInstance(titleList[i])));
        }
        return adapterItems;
    }

    public static List<TabViewPagerAdapterItem> createXianDuFragments(
            List<XianDuCategory> categoryList) {
        ArrayList<TabViewPagerAdapterItem> adapterItems = new ArrayList<>();
        for (XianDuCategory category : categoryList) {
            adapterItems.add(new TabViewPagerAdapterItem(category.getName(),
                    XianDuPageFragment.newInstance(category.getEnName())));
        }
        return adapterItems;
    }
}
