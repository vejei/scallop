package io.github.zeleven.scallop.ui.modules.main.xiandu.list;

import java.util.List;

import io.github.zeleven.scallop.data.model.XianDu;
import io.github.zeleven.scallop.ui.base.BaseContract;

public interface XianDuListContract {
    interface View extends BaseContract.View {
        void showXianDu(List<XianDu> dataList, int page);
    }

    interface Presenter extends BaseContract.Presenter<XianDuListContract.View> {
        void getXianDu(String categoryId, int page);
    }
}
