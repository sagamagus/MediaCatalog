package com.sagamagus.mediacatalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import com.sagamagus.mediacatalog.presentation.navigation.MediaCatalogApp
import com.sagamagus.mediacatalog.presentation.ui.MediaCatalogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme()
            ) {
                MediaCatalogTheme {
                    MediaCatalogApp()
                }
            }
        }
    }
}