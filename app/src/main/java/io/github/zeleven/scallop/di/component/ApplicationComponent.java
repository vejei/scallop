package io.github.zeleven.scallop.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application getApplication();
    DataManager getDataManager();
}
