package com.landmuc.dwm.task.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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
   Box(
       modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
   ) {
       Box(
           modifier = Modifier.align(Alignment.BottomEnd),
           contentAlignment = Alignment.Center
       ) {
           FloatingActionButton(
               onClick = onExpand,
               shape = MaterialTheme.shapes.small.copy(CornerSize(25)),
               backgroundColor = MaterialTheme.colors.primary
           ) {
               Icon(Icons.Filled.Add, "Floating action button.")
           }
       }
       AnimatedVisibility(visible = isExpanded) {
           Column() {
               Text("Task:")
               TextField(
                   value = taskTitle,
                   onValueChange = onTaskTitleChange
               )
               Text("Further Information")
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