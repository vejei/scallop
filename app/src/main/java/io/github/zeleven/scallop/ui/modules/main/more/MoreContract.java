package io.github.zeleven.scallop.ui.modules.main.more;

import io.github.zeleven.scallop.ui.base.BaseContract;

public interface MoreContract {
    interface View extends BaseContract.View {}

    interface Presenter extends BaseContract.Presenter<MoreContract.View> {}
}
