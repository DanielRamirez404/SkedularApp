package com.example.skedularapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skedularapp.components.Header
import com.example.skedularapp.components.HomeworkCard
import com.example.skedularapp.components.SegmentedButtonWeek
import com.example.skedularapp.ui.theme.SkedularAppTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkedularAppTheme {
                Scaffold() { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Header()
                        HomeworkList(modifier = Modifier.padding(top = 15.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun HomeworkList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SegmentedButtonWeek(date = java.sql.Date(Date().time))
        HomeworkCard(
            title = "Math Homework",
            subject = "Math",
            dueTime = "12:45",
            color = Color(0xFFE57373)
        )
        HomeworkCard(
            title = "History Reading",
            subject = "History",
            dueTime = "14:15",
            color = Color(0xFF81C784)
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6)
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6)
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6)
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6)
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6)
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6)
        )
        HomeworkCard(
            title = "Science Project",
            subject = "Science",
            dueTime = "16:00",
            color = Color(0xFF64B5F6)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeworkListPreview() {
    SkedularAppTheme {
        HomeworkList()
    }
}
