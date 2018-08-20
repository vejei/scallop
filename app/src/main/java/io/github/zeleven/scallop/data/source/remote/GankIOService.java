package io.github.zeleven.scallop.data.source.remote;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.data.model.XianDu;
import io.github.zeleven.scallop.data.model.XianDuCategory;
import io.github.zeleven.scallop.data.model.XianDuSubCategory;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankIOService {
    @GET("data/{category}/" + 20 + "/{page}")
    Observable<BaseResponse<GanHuo>> getGanHuo(
            @Path("category") String category, @Path("page") int page
    );

    @GET("xiandu/categories")
    Observable<BaseResponse<XianDuCategory>> getXianDuCategories();

    @GET("xiandu/category/{en_name}")
    Observable<BaseResponse<XianDuSubCategory>> getXianDuSubCategories(@Path("en_name") String enName);

    @GET("xiandu/data/id/{cid}/count/" + 20 + "/page/{page}")
    Observable<BaseResponse<XianDu>> getXianDu(@Path("cid") String categoryId, @Path("page") int page);
}
