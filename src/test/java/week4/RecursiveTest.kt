package week4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RecursiveTest {

    @Test
    fun iterateTest() {
        for (i in 1 .. 100) {
            println(i)
        }

        fun recursiveIterate(start: Int, exclusiveEnd: Int) {
            if(start == exclusiveEnd) return

            println(start)
            val nextIndex = start + 1
            recursiveIterate(nextIndex, exclusiveEnd)
        }

        recursiveIterate(0, 100)
    }

    fun searchFile(node: Node, fileName: String): File? {
        return when(node) {
            is File -> if(node.fileName == fileName) node else null
            is Directory -> {
                for(child in node.childrenNodes) {
                    val ret = searchFile(child, fileName)
                    if(ret != null) return ret
                }
                null
            }
        }
    }

    sealed interface Node
    class Directory(
        val childrenNodes: List<Node>,
        val path: String
    ): Node
    class File(
        val path: String,
        val fileName: String
    ): Node


    @Test
    fun searchFileTest() {
        val fileA = File("/a/b/fileA", "fileA")
        val b = Directory(listOf(fileA), "/a/b")
        val a = Directory(listOf(b), "/a")

        val fileB = File("/d/e/fileB", "fileB")
        val e = Directory(listOf(fileB), "/d/e")
        val d = Directory(listOf(e), "/d")

        val root = Directory(listOf(a, d),"/")


        val retA = searchFile(root, "fileA")
        assertThat(retA?.fileName).isEqualTo("fileA")
        assertThat(retA?.path).isEqualTo("/a/b/fileA")

        val retB = searchFile(root, "fileB")
        assertThat(retB?.fileName).isEqualTo("fileB")
        assertThat(retB?.path).isEqualTo("/d/e/fileB")

        val retC = searchFile(root, "fileC")
        assertThat(retC).isNull()

    }
}

