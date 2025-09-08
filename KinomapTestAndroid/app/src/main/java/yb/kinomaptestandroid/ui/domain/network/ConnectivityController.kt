package yb.kinomaptestandroid.ui.domain.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn

class ConnectivityController(
    val connectivityManager: ConnectivityManager,
    val networkRequest: NetworkRequest
) : ViewModel() {

    val internetStatus = isConnectedFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = InternetStatus.OFFLINE
    )

    val isConnectedFlow: Flow<InternetStatus>
        get() = callbackFlow {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    connectivityManager.getNetworkCapabilities(network)?.let {
                        if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                            trySend(InternetStatus.ONLINE)
                        }
                    }
                }

                override fun onLost(network: Network) {
                    trySend(InternetStatus.OFFLINE)
                }

                override fun onUnavailable() {
                    trySend(InternetStatus.OFFLINE)
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    capabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, capabilities)
                    if (capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
                        trySend(InternetStatus.ONLINE)
                    } else {
                        trySend(InternetStatus.OFFLINE)
                    }
                }
            }
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }

}