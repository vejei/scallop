package io.github.zeleven.scallop.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.zeleven.scallop.Scallop;
import io.github.zeleven.scallop.data.source.DataManager;
import io.github.zeleven.scallop.data.source.remote.GankIOService;
import io.github.zeleven.scallop.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {
    private Scallop application;

    public ApplicationModule(Scallop application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    GankIOService provideGankIOService(Retrofit retrofit) {
        return retrofit.create(GankIOService.class);
    }
}
