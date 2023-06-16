
# Badminton Reservation System

Das ist das Projekt für den Informatikunterricht




## Datenbank erstellen

-- Erstelle die Tabelle "Kunde"
CREATE TABLE Kunde (
  KundenID INT PRIMARY KEY AUTO_INCREMENT,
  Vorname VARCHAR(16),
  Nachname VARCHAR(16),
  Telefonnummer VARCHAR(16),
  Email VARCHAR(32),
  AdressenID INT,
  FOREIGN KEY (AdressenID) REFERENCES Adressen(AdressenID)
);

-- Erstelle die Tabelle "Adressen"
CREATE TABLE Adressen (
  AdressenID INT PRIMARY KEY AUTO_INCREMENT,
  Straße VARCHAR(32),
  PLZ VARCHAR(8),
  Ort VARCHAR(32)
);

-- Erstelle die Tabelle "Reservierungen"
CREATE TABLE Reservierungen (
  ReservierungsID INT PRIMARY KEY AUTO_INCREMENT,
  AnzahlPlätze INT,
  AnzahlEinheiten INT,
  Preis MONEY,
  Datum DATE,
  Uhrzeit TIME,
  Platz INT,
  KundenID INT,
  FOREIGN KEY (KundenID) REFERENCES Kunde(KundenID)
);

-- Erstelle die Tabelle "WertBlock"
CREATE TABLE WertBlock (
  BlockID INT PRIMARY KEY AUTO_INCREMENT,
  KundenID INT,
  Wert MONEY,
  FOREIGN KEY (KundenID) REFERENCES Kunde(KundenID)
);

-- Erstelle die Tabelle "Mitarbeiter"
CREATE TABLE Mitarbeiter (
  MitarbeiterID INT PRIMARY KEY AUTO_INCREMENT,
  Vorname VARCHAR(16),
  Nachname VARCHAR(16),
  Rolle VARCHAR(16),
  Telefonnummer VARCHAR(16),
  AdressenID INT,
  FOREIGN KEY (AdressenID) REFERENCES Adressen(AdressenID)
);

-- Erstelle die Tabelle "LogIn"
CREATE TABLE LogIn (
  LoginID INT PRIMARY KEY AUTO_INCREMENT,
  Username VARCHAR(16),
  Password VARCHAR(32),
  Rolle VARCHAR(16),
  MitarbeiterID INT,
  FOREIGN KEY (MitarbeiterID) REFERENCES Mitarbeiter(MitarbeiterID)
);




##Datenbank befüllen


DROP TABLE Login;
DROP TABLE Mitarbeiter;
DROP TABLE WertBlock;
DROP TABLE Reservierungen;
DROP TABLE Kunde;
DROP TABLE Adressen;

CREATE TABLE Adressen(
    AdressenID INT PRIMARY KEY AUTO_INCREMENT,
    Straße VARCHAR(32),
    PLZ VARCHAR(8),
    Ort VARCHAR(32)
); 
CREATE TABLE Kunde(
    KundenID INT PRIMARY KEY AUTO_INCREMENT,
    Vorname VARCHAR(16),
    Nachname VARCHAR(16),
    Telefonnummer VARCHAR(16),
    Email VARCHAR(32),
    AdressenID INT,
    FOREIGN KEY(AdressenID) REFERENCES Adressen(AdressenID)
); 
CREATE TABLE Reservierungen(
    ReservierungsID INT PRIMARY KEY AUTO_INCREMENT,
    AnzahlPlätze INT,
    AnzahlEinheiten INT,
    Preis DECIMAL(10, 2),
    Datum DATE,
    Uhrzeit TIME,
    Platz INT,
    KundenID INT,
   	Zustand Varchar(16),
    FOREIGN KEY(KundenID) REFERENCES Kunde(KundenID)
); 
CREATE TABLE WertBlock(
    BlockID INT PRIMARY KEY AUTO_INCREMENT,
    KundenID INT,
    Wert DECIMAL(10, 2),
    FOREIGN KEY(KundenID) REFERENCES Kunde(KundenID)
);
CREATE TABLE Mitarbeiter(
    MitarbeiterID INT PRIMARY KEY AUTO_INCREMENT,
    Vorname VARCHAR(16),
    Nachname VARCHAR(16),
    Rolle VARCHAR(16),
    Telefonnummer VARCHAR(16),
    AdressenID INT,
    FOREIGN KEY(AdressenID) REFERENCES Adressen(AdressenID)
); 
CREATE TABLE Login(
    LoginID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(16),
    PASSWORD VARCHAR(32),
    Rolle VARCHAR(16),
    MitarbeiterID INT,
    FOREIGN KEY(MitarbeiterID) REFERENCES Mitarbeiter(MitarbeiterID)
);

-- Befülle die Tabelle "Adressen" mit Beispieldaten
INSERT INTO Adressen (Straße, PLZ, Ort)
VALUES
  ('Musterstraße 1', '12345', 'Musterstadt'),
  ('Beispielweg 2', '54321', 'Beispielort'),
  ('Hauptplatz 3', '67890', 'Hauptstadt'),
  ('Neue Gasse 4', '09876', 'Neustadt'),
  ('Alte Straße 5', '54321', 'Altstadt'),
  ('Bahnhofstraße 6', '98765', 'Bahnhofstadt'),
  ('Parkweg 7', '12345', 'Parkstadt'),
  ('Bergstraße 8', '67890', 'Bergdorf'),
  ('Seestraße 9', '34567', 'Seestadt'),
  ('Am Markt 10', '89012', 'Marktstadt');

-- Befülle die Tabelle "Kunde" mit Beispieldaten
INSERT INTO Kunde (Vorname, Nachname, Telefonnummer, Email, AdressenID)
VALUES
  ('Max', 'Mustermann', '1234567890', 'max.mustermann@example.com', 1),
  ('Anna', 'Schmidt', '9876543210', 'anna.schmidt@example.com', 2),
  ('Peter', 'Müller', '4567890123', 'peter.mueller@example.com', 3),
  ('Sarah', 'Keller', '0123456789', 'sarah.keller@example.com', 4),
  ('Michael', 'Schneider', '7890123456', 'michael.schneider@example.com', 5),
  ('Laura', 'Fischer', '2345678901', 'laura.fischer@example.com', 6),
  ('Thomas', 'Weber', '5678901234', 'thomas.weber@example.com', 7),
  ('Julia', 'Schulz', '9012345678', 'julia.schulz@example.com', 8),
  ('Markus', 'Huber', '3456789012', 'markus.huber@example.com', 9),
  ('Nicole', 'Wagner', '6789012345', 'nicole.wagner@example.com', 10);


-- Befülle die Tabelle "Reservierungen" mit Beispieldaten

INSERT INTO WertBlock (KundenID, Wert)
VALUES
  (1, 50.00),
  (2, 100.00),
  (3, 75.00),
  (4, 25.00),
  (5, 80.00),
  (6, 60.00),
  (7, 90.00),
  (8, 70.00),
  (9, 40.00),
  (10, 95.00);

SELECT * from Kunde;




## Authors

- [@k0hlii](https://www.github.com/k0hlii)
- [@BINGUTUNDDUNICHT](https://github.com/BINGUTUNDDUNICHT)
- [@DrAichhorn](https://github.com/DrAichhorn)



## License

[MIT](https://choosealicense.com/licenses/mit/)
## Deployment

To deploy this project run

```bash
  npm run deploy
```


## Acknowledgements

 - [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Awesome README](https://github.com/matiassingers/awesome-readme)
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)

