package com.challenge.svakt.smchallenge.dagger;

import com.challenge.svakt.smchallenge.dagger.components.AppComponent;
import com.challenge.svakt.smchallenge.dagger.components.DaggerAppComponent;
import com.challenge.svakt.smchallenge.dagger.modules.AppModule;

public class DaggerInjector {
    private static AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();

    public static AppComponent get() {
        return appComponent;
    }
}
