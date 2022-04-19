package ru.zar1official.hackathon_final.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color = Color.White,
    shape: Shape = CircleShape,
    icon: Painter? = null,
    iconTint: Color? = null,
    contentDescription: String,
    contentPaddingValues: PaddingValues,
    borderStroke: BorderStroke? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        elevation = ButtonDefaults.elevation(
            5.dp,
            5.dp
        ),
        contentPadding = contentPaddingValues,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        border = borderStroke
    ) {
        if (icon != null)
            Icon(
                painter = icon,
                contentDescription = contentDescription,
                tint = iconTint ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
            )
    }
}