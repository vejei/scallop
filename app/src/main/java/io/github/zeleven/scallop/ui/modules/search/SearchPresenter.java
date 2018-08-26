package io.github.zeleven.scallop.ui.modules.search;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.scallop.data.model.BaseResponse;
import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.ui.base.BasePresenter;
import io.github.zeleven.scallop.utils.StringUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchContract.View>
        implements SearchContract.Presenter {
    private DataManager dataManager;

    @Inject
    public SearchPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void search(final String keyword, final int page) {
        if (!StringUtils.isEmpty(keyword)) {
            getView().closeKeyboard();
            final List<GanHuo> ganHuoList = new ArrayList<>();
            Observable<BaseResponse<GanHuo>> observable = dataManager.searchGanHuo(page);
            observable.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
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
            }).filter(new Predicate<GanHuo>() {
                @Override
                public boolean test(@NonNull GanHuo ganHuo) throws Exception {
                    return ganHuo.getDesc().toLowerCase().contains(keyword.toLowerCase())
                            || ganHuo.getType().toLowerCase().contains(keyword.toLowerCase());
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
                    if (ganHuoList.size() == 0 && page == 1) {
                        getView().showNoResult();
                    } else {
                        getView().showResults(ganHuoList, page);
                    }
                }
            });
        }
    }
}
