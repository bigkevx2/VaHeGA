/*Voor creëren database eerst de master database aanroepen, hier staat alle systeem info
in en worden de databases in gezet die worden aangemaakt door ontwikkelaars*/
USE master;
GO

/*in de master database worde de VAHEGA database verwijdert, mits deze bestaat*/
DROP DATABASE IF EXISTS VAHEGA;
GO

/*de VAHEGA database wordt nu gemaakt in de master database*/
CREATE DATABASE VAHEGA;
GO

/*de VAHEGA database wordt na het creëren geselecteerd om de verdere database op te bouwen.
Als het script zonder het selecteren van de juiste database wordt gerund dan worden de tabellen
en alle andere objecten in de verkeerde database aangemaakt.*/
USE VAHEGA;
GO

/*TABELLEN*/
CREATE TABLE Rol(
	Rol_ID				int	IDENTITY(1,1)		NOT NULL,
	Rol_omschrijving	varchar(100)			NOT NULL
	CONSTRAINT PK_Rol PRIMARY KEY (Rol_ID)
);
GO

CREATE TABLE Medewerker(
	Medewerker_ID		int IDENTITY(1,1)		NOT NULL,
	Voornaam			varchar(50)				NOT NULL,
	Achternaam			varchar(50)				NOT NULL,
	Gebruikersnaam		varchar(50)				NOT NULL,
	Wachtwoord			varchar(50)				NOT NULL,
	--Toegangsniveau		int						NOT NULL,
	Inlogpoging			int								,
	CONSTRAINT PK_Medewerker PRIMARY KEY (Medewerker_ID)
);
GO

CREATE TABLE Medewerker_Rol(
	Medewerker_ID		int 		NOT NULL,
	Rol_ID				int			NOT NULL,
	CONSTRAINT PK_Medewerker_Rol PRIMARY KEY (Medewerker_ID, Rol_ID)
);
GO

CREATE TABLE [Order](
	Order_ID		int IDENTITY(1,1)	NOT NULL,
	Order_datum		date				NOT NULL,
	Medewerker_ID	int					NOT NULL,
	Klant_ID		int					NOT NULL,
	CONSTRAINT PK_Order PRIMARY KEY (Order_ID)
);
GO

CREATE TABLE Klant(
	Klant_ID		int IDENTITY(1,1)	NOT NULL,
	Klant_soort_ID	int					NOT NULL,
	Voornaam		VARCHAR(50)			NOT NULL,
	Achternaam		VARCHAR(50)			NOT NULL,
	Straatnaam		VARCHAR(50)			NOT NULL,
	Huisnummer		int					NOT NULL,
	Postcode		VARCHAR(10)			NOT NULL,
	Woonplaats		VARCHAR(50)			NOT NULL,
	Telefoonnummer	VARCHAR(12)			NOT NULL,
	Gebruikersnaam	VARCHAR(50)					,
	Wachtwoord		VARCHAR(50)					,
	Email			VARCHAR(50)			NOT NULL,
	Contactpersoon	VARCHAR(50)					,
	Kvk				int							,
	Minimumafname	int							,
	Korting			decimal(4,2)				,
	CONSTRAINT PK_Klant PRIMARY KEY (Klant_ID)
);
GO

CREATE TABLE Klant_soort(
	Klant_soort_ID		int IDENTITY(1,1)	NOT NULL,
	Omschrijving		VARCHAR(50)			NOT NULL,
	CONSTRAINT PK_Klant_soort PRIMARY KEY (Klant_soort_ID)
);
GO

CREATE TABLE Order_Product(
	Product_ID		int			NOT NULL,
	Order_ID		int			NOT NULL,
	CONSTRAINT PK_Order_Product PRIMARY KEY (Product_ID, Order_ID)
);
GO

CREATE TABLE Product(
	Product_ID			int IDENTITY(1,1)	NOT NULL,
	Product_groep_ID	int					NOT NULL,
	Product_naam		VARCHAR(50)			NOT NULL,
	Omschrijving		VARCHAR(50)			NOT NULL,
	Inkoopprijs			decimal(5,2)				,
	Productieprijs		decimal(5,2)				,
	Verkoopprijs		decimal(5,2)		NOT NULL,
	Levertijd			int					NOT NULL,
	CONSTRAINT PK_Product PRIMARY KEY (Product_ID)
);
GO

CREATE TABLE Product_groep(
	Product_groep_ID		int IDENTITY(1,1)	NOT NULL,
	Naam			VARCHAR(50)			NOT NULL,
	CONSTRAINT PK_Product_groep PRIMARY KEY (Product_groep_ID)
);
GO

/*na het aanmaken van alle tabellen van de VAHEGA database worden de tabellen aangevuld
met constraints, dit kunnen FK constraints zijn die we bewust nu pas aanmaken omdat
we er nu zeker van zijn dat de tabellen waar ze afhankelijk van zijn ook daadwerkelijk bestaan.
Dit kunnen ook CHECK CONSTRAINTS zijn die toezien of een bepaalde waarde die in een veld
ingevoerd wordt ook toegestaan is.*/

/*ALTER TABLE statements*/
ALTER TABLE Medewerker_Rol ADD 
	CONSTRAINT FK_Medewerker_Rol FOREIGN KEY(Rol_ID)
		REFERENCES Rol (Rol_ID),
	CONSTRAINT FK_Medewerker_Rol_Medewerker FOREIGN KEY(Medewerker_ID)
		REFERENCES Medewerker(Medewerker_ID)
;
GO

ALTER TABLE [Order] ADD 
	CONSTRAINT FK_Order_Medewerker FOREIGN KEY(Medewerker_ID)
		REFERENCES Medewerker (Medewerker_ID),
	CONSTRAINT FK_Order_Klant FOREIGN KEY(Klant_ID)
		REFERENCES Klant (Klant_ID)
;
GO

ALTER TABLE Klant ADD
	CONSTRAINT FK_Klant_Klant_soort FOREIGN KEY(Klant_soort_ID)
		REFERENCES Klant_soort(Klant_soort_ID)
;
GO

ALTER TABLE Order_Product ADD
	CONSTRAINT FK_Order_Order_Product FOREIGN KEY(Order_ID)
		REFERENCES [Order](Order_ID),
	CONSTRAINT FK_Order_Product_Product FOREIGN KEY(Product_ID)
		REFERENCES Product(Product_ID)
;
GO

ALTER TABLE Product ADD
	CONSTRAINT FK_Product_Product_groep FOREIGN KEY(Product_groep_ID)
		REFERENCES Product_groep(Product_groep_ID)
;
GO