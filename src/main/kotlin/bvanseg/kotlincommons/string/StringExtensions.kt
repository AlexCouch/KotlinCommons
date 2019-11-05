package bvanseg.kotlincommons.string

fun List<String>.joinStrings(startIndex: Int = 0, endIndex: Int = -1): String {
    val sb = StringBuilder()
    val start = kotlin.math.max(startIndex, 0)
    val end = if (endIndex < 0) this.size else kotlin.math.min(endIndex, this.size)
    if (start > end)
        throw IllegalArgumentException("Start index ($start) must not be greater than the end index ($end)")
    (start until end).forEach {
        if (it > start)
            sb.append(" ")
        sb.append(this[it])
    }
    return sb.toString()
}

@Deprecated("This method is far too specific for continued usage in KotlinCommons. To be removed in 2.0.2")
fun String.replaceSeparators(): String = this.replace(",", "").replace("_", "")
