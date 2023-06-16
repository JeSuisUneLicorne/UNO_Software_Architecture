package UNO.controllerComponent

import com.google.inject.AbstractModule
import UNO.controllerComponent.controllerInterface
import UNO.fileIOComponent._
import UNO.fileIOComponent.FileIOTrait

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