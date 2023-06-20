# UNO_Software_Architecture


<!-- ![Logo](src/main/Pics/UNO-Logo.png) -->


[![Coverage Status](https://coveralls.io/repos/github/JeSuisUneLicorne/UNO_Software_Architecture/badge.svg?branch=main)](https://coveralls.io/github/JeSuisUneLicorne/UNO_Software_Architecture?branch=main) [![Scala CI](https://github.com/JeSuisUneLicorne/UNO_Software_Architecture/actions/workflows/scala.yml/badge.svg?branch=main)](https://github.com/JeSuisUneLicorne/UNO_Software_Architecture/actions/workflows/scala.yml)

### About

The classic UNO-Game in a new version, created in Scala 2. Updated to <a href="https://www.scala-lang.org/download/" target="_blank">Scala 3.2.2</a>, <a href="https://www.scala-sbt.org/" target="_blank">sbt 1.8.2</a> and <a href="https://docs.aws.amazon.com/corretto/latest/corretto-19-ug/downloads-list.html" target="_blank">Java 19 (Amazon Corretto)</a>.

This project by <a href="https://github.com/ju391bihhtwgkn" target="_blank">Julian Bihl</a>, <a href="https://github.com/JeSuisUneLicorne" target="_blank">Julian Zimmermann</a> and <a href="https://github.com/s0cy" target="_blank">s0cy</a> is the coursework for software architecture class at [University of Applied Sciences Constance (HTWG Konstanz)](https://www.htwg-konstanz.de/) for spring 2023. <br />



Base version of the game is by [Konstantin Zabaznov](https://github.com/konstantinz001) and [Soniesen Ratnaingam](https://github.com/SoniRat). <br />


### Setup

Recommanded is to use <a href="https://code.visualstudio.com/" target="_blank">VSCode</a> to run the project. Get the recommanded VSCode Plugins from below. Don't forget to enable Preview in the Markdown Plugin in Settings.

<b>SBT </b>: ```run / clean / compile / test```

Run tests with with enabled coverage: ```sbt clean coverage test``` (more info about <a href="https://www.youtube.com/watch?v=oz_HcHvbp7Y" target="_blank">coverage</a>)

<b>Docker </b>: Download <a href="https://docs.docker.com/desktop/install/windows-install/" target="_blank">Docker</a> and install it.

Build with: ```docker-compose up --build```. <br />
Start with: ```docker-compose up -d``` to allow user input via console. <br />
Attach to main-service with: ```docker attach uno_service```.

<details>
    <summary> <b> Troubleshooting Docker </b> </summary>
    <ul>
        <li> In case of wsl error, try: ```wsl --install Ubuntu --web-download``` or check this <a href="https://learn.microsoft.com/de-de/windows/wsl/troubleshooting" target="_blank">site</a>.
        <li> In case of freezes while downloading libs or while compiling: Docker Desktop -> Settings -> Resources -> RAM 4GB or higher + Swap 2GB or higher
        <li> In case of freezes while  ```docker-compose up --build``` delete all images and containers (Docker Desktop GUI or manuel)
        <li> In case of freezes while starting a container (no deletion possible): Restart computer! or find process-id and kill it!
    </ul>
</details>


### Testing

Updated from WordSpec to <a href="https://www.scalatest.org/scaladoc/3.2.0/org/scalatest/wordspec/AnyWordSpec.html" target="_blank">AnyWordSpec</a>.

In sbt console type ```test```.

### Screenshots


<!--TODO: add a couple of screenshots-->



### Guidelines

---

 <!-- <details> -->
  <summary> <b>Guidelines</b> </summary>
    <ul>
        <li><a href="https://www.plainenglish.co.uk/how-to-write-in-plain-english.html" target="_blank">How to write in plain English</a>  
        <li><a href="https://github.com/RomuloOliveira/commit-messages-guide" target="_blank">Commit messages guide</a>  
        <li><a href="https://github.com/databricks/scala-style-guide" target="_blank">Databricks Scala Guide</a>  
        <li><a href="https://google.github.io/styleguide/htmlcssguide.html" target="_blank">Google's HTML/CSS Style Guide</a>  
        <li><a href="https://github.com/airbnb/javascript" target="_blank">Airbnb JavaScript Style Guide</a>  
    </ul>
<!-- </details> -->

---
 <!-- <details> -->
  <summary> <b>Useful Links</b> </summary>
    <ul>
        <li> <a href="https://www.scala-sbt.org/1.x/docs/index.html" target="_blank">sbt Reference Manual</a>  
        <li> <a href="https://learngitbranching.js.org/" target="_blank">Learn Git Branching</a>  
        <li> <a href="https://developer.mozilla.org/en-US/" target="_blank">MDN Web Docs</a>  
        <li> <a href="https://javascript.info/" target="_blank">The Modern JavaScript Tutorial</a>  
        <li> <a href="https://github.blog/2018-09-06-removing-jquery-from-github-frontend/" target="_blank">Removing jQuery from GitHub.com frontend (jQuery is obsolete)</a>  
    </ul>
<!-- </details> -->

---
<details> 
  <summary> <b>Recommanded VSCode Plugins</b> </summary>
    <ul>
        <li>Sbt  
        <li>Scala Syntax (official)
        <li>Docker
        <li>Scala (Metals)
        <li> Markdown All in One
    </ul>
</details>
