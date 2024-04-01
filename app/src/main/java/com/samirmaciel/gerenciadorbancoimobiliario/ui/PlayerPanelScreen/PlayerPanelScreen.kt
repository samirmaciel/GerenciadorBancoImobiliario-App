package com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerPanelScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.R
import com.samirmaciel.gerenciadorbancoimobiliario.dto.Mock
import com.samirmaciel.gerenciadorbancoimobiliario.ui.TransactionsList
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.blue
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.dark_yellow
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.light_white

@Preview
@Composable
fun PlayerPanelScreen(){
    Column {
        Header()
        Content(modifier = Modifier
            .padding(30.dp))
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = blue)
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f), verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Painel",
                    style = SfProRoundedTypography.titleMedium.copy(color = Color.White)
                )
            }
            Text(
                text = "Seu Dinheiro",
                style = SfProRoundedTypography.labelSmall.copy(color = light_white)
            )
            Text(
                text = "$200.000",
                style = SfProRoundedTypography.titleLarge.copy(color = Color.White)
            )

        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = dark_yellow),
                shape = RoundedCornerShape(5.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = "Transferir para jogador"
                )
                Text(text = "Jogador", style = SfProRoundedTypography.titleSmall)
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = blue),
                shape = RoundedCornerShape(5.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = "Transferir para Banco"
                )
                Text(text = "Banco", style = SfProRoundedTypography.titleSmall)
            }
        }

        Text(
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
            text = "Ultimas transações do Jogo",
            style = SfProRoundedTypography.titleSmall
        )

        TransactionsList(moneyTransactionList = listOf(Mock.getMoneyTransaction()))
    }
}