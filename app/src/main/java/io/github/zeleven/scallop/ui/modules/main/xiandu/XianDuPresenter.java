package io.github.zeleven.scallop.ui.modules.main.xiandu;

import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.XianDuCategory;
import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class XianDuPresenter extends BasePresenter<XianDuContract.View>
        implements XianDuContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public XianDuPresenter(DataManager dataManager) {
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
    public void getCategories() {
        Observable<BaseResponse<XianDuCategory>> observable = dataManager.getXianDuCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<XianDuCategory>, List<XianDuCategory>>() {
                    @Override
                    public List<XianDuCategory> apply(
                            @NonNull BaseResponse<XianDuCategory> xianDuCategoryBaseResponse)
                            throws Exception {
                        return xianDuCategoryBaseResponse.getResults();
                    }
                }).subscribe(new Observer<List<XianDuCategory>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<XianDuCategory> xianDuCategories) {
                getView().setupTab(xianDuCategories);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
