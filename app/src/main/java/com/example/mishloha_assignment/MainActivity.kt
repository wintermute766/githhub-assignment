package com.example.mishloha_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.toArgb
import com.example.mishloha_assignment.ui.AllRepos
import com.example.mishloha_assignment.ui.theme.MishlohaassignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MishlohaassignmentTheme {
                window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                Surface(color = MaterialTheme.colors.background) {
                    AllRepos()
                }
            }
        }
    }
}