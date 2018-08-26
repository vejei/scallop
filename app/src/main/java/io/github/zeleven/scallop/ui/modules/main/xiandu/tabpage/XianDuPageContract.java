package io.github.zeleven.scallop.ui.modules.main.xiandu.tabpage;

import java.util.List;

import io.github.zeleven.scallop.data.model.XianDuSubCategory;
import io.github.zeleven.scallop.ui.base.BaseContract;

public interface XianDuPageContract {
    interface View extends BaseContract.View {
        void showList(List<XianDuSubCategory> data);
    }

    interface Presenter extends BaseContract.Presenter<XianDuPageContract.View> {
        void getSubCategories(String parent);
    }
}
