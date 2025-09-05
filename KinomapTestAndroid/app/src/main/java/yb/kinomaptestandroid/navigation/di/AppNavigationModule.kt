package yb.kinomaptestandroid.navigation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import yb.kinomaptestandroid.navigation.presentation.AppNavigationViewModel

val appNavigationModule = module {
    viewModelOf(::AppNavigationViewModel)
}