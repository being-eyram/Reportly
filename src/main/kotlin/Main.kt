// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.koin.core.context.startKoin

fun main() = application {

    val app = startKoin {
        modules(databaseModule, repositoryModule, viewmodelModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(size = DpSize(1080.dp, 720.dp))
    ) {
        ReportlyTheme {
            Screen(app.koin.get())
        }
    }
}


@Composable
fun Screen(viewmodel: ReportlyViewmodel) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val uiState = viewmodel.uiState.collectAsState().value

        ReportsTab(
            treeViewData = uiState.weeksReport,
            onAddButtonClick = { viewmodel.commitWeeksReport() },
            onSelect = viewmodel::onSelect,
            selectedReport = uiState.selectedReport
        )

        ComposeReportSection()
    }
}