package com.samirmaciel.gerenciadorbancoimobiliario.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samirmaciel.gerenciadorbancoimobiliario.R

val SfProRounded = FontFamily(
    Font(R.font.sf_pro_rounded_bold, FontWeight.Bold),
    Font(R.font.sf_pro_rounded_medium, FontWeight.Medium),
    Font(R.font.sf_pro_rounded_semibold, FontWeight.SemiBold),
)

val SfProRoundedTypography = Typography(
    titleMedium = TextStyle(
        fontFamily = SfProRounded,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = SfProRounded,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SfProRounded,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = SfProRounded,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SfProRounded,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SfProRounded,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
)