package command.commandComponent
package controller

import com.google.inject.AbstractModule
import command.commandComponent.controller._
//import UNO.fileIOComponent._
//import UNO.fileIOComponent.FileIOTrait
//import UNO.fileIOComponent.fileIOXmlImp
//import UNO.fileIOComponent.fileIOJsonImp


class UnoGameModule extends AbstractModule {

  override def configure() = {
    bind(classOf[controllerInterface]).to(classOf[controllerBaseImp.Controller])
    //bind(classOf[FileIOTrait]).to(classOf[fileIOJsonImp.FileIO])

    //Old stuff - maybe needed later:
    //bind[controllerInterface].to[controllerBaseImp.controller]
    //bind[controllerInterface].to[controllerStubImp.Controller]
    //bind[FileIOTrait].to[fileIOJsonImp.FileIO]
    //bind[FileIOTrait].to[fileIOXmlImp.FileIO]
  }
}