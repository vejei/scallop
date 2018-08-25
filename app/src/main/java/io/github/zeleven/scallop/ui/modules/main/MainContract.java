package io.github.zeleven.scallop.ui.modules.main;

import io.github.zeleven.scallop.ui.base.BaseContract;

public interface MainContract {
    interface View extends BaseContract.View {}

    interface Presenter extends BaseContract.Presenter<MainContract.View> {}
}
