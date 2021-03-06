/*
 * MIT License
 *
 * Copyright (c) 2019 Boston Vanseghi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package bvanseg.kotlincommons.simplenet.packet

import bvanseg.kotlincommons.compression.compress
import bvanseg.kotlincommons.projects.Version
import bvanseg.kotlincommons.ubjson.UBJ
import com.devsmart.ubjson.UBObject
import com.devsmart.ubjson.UBWriter
import com.github.simplenet.packet.Packet
import org.joml.Vector3dc
import org.joml.Vector3fc
import org.joml.Vector3ic
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

/**
 * @author Bright_Spark
 */

fun Packet.putUUID(uuid: UUID): Packet = this.apply {
    this.putLong(uuid.mostSignificantBits)
    this.putLong(uuid.leastSignificantBits)
}

fun Packet.putVersion(version: Version): Packet = this.apply {
    putInt(version.major)
    putInt(version.minor)
    putInt(version.patch)
    putString(version.label)
}

/** JOML Helper Functions **/
fun Packet.putVector3i(vector3ic: Vector3ic): Packet =
    this.putInt(vector3ic.x()).putInt(vector3ic.y()).putInt(vector3ic.z())

fun Packet.putVector3f(vector3fc: Vector3fc): Packet =
    this.putFloat(vector3fc.x()).putFloat(vector3fc.y()).putFloat(vector3fc.z())

fun Packet.putVector3d(vector3dc: Vector3dc): Packet =
    this.putDouble(vector3dc.x()).putDouble(vector3dc.y()).putDouble(vector3dc.z())

/** UBJ Helper Functions **/
fun Packet.putUBJ(o: UBJ): Packet =
    putUBObject(o.wrappedUBObject)

fun Packet.putUBObject(o: UBObject?): Packet {
    val os = ByteArrayOutputStream()
    var bytes: ByteArray? = null
    try {
        UBWriter(os).use { writer ->
            o?.let {
                writer.write(it)
            }
            bytes = os.toByteArray()
            os.close()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }


    bytes?.let {
        val compBytes = compress(it)
        this.putShort(compBytes.size)
        this.putBytes(*compBytes)
    }
    return this
}