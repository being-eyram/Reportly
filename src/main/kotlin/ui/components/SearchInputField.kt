package ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.unit.sp
import util.Icons

@Composable
fun SearchInputField(
    modifier: Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    cursorColor: Color = Color.White,
) {
    SearchInputLayout(
        modifier = modifier,
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        cursorBrush = SolidColor(cursorColor)
    )
}


@Composable
fun SearchInputLayout(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle.Default.copy(color = Color.White),
    cursorBrush: Brush,
) {


    BasicTextField(modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        singleLine = true,
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->

            Layout(
                modifier = Modifier.background(
                    color = Color.White.copy(0.10f),
                    shape = RoundedCornerShape(4.dp)
                ),
                content = {
                    Box(
                        modifier = Modifier
                            .sizeIn(
                                minWidth = 0.dp,
                                minHeight = 0.dp,
                                maxHeight = 30.dp,
                                maxWidth = 30.dp
                            )
                            .layoutId(LayoutId.searchIcon)
                    ) {
                        Icon(
                            painter = painterResource(Icons.Search),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .layoutId(LayoutId.searchField),
                        propagateMinConstraints = true
                    ) {
                        innerTextField()
                    }

                    Box(
                        modifier = Modifier.sizeIn(
                            minWidth = 0.dp,
                            minHeight = 0.dp,
                            maxHeight = 30.dp,
                            maxWidth = 30.dp
                        ).layoutId(LayoutId.clearIcon),
                    )

                    Box(modifier = Modifier.layoutId(LayoutId.placeholder)) {
                        AnimatedVisibility(
                            visible = value.isEmpty(),
                            enter = slideInHorizontally(initialOffsetX = { -it/8}, animationSpec = tween(100)) + fadeIn(animationSpec = tween(120)),
                            exit = slideOutHorizontally(targetOffsetX = { -it/8}, animationSpec = tween (100)) + fadeOut(animationSpec = tween(100))
                        ) {
                            Text(
                                text = "Search",
                                fontSize = 13.sp,
                                color = Color.White.copy(0.50f),
                                letterSpacing = 0.015.sp,
                            )
                        }
                    }

                }) { measureables, incomingConstraints ->

                var spaceOccupiedHorizontally = 0

                val constraints = incomingConstraints.copy(minWidth = 0, minHeight = 0)
                val searchIconPlaceable = measureables.find { it.layoutId == LayoutId.searchIcon }
                    ?.measure(constraints)
                spaceOccupiedHorizontally += widthOrZero(searchIconPlaceable)

                val clearIconConstraints = constraints.offset(horizontal = -spaceOccupiedHorizontally)
                val clearIconPlaceable = measureables
                    .find { it.layoutId == LayoutId.clearIcon }
                    ?.measure(clearIconConstraints)
                spaceOccupiedHorizontally += widthOrZero(clearIconPlaceable)


                val searchFieldConstraints = constraints.offset(horizontal = -spaceOccupiedHorizontally)
                val searchFieldPlaceable = measureables
                    .find { it.layoutId == LayoutId.searchField }
                    ?.measure(searchFieldConstraints)

                val placeholderPlaceable =
                    measureables.find { it.layoutId == LayoutId.placeholder }?.measure(searchFieldConstraints)


                layout(
                    incomingConstraints.maxWidth, incomingConstraints.maxHeight
                ) {

                    searchIconPlaceable?.placeRelative(
                        0, Alignment.CenterVertically.align(
                            searchIconPlaceable.height, incomingConstraints.maxHeight
                        )
                    )

                    searchFieldPlaceable?.placeRelative(
                        searchIconPlaceable!!.width, Alignment.CenterVertically.align(
                            searchFieldPlaceable.height, incomingConstraints.maxHeight
                        )
                    )


                    placeholderPlaceable?.placeRelative(
                        searchIconPlaceable!!.width, Alignment.CenterVertically.align(
                            searchFieldPlaceable!!.height, incomingConstraints.maxHeight
                        )
                    )

                    clearIconPlaceable?.placeRelative(
                        incomingConstraints.maxWidth - searchIconPlaceable!!.width,
                        Alignment.CenterVertically.align(
                            clearIconPlaceable.height, incomingConstraints.maxHeight
                        )
                    )
                }
            }
        })
}

internal fun widthOrZero(placeable: Placeable?) = placeable?.width ?: 0

object LayoutId {
    const val searchIcon = "SEARCH_ICON"
    const val clearIcon = "CLEAR_ICON"
    const val placeholder = "PLACEHOLDER"
    const val searchField = "SEARCH_FIELD"
}


