package resourse

import com.google.common.io.Resources
import java.io.InputStream

class ResourceStreamLoader: InputStreamLoader {
    override fun load(path: String): InputStream {
        return Resources.getResource(path).openStream()
    }
}