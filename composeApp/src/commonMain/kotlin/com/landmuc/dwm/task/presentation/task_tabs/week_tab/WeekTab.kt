package com.landmuc.dwm.task.presentation.task_tabs.week_tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

// object -> doesn't hold arguments and is not going to be reused
object WeekTab: Tab {
    @Composable
    override fun Content() {
      Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
      ) {
          Text("Week Tab")
      }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Res.string.week_tab"
            val icon = rememberVectorPainter(Icons.Sharp.AccountBox)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }
}