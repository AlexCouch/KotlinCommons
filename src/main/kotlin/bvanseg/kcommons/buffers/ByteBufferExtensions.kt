package bvanseg.kcommons.buffers

import bvanseg.kcommons.projects.Version
import bvanseg.kcommons.ubjson.UBJ
import com.devsmart.ubjson.UBObject
import com.devsmart.ubjson.UBReader
import org.joml.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.util.*
import java.util.zip.DeflaterOutputStream
import java.util.zip.InflaterInputStream

fun ByteBuffer.getBytes(): ByteArray = getBytes(this.short.toInt())

fun ByteBuffer.getBytes(num: Int): ByteArray {
    val bytes = ByteArray(num)
    if(this.remaining() > 0)
        this.get(bytes)
    return bytes
}

fun ByteBuffer.getUUID(): UUID = UUID(long, long)

fun ByteBuffer.getString(): String = String(this.getBytes())

fun ByteBuffer.getVersion(): Version = Version(int, int, int)

fun ByteBuffer.getBoolean(): Boolean  = get().toInt() == 1

/** JOML Helper Functions **/
fun ByteBuffer.getVector3i(): Vector3i = Vector3i(this.int, this.int, this.int)
fun ByteBuffer.getVector3f(): Vector3f = Vector3f(this.float, this.float, this.float)
fun ByteBuffer.getVector3d(): Vector3d = Vector3d(this.double, this.double, this.double)

/** UBJ Helper Functions **/
fun ByteBuffer.getUBJ(): UBJ? = getUBObject()?.let { UBJ(it) }

fun ByteBuffer.getUBObject(): UBObject? {
    val bytes = this.getBytes()
    try {
        UBReader(InflaterInputStream(ByteArrayInputStream(bytes))).use { reader -> return reader.read().asObject() }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}