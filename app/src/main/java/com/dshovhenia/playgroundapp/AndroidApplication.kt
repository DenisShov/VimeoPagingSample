package com.dshovhenia.playgroundapp

import android.app.Application
import timber.log.Timber
import com.dshovhenia.playgroundapp.injection.component.ApplicationComponent
import com.dshovhenia.playgroundapp.injection.component.DaggerApplicationComponent
import com.dshovhenia.playgroundapp.injection.module.ApplicationModule

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    val component: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}
