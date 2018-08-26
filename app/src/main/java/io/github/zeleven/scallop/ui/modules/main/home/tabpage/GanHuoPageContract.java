package io.github.zeleven.scallop.ui.modules.main.home.tabpage;

import java.util.List;

import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.ui.base.BaseContract;

public interface GanHuoPageContract {
    interface View extends BaseContract.View {
        void showList(List<GanHuo> data, int page);
    }

    interface Presenter extends BaseContract.Presenter<GanHuoPageContract.View> {
        void getGanHuo(String category, int page);
    }
}
