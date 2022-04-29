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
import models.DailyReport
import models.Day
import models.WeeksReport
import util.Icons
import java.time.LocalTime

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(size = DpSize(1080.dp, 720.dp))
    ) {
        ReportlyTheme {
            Screen()
        }
    }
}


@Composable
@Preview
fun Screen() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        WeeksTab(treeViewData = treeViewData)

        ReportDetailsSection()

    }
}

@Composable
fun RowScope.WeeksTab(
    treeViewData: List<WeeksReport>
) {

    val mapofExpandedWeeklyReport = remember { mutableStateMapOf<Int, Boolean>() }
    mapofExpandedWeeklyReport.putAll(fakedata.treeViewData.associate { it.id to false })


    Column(
        Modifier.weight(0.22f)
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.surface)
    ) {

        Text(
            modifier = Modifier
                .paddingFromBaseline(
                    top = 29.dp,
                    bottom = 19.dp
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Weeks",
            color = Color.White,
        )

        var searchQuery by remember { mutableStateOf("") }

        SearchInputField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(30.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(4.dp)
                ),

            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
        )

        Spacer(Modifier.height(14.dp))

        Divider(Modifier.fillMaxWidth())

        val selectedColor = Color.White.copy(alpha = 0.07f)

        LazyColumn() {

            items(items = treeViewData) { item ->
                TreeView(
                    modifier = Modifier.wrapContentHeight(),
                    title = "Week${item.id}",
                    isExpanded = mapofExpandedWeeklyReport[item.id]!!,
                    onExpandButtonClick = { mapofExpandedWeeklyReport[item.id] = !mapofExpandedWeeklyReport[item.id]!! }
                ) {
                    TreeViewItem(
                        selected = false,
                        onClick = { },
                        label = { Text("Monday", color = Color.White) },
                        selectedContentColor = selectedColor,
                    )

                    TreeViewItem(
                        selected = false,
                        onClick = { },
                        label = { Text("Tuesday", color = Color.White) },
                        selectedContentColor = selectedColor,
                    )

                    TreeViewItem(
                        selected = false,
                        onClick = { },
                        label = { Text("Wednesday", color = Color.White) },
                        selectedContentColor = selectedColor,
                    )

                    TreeViewItem(
                        selected = false,
                        onClick = { },
                        label = { Text("Thursday", color = Color.White) },
                        selectedContentColor = selectedColor,
                    )
                    TreeViewItem(
                        selected = false,
                        onClick = { },
                        label = { Text("Friday", color = Color.White) },
                        selectedContentColor = selectedColor,
                    )
                }
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}

@Composable
fun RowScope.ReportDetailsSection() {
    Column(
        Modifier.weight(0.88f)
            .fillMaxHeight()
            .background(color = MaterialTheme.colors.background)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(Modifier.padding(start = 64.dp)) {
                Icon(
                    modifier = Modifier.padding(top = 28.dp),
                    painter = painterResource(Icons.Tag),
                    contentDescription = null,
                    tint = Color.Yellow
                )

                Spacer(Modifier.width(4.dp))

                Column(Modifier.wrapContentSize()) {
                    Text(
                        modifier = Modifier
                            .paddingFromBaseline(
                                top = 42.dp,
                                bottom = 4.dp
                            ),
                        text = "Monday",
                        color = Color.White,
                        fontSize = 16.sp
                    )

                    Text(
                        modifier = Modifier.paddingFromBaseline(
                            top = 12.dp,
                            bottom = 24.dp
                        ),
                        text = "Week 1",
                        color = Color.White.copy(alpha = 0.65f),
                        fontSize = 12.sp
                    )
                }
            }

            TextEditingButtons(Modifier.padding(end = 64.dp))
        }

        Divider(Modifier.fillMaxWidth())

        Spacer(Modifier.height(44.dp))

        val report = remember { mutableStateOf("") }
        TextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            value = report.value,
            onValueChange = { report.value = it },
            placeholder = { Text(text = "Write a report...") },
            colors = TextFieldDefaults.textFieldColors(
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.White.copy(alpha = 0.07f),
                textColor = Color.White,
            )
        )

        Spacer(Modifier.height(54.dp))

        Row(Modifier.fillMaxWidth().background(Color.Red).height(55.dp)) {}

        Spacer(Modifier.height(54.dp))
    }

}

@Composable
fun TextEditingButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        IconButton(
            modifier = Modifier.background(
                color = Color.White.copy(0.07f),
                shape = RoundedCornerShape(12.dp)
            ),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(Icons.Bold),
                contentDescription = null,
                tint = Color.White
            )
        }

        Spacer(Modifier.width(10.dp))

        IconButton(
            modifier = Modifier.background(
                color = Color.White.copy(0.07f),
                shape = RoundedCornerShape(12.dp)
            ),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(Icons.Italic),
                contentDescription = null,
                tint = Color.White
            )
        }

        Spacer(Modifier.width(10.dp))

        IconButton(
            modifier = Modifier.background(
                color = Color.White.copy(0.07f),
                shape = RoundedCornerShape(12.dp)
            ),
            onClick = {}
        ) {
            Icon(
                painter = painterResource(Icons.Underline),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun ReportlyIconButton(modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = true,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = 18.dp
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides LocalContentAlpha.current,
            content = content
        )
    }
}