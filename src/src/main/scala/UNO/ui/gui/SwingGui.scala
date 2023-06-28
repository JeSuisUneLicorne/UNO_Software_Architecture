package UNO.ui
package gui

import UNO.ui.gui.CardPanel
import UNO.controllerComponent.controllerInterface
import UNO.controllerComponent.controllerBaseImp.*
import UNO.model.cardComponent.cardBaseImp.Card
import UNO.util._

import java.awt.Image
import javax.swing.ImageIcon
import scala.swing.*
import scala.swing.BorderPanel.Position
import scala.swing.Swing.LineBorder
import scala.swing.event.{ButtonClicked, Key}

import java.awt.Color
import javax.swing.{BorderFactory, ImageIcon}
import scala.swing.Alignment.Top
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.swing.{Action, BoxPanel, Button, Dimension, FlowPanel, Font, GridBagPanel, Insets, Label, Menu, MenuBar, MenuItem, Orientation, PopupMenu, Slider, Swing}

class SwingGui(controller: controllerInterface) extends Frame:
  listenTo(controller)
  title = " UNO Game"
  peer.setPreferredSize(new Dimension(1600, 1000))
  peer.setResizable(true)
  peer.setBackground(java.awt.Color.DARK_GRAY)

  menuBar = new MenuBar:
      contents += new Menu("File"):
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          controller.setDefault()
        })
        contents += new MenuItem(Action("Save") {
          controller.save
        })
        contents += new MenuItem(Action("Load") {
          controller.load
        })
        contents += new MenuItem(Action("Quit") {
          System.exit(0)
        })
      contents += new Menu("Edit"):
        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") {
          controller.undoGet
        })
        contents += new MenuItem(Action("Redo") {
          controller.redoGet
        })
      contents += new Menu("Info"):
        mnemonic = Key.V
        contents += new MenuItem(Action("Gamerule") {
        })

  // was further down
  contents = new BorderPanel:
    add(welcomePanel, Position.Center)

  def gridBagPanel: GridBagPanel = new GridBagPanel {
    def constraints(x: Int, y: Int,
                    gridwidth: Int = 1, gridheight: Int = 1,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
    : Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.fill = fill
      c
    }
    add(handCards, constraints(0, 0, gridheight = 2, gridwidth = 2))
    add(deck, constraints(3, 1, gridheight = 1))
    add(disCardPile, constraints(3, 2, gridheight = 1))
  }

  def handCards : GridBagPanel = new GridBagPanel {
    def constraints(x: Int, y: Int,
                    gridwidth: Int, gridheight: Int,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None,
                    insets: Insets
                   )
    : Constraints = {
      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.fill = fill
      c.insets = insets
      c
    }
    new Dimension(1000, 600)
      var x = 1
      var b = 1
      for (i <- 0 to controller.playerList(0).playerCards.length -1 ) {
          add(new Button() {
            icon = controller.playerList(0).playerCards(i).color match {
              case "red" => (scaledImageIcon(getValue("red" , controller.playerList(0).playerCards(i).value), 100, 200))
              case "blue" => (scaledImageIcon(getValue("blue", controller.playerList(0).playerCards(i).value), 100, 200))
              case "green" => (scaledImageIcon(getValue("green", controller.playerList(0).playerCards(i).value), 100, 200))
              case "yellow" => (scaledImageIcon(getValue("yellow", controller.playerList(0).playerCards(i).value), 100, 200))
              case "black" => (scaledImageIcon(getValue("black", controller.playerList(0).playerCards(i).value), 100, 200))
            }
            preferredSize = new Dimension(100, 200)
            listenTo(this)
            reactions += {
              case event.ButtonClicked(_) =>
                //controller.removeCard(i)
                //redraw
            
                if Strategy.handle(removeCardEvent(i),i) && controller.playerList.head.playerCards.size >= 3 && !controller.unoCall 
                  || controller.playerList.head.playerCards.size == 2 && controller.unoCall then
                  controller.removeCard(i)
                  redraw
                else if controller.playerList.head.playerCards.size == 1 && controller.unoCall then
                  controller.publish(new endStates)
                    // UnoGame.UNO.gui.redraw2
                else if !Strategy.handle(removeCardEvent(i),i) then
                  print("bla") //null TODO: Fix this
                else
                  controller.removeCard(i)
            //controller.playerList = controller.playerList.reverse
            //controller.getCard()
            //controller.playerList = controller.playerList.reverse
            //controller.getCard()
                redraw
            }
          }, constraints(i % 4, x, gridheight = 1, gridwidth = 1, insets = new Insets(2, 2, 2, 2)))
            b += 1
            if (b > 4) {
              x += 1
              b = 0
            }
      }
    }

  def deck : Button = new Button() {
    reactions += { case event.ButtonClicked(_) =>
      controller.getCard()
      redraw
    }
    preferredSize = new Dimension(100,200)
    icon = scaledImageIcon("src/main/Pics/Card_Back.PNG", 100, 200)
  }

  def disCardPile: Button = new Button() {
    icon = controller.playStack2(0).color match {
              case "red" => (scaledImageIcon(getValue("red" ,controller.playStack2(0).value), 100, 200))
              case "blue" => (scaledImageIcon(getValue("blue", controller.playStack2(0).value), 100, 200))
              case "green" => (scaledImageIcon(getValue("green", controller.playStack2(0).value), 100, 200))
              case "yellow" => (scaledImageIcon(getValue("yellow", controller.playStack2(0).value), 100, 200))
              case "black" => (scaledImageIcon(getValue("black", controller.playStack2(0).value), 100, 200))
            }
  }

  def getValue(color: String, value: String): String =
    //val icon = new ImageIcon()
    val icon = color match {
      case "red" => 
        value match {
          case "<-->" => "src/main/Pics/Red_Reverse.png"
          case "+2" => "src/main/Pics/Red_+2.png"
          case "Skip" => "src/main/Pics/Red_Skip.png"
          case "0" => "src/main/Pics/Red_0.png"
          case "1" => "src/main/Pics/Red_1.png"
          case "2" => "src/main/Pics/Red_2.png"
          case "3" => "src/main/Pics/Red_3.png"
          case "4" => "src/main/Pics/Red_4.png"
          case "5" => "src/main/Pics/Red_5.png"
          case "6" => "src/main/Pics/Red_6.png"
          case "7" => "src/main/Pics/Red_7.png"
          case "8" => "src/main/Pics/Red_8.png"
          case "9" => "src/main/Pics/Red_9.png"
          case "+2" => "src/main/Pics/Red_+2.png"
        }
      case "blue" => 
        value match {
          case "<-->" => "src/main/Pics/Blue_Reverse.png"
          case "+2" => "src/main/Pics/Blue_+2.png"
          case "Skip" => "src/main/Pics/Blue_Skip.png"
          case "0" => "src/main/Pics/Blue_0.png"
          case "1" => "src/main/Pics/Blue_1.png"
          case "2" => "src/main/Pics/Blue_2.png"
          case "3" => "src/main/Pics/Blue_3.png"
          case "4" => "src/main/Pics/Blue_4.png"
          case "5" => "src/main/Pics/Blue_5.png"
          case "6" => "src/main/Pics/Blue_6.png"
          case "7" => "src/main/Pics/Blue_7.png"
          case "8" => "src/main/Pics/Blue_8.png"
          case "9" => "src/main/Pics/Blue_9.png"
          case "+2" => "src/main/Pics/Blue_+2.png"
        }
      case "yellow" =>
        value match {
          case "<-->" => "src/main/Pics/Yellow_Reverse.png"
          case "+2" => "src/main/Pics/Yellow_+2.png"
          case "Skip" => "src/main/Pics/Yellow_Skip.png"
          case "0" => "src/main/Pics/Yellow_0.png"
          case "1" => "src/main/Pics/Yellow_1.png"
          case "2" => "src/main/Pics/Yellow_2.png"
          case "3" => "src/main/Pics/Yellow_3.png"
          case "4" => "src/main/Pics/Yellow_4.png"
          case "5" => "src/main/Pics/Yellow_5.png"
          case "6" => "src/main/Pics/Yellow_6.png"
          case "7" => "src/main/Pics/Yellow_7.png"
          case "8" => "src/main/Pics/Yellow_8.png"
          case "9" => "src/main/Pics/Yellow_9.png"
          case "+2" => "src/main/Pics/Yellow_+2.png"
        }
      case "green" =>
        value match {
          case "<-->" => "src/main/Pics/Green_Reverse.png"
          case "+2" => "src/main/Pics/Green_+2.png"
          case "Skip" => "src/main/Pics/Green_Skip.png"
          case "0" => "src/main/Pics/Green_0.png"
          case "1" => "src/main/Pics/Green_1.png"
          case "2" => "src/main/Pics/Green_2.png"
          case "3" => "src/main/Pics/Green_3.png"
          case "4" => "src/main/Pics/Green_4.png"
          case "5" => "src/main/Pics/Green_5.png"
          case "6" => "src/main/Pics/Green_6.png"
          case "7" => "src/main/Pics/Green_7.png"
          case "8" => "src/main/Pics/Green_8.png"
          case "9" => "src/main/Pics/Green_9.png"
          case "+2" => "src/main/Pics/Green_+2.png"          
        }
      case "black" =>
        value match {
          case "4+ ColorSwitch" => "src/main/Pics/Black_4+_Colorswitch.png"
          case "ColorSwitch" => "src/main/Pics/Black_ColorSwitch.png"
        }
      }
    icon




  visible = true

    /*
    contents += new GridPanel(1, 4):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      val cardStack = new CardPanel(4, 0, controller)
      contents += cardStack.card
      val playStack = new CardPanel(3, 0, controller)
      contents += playStack.card
      val unoCall = new Button("")
      unoCall.background = java.awt.Color.DARK_GRAY
      unoCall.icon = scaledImageIcon("src\\main\\Pics\\UNO-Button.png", 110, 100)
      contents += unoCall
      listenTo(unoCall)
      reactions += {
        case ButtonClicked(`unoCall`) =>
          if controller.unoCall then
            unoCall.background = java.awt.Color.DARK_GRAY
            controller.unoCall = false
          else
            controller.unoCall = true
            unoCall.background = java.awt.Color.RED
      }
    
      
    contents += new GridPanel(1, controller.playerList.head.playerCards.size + 1):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      var cards: List[BoxPanel] = List.empty
      for (i <- (1 to controller.playerList.head.playerCards.length))
        val cardPanel = new CardPanel(0, i - 1, controller)
        cards = cardPanel.card :: cards
      cards.map(x => x.visible = false)
      val showButton = new Button("Show Cards!")
      listenTo(showButton)
      reactions += {
        case ButtonClicked(`showButton`) =>
          if cards.head.visible then
            cards.map(x => x.visible = false)
          else if !cards.head.visible then
            cards.map(x => x.visible = true)
      }
      contents ++= cards.reverse
      contents += showButton

    contents += new GridPanel(1, 4):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      val buttonGroup = new ButtonGroup
      //val red, blue, green, yellow = new RadioButton("").background = java.awt.Color.DARK_GRAY //before: red.background = java.awt.Color.DARK_GRAY same for other colors -> not working correctly
      val red = new RadioButton("")
      val blue = new RadioButton("")
      val green = new RadioButton("")
      val yellow = new RadioButton("")
      red.background = java.awt.Color.DARK_GRAY;
      red.icon = scaledImageIcon("src\\main\\Pics\\Red_Radio.png", 110, 100)
      blue.background = java.awt.Color.DARK_GRAY;
      blue.icon = scaledImageIcon("src\\main\\Pics\\Blue_Radio.png", 110, 100)
      green.background = java.awt.Color.DARK_GRAY;
      green.icon = scaledImageIcon("src\\main\\Pics\\Green_Radio.png", 110, 100)
      yellow.background = java.awt.Color.DARK_GRAY;
      yellow.icon = scaledImageIcon("src\\main\\Pics\\Yellow_Radio.png", 110, 100)
      buttonGroup.buttons ++= List(red, blue, green, yellow)
      buttonGroup.select(red)
      contents ++= List(red, blue, green, yellow)
      listenTo(red, green, blue, green, yellow)
      reactions += {
        case ButtonClicked(`yellow`) =>
          controller.colorSet = "yellow"
          yellow.background = java.awt.Color.YELLOW
          reactions += {
            case ButtonClicked(`yellow`) =>
              if controller.colorSet == "yellow" then
                controller.colorSet = ""
                yellow.background = java.awt.Color.DARK_GRAY
              else
                controller.colorSet = "yellow"
                yellow.background = java.awt.Color.YELLOW
                red.background = java.awt.Color.DARK_GRAY
                blue.background = java.awt.Color.DARK_GRAY
                green.background = java.awt.Color.DARK_GRAY
          }
        case ButtonClicked(`red`) =>
          if controller.colorSet == "red" then
            controller.colorSet = ""
            red.background = java.awt.Color.DARK_GRAY
          else
            controller.colorSet = "red"
            red.background = java.awt.Color.RED
            blue.background = java.awt.Color.DARK_GRAY
            green.background = java.awt.Color.DARK_GRAY
            yellow.background = java.awt.Color.DARK_GRAY
        case ButtonClicked(`blue`) =>
          if controller.colorSet == "blue" then
            controller.colorSet = ""
            blue.background = java.awt.Color.DARK_GRAY
          else
            controller.colorSet = "blue"
            blue.background = java.awt.Color.BLUE
            red.background = java.awt.Color.DARK_GRAY
            green.background = java.awt.Color.DARK_GRAY
            yellow.background = java.awt.Color.DARK_GRAY
        case ButtonClicked(`green`) =>
          if controller.colorSet == "green" then
            controller.colorSet = ""
            green.background = java.awt.Color.DARK_GRAY
          else
            controller.colorSet = "green"
            green.background = java.awt.Color.GREEN
            red.background = java.awt.Color.DARK_GRAY
            blue.background = java.awt.Color.DARK_GRAY
            yellow.background = java.awt.Color.DARK_GRAY
      }
    
    contents += new GridPanel(2, 1):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      val label: Label = new Label:
        text = "Player: " + controller.playerList.head.name
        font = new Font("Arial Black", java.awt.Font.BOLD, 20)
        foreground = java.awt.Color.WHITE
      contents += label

    menuBar = new MenuBar:
      contents += new Menu("File"):
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          controller.setDefault()
        })
        contents += new MenuItem(Action("Save") {
          controller.save
        })
        contents += new MenuItem(Action("Load") {
          controller.load
        })
        contents += new MenuItem(Action("Quit") {
          System.exit(0)
        })
      contents += new Menu("Edit"):
        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") {
          controller.undoGet
        })
        contents += new MenuItem(Action("Redo") {
          controller.redoGet
        })
      contents += new Menu("Info"):
        mnemonic = Key.V
        contents += new MenuItem(Action("Gamerule") {
        })
  contents = new BorderPanel:
    add(welcomePanel, Position.Center)

  def endGamePanel: GridPanel = new GridPanel(2, 1):
    contents += new GridPanel(2, 1):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      val winLabel = new Label("PLAYER " + controller.playerList.head.name.toUpperCase + ": YOU WON!")
      winLabel.foreground = java.awt.Color.WHITE
      winLabel.font = new Font("Arial Black", java.awt.Font.BOLD, 50)
      val againLabel = new Label("Play Again?")
      againLabel.foreground = java.awt.Color.WHITE
      againLabel.font = new Font("Arial Black", java.awt.Font.BOLD, 20)
      contents += winLabel
      contents += againLabel
    contents += new GridPanel(1, 2):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      val yesButton = new Button("YES")
      val noButton = new Button("NO")
      contents += yesButton
      contents += noButton
      listenTo(yesButton, noButton)
      reactions += {
        case ButtonClicked(`yesButton`) =>
          controller.setDefault()
        case ButtonClicked(`noButton`) =>
          System.exit(0)
      }
    menuBar = new MenuBar:
      contents += new Menu("File"):
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          controller.setDefault()
        })
        contents += new MenuItem(Action("Save") {
          controller.save
        })
        contents += new MenuItem(Action("Load") {
          controller.load
        })
        contents += new MenuItem(Action("Quit") {
          System.exit(0)
        })
      contents += new Menu("Edit"):
        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") {
          controller.undoGet
        })
        contents += new MenuItem(Action("Redo") {
          controller.redoGet
        })
        */

  def welcomePanel = new GridPanel(2, 1):
    contents += new GridPanel(1, 1):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      val WelcomeLabel = new Label("Welcome to UNO!")
      WelcomeLabel.foreground = java.awt.Color.WHITE
      WelcomeLabel.font = new Font("Arial Black", java.awt.Font.BOLD, 50)
      contents += WelcomeLabel
    contents += new GridPanel(1, 2):
      border = LineBorder(java.awt.Color.DARK_GRAY, 50)
      background = java.awt.Color.DARK_GRAY
      val enterButton = new Button("Enter")
      val exitButton = new Button("Exit")
      contents += enterButton
      contents += exitButton
      listenTo(enterButton, exitButton)
      reactions += {
        case ButtonClicked(`enterButton`) =>
          redraw
        case ButtonClicked(`exitButton`) =>
          System.exit(0)
      }

  def redraw: Unit =
    contents = gridBagPanel
    //  new BorderPanel: add(gamePanel, BorderPanel.Position.Center)

/*
  def redraw2: Unit =
    contents = new BorderPanel:
      add(endGamePanel, BorderPanel.Position.Center)

*/

  def redraw3: Unit = 
    contents = new BorderPanel:
      add(welcomePanel, BorderPanel.Position.Center)


  reactions += {
    case event: updateStates => redraw
    //case event: endStates => redraw2
  }


  //visible = true

  def scaledImageIcon(path: String, width: Int, height: Int): ImageIcon =
    val orig = new ImageIcon(path)
    val scaledImage = orig.getImage.getScaledInstance(width, height, Image.SCALE_REPLICATE)
    new ImageIcon(scaledImage)
