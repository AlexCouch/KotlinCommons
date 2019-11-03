package bvanseg.kotlincommons.files

import java.io.File
import java.net.URI

/**
 * Lightweight wrapper around a [File] that automatically creates directories and the
 * actual [File] on instantiation (if the file does not already exist).
 *
 * @author Boston Vanseghi
 * @since 2.0.1
 */
class KFile: File {

    constructor(path: String): super(path)
    constructor(uri: URI): super(uri)
    constructor(parent: String, child: String): super(parent, child)
    constructor(parent: File, path: String): super(parent, path)

    init {
        if(!exists()) {
            parentFile.mkdirs()
            createNewFile()
        }
    }

    fun rename(newName: String): KFile {
        val newFile = KFile(this.parent + "/$newName." + this.extension)
        this.copyTo(newFile)
        this.delete()
        return newFile
    }
}