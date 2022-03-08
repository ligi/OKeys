package org.komputing.konsolidator

import org.devcon.okeys.GAS_PRICE
import org.kethereum.DEFAULT_GAS_LIMIT
import org.kethereum.crypto.toAddress
import org.kethereum.crypto.toECKeyPair
import org.kethereum.eip155.signViaEIP155
import org.kethereum.extensions.transactions.encodeRLP
import org.kethereum.model.ChainId
import org.kethereum.model.PrivateKey
import org.kethereum.model.createTransactionWithDefaults
import org.kethereum.rpc.EthereumRPCException
import org.kethereum.rpc.min3.GOERLI_BOOTNODES
import org.kethereum.rpc.min3.getMin3RPC
import org.komputing.khex.extensions.toHexString
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.valueOf

suspend fun main() {
/*
    val min3 = getMin3RPC(GOERLI_BOOTNODES)
    val toECKeyPair =
        PrivateKey(BigInteger("82423795448139007154546773831255565936642816189860016900283307020970429986513")).toECKeyPair()
    val address = toECKeyPair.toAddress()
    println(address)

    var nonce = min3.getTransactionCount(address.toString())
    while (nonce == null) {
        nonce = min3.getTransactionCount(address.toString())
    }



    keyStore.getAddresses().forEach { it ->
        val tx = createTransactionWithDefaults(
            from = address,
            value = DEFAULT_GAS_LIMIT * GAS_PRICE + ONE,
            gasPrice = GAS_PRICE,
            to = it,
            nonce = nonce
        )
        val signature = tx.signViaEIP155(toECKeyPair, ChainId(valueOf(5)))

        nonce = nonce!! + ONE
        while (min3.getTransactionCount(address.toString()) != nonce) {
            try {
                min3.sendRawTransaction(tx.encodeRLP(signature).toHexString())?.let {
                    println("relaying tx with hash " + it + " at " + System.currentTimeMillis() / 1000)
                }
            } catch (e: EthereumRPCException) {
            }

            Thread.sleep(420)
        }
    }
  */
    /*
    keyStore.getAddresses().forEach {
        println(keyStore.getKeyForAddress(it, password)?.privateKey)
    }

     */
}