import SystemClipboard.copy
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.random.Random

fun main() = Window(title= "MortiseLock", size = IntSize(300,150),icon= getWindowIcon()) {
    var passwordText by remember { mutableStateOf(" ") }
    val pink = Color(0xFFFF1493)
    val white = Color(0xFFFFFFFF)

    MaterialTheme {
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            Button(modifier = Modifier.align(Alignment.CenterHorizontally), colors = ButtonConstants.defaultButtonColors(
                backgroundColor = pink),
                onClick = {
                    passwordText = createPassword()
                }) {
                Text("Generate Password")


            }
            Button(modifier = Modifier.align(Alignment.CenterHorizontally), colors = ButtonConstants.defaultButtonColors(
                backgroundColor = white,
            ),

                onClick = {
                    copy(passwordText)
                    passwordText = "Copied!"
                }){
                Text(passwordText,color = Color.Black)

            }

        }
    }
}
//Creates the icon picture
fun getWindowIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
        image = ImageIO.read(File("key_image.png"))
    } catch (e: Exception) {
        // image file does not exist
    }

    if (image == null) {
        image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
}
//Password Generator
fun createPassword(): String{
    val list = readFile()
    val randomWord1 = list[Random.nextInt(1,81883)]
    val randomWord2 = list[Random.nextInt(1,81883)]
    return randomWord1 + randomWord2 + Random.nextInt(10,99)
}
fun readFile(): MutableList<String> {
    val fileName = "2of12inf.txt"
    val fileList = mutableListOf<String>()
    File(fileName).useLines { lines -> fileList.addAll(lines) }
    return fileList
}

//Clipboard
object SystemClipboard {
    fun copy(text: String?) {
        val clipboard = systemClipboard
        clipboard.setContents(StringSelection(text), null)
    }
    }

    @Throws(java.lang.Exception::class)
    fun get(): String? {
        val systemClipboard = systemClipboard
        val dataFlavor = DataFlavor.stringFlavor
        if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
            val text = systemClipboard.getData(dataFlavor)
            return text as String
        }
        return null
    }

    private val systemClipboard: Clipboard
        get() {
            val defaultToolkit = Toolkit.getDefaultToolkit()
            return defaultToolkit.systemClipboard
        }
