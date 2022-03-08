package org.devcon.okeys

import kotlinx.coroutines.delay
import org.kethereum.crypto.createEthereumKeyPair
import org.kethereum.crypto.toAddress
import org.kethereum.crypto.toECKeyPair
import org.kethereum.eip155.signViaEIP155
import org.kethereum.extensions.transactions.encodeRLP
import org.kethereum.keystore.api.FileKeyStore
import org.kethereum.model.ChainId
import org.kethereum.model.createTransactionWithDefaults
import org.kethereum.rpc.min3.GOERLI_BOOTNODES
import org.kethereum.rpc.min3.getMin3RPC
import org.komputing.khex.extensions.toHexString
import java.io.File
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO
import kotlin.system.exitProcess

val GOERLI_GAS_PRICE = BigInteger("1000000000")

fun setupKeyStore() : FileKeyStore {
    val defaultPretixKeys = "pretixkeys"
    println("Please enter the keystore path path - press enter to use '$defaultPretixKeys' in the current directory")
    val keyPathInput = readLine()

    val keyStorePath = File(if (keyPathInput?.isNotBlank() == true) keyPathInput else defaultPretixKeys)
    println("using " + keyStorePath.absoluteFile)

    if (!keyStorePath.exists()) {
        println("keystore path does not exist")
        exitProcess(1)
    }

    return FileKeyStore(keyStorePath)
}
suspend fun main() {

    println("V4.2")

    while (true) {
        usage()
        val command = readLine()
        when (command?.lowercase()) {
            "1" -> {
                val keyStore = setupKeyStore()
                val txOutFile = File("transactions.otx").apply {
                    createNewFile()
                }
                println("Please enter the password")
                var pw = System.console()?.readPassword() ?.let { String(it) }

                if (pw == null) {
                    println("Warning! Could not get password without echo (you might run in a terminal that does not support it like the one in the idea IDE")
                    println("It will be echoed - watch your back!")
                    pw = readLine()
                }

                println("starting to check " + keyStore.getAddresses().size + " keys")

                keyStore.getAddresses().forEach {
                    val keyForAddress = keyStore.getKeyForAddress(it, pw!!)
                    val privateKey = keyForAddress?.privateKey
                    if (privateKey?.toECKeyPair()?.toAddress() == it) {
                        println("$it OK")
                    } else {
                        println("Could not verify address $it")
                        exitProcess(1)
                    }

                    val tx = createTransactionWithDefaults(
                        from = keyForAddress.toAddress(),
                        value = BigInteger.valueOf(42L),
                        to = it,
                        nonce = BigInteger.ZERO,
                        gasPrice = GOERLI_GAS_PRICE
                    )
                    val signature = tx.signViaEIP155(keyForAddress, ChainId(5))

                    txOutFile.appendText(keyForAddress.toAddress().hex + ";" + tx.encodeRLP(signature).toHexString() + "\n")
                }
                println("written transactions file to: " + txOutFile.absoluteFile)
            }

            "2" -> {
                val keyStore = setupKeyStore()
                val addressTextFile = File("addresses.txt").apply {
                    createNewFile()
                }
                addressTextFile.writer().use { writer ->
                    keyStore.getAddresses().forEach {
                        writer.write(it.toString() + "\n")
                    }
                }

                println("written: " + addressTextFile.absoluteFile)
            }

            "3" -> {
                val key = createEthereumKeyPair()
                println("Please fund " + key.toAddress())

                val rpc = getMin3RPC(GOERLI_BOOTNODES)

                var currentBalance = ZERO
                while (currentBalance < ONE ) {
                    currentBalance = rpc.getBalance(key.toAddress())?: ZERO
                    println("balance too low")
                    delay(1000)

                }
                TODO()
            }

            "4" -> {
                TODO()
            }
            "x" -> exitProcess(0)
        }
    }
}

private fun usage() {
    println("Please select the action to perform: ")
    println("1 -> verify keys and generate test-tx (offline)")
    println("2 -> generate addresses.txt (offline)")
    println("3 -> fund addresses with GOETH (online)")
    println("4 -> relay transactions (online)")
    println("X -> eXit")
}