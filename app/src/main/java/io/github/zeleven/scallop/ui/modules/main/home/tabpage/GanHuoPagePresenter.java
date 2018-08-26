package io.github.zeleven.scallop.ui.modules.main.home.tabpage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class GanHuoPagePresenter extends BasePresenter<GanHuoPageContract.View>
        implements GanHuoPageContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public GanHuoPagePresenter(DataManager dataManager) {
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
    public void getGanHuo(String category, final int page) {
        final List<GanHuo> ganHuoList = new ArrayList<>();
        Observable<BaseResponse<GanHuo>> observable = dataManager.getGanHuo(category, page);
        disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Function<BaseResponse<GanHuo>, ObservableSource<GanHuo>>() {
            @Override
            public ObservableSource<GanHuo> apply(@NonNull BaseResponse<GanHuo> ganHuoBaseResponse)
                    throws Exception {
                return Observable.fromIterable(ganHuoBaseResponse.getResults());
            }
        }).filter(new Predicate<GanHuo>() {
            @Override
            public boolean test(@NonNull GanHuo ganHuo) throws Exception {
                return !ganHuo.getType().equals("福利");
            }
        }).subscribe(new Consumer<GanHuo>() {
            @Override
            public void accept(GanHuo ganHuo) throws Exception {
                ganHuoList.add(ganHuo);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                getView().showError(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                getView().showList(ganHuoList, page);
            }
        });
    }
}
