package com.samirmaciel.gerenciadorbancoimobiliario.controller

import android.util.Log
import com.samirmaciel.gerenciadorbancoimobiliario.bluetooth.AndroidBluetoothServerController
import com.samirmaciel.gerenciadorbancoimobiliario.bluetooth.ServerConnectionResult
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.ServerStatusManager
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class GameServerController(
    private val coroutineScope: CoroutineScope,
    val bluetoothServerController: AndroidBluetoothServerController
) : ServerController {

    val _activePlayers: MutableStateFlow<List<Player>> = MutableStateFlow(emptyList())
    val activePlayers get() = _activePlayers.asStateFlow()

    private val _serverStatusManager = MutableStateFlow(ServerStatusManager())
    val dataTransferResult get() = _serverStatusManager.asStateFlow()

    init {
        bluetoothServerController.errors.onEach { error ->
            _serverStatusManager.update {
                it.copy(
                    errorMessage = error
                )
            }
        }.launchIn(coroutineScope)
    }

    private var serverConnectionJob: Job? = null

    fun startServer() {
        serverConnectionJob = bluetoothServerController
            .startBluetoothServer()
            .listenDataTransferResult()

    }

    override suspend fun updateAllPlayers(message: Any?) {

    }

    override suspend fun updatePlayer(message: Any?) {

    }

    private fun Flow<ServerConnectionResult>.listenDataTransferResult(): Job {
        return onEach { result ->
            when (result) {

                is ServerConnectionResult.TransferSucceeded -> {
                    when(result.message){
                        is Player ->{
                            if(bluetoothServerController.devicesConnected.value.containsKey(result.message.bluetoothDevice?.address)){
                                addPlayerToActiveList(result.message)
                            }
                        }

                        else ->{
                            Log.d("X", "listenDataTransferResult: ")
                        }
                    }
                }

                is ServerConnectionResult.Error -> {
                    _serverStatusManager.update {
                        it.copy(
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
            .catch { throwable ->
                _serverStatusManager.update {
                    it.copy(
                        errorMessage = throwable.message
                    )
                }
            }.launchIn(coroutineScope)
    }

    private fun addPlayerToActiveList(player: Player) {
        _activePlayers.update {
            val newList = it
            if(!newList.contains(player)){
                newList + player
            }

            newList
        }
    }


}