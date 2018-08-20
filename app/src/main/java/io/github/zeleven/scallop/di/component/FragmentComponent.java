package io.github.zeleven.scallop.di.component;

import dagger.Component;
import io.github.zeleven.scallop.di.module.FragmentModule;
import io.github.zeleven.scallop.di.scope.FragmentScope;
import io.github.zeleven.scallop.ui.modules.home.HomeFragment;
import io.github.zeleven.scallop.ui.modules.home.tabpage.GanHuoPageFragment;
import io.github.zeleven.scallop.ui.modules.more.MoreFragment;
import io.github.zeleven.scallop.ui.modules.pictures.PicturesFragment;
import io.github.zeleven.scallop.ui.modules.xiandu.XianDuFragment;
import io.github.zeleven.scallop.ui.modules.xiandu.tabpage.XianDuPageFragment;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);
    void inject(GanHuoPageFragment pageFragment);
    void inject(XianDuFragment xianDuFragment);
    void inject(XianDuPageFragment xianDuPageFragment);
    void inject(PicturesFragment picturesFragment);
    void inject(MoreFragment moreFragment);
}
