Docker Desktop herunterladen, installieren und starten.

im verzeichnis UNO_Software_Architecture:

Build with: "docker-compose up --build"
Start with: "docker-compose up -d" to allow User-input
Attach to main-Service with: "docker attach uno_service"

Terminal can be used for Input now

Falls es hängen belibt beim download von librarys oder beim compilen
-> in Docker Desktop auf Setttings -> Resources -> Memory auf 4 Gb -> Swap auf 2 Gb

Ansonsten wenn es hängt vor dem "docker-compose up --build" immer alle Images und Container löschen ( geht in der Docker Desktop GUI). Wenn der Container beim starten hängen bleibt konnte ich ihn nicht stoppen und damit nicht löschen. PC neustart war die schnellste Lösung die ich gefunden hab. Man könnte auch process-id von Container rausfinden und den process dann killen.