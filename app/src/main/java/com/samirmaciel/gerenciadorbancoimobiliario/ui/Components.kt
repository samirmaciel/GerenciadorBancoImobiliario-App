package com.samirmaciel.gerenciadorbancoimobiliario.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.R
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.dark_yellow
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.light_white
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.light_black


@Composable
fun PlayerCard(player: Player, enabledSendMoney: Boolean = false, onSendMoney: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(light_white, CircleShape), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${player.name[0]}",
                style = SfProRoundedTypography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.height(20.dp),
                text = player.name,
                style = SfProRoundedTypography.titleSmall.copy(color = Color.Black)
            )
            Text(
                modifier = Modifier.height(20.dp),
                text = player.type,
                style = SfProRoundedTypography.labelSmall.copy(color = light_black)
            )
        }
        IconButton(modifier = Modifier
            .background(dark_yellow, RoundedCornerShape(5.dp))
            .height(40.dp)
            .width(40.dp), colors = IconButtonDefaults.iconButtonColors(
            contentColor = Color.Black
        ), enabled = enabledSendMoney,
            onClick = { onSendMoney(player.id) }) {
            Icon(
                modifier = Modifier.padding(0.dp),
                painter = painterResource(id = R.drawable.send_icon),
                contentDescription = "Send money"
            )
        }
    }
}

@Composable
fun AnimationLottie(modifier: Modifier = Modifier, rawAnimationResource: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawAnimationResource))
    val progress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}

@Composable
fun CustomSearchTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier.padding(0.dp), colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
        ), label = {
            Text(
                text = hint,
                style = SfProRoundedTypography.titleMedium.copy(
                    color = Color.Gray,
                    fontSize = 15.sp
                ),
            )
        }, value = value,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(15.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }
    )
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
        ), label = {
            Text(
                text = hint,
                style = SfProRoundedTypography.titleMedium.copy(color = Color.Gray, fontSize = 15.sp),
            )
        }, value = value,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        shape = RoundedCornerShape(15.dp)
    )
}

@Composable
fun CustomTopBar(title: String, navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), contentAlignment = Alignment.BottomCenter
    ) {

        Divider(
            color = Color.Gray, modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
        )

        Row(modifier = Modifier.matchParentSize(), horizontalArrangement = Arrangement.Start) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back")
            }
        }

        Row(
            modifier = Modifier.matchParentSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.Black,
                style = SfProRoundedTypography.titleMedium
            )
        }
    }
}