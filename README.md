# Manuale d'uso per il software "Dungeon Unipd"

## Breve descrizione del gioco:

DUNGEON UNIPD è un text adventure ambientato in un dungeon avente otto stanze più una nona stanza segreta.
Lo scopo del gioco è quello di sconfiggere il boss e conquistare il dungeon.

## Caratteristiche del gioco:
- Il gioco permette di selezionare tra 3 divese classi: **"Warrior"**, **"Archer"**, **"Thief"** ognuna con le sue specifice caratteristiche 
- Il giocatore può muoversi liberamente all'interno di ogni stanza usando i comandi da tastiera ***WASD*** 
- Il giocatore durante l'esplorazione delle stanze può incontrare delle porte che gli permettono di cambiare stanza usando i comandi: **"Nord"**,**"Sud"**,**"Est"**,**"Ovest"** in base alla porta trovata.
- Nelle stanze si possono trovare dei mostri, generati casualmente, che possono attaccare il giocatore quando si avvicina
- Il giocatore, se possiede una spada, può rispondere all'attacco con il comando **"attack"** oppure scappare con il comando **"run"**
- Il giocatore può raccogliere degli oggetti presenti nelle stanze con il comando **"take"**
- Ogni oggetto ha un suo peso specifico
- L'inventario del giocatore ha una capienza massima di 100
- Tutti gli oggetti e i mostri che si possono trovare nelle stanze del dungeon sono generati casualmente 
- Il giocatore perde la partita quando la sua vita arriva a 0
- Il giocatore vince la partita quando sconfigge il boss finale 
- Il gioco permette di avere fino a 4 slot salvataggi utilizzabili con il comando **"slot (1-4)"**
- Il gioco permette di salvare la partita in uno dei 4 slot salvataggio liberi con il comando **"save (1 -4)"** oppure sovrascrivendo un precedente slot 


## Tecnologie usate 
- SDK: Java OpenJDK 22.0.1 
- [Maven]
- Java Swing 
- [Amazon aws API]  version: 1.12.733
- [Amazon aws s3 cloud service]
- Jupiter version: 5.11.0-M2
- [ChatGPT 4]
- [Claude 3.5 Sonnet ai]
- [Jira] per le user stories
- IDE: IntelliJ IDEA community edition e VS Code

> Il salvataggio dei file di testo contenenti i dati avviene tramite le api di amazon aws, per poter caricare e/o scaricare eventuali file dal bucket è necessaria una connessione ad internet


[Maven]: <https://mvnrepository.com/>
[Amazon aws API]: <https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-s3>
[Amazon aws s3 cloud service]: <https://aws.amazon.com/pm/serv-s3/?gclid=CjwKCAjwp4m0BhBAEiwAsdc4aF26ZOv6PuYAxJjI4VFSovc2K0MLmS86Qs0JGo-zJdFDZqPMBDfdvRoCNQ8QAvD_BwE&trk=fe2078a2-393b-4713-b274-14590f118a9d&sc_channel=ps&ef_id=CjwKCAjwp4m0BhBAEiwAsdc4aF26ZOv6PuYAxJjI4VFSovc2K0MLmS86Qs0JGo-zJdFDZqPMBDfdvRoCNQ8QAvD_BwE:G:s&s_kwcid=AL!4422!3!536324415084!e!!g!!amazon%20s3!12196406601!115425125885>
[ChatGPT 4]: <https://chatgpt.com/>
[Claude 3.5 Sonnet ai]: <https://claude.ai/new>
[Jira]: <https://dungeonunipd.atlassian.net/jira/software/projects/KAN/boards/1>
## Installazione software

Per poter eseguire il software è necessario prima di tutto scaricare il progetto contenuto nella branch ***Main***

Una volta entrati nel terminale con la path relativa alla directory scaricata utilizzare i comandi:

```sh
mvn clean install

java -jar target/DungeonUnipd-1.0-SNAPSHOT.jar 
```
Per generare gli unit test utilizzare i comandi:
```sh
mvn test
```
## Nota:
> Il progetto è stato sviluppato usando OpenJDK 22 e contiene dipendenze maven che potrebbero non essere compatibili con altre versioni di JDK.
> Per poter eseguire il software, ci si deve assicurare che le dipendenze maven siano coerenti con la versione JDK utilizzata 

## Comandi di gioco 
### 1) Bottoni menu
> Bottone **START** permette di iniziare una nuova partita e apre la pagina di creazione del personaggio
>
> Bottone **LOAD** apre la pagina di load che permette di caricare una partita precedentemente salvata 
>
> Bottone **EXIT** chiude il gioco 

### 2) Comandi testuali finestra LOAD
> Comando **"slot (1-4)"** permette di scegliere quale slot caricare, es "slot 1" carica il salvataggio presente nello slot 1
>
> Comando **"back" / "exit"** permette di tornare indietro dalla finestra di **LOAD** al menu di gioco

### 3) Comandi testuali player 
> Tasti **WASD** permettono di far muovere il player nella stanza
>
> Comandi **"sud"**, **"nord"**,**"est"**,**"ovest"** permettono al giocatore di cambiare stanza quando incontra rispettivamente i simboli **S**, **N**,**E**,**O** sulla mappa
>
> Comando **"take"** permette al giocatore di raccogliere un item
>
> Comando **"look"** permette al giocatore di avere un elenco generale degli oggetti presenti nella stanza
>
> Comando **"potion"** permette al giocatore di utilizzare una pozione curativa, se la possiede
>
> Comando **"attack"** permette al giocatore di attaccare un mostro, se possiede una spada
>
> Comando **"run"** permette al giocatore di scappare da un mostro
>
> Comando **"save (1-4)"** permette al giocatore di salvare la partita in uno dei 4 slot di salvataggio disponibili, es. **save 1** salva la partita nello slot salvataggio 1
>
> Comando **"exit"** permette al giocatore di ritornare al menu principale senza salvare la partita corrente





## Gruppo formato da: 
- ALESSANDRO MARANGON
- ANDREA BARUFFOL
- ANDREA ZANIN
- SANJIN MOCEVIC
