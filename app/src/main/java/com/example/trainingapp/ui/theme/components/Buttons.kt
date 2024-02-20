package com.example.trainingapp.ui.theme.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.trainingapp.ui.theme.ExtraSmallPadding
import com.example.trainingapp.ui.theme.MediumPadding
import com.example.trainingapp.ui.theme.Shapes
import com.example.trainingapp.ui.theme.SmallPadding

@Composable
fun BigButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val sizeScale by animateFloatAsState(if (isPressed) 0.98f else 1f, label = "")
    Button(
        modifier = modifier
            .fillMaxWidth()
            .scale(sizeScale),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        interactionSource = interactionSource,
        colors = ButtonDefaults.elevatedButtonColors(),
        elevation = ButtonDefaults.elevatedButtonElevation(pressedElevation = 0.dp)
    ) {
        Text(text = text)
    }
}

