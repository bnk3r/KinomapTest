package yb.kinomaptestandroid.ui.presentation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import yb.kinomaptestandroid.badges.di.badgesModule
import yb.kinomaptestandroid.navigation.di.appNavigationModule
import yb.kinomaptestandroid.ui.di.NetworkModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                NetworkModule,
                appNavigationModule,
                badgesModule,
            )
        }
    }

}