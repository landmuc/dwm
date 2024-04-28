package com.landmuc.dwm.task.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.landmuc.dwm.core.presentation.gradientSurface
import com.landmuc.dwm.core.theme.AccentViolet

@Composable
fun taskCard(
    taskTitle: String,
    taskFurtherInformation: String?,
    dateDue: String?,
    isDone: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp ),
        elevation = 5.dp
    ) {
        Column() {
            Box() {
                Text(
                    text = taskTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (isDone) TextDecoration.LineThrough else TextDecoration.None
                )
            }
            Box() {
                Column {
                    Text(
                        text = taskFurtherInformation ?: "No further Information added",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = dateDue ?: "No due time added",
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}