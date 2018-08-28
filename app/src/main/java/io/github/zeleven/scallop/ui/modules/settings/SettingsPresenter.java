package io.github.zeleven.scallop.ui.modules.settings;

import javax.inject.Inject;

import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.ui.base.BasePresenter;

public class SettingsPresenter extends BasePresenter<SettingsContract.View>
        implements SettingsContract.Presenter {
    private DataManager dataManager;

    @Inject
    public SettingsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public String getLanguageEntriesValue(String key) {
        return dataManager.getPreferencesHelper().getStringPrefValue(key);
    }
}
