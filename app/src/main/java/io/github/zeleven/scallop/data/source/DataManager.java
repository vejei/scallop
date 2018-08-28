package io.github.zeleven.scallop.data.source;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.data.model.XianDu;
import io.github.zeleven.scallop.data.model.XianDuCategory;
import io.github.zeleven.scallop.data.model.XianDuSubCategory;
import io.github.zeleven.scallop.data.source.local.PreferencesHelper;
import io.github.zeleven.scallop.data.source.remote.GankIOService;
import io.reactivex.Observable;

@Singleton
public class DataManager {
    private GankIOService gankIOService;
    private PreferencesHelper preferencesHelper;

    @Inject
    public DataManager(GankIOService gankIOService, PreferencesHelper preferencesHelper) {
        this.gankIOService = gankIOService;
        this.preferencesHelper = preferencesHelper;
    }

    public Observable<BaseResponse<GanHuo>> getGanHuo(String category, int page) {
        return gankIOService.getGanHuo(category, page);
    }

    public Observable<BaseResponse<XianDuCategory>> getXianDuCategories() {
        return gankIOService.getXianDuCategories();
    }

    public Observable<BaseResponse<XianDuSubCategory>> getXianDuSubCategories(String enName) {
        return gankIOService.getXianDuSubCategories(enName);
    }

    public Observable<BaseResponse<XianDu>> getXianDu(String categoryId, int page) {
        return gankIOService.getXianDu(categoryId, page);
    }

    public Observable<BaseResponse<GanHuo>> searchGanHuo(int page) {
        return gankIOService.searchGanHuo(page);
    }

    public PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }
}
