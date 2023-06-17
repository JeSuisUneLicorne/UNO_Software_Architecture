package command
package commandComponent

import command.commandComponent.controller.controllerInterface

trait Command:
  def doStep(): controllerInterface

  def undoStep(): controllerInterface
  
  def redoStep(): controllerInterface
