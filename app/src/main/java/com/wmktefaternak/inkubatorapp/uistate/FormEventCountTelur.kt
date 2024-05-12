package com.wmktefaternak.inkubatorapp.uistate

sealed class FormEventCountTelur {

    data class CountChanged(val jumlah: String) : FormEventCountTelur()

    object Submit: FormEventCountTelur()
}

sealed class FormEventVertilInvertilTelur {

    data class InvertilChanged(val invertil: String) : FormEventVertilInvertilTelur()
    data class VertilChanged(val vertil: String) : FormEventVertilInvertilTelur()

    object Submit: FormEventVertilInvertilTelur()
}

sealed class FormEventMenetasTelur {

    data class MenetasChanged(val menetas: String) : FormEventMenetasTelur()
    data class GagalMenetasChanged(val gagalMenetas: String) : FormEventMenetasTelur()

    object Submit: FormEventMenetasTelur()
}
sealed class FormEventDate {
    data class TanggalChanged(val tanggal:String) : FormEventDate()
    object Submit: FormEventDate()
}