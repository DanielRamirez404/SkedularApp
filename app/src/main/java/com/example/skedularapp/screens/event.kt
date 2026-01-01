package com.example.skedularapp.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skedularapp.components.DropDown
import com.example.skedularapp.components.FormScreen
import com.example.skedularapp.components.FormTextField
import com.example.skedularapp.components.Title

@Composable
fun EventScreen() {

    FormScreen(
        title = "Event"
    ) {
        var title by remember { mutableStateOf("") }

        val activities = listOf("Homework", "Exam", "Meeting", "Assignment", "Other")
        var selectedActivity by remember { mutableStateOf(activities[0]) }

        val subjects = listOf("Math", "Science", "History", "English", "Other")
        var selectedSubject by remember { mutableStateOf(subjects[0]) }

        InfoSection(
            title = title,
            onTitleChange = { title = it },
            activities = activities,
            selectedActivity = selectedActivity,
            onActivityChange = { selectedActivity = it },
            subjects = subjects,
            selectedSubject = selectedSubject,
            onSubjectChange = { selectedSubject = it }
        )

    }
}

@Composable
fun InfoSection(
    title: String = "Event Title",
    onTitleChange: (String) -> Unit = {},
    activities: List<String>,
    selectedActivity: String,
    onActivityChange: (String) -> Unit = {},
    subjects: List<String>,
    selectedSubject: String,
    onSubjectChange: (String) -> Unit = {}
) {
    Title("Event Info")
    Spacer(modifier = Modifier.height(16.dp))

    FormTextField(
        label = "Title",
        value = title,
        onValueChange = onTitleChange
    )
    Spacer(modifier = Modifier.height(16.dp))

    DropDown(
        title = "Activity",
        options = activities,
        selectedOption = selectedActivity,
        onChange = onActivityChange,
    )
    Spacer(modifier = Modifier.height(16.dp))

    DropDown(
        title = "Subject",
        options = subjects,
        selectedOption = selectedSubject,
        onChange = onSubjectChange,
    )
}

@Composable
fun DueDateSection() {

}