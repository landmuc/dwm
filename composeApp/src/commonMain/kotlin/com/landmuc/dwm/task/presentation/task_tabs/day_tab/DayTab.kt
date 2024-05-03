package com.landmuc.dwm.task.presentation.task_tabs.day_tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.presentation.components.TaskLazyColumn
import dwm.composeapp.generated.resources.Res
import dwm.composeapp.generated.resources.day_tab
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
data class DayTab(
    val taskList: List<Task>,
    val onCheckedChange: (Task) -> Unit,
    val deleteTask: (Task) -> Unit
): Tab {
    @Composable
    override fun Content() {
        TaskLazyColumn(
            taskList = taskList,
            onCheckedChange = onCheckedChange,
            deleteTask = deleteTask
            )
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.day_tab)
            //val icon = rememberVectorPainter(Icons.Sharp.Warning)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = null
                )
            }
        }
}