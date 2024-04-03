package com.sm.gamecolor.bluetooth

import android.bluetooth.BluetoothSocket
import android.util.Log
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.ClassCastException

class BluetoothDataTransferService(
    private val socket: BluetoothSocket
) {
    fun listenForIncomingMessages(): Flow<Any> {
        return flow {
            if(!socket.isConnected) {
                return@flow
            }

            while(true) {
                try {
                    val objectInputStream = ObjectInputStream(socket.inputStream)
                    val result = objectInputStream.readObject()

                    if(result is Player){
                        emit(
                            result
                        )
                    }
                } catch(e: IOException) {
                    Log.e(TAG, "listenForIncomingMessages: ${e.message} ")
                    throw TransferFailedException()
                } catch (e: ClassNotFoundException){
                    Log.e(TAG, "listenForIncomingMessages: ${e.message} ")
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun sendMessage(message: Any): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val outputStream = socket.outputStream
                val objectOutputStream = ObjectOutputStream(outputStream)
                objectOutputStream.writeObject(message)
                objectOutputStream.flush()

            } catch(e: IOException) {
                e.printStackTrace()
                return@withContext false
            }

            true
        }
    }

    companion object {
        const val TAG = "BluetoothDataTransferService"
    }
}