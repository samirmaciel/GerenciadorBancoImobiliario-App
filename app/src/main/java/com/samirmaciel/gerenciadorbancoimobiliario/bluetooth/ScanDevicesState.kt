package com.sm.gamecolor.bluetooth

sealed class ScanDevicesState {

    data object Scanning: ScanDevicesState()
    data object ScanNotFoundDevices: ScanDevicesState()
    data object  FoundedDevices: ScanDevicesState()

}