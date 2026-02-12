package cz.musilto5.transparentaccounts.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cz.musilto5.transparentaccounts.common.presentation.theme.TransparentAccountsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TransparentAccountsTheme {
                TransparentAccountsApp()
            }
        }
    }
}
