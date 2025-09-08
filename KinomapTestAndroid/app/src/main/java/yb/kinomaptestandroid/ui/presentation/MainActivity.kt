package yb.kinomaptestandroid.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import yb.kinomaptestandroid.navigation.presentation.AppNavigationScreen
import yb.kinomaptestandroid.ui.presentation.theme.KinomapTestAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinomapTestAndroidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    AppNavigationScreen(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }

}