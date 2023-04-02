package UNO

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import controller.controllerComponent._
import model.fileIOComponent._

class UnoGameModule extends AbstractModule {

  override def configure() = {
    bind(classOf[controllerInterface]).to(classOf[controllerBaseImp.Controller])
    bind(classOf[FileIOTrait]).to(classOf[fileIOXmlImp.FileIO])

    //Old stuff - maybe needed later:
    //bind[controllerInterface].to[controllerBaseImp.controller]
    //bind[controllerInterface].to[controllerStubImp.Controller]
    //bind[FileIOTrait].to[fileIOJsonImp.FileIO]
    //bind[FileIOTrait].to[fileIOXmlImp.FileIO]
  }
}
