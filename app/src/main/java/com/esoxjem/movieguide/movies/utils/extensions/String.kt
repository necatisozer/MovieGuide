package com.esoxjem.movieguide.movies.utils.extensions

import com.esoxjem.movieguide.movies.utils.DES

fun String.encrypt(key: String): String {
    val ks = DES.getSubKeys(key)
    var m = this
    val r = m.length % 16 // check if multiple of 16 hex digits
    val rem = 8 - r / 2
    val remStr = "%02X".format(rem)
    for (i in 1..rem) {
        m += remStr
    }
    assert(m.length % 16 == 0)

    val sb = StringBuilder()
    for (i in 0 until m.length / 16) {
        val j = i * 16
        val enc = DES.processMessage(m.substring(j, j + 16), ks)
        sb.append(enc)
    }
    return sb.toString()
}

fun String.decrypt(key: String): String {
    val ks = DES.getSubKeys(key)
    // reverse the subkeys
    for (i in 1..8) {
        val temp = ks[i]
        ks[i] = ks[17 - i]
        ks[17 - i] = temp
    }
    val sb = StringBuilder()
    for (i in 0 until length / 16) {
        val j = i * 16
        val dec = DES.processMessage(substring(j, j + 16), ks)
        sb.append(dec)
    }
    //remove the padding
    val padByte = sb[sb.length - 1] - '0'
    return sb.substring(0, sb.length - 2 * padByte)
}