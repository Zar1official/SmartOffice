package ru.zar1official.hackathon_final.presentation.screens.chill.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import ru.zar1official.hackathon_final.domain.models.MassageMode


@Composable
fun DropDownMenu(
    suggestions: List<MassageMode>,
    modifier: Modifier = Modifier,
    selected: State<MassageMode>,
    onChangeSelected: (label: MassageMode) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column(modifier) {
        OutlinedTextField(
            value = stringResource(id = selected.value.name),
            textStyle = MaterialTheme.typography.h6,
            readOnly = true,
            onValueChange = { },
            modifier = Modifier
                .width(200.dp)
                .onGloballyPositioned { c ->
                    textFieldSize = c.size.toSize()
                },
            trailingIcon = {
                Icon(
                    icon, "",
                    Modifier.clickable { expanded = !expanded }
                )
            }
        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                val string = stringResource(id = label.name)
                DropdownMenuItem(onClick = {
                    onChangeSelected(label)
                    expanded = false
                }) {
                    Text(
                        text = string,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }
            }
        }
    }

}