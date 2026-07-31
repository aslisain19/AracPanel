package com.example.myapplication.presentation.machinedetail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.ConnectionStatus
import com.example.myapplication.ui.theme.AppTheme

@Composable
fun StatusBadge(
    status: ConnectionStatus
) {

    val backgroundColor =
        if (status == ConnectionStatus.ONLINE)
            Color(0xFFDCFCE7)
        else
            Color(0xFFFEE2E2)

    val textColor =
        if (status == ConnectionStatus.ONLINE)
            Color(0xFF15803D)
        else
            Color(0xFFB91C1C)

    val text =
        if (status == ConnectionStatus.ONLINE)
            AppTheme.strings.onlineStatusLabel
        else
            AppTheme.strings.offlineStatusLabel

    Box(
        modifier = Modifier
            .background(
                backgroundColor,
                RoundedCornerShape(50)
            )
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                "●",
                color = textColor
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}