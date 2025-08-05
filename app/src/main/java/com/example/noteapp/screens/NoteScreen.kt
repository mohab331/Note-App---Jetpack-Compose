package com.example.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.noteapp.R
import com.example.noteapp.components.NoteTextField
import com.example.noteapp.models.NoteModel
import java.time.format.DateTimeFormatter


@Composable
fun NoteScreen(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    noteViewModel: NoteViewModel = viewModel()
) {
    var titleTextFieldValue by remember {
        mutableStateOf("")
    }
    var noteDescriptionTextFieldValue by remember {
        mutableStateOf("")
    }

    // collectAsState() This converts your StateFlow into a Compose state (State<T>)
    // that automatically triggers recomposition when the data changes — just like ref.watch() or Consumer in Flutter.
    val notesList: List<NoteModel> =
        noteViewModel.noteList.collectAsState(initial = emptyList()).value
    /// Note:
    /// Under the Hood:
    /// Both of these:
    /// val state = noteList.collectAsState()
    /// val list = state.value
    ///  are equivalent to:   val notesList by noteViewModel.noteList.collectAsState()



    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .padding(
                start = 16.dp,
                end = 16.dp,
            )
    ) {
        AddNoteWidget(
            titleTextFieldValue = titleTextFieldValue,
            noteDescriptionTextFieldValue = noteDescriptionTextFieldValue,
            onNoteTitleValueChange = { titleTextFieldValue = it },
            onNoteDescriptionValueChange = { noteDescriptionTextFieldValue = it },
            onSaveCallback = {
                focusManager.clearFocus()
                if (titleTextFieldValue.isNotBlank() && noteDescriptionTextFieldValue.isNotBlank()) {
                    noteViewModel.addNote(
                        NoteModel(
                            title = titleTextFieldValue,
                            content = noteDescriptionTextFieldValue
                        )
                    )
                    Toast.makeText(context, "Note saved successfully!", Toast.LENGTH_SHORT).show()
                    titleTextFieldValue = ""
                    noteDescriptionTextFieldValue = ""
                }

            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        NotesListWidget(notesList)
    }
}


@Composable
private fun NotesListWidget(noteList: List<NoteModel>) {
    LazyColumn {
        items(noteList) {
            NoteItemWidget(it)
        }
    }
}

@Composable
private fun NoteItemWidget(it: NoteModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(topStart = 12.dp, bottomEnd = 12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = it.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = it.content,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                Text(
                    text = it.creationDate.toLocaleString(),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun AddNoteWidget(
    titleTextFieldValue: String,
    noteDescriptionTextFieldValue: String,
    onNoteTitleValueChange: (String) -> Unit,
    onNoteDescriptionValueChange: (String) -> Unit,
    onSaveCallback: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        NoteTextField(
            value = titleTextFieldValue,
            onValueChange = onNoteTitleValueChange,
            leadingIcon = Icons.Default.Edit,
            leadingIconContentDescription = stringResource(id = R.string.title_icon),
            labelText = stringResource(id = R.string.title),
            placeHolderText = stringResource(id = R.string.enter_note_title),
        )
        Spacer(modifier = Modifier.height(12.dp))
        NoteTextField(
            value = noteDescriptionTextFieldValue,
            onValueChange = onNoteDescriptionValueChange,
            leadingIcon = Icons.Default.Info,
            leadingIconContentDescription = stringResource(id = R.string.description_icon),
            labelText = stringResource(id = R.string.description),
            placeHolderText = stringResource(id = R.string.enter_description),
            maxLines = 3,
        )
        Spacer(modifier = Modifier.height(12.dp))
        ElevatedButton(
            onClick = onSaveCallback,
            modifier = Modifier
                .width(200.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.save_icon),
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.save),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen()
}