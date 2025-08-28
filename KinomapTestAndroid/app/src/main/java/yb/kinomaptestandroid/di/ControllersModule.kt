package yb.kinomaptestandroid.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import yb.kinomaptestandroid.ui.navigation.controllers.AppNavigationCtrl

val controllersModule = module {
    viewModel { AppNavigationCtrl(get()) }
}