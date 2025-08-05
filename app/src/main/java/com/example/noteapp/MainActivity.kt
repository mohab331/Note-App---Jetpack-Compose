package com.example.noteapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.components.DismissKeyboardOnClickOutside
import com.example.noteapp.screens.NoteScreen
import com.example.noteapp.screens.NoteViewModel
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
///  What is it?
//    Tells Hilt: “This Android class (Activity, Fragment, Service, etc.) wants to receive dependencies via @Inject.”
//
//Hilt generates a component behind the scenes that injects the dependencies into that class.
//
//Why use it?
//Because you cannot just @Inject into any class — only classes annotated with @AndroidEntryPoint can receive injections from Hilt.
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                MyNoteApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyNoteApp() {
    val noteViewModel = viewModel<NoteViewModel>()
    DismissKeyboardOnClickOutside {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_title),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.LightGray
                    ),
                    actions = {
                        Icon(
                            imageVector = Icons.Rounded.Notifications,
                            contentDescription = "Notifications Icon",
                        )
                    }
                )
            }
        ) { innerPadding ->
            NoteScreen(innerPadding,noteViewModel)
        }
    }
}
