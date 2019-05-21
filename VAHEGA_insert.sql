/* insert script for db VAHEGA*/
USE VAHEGA
GO

IF NOT EXISTS (SELECT 1 FROM Rol) /*Opletten: door te kijken naar een inhoud op 1 tabel wordt gekeken of de db gevuld is, mocht er vulling zijn in een andere dan deze tabel dan gaat dit mis.*/
    BEGIN
		DBCC CHECKIDENT('Rol', RESEED, 1)
		DBCC CHECKIDENT('Medewerker', RESEED, 1)
		DBCC CHECKIDENT('[Order]', RESEED, 1)
		DBCC CHECKIDENT('Klant', RESEED, 1)
		DBCC CHECKIDENT('Klant_soort', RESEED, 1)
		DBCC CHECKIDENT('Product', RESEED, 1)
		DBCC CHECKIDENT('Product_groep', RESEED, 1)
    END
ELSE
    BEGIN
		DELETE FROM Medewerker_Rol
		DELETE FROM Order_Product
		DELETE FROM [Order]
		DELETE FROM Rol
		DBCC CHECKIDENT('Rol', RESEED, 0)		
		DELETE FROM Medewerker
		DBCC CHECKIDENT('Medewerker', RESEED, 0)
		DELETE FROM [Order]
		DBCC CHECKIDENT('[Order]', RESEED, 0)
		DELETE FROM Klant
		DBCC CHECKIDENT('Klant', RESEED, 0)
		DELETE FROM Klant_soort
		DBCC CHECKIDENT('Klant_soort', RESEED, 0)		
		DELETE FROM Product
		DBCC CHECKIDENT('Product', RESEED, 0)
		DELETE FROM Product_groep
		DBCC CHECKIDENT('Product_groep', RESEED, 0)
	END
GO

INSERT INTO Rol VALUES
('Locked'),
('Gedeactiveerd'), 
('Administrator'),
('Verkoopmedewerker');
GO

INSERT INTO Medewerker VALUES
('Koos', 'Werkeloos', 'kwerk', '12345', null),
('Mathijs', 'Wolfs', 'mwol', '12345', null),
('Agnes', 'Wolfs', 'awol', '54321', null),
('Mark', 'Mestrom', 'mmes', '12', null),
('Mark', 'Mestrom', 'mstrom', '12', null),
('Leon', 'Bokhorst', 'lbok', '12', null),
('Leon', 'Bokhorst', 'lhorst', '12', null),
('ik', 'ik', 'ik', 'ik', null);
GO

INSERT INTO Medewerker_Rol VALUES
(1, 2),
(2, 3),
(3, 4),
(4, 4),
(5, 3),
(6, 4),
(7, 3),
(8, 3);
GO

INSERT INTO Klant_soort VALUES
('Particulier'),
('Groothandel'),
('Inbouwstation');
GO

INSERT INTO Klant VALUES
(1, 'Klant1', 'achternaamKlant1', 'klant1straat', 1, '1234ab', 'klant1istan', '123456789', 'klant1', 'wwklant1', 'e1@mail.nl', null,null ,null ,null ),
(3, 'Klant2', 'achternaamKlant2', 'klant2straat', 2, '5678cd', 'klant2istan', '123456789', 'klant2', 'wwklant2', 'e2@mail.nl', 'Pieter',1278951 ,10 ,15.00 );
GO

INSERT INTO [Order] VALUES
('2018-10-20', 1, 1),
('2018-10-19', 2, 2);
GO

INSERT INTO Product_groep VALUES
('Autogassysteem'),
('Warmtepomp');
GO

INSERT INTO Product VALUES
(1, 'VSI 1', 'Mooi product', null, 100.00, 200.00, 2),
(2, 'Warm1', 'Mooi warm product', 200.00, null, 200.00, 3);
GO

INSERT INTO Order_Product VALUES
(1, 1),
(2, 2);
GO