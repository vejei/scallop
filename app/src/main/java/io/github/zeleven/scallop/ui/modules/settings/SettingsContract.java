package io.github.zeleven.scallop.ui.modules.settings;

import io.github.zeleven.scallop.ui.base.BaseContract;

public interface SettingsContract {
    interface View extends BaseContract.View {}

    interface Presenter extends BaseContract.Presenter<View> {
        String getLanguageEntriesValue(String key);
    }
}
