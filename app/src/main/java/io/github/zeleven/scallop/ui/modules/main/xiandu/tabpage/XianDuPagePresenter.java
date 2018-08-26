package io.github.zeleven.scallop.ui.modules.main.xiandu.tabpage;

import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.XianDuSubCategory;
import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class XianDuPagePresenter extends BasePresenter<XianDuPageContract.View>
        implements XianDuPageContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public XianDuPagePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void getSubCategories(String parent) {
        Observable<BaseResponse<XianDuSubCategory>> observable = dataManager.getXianDuSubCategories(parent);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<XianDuSubCategory>, List<XianDuSubCategory>>() {
            @Override
            public List<XianDuSubCategory> apply(@NonNull BaseResponse<XianDuSubCategory> xianDuSubCategoryBaseResponse)
                    throws Exception {
                return xianDuSubCategoryBaseResponse.getResults();
            }
        }).subscribe(new Observer<List<XianDuSubCategory>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<XianDuSubCategory> xianDuSubCategories) {
                getView().showList(xianDuSubCategories);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {
//                getView().showSuccessful();
            }
        });
    }
}
