package com.landmuc.dwm.task.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.landmuc.dwm.task.domain.model.Task

@Composable
fun ExpandingFab(
    isExpanded: Boolean,
    onExpand: () -> Unit,
    addTask: () -> Unit,
    taskTitle: String,
    onTaskTitleChange: (String) -> Unit,
    taskFurtherInformation: String,
    onTaskFurtherInformationChange: (String) -> Unit
) {
   Box() {
       Box(
           modifier = Modifier.align(Alignment.BottomEnd),
           contentAlignment = Alignment.Center
       ) {
           FloatingActionButton( onClick = onExpand) {
               Icon(Icons.Filled.Add, "Floating action button.")
           }
       }
       AnimatedVisibility(visible = isExpanded) {
           Column(
               modifier = Modifier.padding(end = 50.dp, bottom = 50.dp)
           ) {
               TextField(
                   value = taskTitle,
                   onValueChange = onTaskTitleChange
               )
               TextField(
                   value = taskFurtherInformation,
                   onValueChange = onTaskFurtherInformationChange
               )
               Button(
                   onClick = addTask
               ) {
                   Text("Add task")
               }
           }
       }
   }
}