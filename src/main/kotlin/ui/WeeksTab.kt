import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import models.WeeksReport
import models.WorkDay
import ui.components.SearchInputField
import util.Icons

@Composable
@Preview
fun RowScope.WeeksTab(treeViewData: List<WeeksReport>, onAddButtonClick : () -> Unit) {

    val mapofExpandedWeeklyReport = remember { mutableStateMapOf<Int, Boolean>() }
    mapofExpandedWeeklyReport.putAll(fakedata.treeViewData.associate { it.id to false })


    Column(
        Modifier.weight(0.22f)
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.surface),
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

        LazyColumn(modifier = Modifier.weight(0.7f)) {

            items(items = treeViewData) { item ->
                TreeView(
                    modifier = Modifier.wrapContentHeight(),
                    title = "Week${item.id}",
                    isExpanded = mapofExpandedWeeklyReport[item.id]!!,
                    onExpandButtonClick = { mapofExpandedWeeklyReport[item.id] = !mapofExpandedWeeklyReport[item.id]!! }
                ) {
                    WorkDay.values().forEach {
                        TreeViewItem(
                            selected = false,
                            onClick = { },
                            label = { Text(it.name, color = Color.White) },
                            selectedContentColor = selectedColor,
                        )
                    }
                }
            }
        }

        Divider(Modifier.fillMaxWidth())

        AddNewButton(onClick = onAddButtonClick)

    }
}


@Composable
fun AddNewButton(modifier : Modifier = Modifier,onClick: () -> Unit) {
    Row(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                enabled = true,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    radius = Dp.Unspecified
                )

            ),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Spacer(Modifier.width(24.dp))

        Icon(
            painter = painterResource(Icons.Add),
            contentDescription = null,
            tint = Color.White
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = "Add New Week",
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
    }
}