package io.github.zeleven.scallop.data.source;

import javax.inject.Inject;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.data.model.XianDu;
import io.github.zeleven.scallop.data.model.XianDuCategory;
import io.github.zeleven.scallop.data.model.XianDuSubCategory;
import io.github.zeleven.scallop.data.source.remote.GankIOService;
import io.github.zeleven.scallop.utils.Constants;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    private GankIOService gankIOService;

    @Inject
    public DataManager(GankIOService gankIOService) {
        this.gankIOService = gankIOService;
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
}
