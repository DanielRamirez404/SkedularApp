package com.example.skedularapp.components

import com.example.skedularapp.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skedularapp.utilities.getDayOfDate
import com.example.skedularapp.utilities.getMondayOfWeek
import com.example.skedularapp.utilities.getNextWeek
import com.example.skedularapp.utilities.isWeekend
import com.example.skedularapp.utilities.getDayOfWeekNumber
import com.example.skedularapp.utilities.getNthDayAfter
import java.sql.Date


class BottomRoundedShape(private val roundedLength: Dp) : Shape {
    val highMultiplier = 5f
    val lowMultiplier = 1.25f

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val roundedLengthPx = with(density) { roundedLength.toPx() }
        val path = Path().apply {
            reset()
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height - roundedLengthPx)
            arcTo(
                rect = Rect(
                    left = size.width - highMultiplier * roundedLengthPx,
                    top = size.height - highMultiplier * roundedLengthPx,
                    right = size.width + lowMultiplier * roundedLengthPx,
                    bottom = size.height
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            arcTo(
                rect = Rect(
                    left = -lowMultiplier * roundedLengthPx,
                    top = size.height - highMultiplier * roundedLengthPx,
                    right = highMultiplier * roundedLengthPx,
                    bottom = size.height
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )
            lineTo(0f, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun Header(
    username: String = "User",
    eventsNumber: Int = 0,
    onSettingsClick: () -> Unit = {},
    onAddEventClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(325.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = BottomRoundedShape(roundedLength = 80.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier.size(40.dp),
                        onClick = onSettingsClick,
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                    Text(
                        text = "Welcome, $username!",
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                IconButton(
                    modifier = Modifier.size(40.dp),
                    onClick = onAddEventClick,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
            ) {

                val homeworkText = when (eventsNumber) {
                    0 -> "No events\ndue!"
                    1 -> "1 event\ndue"
                    else -> "$eventsNumber events\ndue"
                }

                Text(
                    text = homeworkText,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )

                Image(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .size(175.dp),
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.spring_desktop_calendar_variant),
                    contentDescription = "Calendar Image",
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkCard(
    title: String,
    subject: String,
    dueTime: String,
    color: Color,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
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
            IconButton(
                modifier = Modifier.padding(end = 16.dp),
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                )
            ){
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                )
            }
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
fun SegmentedButtonWeek(modifier: Modifier = Modifier, date: Date, onClick: (Int) -> Unit = {}) {
    val dayOfWeekNumber = getDayOfWeekNumber(date)
    var selectedIndex by remember { mutableIntStateOf(dayOfWeekNumber - 1) }

    val week = if (!isWeekend(date)) date else getNextWeek(date)
    val monday = getMondayOfWeek(week)

    val options = listOf("Lun", "Mar", "Mie", "Jue", "Vie")

    val outlineColor = MaterialTheme.colorScheme.outlineVariant

    SingleChoiceSegmentedButtonRow(
        modifier = modifier
            .padding(bottom = 25.dp)
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = 1.5f * size.height
                val x = 75f
                drawLine(
                    color = outlineColor,
                    start = Offset(x, y),
                    end = Offset(size.width - x, y),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        options.forEachIndexed { index, label ->

            val optionDate = getNthDayAfter(monday, index)
            val dayNumber = getDayOfDate(optionDate)

            val selected = index == selectedIndex

            SegmentedButton(
                modifier = modifier
                    .padding(horizontal = 5.dp)
                    .height(33.dp)
                    .width(70.dp),

                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                shape = RoundedCornerShape(5.dp),

                onClick = {
                    selectedIndex = index
                    onClick(index + 1)
                },

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
