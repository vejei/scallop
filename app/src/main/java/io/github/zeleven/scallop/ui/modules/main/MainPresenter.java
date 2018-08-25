package io.github.zeleven.scallop.ui.modules.main;

import javax.inject.Inject;

import io.github.zeleven.scallop.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {
    @Inject
    public MainPresenter() {
    }
}
