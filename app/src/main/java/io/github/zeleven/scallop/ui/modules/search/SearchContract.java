package io.github.zeleven.scallop.ui.modules.search;

import java.util.List;

import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.ui.base.BaseContract;

public interface SearchContract {
    interface View extends BaseContract.View {
        void closeKeyboard();
        void showNoResult();
        void showError();
        void showResults(List<GanHuo> resultItems, int page);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void search(String keyword, int page);
    }
}
