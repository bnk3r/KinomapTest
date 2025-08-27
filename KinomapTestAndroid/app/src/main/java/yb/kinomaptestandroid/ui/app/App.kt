package yb.kinomaptestandroid.ui.app

import android.app.Application
import org.koin.core.context.startKoin
import yb.kinomaptestandroid.di.controllersModule
import yb.kinomaptestandroid.di.serviceModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                serviceModule,
                controllersModule
            )
        }
    }

}