package io.github.zeleven.scallop.ui.modules.main.home;

import javax.inject.Inject;

import io.github.zeleven.scallop.ui.base.BasePresenter;

public class HomePresenter extends BasePresenter<HomeContract.View>
        implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }
}
