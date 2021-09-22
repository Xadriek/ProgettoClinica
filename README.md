
1.	Contesto di riferimento e obiettivi

Contesto

Si vuole realizzare un sistema informativo su Web per la prenotazione degli esami medici di una piccola clinica
Oltre agli utenti occasionali, due tipologie di attori interagiscono con il sistema: i pazienti e l'amministrazione
Un paziente può svolgere le seguenti operazioni:
	•	Consultazione tipologie di esami offerti dalla clinica
	•	Consultazione dei risultati di un proprio esame

L'amministrazione può svolgere le seguenti operazioni:
	•	Inserimento di una tipologia di esame 
	•	Inserimento di un esame
	•	Inserimento di un paziente nella anagrafica pazienti
	•	Inserimento risultati di un esame

Per ogni tipologia di esame sono di interesse un nome, un codice, una descrizione, un costo
	•	Ogni tipologia di esame ha inoltre una lista di prerequisiti 
			• Per ogni requisito sono di interesse il nome e una breve descrizione
	•	Ogni tipologia di esame ha una lista di risultati
			•Per ogni risultato sono di interesse il parametro ed il valore

Per ogni esame è necessario riportare, oltre al paziente, la data di prenotazione (con data e ora in cui è stata effettuata la prenotazione), la data in cui è stato effettuato l'esame, il nome del medico che ha condotto l'esame
	•	Per ogni medico è necessario gestire: nome, cognome, specializzazione
	
2.	Obiettivi

L’obiettivo è creare un sistema informativo su Web che contempli i seguenti casi d’uso che seguono 


3.	Casi D’Uso



Caso d'uso UC1: consulta offerta:
Attore primario: utente non registrato. Un qualunque accesso anonimo (che può fruire direttamente il portale senza essere necessariamente registrato) può accedere alle pagine 
di consultazione per consultare sia la tipologia degli esami che la clinica offre richiedone i dettagli.

Scenario principale di successo: 

Il sistema mostra i dettagli della richiesta visualizzando la pagina.
L'utente ripete i passi precedenti un numero indefinito di volte. 
La stessa cosa è disponibile anche per i medici con le stesse modalità.

 


Caso d'uso UC2: crea esame (prenotazione).

Attore primario: amministrazione. Si presuppone che l’utente principale sia  quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’esame.

Scenario principale di successo: 
E’ altresì necessario ovviamente loggarsi al sistema con le proprie credenziali che verranno riconosciute dal sistema come utenza di amministrazione. 
L’amministratore (o amministrazione) crea un esame dall’apposita voce di menu, successivamente imposta una tipologia all’esame creato, il medico, il paziente e la data in cui è stato effettuato l'esame.

 

Caso d'uso UC3: consulta e download risultati proprio esame
Attore primario: paziente. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “utente paziente”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di consultazione del dato come quello dell’esame.

Scenario principale di successo: 

Il paziente consulta l'elenco dei propri esami, attraverso i menu.
Il sistema mostra al paziente l'elenco dei suoi esami
Il paziente seleziona un proprio esame
Il sistema mostra l'esame nel dettaglio e i risultati
Il paziente ha la possibilità di esportare l'esame in formato PDF

 

Caso d'uso UC4: inserimento tipologia di esame.

Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’esame.

Scenario principale di successo: 

L'amministratore inserisce una nuova tipologia di esame indicando nome, descrizione e prezzo.
Il sistema registra la tipologia di esame e mostra la form per l'inserimento dei requisiti.
L'amministratore inserisce il requisito(se presente) altrimenti termina l'operazione
I punti precedenti vengono ripetuti fino a che necessario.



Caso d'uso UC5: esami effettuati da un medico.

Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’esame.

Scenario principale di successo: 
L’amministratore selezione dall’apposito menu nome e cognome di un medico.
Il sistema mostra all’amministratore tutti gli esami effettuati dal medico attraverso la selezione dello stesso, con il consueto link al nome:
Il sistema mostra i dati del medico e in basso gli esami svolti dallo stesso.




Caso d'uso UC6: inserimento risultati esame.
Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’esame.


Scenario principale di successo: 
L’amministratore ha la possibilità di inserire i risultati in due occasioni:

La prima è selezionando l'esame a cui si vuole aggiungere il risultato
Il sistema registra il risultato 
I passi precedenti si ripetono fino a che l'amministratore non preme su "Termina"

La seconda è selezionando la voce dal menù
Il sistema mostra la form dando la facoltà di selezionare un qualunque esame
L'amministratore seleziona l'esame e ne inserisce i valori

Il sistema registra il risultato
I passi precedenti si ripetono fino a che l'amministratore non preme su "Termina





Caso d'uso UC7: inserimento medico.
Attore primario: amministrazione. Si presuppone che l’utente principale sia quello registrato con appositi permessi di “amministrazione”, registrato con un apposito ruolo su DB in grado di effettuare operazioni di creazione del dato come quello dell’esame.

Scenario principale di successo:	
L'amministrazione seleziona la voce "inserisci medico"
Il sistema mostra la form
L'amministrazione inserisce nome, cognome, specializzazione e foto del medico
Il sistema registra il medico e mostra la lista di tutti i medici
 


Caso d'uso UC8:contatti & chi siamo.
Attore primario: utente generico

Scenario principale di successo:
L'utente seleziona i contatti della clinica
Il sistema mostra la form
L'utente inserisce il proprio nome, email e richieste
Il sistema inoltra tutto ad un indirizzo email della clinica.



Caso d'uso UC9: chi siamo.
Attore primario: utente generico

Scenario principale di successo:

L'utente seleziona le informazioni della clinica
Il sistema mostra la pagina con le informazioni





