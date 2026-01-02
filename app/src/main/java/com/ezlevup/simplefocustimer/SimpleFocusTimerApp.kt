package com.ezlevup.simplefocustimer

import android.app.Application
import com.ezlevup.simplefocustimer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SimpleFocusTimerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SimpleFocusTimerApp)
            modules(appModule)
        }
    }
}
