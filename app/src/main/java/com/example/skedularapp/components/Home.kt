package com.example.skedularapp.components

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skedularapp.utilities.getDayOfDate
import com.example.skedularapp.utilities.getMondayOfWeek
import com.example.skedularapp.utilities.getNextWeek
import com.example.skedularapp.utilities.isWeekend
import com.example.skedularapp.utilities.getDayOfWeekNumber
import com.example.skedularapp.utilities.getNthDayAfter
import java.sql.Date


@Composable
fun HomeworkCardText(
    text: String,
    alpha: Float = 1f,
    style: androidx.compose.ui.text.TextStyle
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = alpha),
        fontWeight = FontWeight.ExtraBold,
        style = style
    )
}

@Composable
fun HomeworkCard(
    title: String,
    subject: String,
    dueTime: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f),
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .background(color, CircleShape)
                )
                Column {
                    HomeworkCardText(text = title, alpha = 0.8f, style = MaterialTheme.typography.titleMedium)
                    HomeworkCardText(text = "$subject • $dueTime", alpha = 0.6f, style = MaterialTheme.typography.bodySmall)
                }
            }
            Icon(
                modifier = Modifier.padding(end = 16.dp),
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
fun DateIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.offset(y = (-1).dp),
        imageVector = Icons.Filled.DateRange,
        contentDescription = "Date",
        tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
    )
}

@Composable
fun SegmentedButtonWeek(modifier: Modifier = Modifier, date: Date) {
    val dayOfWeekNumber = getDayOfWeekNumber(date)
    var selectedIndex by remember { mutableIntStateOf(dayOfWeekNumber - 1) }

    val week = if (!isWeekend(date)) date else getNextWeek(date)
    val monday = getMondayOfWeek(week)

    val options = listOf("Lun", "Mar", "Mie", "Jue", "Vie")

    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, label ->

            val optionDate = getNthDayAfter(monday, index)
            val dayNumber = getDayOfDate(optionDate)

            val selected = index == selectedIndex

            SegmentedButton(
                modifier = modifier
                    .padding(horizontal = 5.dp)
                    .height(33.dp),

                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(5.dp),

                onClick = { selectedIndex = index },
                selected = selected,
                icon = {
                    SegmentedButtonDefaults.Icon(
                        active = selected,
                        activeContent = { DateIcon(modifier = modifier.padding(start = 8.dp)) },
                    )
                },

                label = {
                    Text(
                        modifier = Modifier.padding(end = if (selected) 2.dp else 0.dp),
                        text = "$label $dayNumber",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
                    )
                },

                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.75f),
                    activeContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        }
    }
}