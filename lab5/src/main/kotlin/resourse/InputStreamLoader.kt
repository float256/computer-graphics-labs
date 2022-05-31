package resourse

import java.io.InputStream

interface InputStreamLoader {
    fun load(path: String): InputStream
}