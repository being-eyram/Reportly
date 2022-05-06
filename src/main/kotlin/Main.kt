// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import fakedata.treeViewData
import models.WeeksReport
import models.WorkDay
import org.koin.core.context.startKoin
import util.Icons

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
@Preview
fun Screen(viewmodel: ReportlyViewmodel) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        WeeksTab(
            treeViewData = viewmodel.weeksReport.collectAsState().value,
            onAddButtonClick = {
                viewmodel.commitWeeksReport()
            }
        )

        ReportDetailsSection()
        println( viewmodel.weeksReport.collectAsState().value)
    }
}