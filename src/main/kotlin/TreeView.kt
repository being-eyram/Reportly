import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import util.Icons

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TreeView(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = contentColorFor(backgroundColor),
    isExpanded: Boolean,
    onExpandButtonClick: () -> Unit,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        color = backgroundColor,
        contentColor = contentColor,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .selectableGroup()
                .wrapContentSize()
                .animateContentSize(
                    animationSpec = tween()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TreeViewHeader(title, onExpandButtonClick)

            if (isExpanded) {
                content()
            }
        }
    }
}


@Composable
fun TreeViewItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = MaterialTheme.colors.primary,
    unselectedContentColor: Color = Color.Transparent
) {
    // Todo remember to make selecteed color the icon color.
    var color = remember { mutableStateOf(unselectedContentColor) }

    color.value = if (selected) selectedContentColor else unselectedContentColor

    Box(
        modifier
            .fillMaxWidth()
            .requiredHeightIn(32.dp, 32.dp)
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxSize()
                .background(
                    color = color.value,
                    shape = RoundedCornerShape(4.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (selected) {
                SelectedIndicator(Color.Yellow)
            }

            Spacer(Modifier.width(80.dp))

            Icon(
                painter = painterResource(Icons.Tag),
                contentDescription = null,
                tint = Color.Yellow
            )

            Spacer(Modifier.width(12.dp))

            if (label != null) label()

        }
    }
}

@Composable
fun SelectedIndicator(color: Color) {
    Surface(
        modifier = Modifier.size(4.dp, 16.dp),
        color = color,
        shape = RoundedCornerShape(50),
        content = {}
    )
}


@Composable
fun TreeViewHeader(title: String, onExpandButtonClick: () -> Unit) {
    Row(
        modifier = Modifier.requiredSize(
            width = Dp.Unspecified,
            height = 40.dp
        ).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Spacer(modifier = Modifier.width(24.dp))

        Icon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                role = Role.Button,
                onClick = onExpandButtonClick
            ),
            painter = painterResource(Icons.CaretDown),
            contentDescription = null,
            tint = Color.White
        )


        Spacer(modifier = Modifier.width(20.dp))

        Icon(
            painter = painterResource(Icons.Hash),
            contentDescription = null,
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            color = Color.White
        )
    }
}