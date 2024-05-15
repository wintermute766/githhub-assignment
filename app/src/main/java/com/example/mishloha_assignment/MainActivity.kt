package com.example.mishloha_assignment

import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import com.example.mishloha_assignment.ui.AllRepos
import com.example.mishloha_assignment.ui.theme.MishlohaassignmentTheme
import com.example.mishloha_assignment.util.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val networksFlow = MutableSharedFlow<ConnectionState>()
    private lateinit var networkMonitor: NetworkMonitor
    private val networkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {
            lifecycleScope.launch {
                networksFlow.emit(ConnectionState.Connected)
            }
        }

        override fun onLost(network: Network) {
            lifecycleScope.launch {
                networksFlow.emit(ConnectionState.Disconnected)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        networkMonitor = NetworkMonitor(this)
        networkMonitor.registerNetworkCallback(networkCallback)
        listenConnectionState()
        if (!networkMonitor.isNetworkAvailable()) {
            lifecycleScope.launch {
                networksFlow.emit(ConnectionState.Disconnected)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkMonitor.unregisterNetworkCallback(networkCallback)
    }

    private fun listenConnectionState() {
        lifecycleScope.launch {
            networksFlow.collectLatest {
                when (it) {
                    ConnectionState.Connected -> setContent {
                        MishlohaassignmentTheme {
                            Surface(color = MaterialTheme.colors.background) {
                                AllRepos()
                            }
                        }
                    }

                    ConnectionState.Disconnected -> setContent {
                        Column(Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                painter = painterResource(id = R.drawable.no_internet),
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

sealed class ConnectionState {
    data object Connected : ConnectionState()
    data object Disconnected : ConnectionState()
}