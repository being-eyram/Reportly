import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import util.Icons

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