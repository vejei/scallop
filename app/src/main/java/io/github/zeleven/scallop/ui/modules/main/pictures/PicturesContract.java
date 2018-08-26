package io.github.zeleven.scallop.ui.modules.main.pictures;

import java.util.List;

import io.github.zeleven.scallop.data.model.GanHuo;
import io.github.zeleven.scallop.ui.base.BaseContract;

public interface PicturesContract {
    interface View extends BaseContract.View {
        void showList(List<GanHuo> data, int page);
    }

    interface Presenter extends BaseContract.Presenter<PicturesContract.View> {
        void getPictures(String category, int page);
    }
}
