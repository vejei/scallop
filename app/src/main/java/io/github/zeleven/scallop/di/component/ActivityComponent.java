package io.github.zeleven.scallop.di.component;

import dagger.Component;
import io.github.zeleven.scallop.di.module.ActivityModule;
import io.github.zeleven.scallop.di.scope.ActivityScope;
import io.github.zeleven.scallop.ui.modules.imageviewer.ImageViewerActivity;
import io.github.zeleven.scallop.ui.modules.main.MainActivity;
import io.github.zeleven.scallop.ui.modules.main.xiandu.list.XianDuListActivity;
import io.github.zeleven.scallop.ui.modules.search.SearchActivity;
import io.github.zeleven.scallop.ui.modules.settings.SettingsActivity;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(XianDuListActivity xianDuListActivity);
    void inject(ImageViewerActivity imageViewerActivity);
    void inject(SearchActivity searchActivity);
    void inject(SettingsActivity settingsActivity);
}
