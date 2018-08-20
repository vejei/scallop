package io.github.zeleven.scallop.di.component;

import dagger.Component;
import io.github.zeleven.scallop.di.module.ActivityModule;
import io.github.zeleven.scallop.di.scope.ActivityScope;
import io.github.zeleven.scallop.ui.modules.main.MainActivity;
import io.github.zeleven.scallop.ui.modules.xiandu.categorydetail.XianDuListActivity;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(XianDuListActivity xianDuListActivity);
}
