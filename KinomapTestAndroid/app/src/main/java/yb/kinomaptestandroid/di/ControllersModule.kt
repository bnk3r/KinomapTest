package yb.kinomaptestandroid.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import yb.kinomaptestandroid.ui.navigation.controllers.AppNavigationCtrl

val controllersModule = module {
    viewModelOf(::AppNavigationCtrl)
}