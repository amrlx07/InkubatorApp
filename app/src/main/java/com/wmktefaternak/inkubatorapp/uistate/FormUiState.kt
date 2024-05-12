package com.wmktefaternak.inkubatorapp.uistate

data class FormDate(
    val tanggal: String = "",
    val tanggalError: String? = null,
)

data class Form1(
    val jumlah: String = "",
    val jumlahError: String? = null
)

data class Form2(
    val vertil: String = " ",
    val vertilError: String? = null,
    val invertil: String = " ",
    val invertilError: String? = null
)

data class Form3(
    val menetas: String = " ",
    val menetasError: String? = null,
    val gagalMenetas: String = " ",
    val gagalMenetasError: String? = null
)
