package UNO
package controllerComponent

trait Command:
  def doStep(): Unit

  def undoStep(): Unit
  
  def redoStep(): Unit
