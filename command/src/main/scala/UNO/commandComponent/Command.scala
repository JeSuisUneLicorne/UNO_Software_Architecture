package command
package commandComponent

import UNO.controllerComponent.controllerInterface

trait Command:
  def doStep(): controllerInterface

  def undoStep(): controllerInterface
  
  def redoStep(): controllerInterface
