package io.github.zeleven.scallop;

import android.app.Application;

import io.github.zeleven.scallop.di.component.ApplicationComponent;
import io.github.zeleven.scallop.di.component.DaggerApplicationComponent;
import io.github.zeleven.scallop.di.module.ApplicationModule;

public class Scallop extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
