package org.komputing.konsolidator

import org.kethereum.crypto.toAddress
import org.kethereum.crypto.toECKeyPair
import org.kethereum.eip155.signViaEIP155
import org.kethereum.extensions.transactions.encodeRLP
import org.kethereum.model.ChainId
import org.kethereum.model.PrivateKey
import org.kethereum.model.createTransactionWithDefaults
import java.math.BigInteger
import java.math.BigInteger.ONE

fun main() {

    val toECKeyPair =
        PrivateKey(BigInteger("82423795448139007154546773831255565936642816189860016900283307020970429986513")).toECKeyPair()
    println(toECKeyPair.toAddress())

    /*keyStore.getAddresses().forEach {
        val tx = createTransactionWithDefaults(from = toECKeyPair.toAddress(), value = ONE, to = it)
        val signature = tx.signViaEIP155(toECKeyPair, ChainId(ONE))
        println(""+tx.encodeRLP(signature).toString())
    }*/
    /*
    keyStore.getAddresses().forEach {
        println(keyStore.getKeyForAddress(it, password)?.privateKey)
    }

     */
}