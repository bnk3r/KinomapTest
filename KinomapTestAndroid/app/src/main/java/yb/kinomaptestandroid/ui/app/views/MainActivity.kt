package yb.kinomaptestandroid.ui.app.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import yb.kinomaptestandroid.ui.navigation.views.AppNavigation
import yb.kinomaptestandroid.ui.theme.KinomapTestAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KinomapTestAndroidTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize()
                ) { innerPadding ->

                    AppNavigation(
                        modifier = Modifier.Companion.padding(innerPadding)
                    )

                }
            }
        }
    }

}