package com.samirmaciel.gerenciadorbancoimobiliario.ui

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import androidx.annotation.ColorInt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.rotate
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.MoneyTransaction
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.blue
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.light_black


@Composable
fun PlayerCard(player: Player, enabledSendMoney: Boolean = false, onSendMoney: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), verticalAlignment = Alignment.CenterVertically
    ) {

        PlayerImage(playerInitial = player.name[0], playerColor = player.color)

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

@SuppressLint("MissingPermission")
@Composable
fun BluetoothConnectionList(
    deviceList: List<BluetoothDevice>,
    onClick: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.Top
    ) {
       items(deviceList) {device ->
           Row {
               Text(
                   text = device.name,
                   modifier = Modifier
                       .clickable { onClick(device) }
                       .padding(16.dp)
               )
           }
       }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun DeviceConnectionCard(device: BluetoothDevice, onClickConnection: (BluetoothDevice) -> Unit){
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(modifier = Modifier.weight(1f), text = device.name, style = SfProRoundedTypography.titleMedium.copy(color = Color.Black))
        IconButton(modifier = Modifier
            .background(blue, RoundedCornerShape(5.dp))
            .height(40.dp)
            .width(40.dp), colors = IconButtonDefaults.iconButtonColors(
            contentColor = Color.Black
        ), onClick = { onClickConnection(device) }) {
            Icon(
                modifier = Modifier
                    .padding(0.dp)
                    .rotate(90f),
                painter = painterResource(id = R.drawable.send_icon),
                contentDescription = "Connect bluetooth"
            )
        }
    }
}

@Composable
fun PlayerImage(modifier: Modifier = Modifier, size: Dp = 40.dp, playerInitial: Char, playerColor: Color = light_white){

    Box(modifier = modifier){
        Box(
            modifier = Modifier
                .size(size)
                .background(playerColor, CircleShape), contentAlignment = Alignment.Center
        ) {
            Text(
                text = playerInitial.toString(),
                style = SfProRoundedTypography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun TransactionsList(moneyTransactionList: List<MoneyTransaction>) {
    LazyColumn {
        items(moneyTransactionList) { moneyTransaction ->
            MoneyTransactionCard(moneyTransaction = moneyTransaction)
        }
    }
}

@Composable
fun MoneyTransactionCard(moneyTransaction: MoneyTransaction) {
    val sender = moneyTransaction.playerSender
    val receiver = moneyTransaction.playerReceiver

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            PlayerImage(
                modifier = Modifier.padding(end = 10.dp),
                size = 30.dp,
                playerInitial = sender.name[0],
                playerColor = sender.color
            )
            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = sender.name,
                style = SfProRoundedTypography.titleSmall
            )
            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .rotate(90f),
                painter = painterResource(id = R.drawable.send_icon),
                contentDescription = "Send Icon"
            )
            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = receiver.name,
                style = SfProRoundedTypography.titleSmall
            )
            PlayerImage(
                playerInitial = receiver.name[0],
                size = 30.dp,
                playerColor = receiver.color
            )
        }

        Text(
            modifier = Modifier,
            text = moneyTransaction.value.toString(),
            style = SfProRoundedTypography.labelMedium
        )
    }
}