package yb.kinomaptestandroid.di

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun provideConnectivityManager(
    appContext: Context
) = appContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

fun provideNetworkRequest(): NetworkRequest = NetworkRequest.Builder()
    .addCapability(NET_CAPABILITY_INTERNET)
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
    .build()

val appModule = module {
    single { provideConnectivityManager(androidContext()) }
    singleOf(::provideNetworkRequest)
}