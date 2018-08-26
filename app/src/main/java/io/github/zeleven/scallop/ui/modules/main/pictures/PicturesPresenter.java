package io.github.zeleven.scallop.ui.modules.main.pictures;

import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PicturesPresenter extends BasePresenter<PicturesContract.View>
        implements PicturesContract.Presenter {

    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public PicturesPresenter(DataManager dataManager) {
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
    public void getPictures(String category, final int page) {
        Observable<BaseResponse<GanHuo>> observable = dataManager.getGanHuo(category, page);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<GanHuo>, List<GanHuo>>() {
            @Override
            public List<GanHuo> apply(@NonNull BaseResponse<GanHuo> ganHuoBaseResponse)
                    throws Exception {
                return ganHuoBaseResponse.getResults();
            }
        }).subscribe(new Observer<List<GanHuo>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<GanHuo> ganHuoList) {
                getView().showList(ganHuoList, page);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {
//                getView().showComplete();
            }
        });

    }
}
