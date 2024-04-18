import com.landmuc.dwm.di.sharedModule
import org.koin.core.context.startKoin
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

//fun initKoin() {
//    startKoin {
//        modules(sharedModule)
//    }
//}