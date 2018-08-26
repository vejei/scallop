package io.github.zeleven.scallop.ui.modules.main.xiandu;

import java.util.List;

import io.github.zeleven.scallop.data.model.XianDuCategory;
import io.github.zeleven.scallop.ui.base.BaseContract;

public interface XianDuContract {
    interface View extends BaseContract.View {
        void setupTab(List<XianDuCategory> data);
    }

    interface Presenter extends BaseContract.Presenter<XianDuContract.View> {
        void getCategories();
    }
}
