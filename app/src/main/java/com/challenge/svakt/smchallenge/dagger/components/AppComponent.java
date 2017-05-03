package com.challenge.svakt.smchallenge.dagger.components;

import com.challenge.svakt.smchallenge.dagger.modules.AppModule;
import com.challenge.svakt.smchallenge.ui.activities.DealsDetailActivity;
import com.challenge.svakt.smchallenge.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(DealsDetailActivity activity);
}
