package ru.zar1official.hackathon_final.presentation.screens.work.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.zar1official.hackathon_final.domain.models.MicroclimateType
import ru.zar1official.hackathon_final.presentation.components.CustomButton

@Composable
fun ButtonGroup(
    modifier: Modifier = Modifier,
    buttons: List<MicroclimateType>,
    onChange: (button: MicroclimateType) -> Unit,
    selectedButton: State<MicroclimateType>,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    contentPaddingValues: PaddingValues,
    borderStrokeColor: Color,
    borderStrokeWidth: Dp
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        modifier = modifier,
        verticalAlignment = verticalAlignment
    ) {
        buttons.forEach { button ->
            CustomButton(
                modifier = Modifier.size(100.dp),
                backgroundColor = Color.White,
                contentDescription = "",
                contentPaddingValues = contentPaddingValues,

                onClick = {
                    onChange(button)
                },
                borderStroke = if (selectedButton.value == button) BorderStroke(
                    borderStrokeWidth,
                    borderStrokeColor
                ) else null,
                icon = painterResource(id = button.icon),
                iconTint = Color.Black
            )
        }
    }
}
