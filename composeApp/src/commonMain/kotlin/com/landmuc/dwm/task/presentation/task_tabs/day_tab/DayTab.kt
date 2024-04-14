package com.landmuc.dwm.task.presentation.task_tabs.day_tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.landmuc.dwm.task.domain.model.Task
import com.landmuc.dwm.task.presentation.components.TaskLazyColumn
import com.landmuc.dwm.task.presentation.components.taskCard
import dwm.composeapp.generated.resources.Res
import dwm.composeapp.generated.resources.day_tab
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
data class DayTab(
    val taskList: List<Task>
): Tab {
    @Composable
    override fun Content() {
        TaskLazyColumn(taskList = taskList)
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