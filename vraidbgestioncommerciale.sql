/*==============================================================*/
/* Script SQL unifié pour le système de gestion de boutique     */
/* Version corrigée - Problèmes de clés étrangères résolus      */
/*==============================================================*/

-- Suppression des contraintes de clé étrangère en premier (si elles existent)
DO $$
DECLARE
    r RECORD;
BEGIN
    FOR r IN (
        SELECT 'ALTER TABLE '||nspname||'.'||relname||' DROP CONSTRAINT '||conname||';' AS drop_command
        FROM pg_constraint 
        JOIN pg_class ON conrelid = pg_class.oid 
        JOIN pg_namespace ON pg_class.relnamespace = pg_namespace.oid
        WHERE nspname = 'public'
        AND contype = 'f'
    ) LOOP
        BEGIN
            EXECUTE r.drop_command;
        EXCEPTION WHEN OTHERS THEN
            RAISE NOTICE 'Échec de suppression de la contrainte: %', r.drop_command;
        END;
    END LOOP;
END $$;

-- Suppression des tables en respectant l'ordre des dépendances
DROP TABLE IF EXISTS augmentationPrix CASCADE;
DROP TABLE IF EXISTS variationPrixArticle CASCADE;
DROP TABLE IF EXISTS piececommandee CASCADE;
DROP TABLE IF EXISTS catalogueProduit CASCADE;
DROP TABLE IF EXISTS salaireContrat CASCADE;
DROP TABLE IF EXISTS preferenceClient CASCADE;
DROP TABLE IF EXISTS lier CASCADE;
DROP TABLE IF EXISTS horaireEmployee CASCADE;
DROP TABLE IF EXISTS constituer CASCADE;
DROP TABLE IF EXISTS AugmentationSalaire CASCADE;
DROP TABLE IF EXISTS Session CASCADE;
DROP TABLE IF EXISTS Notifications CASCADE;
DROP TABLE IF EXISTS Conge CASCADE;
DROP TABLE IF EXISTS Employe CASCADE;
DROP TABLE IF EXISTS Contrat CASCADE;
DROP TABLE IF EXISTS Collaborateur CASCADE;
DROP TABLE IF EXISTS CompteUtilisateur CASCADE;
DROP TABLE IF EXISTS Profile CASCADE;
DROP TABLE IF EXISTS Utilisateur CASCADE;
DROP TABLE IF EXISTS TypeCollaborateur CASCADE;
DROP TABLE IF EXISTS TypeConge CASCADE;
DROP TABLE IF EXISTS TypeContrat CASCADE;
DROP TABLE IF EXISTS TypeUtilisateur CASCADE;
DROP TABLE IF EXISTS Salaire CASCADE;
DROP TABLE IF EXISTS VarieteSalaire CASCADE;
DROP TABLE IF EXISTS Poste CASCADE;
DROP TABLE IF EXISTS Permission CASCADE;
DROP TABLE IF EXISTS Role CASCADE;
DROP TABLE IF EXISTS Preference CASCADE;
DROP TABLE IF EXISTS Pays CASCADE;
DROP TABLE IF EXISTS Villes CASCADE;
DROP TABLE IF EXISTS Motif CASCADE;
DROP TABLE IF EXISTS JournalActivite CASCADE;
DROP TABLE IF EXISTS HoraireTravail CASCADE;
DROP TABLE IF EXISTS GestionnaireBoutiques CASCADE;
DROP TABLE IF EXISTS PatronBoutique CASCADE;
DROP TABLE IF EXISTS Client CASCADE;
DROP TABLE IF EXISTS Rapport CASCADE;
DROP TABLE IF EXISTS ArchiveActiviteBoutique CASCADE;
DROP TABLE IF EXISTS Solde CASCADE;
DROP TABLE IF EXISTS SessionCaisse CASCADE;
DROP TABLE IF EXISTS Caisse CASCADE;
DROP TABLE IF EXISTS horairesBoutique CASCADE;
DROP TABLE IF EXISTS HoraireBoutique CASCADE;
DROP TABLE IF EXISTS paramBoutique CASCADE;
DROP TABLE IF EXISTS ConfigurationBoutique CASCADE;
DROP TABLE IF EXISTS Evenement CASCADE;
DROP TABLE IF EXISTS Promotion CASCADE;
DROP TABLE IF EXISTS Boutique CASCADE;
DROP TABLE IF EXISTS collabVente CASCADE;
DROP TABLE IF EXISTS Transaction CASCADE;
DROP TABLE IF EXISTS Bilan CASCADE;
DROP TABLE IF EXISTS Article CASCADE;
DROP TABLE IF EXISTS LigneVente CASCADE;
DROP TABLE IF EXISTS Piece CASCADE;
DROP TABLE IF EXISTS Produit CASCADE;
DROP TABLE IF EXISTS LigneCommande CASCADE;
DROP TABLE IF EXISTS Commande CASCADE;
DROP TABLE IF EXISTS Livraison CASCADE;
DROP TABLE IF EXISTS LigneApprovisionnement CASCADE;
DROP TABLE IF EXISTS Approvisionnement CASCADE;
DROP TABLE IF EXISTS Lot CASCADE;
DROP TABLE IF EXISTS Stock CASCADE;
DROP TABLE IF EXISTS Marchandise CASCADE;
DROP TABLE IF EXISTS Origine CASCADE;
DROP TABLE IF EXISTS CategorieVariete CASCADE;
DROP TABLE IF EXISTS Variete CASCADE;
DROP TABLE IF EXISTS Famille CASCADE;
DROP TABLE IF EXISTS Catalogue CASCADE;
DROP TABLE IF EXISTS Avis CASCADE;
DROP TABLE IF EXISTS FluctuationPrix CASCADE;
DROP TABLE IF EXISTS Periode CASCADE;

/*==============================================================*/
/* Recréation des tables dans le bon ordre                      */
/*==============================================================*/

CREATE TABLE TypeUtilisateur (
   idTypeUser           INT4                 NOT NULL,
   nomType              VARCHAR(254)         NULL,
   description          VARCHAR(254)         NULL,
   niveauAcces          INT4                 NULL,
   CONSTRAINT PK_TYPEUTILISATEUR PRIMARY KEY (idTypeUser)
);

CREATE TABLE Role (
   idRole               INT4                 NOT NULL,
   nomRole              VARCHAR(254)         NULL,
   description          VARCHAR(254)         NULL,
   dateCreation         DATE                 NULL,
   CONSTRAINT PK_ROLE PRIMARY KEY (idRole)
);

CREATE TABLE Utilisateur (
   idUtilisateur        INT4                 NOT NULL,
   idRole               INT4                 NOT NULL,
   idTypeUser           INT4                 NULL,
   nom                  VARCHAR(254)         NULL,
   prenom               VARCHAR(254)         NULL,
   email                VARCHAR(254)         NULL,
   telephone            VARCHAR(254)         NULL,
   motpass              VARCHAR(254)         NULL,
   dateCreation         DATE                 NULL,
   derniereConnexion    DATE                 NULL,
   adresse              VARCHAR(254)         NULL,
   codePostale          VARCHAR(254)         NULL,
   actif                BOOL                 NULL,
   photo                VARCHAR(254)         NULL,
   CONSTRAINT PK_UTILISATEUR PRIMARY KEY (idUtilisateur)
);

CREATE TABLE CompteUtilisateur (
   idcompte             INT4                 NOT NULL,
   idUtilisateur        INT4                 NOT NULL,
   login                VARCHAR(254)         NULL,
   motpasse             VARCHAR(254)         NULL,
   etat                 VARCHAR(254)         NULL,
   datecreation         TIMESTAMP            NULL,
   codesecret           VARCHAR(254)         NULL,
   CONSTRAINT PK_COMPTEUTILISATEUR PRIMARY KEY (idcompte),
   CONSTRAINT AK_IDENTIFIANT_1_COMPTEUT UNIQUE (idcompte)
);

CREATE TABLE Profile (
   idprofile            INT4                 NOT NULL,
   idcompte             INT4                 NOT NULL,
   libelle              VARCHAR(254)         NULL,
   etat                 BOOL                 NULL,
   code                 VARCHAR(254)         NULL,
   datecreation         DATE                 NULL,
   CONSTRAINT PK_PROFILE PRIMARY KEY (idprofile),
   CONSTRAINT AK_IDENTIFIANT_1_PROFILE UNIQUE (idprofile)
);

CREATE TABLE Permission (
   idpermission         INT4                 NOT NULL,
   libelle              VARCHAR(254)         NULL,
   datecreation         DATE                 NULL,
   etat                 VARCHAR(254)         NULL,
   CONSTRAINT PK_PERMISSION PRIMARY KEY (idpermission),
   CONSTRAINT AK_IDENTIFIANT_1_PERMISSI UNIQUE (idpermission)
);

CREATE TABLE Collaborateur (
   idUtilisateur        INT4                 NOT NULL,
   idCollab             INT4                 NOT NULL,
   date                 DATE                 NULL,
   CONSTRAINT PK_COLLABORATEUR PRIMARY KEY (idUtilisateur, idCollab)
);

CREATE TABLE TypeCollaborateur (
   idTC                 INT4                 NOT NULL,
   idUtilisateur        INT4                 NULL,
   idCollab             INT4                 NULL,
   nom                  VARCHAR(254)         NULL,
   valeur               VARCHAR(254)         NULL,
   description          VARCHAR(254)         NULL,
   CONSTRAINT PK_TYPECOLLABORATEUR PRIMARY KEY (idTC)
);

CREATE TABLE TypeContrat (
   idTypeContrat        INT4                 NOT NULL,
   nom                  VARCHAR(254)         NULL,
   valeur               VARCHAR(254)         NULL,
   description          VARCHAR(254)         NULL,
   CONSTRAINT PK_TYPECONTRAT PRIMARY KEY (idTypeContrat)
);

CREATE TABLE VarieteSalaire (
   idVariete            INT4                 NOT NULL,
   nom                  VARCHAR(254)         NULL,
   valeur               VARCHAR(254)         NULL,
   CONSTRAINT PK_VARIETESALAIRE PRIMARY KEY (idVariete)
);

CREATE TABLE Salaire (
   idSalaire            INT4                 NOT NULL,
   idVariete            INT4                 NULL,
   montant              NUMERIC              NULL,
   dateCreation         DATE                 NULL,
   CONSTRAINT PK_SALAIRE PRIMARY KEY (idSalaire)
);
create table AugmentationSalaire (
   idAugmentation       INT4                 not null,
   idContrat            INT4                 null,
   idVariete            INT4                 null,
   date                 DATE                 null,
   isvalide             BOOL                 null,
   montantPrecedent     NUMERIC              null,
   nouveauMontant       NUMERIC              null,
   constraint PK_AUGMENTATIONSALAIRE primary key (idAugmentation)
);


CREATE TABLE Poste (
   idPoste              INT4                 NOT NULL,
   libelle              VARCHAR(254)         NULL,
   description          VARCHAR(254)         NULL,
   niveauResponsabilite INT4                 NULL,
   competencesRequises  TEXT                 NULL,
   CONSTRAINT PK_POSTE PRIMARY KEY (idPoste)
);

CREATE TABLE PatronBoutique (
   idPatron             INT4                 NOT NULL,
   CONSTRAINT PK_PATRONBOUTIQUE PRIMARY KEY (idPatron)
);

CREATE TABLE TypeConge (
   idTypeConge          INT4                 NOT NULL,
   libelle              VARCHAR(254)         NULL,
   description          VARCHAR(254)         NULL,
   CONSTRAINT PK_TYPECONGE PRIMARY KEY (idTypeConge)
);

CREATE TABLE Motif (
   idMotif              INT4                 NOT NULL,
   motif                TEXT                 NULL,
   CONSTRAINT PK_MOTIF PRIMARY KEY (idMotif)
);

CREATE TABLE Preference (
   idPreference         INT4                 NOT NULL,
   cle                  VARCHAR(254)         NULL,
   valeur               VARCHAR(254)         NULL,
   type                 VARCHAR(254)         NULL,
   CONSTRAINT PK_PREFERENCE PRIMARY KEY (idPreference)
);

CREATE TABLE Client (
   idClient             INT4                 NOT NULL,
   pointFidelite        INT4                 NULL,
   dateInscription      DATE                 NULL,
   coordonneeGPS        VARCHAR(254)         NULL,
   CONSTRAINT PK_CLIENT PRIMARY KEY (idClient)
);

CREATE TABLE Pays (
   idPays               INT4                 NOT NULL,
   idUtilisateur        INT4                 NULL,
   nom                  VARCHAR(254)         NULL,
   CONSTRAINT PK_PAYS PRIMARY KEY (idPays)
);

CREATE TABLE Villes (
   idVille              INT4                 NOT NULL,
   idUtilisateur        INT4                 NULL,
   nom                  VARCHAR(254)         NULL,
   CONSTRAINT PK_VILLES PRIMARY KEY (idVille)
);

CREATE TABLE JournalActivite (
   idJournal            INT4                 NOT NULL,
   dateAction           TIMESTAMP            NULL,
   typeAction           VARCHAR(254)         NULL,
   description          TEXT                 NULL,
   CONSTRAINT PK_JOURNALACTIVITE PRIMARY KEY (idJournal)
);

CREATE TABLE HoraireTravail (
   idHT                 INT4                 NOT NULL,
   datedebut            TIMESTAMP            NULL,
   datefin              TIMESTAMP            NULL,
   typejournee          VARCHAR(254)         NULL,
   valide               INT4                 NULL,
   pause                INT4                 NULL,
   CONSTRAINT PK_HORAIRETRAVAIL PRIMARY KEY (idHT)
);

CREATE TABLE GestionnaireBoutiques (
   idGestionnaire       INT4                 NOT NULL,
   CONSTRAINT PK_GESTIONNAIREBOUTIQUES PRIMARY KEY (idGestionnaire)
);

CREATE TABLE Boutique (
   idBoutique           INT4                 NOT NULL,
   nom                  VARCHAR(254)         NULL,
   adresse              VARCHAR(254)         NULL,
   telephone            VARCHAR(254)         NULL,
   email                VARCHAR(254)         NULL,
   siteweb              VARCHAR(254)         NULL,
   logo                 VARCHAR(254)         NULL,
   actif                BOOL                 NULL,
   dateCreation         DATE                 NULL,
   fermeture            DATE                 NULL,
   deviseUtilisee       VARCHAR(254)         NULL,
   fuseauHoraire        VARCHAR(254)         NULL,
   surface              NUMERIC              NULL,
   statut               VARCHAR(254)         NULL,
   typeBoutique         VARCHAR(254)         NULL,
   coordonneesGPS       VARCHAR(254)         NULL,
   capital              NUMERIC              NULL,
   CONSTRAINT PK_BOUTIQUE PRIMARY KEY (idBoutique)
);

CREATE TABLE ConfigurationBoutique (
   idConfiguration      INT4                 NOT NULL,
   gestionStockAvancee  BOOL                 NULL,
   multiemplacement     BOOL                 NULL,
   tracabiliteLot       BOOL                 NULL,
   gestionAttributs     BOOL                 NULL,
   multidevise         BOOL                 NULL,
   multilangue         BOOL                 NULL,
   locationProduits    BOOL                 NULL,
   serviceApresVente   BOOL                 NULL,
   programmeAffiliation BOOL                 NULL,
   CONSTRAINT PK_CONFIGURATIONBOUTIQUE PRIMARY KEY (idConfiguration)
);

CREATE TABLE HoraireBoutique (
   idHoraire            INT4                 NOT NULL,
   jourSemaine          VARCHAR(254)         NULL,
   heureopen            TIME                 NULL,
   heureFermeture       TIME                 NULL,
   ouvert               BOOL                 NULL,
   casimprevu           BOOL                 NULL,
   CONSTRAINT PK_HORAIREBOUTIQUE PRIMARY KEY (idHoraire)
);

CREATE TABLE Periode (
   idPeriode            INT4                 NOT NULL,
   datedebut            DATE                 NULL,
   datefin              DATE                 NULL,
   CONSTRAINT PK_PERIODE PRIMARY KEY (idPeriode)
);

CREATE TABLE Catalogue (
   idcatalogue          INT4                 NOT NULL,
   nom                  VARCHAR(254)         NULL,
   code                 VARCHAR(254)         NULL,
   datecreation         DATE                 NULL,
   etat                 VARCHAR(254)         NULL,
   nombreelements       INT4                 NULL,
   description          TEXT                 NULL,
   CONSTRAINT PK_CATALOGUE PRIMARY KEY (idcatalogue),
   CONSTRAINT AK_IDENTIFIANT_1_CATALOGU UNIQUE (idcatalogue)
);

CREATE TABLE Famille (
   idfamille            INT4                 NOT NULL,
   Fam_idfamille        INT4                 NULL,
   nom                  VARCHAR(254)         NULL,
   code                 VARCHAR(254)         NULL,
   datecreation         DATE                 NULL,
   etat                 VARCHAR(254)         NULL,
   CONSTRAINT PK_FAMILLE PRIMARY KEY (idfamille)
);

CREATE TABLE Origine (
   idOrigine            INT4                 NOT NULL,
   nomOrigine           VARCHAR(254)         NULL,
   CONSTRAINT PK_ORIGINE PRIMARY KEY (idOrigine)
);

CREATE TABLE Marchandise (
   idMarchandise        INT4                 NOT NULL,
   idBoutique           INT4                 NULL,
   designation          VARCHAR(254)         NULL,
   reference            VARCHAR(254)         NULL,
   code                 VARCHAR(254)         NULL,
   etat                 VARCHAR(254)         NULL,
   datecreation         DATE                 NULL,
   CONSTRAINT PK_MARCHANDISE PRIMARY KEY (idMarchandise),
   CONSTRAINT AK_IDENTIFIANT_1_MARCHAND UNIQUE (idMarchandise)
);

CREATE TABLE Stock (
   idstock              INT4                 NOT NULL,
   idEmploye            INT4                 NULL,
   idBoutique           INT4                 NULL,
   designation          VARCHAR(254)         NULL,
   reference            VARCHAR(254)         NULL,
   quantiteenStock      INT4                 NULL,
   datecreation         DATE                 NULL,
   etat                 VARCHAR(254)         NULL,
   quantiterestance     INT4                 NULL,
   depenses             NUMERIC              NULL,
   ventecumul           NUMERIC              NULL,
   benefices            NUMERIC              NULL,
   CONSTRAINT PK_STOCK PRIMARY KEY (idstock),
   CONSTRAINT AK_IDENTIFIANT_1_STOCK UNIQUE (idstock)
);

CREATE TABLE LigneApprovisionnement (
   id                   INT4                 NOT NULL,
   quantite             INT4                 NULL,
   prixU                NUMERIC              NULL,
   montant              NUMERIC              NULL,
   CONSTRAINT PK_LIGNEAPPROVISIONNEMENT PRIMARY KEY (id)
);

CREATE TABLE Approvisionnement (
   id                   INT4                 NOT NULL,
   Lig_id               INT4                 NULL,
   idCollaborateur      INT4                 NULL,
   idEmploye            INT4                 NULL,
   reference            VARCHAR(254)         NULL,
   dateApprovionnement  DATE                 NULL,
   statut               VARCHAR(254)         NULL,
   montantTotal         NUMERIC              NULL,
   CONSTRAINT PK_APPROVISIONNEMENT PRIMARY KEY (id)
);

CREATE TABLE Lot (
   idLot                INT4                 NOT NULL,
   idstock              INT4                 NULL,
   idMarchandise        INT4                 NULL,
   reference            VARCHAR(254)         NULL,
   description          TEXT                 NULL,
   qtinitiale           INT4                 NULL,
   qtactuelle           INT4                 NULL,
   CONSTRAINT PK_LOT PRIMARY KEY (idLot)
);

CREATE TABLE Piece (
   idpiece              INT4                 NOT NULL,
   idOrigine            INT4                 NULL,
   idMarchandise        INT4                 NULL,
   idLot                INT4                 NULL,
   id                   INT4                 NULL,
   datecreation         DATE                 NULL,
   etat                 VARCHAR(254)         NULL,
   reference            VARCHAR(254)         NULL,
   qrcode               VARCHAR(254)         NULL,
   prixachat            NUMERIC              NULL,
   prixvente            NUMERIC              NULL,
   promotion            BOOL                 NULL,
   paysorigine          VARCHAR(254)         NULL,
   description          TEXT                 NULL,
   CONSTRAINT PK_PIECE PRIMARY KEY (idpiece),
   CONSTRAINT AK_IDENTIFIANT_1_PIECE UNIQUE (idpiece)
);

CREATE TABLE CategorieVariete (
   idcatvariete         INT4                 NOT NULL,
   idpiece              INT4                 NULL,
   designation          VARCHAR(254)         NULL,
   code                 VARCHAR(254)         NULL,
   etat                 VARCHAR(254)         NULL,
   datecretaion         DATE                 NULL,
   nombreelements       INT4                 NULL,
   CONSTRAINT PK_CATEGORIEVARIETE PRIMARY KEY (idcatvariete),
   CONSTRAINT AK_IDENTIFIANT_1_CATEGORI UNIQUE (idcatvariete)
);

CREATE TABLE Variete (
   idcatvariete         INT4                 NOT NULL,
   idvariete            INT4                 NOT NULL,
   designation          VARCHAR(254)         NULL,
   code                 VARCHAR(254)         NULL,
   etat                 VARCHAR(254)         NULL,
   datecreation         DATE                 NULL,
   CONSTRAINT PK_VARIETE PRIMARY KEY (idcatvariete, idvariete),
   CONSTRAINT AK_IDENTIFIANT_1_VARIETE UNIQUE (idvariete)
);

CREATE TABLE LigneCommande (
   id                   INT4                 NOT NULL,
   quantite             INT4                 NULL,
   prixU                NUMERIC              NULL,
   montant              NUMERIC              NULL,
   CONSTRAINT PK_LIGNECOMMANDE PRIMARY KEY (id)
);

CREATE TABLE Livraison (
   id                   INT4                 NOT NULL,
   idCollaborateur      INT4                 NULL,
   reference            VARCHAR(254)         NULL,
   statut               VARCHAR(254)         NULL,
   dateLivraison        DATE                 NULL,
   CONSTRAINT PK_LIVRAISON PRIMARY KEY (id)
);

CREATE TABLE LigneVente (
   id                   INT4                 NOT NULL,
   quantite             INT4                 NULL,
   prixU                NUMERIC              NULL,
   montant              NUMERIC              NULL,
   CONSTRAINT PK_LIGNEVENTE PRIMARY KEY (id)
);

CREATE TABLE Contrat (
   idContrat            INT4                 NOT NULL,
   idUtilisateur        INT4                 NULL,
   idCollab             INT4                 NULL,
   idTypeContrat        INT4                 NULL,
   datedeb              DATE                 NULL,
   datefin              DATE                 NULL,
   avantages            TEXT                 NULL,
   isValid              BOOL                 NULL,
   clauses              TEXT                 NULL,
   CONSTRAINT PK_CONTRAT PRIMARY KEY (idContrat)
);

CREATE TABLE Employe (
   idEmploye            INT4                 NOT NULL,
   idContrat            INT4                 NULL,
   idPatron             INT4                 NULL,
   idPoste              INT4                 NULL,
   poste                VARCHAR(254)         NULL,
   dateEmbauche         DATE                 NULL,
   contrat              VARCHAR(254)         NULL,
   salaire              NUMERIC              NULL,
   statutEmploye        VARCHAR(254)         NULL,
   CONSTRAINT PK_EMPLOYE PRIMARY KEY (idEmploye)
);

CREATE TABLE Conge (
   idConge              INT4                 NOT NULL,
   idEmploye            INT4                 NULL,
   idTypeConge          INT4                 NULL,
   datedeb              DATE                 NULL,
   datefin              DATE                 NULL,
   CONSTRAINT PK_CONGE PRIMARY KEY (idConge)
);

CREATE TABLE Session (
   idHistorique         INT4                 NOT NULL,
   idUtilisateur        INT4                 NULL,
   idJournal            INT4                 NOT NULL,
   dateConnexion        TIMESTAMP            NULL,
   dateDeconnexion      TIMESTAMP            NULL,
   adresseIP            VARCHAR(254)         NULL,
   navigateur           VARCHAR(254)         NULL,
   systemeExploitation  VARCHAR(254)         NULL,
   statut               VARCHAR(254)         NULL,
   CONSTRAINT PK_SESSION PRIMARY KEY (idHistorique)
);

CREATE TABLE Notifications (
   idNotifications      INT4                 NOT NULL,
   idUtilisateur        INT4                 NULL,
   emailNotif           BOOL                 NULL,
   alerteSecurite       BOOL                 NULL,
   alerteTransaction    BOOL                 NULL,
   rappelPanier         INT4                 NULL,
   CONSTRAINT PK_NOTIFICATIONS PRIMARY KEY (idNotifications)
);

CREATE TABLE paramBoutique (
   idConfiguration      INT4                 NOT NULL,
   idBoutique           INT4                 NOT NULL,
   CONSTRAINT PK_PARAMBOUTIQUE PRIMARY KEY (idConfiguration, idBoutique)
);

CREATE TABLE horairesBoutique (
   idHoraire            INT4                 NOT NULL,
   idBoutique           INT4                 NOT NULL,
   CONSTRAINT PK_HORAIREBOUTIQUE2 PRIMARY KEY (idHoraire, idBoutique)
);

CREATE TABLE Caisse (
   idCaisse             INT4                 NOT NULL,
   numeroSerie          VARCHAR(254)         NOT NULL,
   idBoutique           INT4                 NULL,
   idEmploye            INT4                 NULL,
   actif                BOOL                 NULL,
   dateActivation       DATE                 NULL,
   typeCaisse           VARCHAR(254)         NULL,
   montantCaisse        NUMERIC              NULL,
   CONSTRAINT PK_CAISSE PRIMARY KEY (idCaisse, numeroSerie)
);

CREATE TABLE SessionCaisse (
   idSession            INT4                 NOT NULL,
   idCaisse             INT4                 NULL,
   numeroSerie          VARCHAR(254)         NULL,
   date                 DATE                 NULL,
   heure                TIME                 NULL,
   montant              NUMERIC              NULL,
   type                 VARCHAR(254)         NULL,
   CONSTRAINT PK_SESSIONCAISSE PRIMARY KEY (idSession)
);

CREATE TABLE Solde (
   idSolde              INT4                 NOT NULL,
   idBoutique           INT4                 NULL,
   montant              NUMERIC              NULL,
   CONSTRAINT PK_SOLDE PRIMARY KEY (idSolde)
);

CREATE TABLE ArchiveActiviteBoutique (
   idlog                INT4                 NOT NULL,
   idBoutique           INT4                 NULL,
   dateEnregisrement    DATE                 NULL,
   actionEffectuer      VARCHAR(254)         NULL,
   description          VARCHAR(254)         NULL,
   CONSTRAINT PK_ARCHIVEACTIVITEBOUTIQUE PRIMARY KEY (idlog)
);

CREATE TABLE Evenement (
   idEvenement          INT4                 NOT NULL,
   idBoutique           INT4                 NULL,
   titre                VARCHAR(254)         NULL,
   description          TEXT                 NULL,
   datedebut            DATE                 NULL,
   datefin              DATE                 NULL,
   type                 VARCHAR(254)         NULL,
   statut               VARCHAR(254)         NULL,
   CONSTRAINT PK_EVENEMENT PRIMARY KEY (idEvenement)
);

CREATE TABLE Promotion (
   idPromotion          INT4                 NOT NULL,
   idBoutique           INT4                 NULL,
   idPeriode            INT4                 NULL,
   description          TEXT                 NULL,
   date                 DATE                 NULL,
   CONSTRAINT PK_PROMOTION PRIMARY KEY (idPromotion)
);

CREATE TABLE Rapport (
   idRapport            INT4                 NOT NULL,
   idlog                INT4                 NULL,
   titre                VARCHAR(254)         NULL,
   type                 VARCHAR(254)         NULL,
   dateCreation         DATE                 NULL,
   contenu              TEXT                 NULL,
   format               VARCHAR(254)         NULL,
   CONSTRAINT PK_RAPPORT PRIMARY KEY (idRapport),
   CONSTRAINT AK_IDENTIFIANT_1_RAPPORT UNIQUE (idRapport)
);

CREATE TABLE Commande (
   id                   INT4                 NOT NULL,
   idClient             INT4                 NULL,
   Liv_id               INT4                 NULL,
   Lig_id               INT4                 NULL,
   reference            VARCHAR(254)         NULL,
   dateCommande         DATE                 NULL,
   statut               VARCHAR(254)         NULL,
   montant              FLOAT8               NULL,
   modePaiement         VARCHAR(254)         NULL,
   CONSTRAINT PK_COMMANDE PRIMARY KEY (id)
);

CREATE TABLE piececommandee (
   id                   INT4                 NOT NULL,
   idpiece              INT4                 NOT NULL,
   CONSTRAINT PK_PIECECOMMANDEE PRIMARY KEY (id, idpiece)
);

CREATE TABLE Produit (
   idproduit            INT4                 NOT NULL,
   id                   INT4                 NULL,
   idfamille            INT4                 NULL,
   idMarchandise        INT4                 NULL,
   Lig_id               INT4                 NULL,
   idstock              INT4                 NULL,
   Lig_id2              INT4                 NULL,
   designation          VARCHAR(254)         NULL,
   reference            VARCHAR(254)         NULL,
   code                 VARCHAR(254)         NULL,
   etat                 VARCHAR(254)         NULL,
   datecreation         DATE                 NULL,
   prix                 NUMERIC              NULL,
   qrcode               VARCHAR(254)         NULL,
   nom                  VARCHAR(254)         NULL,
   description          TEXT                 NULL,
   CONSTRAINT PK_PRODUIT PRIMARY KEY (idproduit),
   CONSTRAINT AK_IDENTIFIANT_1_PRODUIT UNIQUE (idproduit)
);

CREATE TABLE catalogueProduit (
   idproduit            INT4                 NOT NULL,
   idcatalogue          INT4                 NOT NULL,
   CONSTRAINT PK_CATALOGUEPRODUIT PRIMARY KEY (idproduit, idcatalogue)
);

CREATE TABLE Vente (
   idvente              INT4                 NOT NULL,
   id                   INT4                 NULL,
   idEmploye            INT4                 NULL,
   montant              NUMERIC              NULL,
   reference            VARCHAR(254)         NULL,
   CONSTRAINT PK_VENTE PRIMARY KEY (idvente)
);

CREATE TABLE collabVente (
   idVente              INT4                 NOT NULL,
   idEmploye            INT4                 NOT NULL,
   idCollab             INT4                 NOT NULL,
   CONSTRAINT PK_COLLABVENTE PRIMARY KEY (idVente, idEmploye, idCollab)
);

CREATE TABLE Avis (
   idAvis               INT4                 NOT NULL,
   idClient             INT4                 NULL,
   note                 INT4                 NULL,
   commentaire          TEXT                 NULL,
   dateCreation         DATE                 NULL,
   CONSTRAINT PK_AVIS PRIMARY KEY (idAvis)
);

CREATE TABLE FluctuationPrix (
   idHistorique         INT4                 NOT NULL,
   ancienPrix           NUMERIC              NULL,
   nouveauPrix          NUMERIC              NULL,
   dateChangement       DATE                 NULL,
   raison               VARCHAR(254)         NULL,
   CONSTRAINT PK_FLUCTUATIONPRIX PRIMARY KEY (idHistorique)
);

CREATE TABLE Bilan (
   IdBilan              INT4                 NOT NULL,
   idVente              INT4                 NULL,
   DateDebut            DATE                 NULL,
   DateFin              DATE                 NULL,
   Type                 VARCHAR(254)         NULL,
   chiffreAffaire       NUMERIC              NULL,
   BeneficeBrut         NUMERIC              NULL,
   Charge               NUMERIC              NULL,
   BenefiveNet          NUMERIC              NULL,
   CONSTRAINT PK_BILAN PRIMARY KEY (IdBilan)
);

CREATE TABLE Transaction (
   IdTransaction        INT4                 NOT NULL,
   IdBilan              INT4                 NULL,
   idVente              INT4                 NULL,
   DateTransaction      DATE                 NULL,
   Type                 VARCHAR(254)         NULL,
   Montant              NUMERIC              NULL,
   Description          TEXT                 NULL,
   PieceComptable       VARCHAR(254)         NULL,
   CONSTRAINT PK_TRANSACTION PRIMARY KEY (IdTransaction)
);

CREATE TABLE Article (
   IdArticle            INT4                 NOT NULL,
   idLigneVente         INT4                 NULL,
   CONSTRAINT PK_ARTICLE PRIMARY KEY (IdArticle)
);

CREATE TABLE constituer (
   idpermission         INT4                 NOT NULL,
   idRole               INT4                 NOT NULL,
   CONSTRAINT PK_CONSTITUER PRIMARY KEY (idpermission, idRole)
);

CREATE TABLE horaireEmployee (
   idHT                 INT4                 NOT NULL,
   idEmploye            INT4                 NOT NULL,
   CONSTRAINT PK_HORAIREEMPLOYEE PRIMARY KEY (idHT, idEmploye)
);

CREATE TABLE lier (
   idConge              INT4                 NOT NULL,
   idMotif              INT4                 NOT NULL,
   CONSTRAINT PK_LIER PRIMARY KEY (idConge, idMotif)
);

CREATE TABLE preferenceClient (
   idPreference         INT4                 NOT NULL,
   idClient             INT4                 NOT NULL,
   CONSTRAINT PK_PREFERENCECLIENT PRIMARY KEY (idPreference, idClient)
);

CREATE TABLE salaireContrat (
   idContrat            INT4                 NOT NULL,
   idSalaire            INT4                 NOT NULL,
   CONSTRAINT PK_SALAIRECONTRAT PRIMARY KEY (idContrat, idSalaire)
);

CREATE TABLE variationPrixArticle (
   idpiece              INT4                 NOT NULL,
   idHistorique         INT4                 NOT NULL,
   CONSTRAINT PK_VARIATIONPRIXARTICLE PRIMARY KEY (idpiece, idHistorique)
);

CREATE TABLE augmentationPrix (
   idPeriode            INT4                 NOT NULL,
   idHistorique         INT4                 NOT NULL,
   CONSTRAINT PK_AUGMENTATIONPRIX PRIMARY KEY (idPeriode, idHistorique)
);

/*==============================================================*/
/* Création des index                                           */
/*==============================================================*/

-- Index pour la table Utilisateur
CREATE INDEX IF NOT EXISTS AFFECTERROLEUSER_FK ON Utilisateur (idRole);
CREATE INDEX IF NOT EXISTS ASSOCIERTYPEUSER_FK ON Utilisateur (idTypeUser);

-- Index pour la table CompteUtilisateur
CREATE INDEX IF NOT EXISTS ASSOCIERCOMPTE_FK ON CompteUtilisateur (idUtilisateur);

-- Index pour la table Profile
CREATE INDEX IF NOT EXISTS PROFILUSER_FK ON Profile (idcompte);

-- Index pour la table Collaborateur
CREATE INDEX IF NOT EXISTS GENERALISATION_1_FK ON Collaborateur (idUtilisateur);

-- Index pour la table Contrat
CREATE INDEX IF NOT EXISTS CONTRATCOLLABORATEUR_FK ON Contrat (idUtilisateur, idCollab);
CREATE INDEX IF NOT EXISTS CONTRATTYPE_FK ON Contrat (idTypeContrat);

-- Index pour la table Salaire
CREATE INDEX IF NOT EXISTS VARIETEDELASALAIRE_FK ON Salaire (idVariete);

-- Index pour la table AugmentationSalaire
CREATE INDEX IF NOT EXISTS SALAIREAUGMENTE_FK ON AugmentationSalaire (idContrat);
CREATE INDEX IF NOT EXISTS VARIETEAUGMANTATION_FK ON AugmentationSalaire (idVariete);

-- Index pour la table Employe
CREATE INDEX IF NOT EXISTS POSTEEMPLYEE_FK ON Employe (idPoste);
CREATE INDEX IF NOT EXISTS CONTRATEMPLOYEE_FK ON Employe (idContrat);
CREATE INDEX IF NOT EXISTS GERER_FK ON Employe (idPatron);

-- Index pour la table Conge
CREATE INDEX IF NOT EXISTS ALLERCONGE_FK ON Conge (idEmploye);
CREATE INDEX IF NOT EXISTS CONGERTYPE_FK ON Conge (idTypeConge);

-- Index pour la table Notifications
CREATE INDEX IF NOT EXISTS RECEVOIR_FK ON Notifications (idUtilisateur);

-- Index pour la table Pays
CREATE INDEX IF NOT EXISTS APPARTENIRPAYS_FK ON Pays (idUtilisateur);

-- Index pour la table Villes
CREATE INDEX IF NOT EXISTS VILLEAPPARTENANCE_FK ON Villes (idUtilisateur);

-- Index pour la table TypeCollaborateur
CREATE INDEX IF NOT EXISTS TYPECOLLABORATION_FK ON TypeCollaborateur (idUtilisateur, idCollab);

-- Index pour la table Session
CREATE INDEX IF NOT EXISTS ENREGISTRER_FK ON Session (idUtilisateur);
CREATE INDEX IF NOT EXISTS ARCHIVERACTIVITY_FK ON Session (idJournal);

-- Index pour la table constituer
CREATE INDEX IF NOT EXISTS CONSTITUER_FK ON constituer (idpermission);
CREATE INDEX IF NOT EXISTS CONSTITUER_FK2 ON constituer (idRole);

-- Index pour la table horaireEmployee
CREATE INDEX IF NOT EXISTS HORAIREEMPLOYEE_FK ON horaireEmployee (idHT);
CREATE INDEX IF NOT EXISTS HORAIREEMPLOYEE_FK2 ON horaireEmployee (idEmploye);

-- Index pour la table lier
CREATE INDEX IF NOT EXISTS LIER_FK ON lier (idConge);
CREATE INDEX IF NOT EXISTS LIER_FK2 ON lier (idMotif);

-- Index pour la table preferenceClient
CREATE INDEX IF NOT EXISTS PREFERENCECLIENT_FK ON preferenceClient (idPreference);
CREATE INDEX IF NOT EXISTS PREFERENCECLIENT_FK2 ON preferenceClient (idClient);

-- Index pour la table salaireContrat
CREATE INDEX IF NOT EXISTS SALAIRECONTRAT_FK ON salaireContrat (idContrat);
CREATE INDEX IF NOT EXISTS SALAIRECONTRAT_FK2 ON salaireContrat (idSalaire);

-- Index pour la table Boutique
CREATE INDEX IF NOT EXISTS BOUTIQUE_PK ON Boutique (idBoutique);

-- Index pour la table Caisse
CREATE INDEX IF NOT EXISTS CAISSE_PK ON Caisse (idCaisse, numeroSerie);
CREATE INDEX IF NOT EXISTS CAISSEBOUTIQUE_FK ON Caisse (idBoutique);
CREATE INDEX IF NOT EXISTS ENTREECAISSEPAR_FK ON Caisse (idEmploye);

-- Index pour la table SessionCaisse
CREATE INDEX IF NOT EXISTS SESSIONCAISSE_PK ON SessionCaisse (idSession);
CREATE INDEX IF NOT EXISTS OPENSESSIONCAISSE_FK ON SessionCaisse (idCaisse, numeroSerie);

-- Index pour la table Solde
CREATE INDEX IF NOT EXISTS SOLDE_PK ON Solde (idSolde);
CREATE INDEX IF NOT EXISTS SOLDEBOUTIQUE_FK ON Solde (idBoutique);

-- Index pour la table ArchiveActiviteBoutique
CREATE INDEX IF NOT EXISTS ARCHIVEACTIVITEBOUTIQUE_PK ON ArchiveActiviteBoutique (idlog);
CREATE INDEX IF NOT EXISTS LOGBOUTIQUE_FK ON ArchiveActiviteBoutique (idBoutique);

-- Index pour la table Evenement
CREATE INDEX IF NOT EXISTS EVENEMENT_PK ON Evenement (idEvenement);
CREATE INDEX IF NOT EXISTS EVENEMENTBOUTIQUE_FK ON Evenement (idBoutique);

-- Index pour la table Promotion
CREATE INDEX IF NOT EXISTS PROMOTION_PK ON Promotion (idPromotion);
CREATE INDEX IF NOT EXISTS PROMOBOUTIQUE_FK ON Promotion (idBoutique);
CREATE INDEX IF NOT EXISTS PERIODEPROMO_FK ON Promotion (idPeriode);

-- Index pour la table Rapport
CREATE INDEX IF NOT EXISTS RAPPORT_PK ON Rapport (idRapport);
CREATE INDEX IF NOT EXISTS GENERERRAPPORT_FK ON Rapport (idlog);

-- Index pour la table horairesBoutique
CREATE INDEX IF NOT EXISTS HORAIREBOUTIQUE_PK2 ON horairesBoutique (idHoraire, idBoutique);
CREATE INDEX IF NOT EXISTS HORAIREBOUTIQUE_FK ON horairesBoutique (idHoraire);
CREATE INDEX IF NOT EXISTS HORAIREBOUTIQUE_FK2 ON horairesBoutique (idBoutique);

-- Index pour la table paramBoutique
CREATE INDEX IF NOT EXISTS PARAMBOUTIQUE_PK ON paramBoutique (idConfiguration, idBoutique);
CREATE INDEX IF NOT EXISTS PARAMBOUTIQUE_FK ON paramBoutique (idConfiguration);
CREATE INDEX IF NOT EXISTS PARAMBOUTIQUE_FK2 ON paramBoutique (idBoutique);

-- Index pour la table Catalogue
CREATE INDEX IF NOT EXISTS CATALOGUE_PK ON Catalogue (idcatalogue);

-- Index pour la table Famille
CREATE INDEX IF NOT EXISTS FAMILLE_PK ON Famille (idfamille);
CREATE INDEX IF NOT EXISTS SOUSFAMILLE_FK ON Famille (Fam_idfamille);

-- Index pour la table Marchandise
CREATE INDEX IF NOT EXISTS MARCHANDISE_PK ON Marchandise (idMarchandise);
CREATE INDEX IF NOT EXISTS MARCHANDISEBOUTIQUE_FK ON Marchandise (idBoutique);

-- Index pour la table Stock
CREATE INDEX IF NOT EXISTS STOCK_PK ON Stock (idstock);
CREATE INDEX IF NOT EXISTS STOCKBOUTIQUE_FK ON Stock (idBoutique);
CREATE INDEX IF NOT EXISTS GERERSTOCK_FK ON Stock (idEmploye);

-- Index pour la table Lot
CREATE INDEX IF NOT EXISTS LOT_PK ON Lot (idLot);
CREATE INDEX IF NOT EXISTS STOCKDULOT_FK ON Lot (idstock);
CREATE INDEX IF NOT EXISTS LOTMARCHANDISE_FK ON Lot (idMarchandise);

-- Index pour la table Piece
CREATE INDEX IF NOT EXISTS PIECE_PK ON Piece (idpiece);
CREATE INDEX IF NOT EXISTS LOTARTICLE_FK ON Piece (idLot);
CREATE INDEX IF NOT EXISTS APPROPIECE_FK ON Piece (id);
CREATE INDEX IF NOT EXISTS PIECEMARCHANDISE_FK ON Piece (idMarchandise);
CREATE INDEX IF NOT EXISTS ORIGINEPIECE_FK ON Piece (idOrigine);

-- Index pour la table CategorieVariete
CREATE INDEX IF NOT EXISTS CATEGORIEVARIETE_PK ON CategorieVariete (idcatvariete);
CREATE INDEX IF NOT EXISTS CATEGORIEVIARIETEARTICLE_FK ON CategorieVariete (idpiece);

-- Index pour la table Variete
CREATE INDEX IF NOT EXISTS VARIETE_PK ON Variete (idcatvariete, idvariete);
CREATE INDEX IF NOT EXISTS VARITECATEGORIE_FK ON Variete (idcatvariete);

-- Index pour la table Commande
CREATE INDEX IF NOT EXISTS COMMANDE_PK ON Commande (id);
CREATE INDEX IF NOT EXISTS CANALCOMMANDE_FK ON Commande (Lig_id);
CREATE INDEX IF NOT EXISTS LIVRERCOMMANDE_FK ON Commande (Liv_id);
CREATE INDEX IF NOT EXISTS EFFECTUERCOMMANDER_FK ON Commande (idClient);

-- Index pour la table Livraison
CREATE INDEX IF NOT EXISTS LIVRAISON_PK ON Livraison (id);
CREATE INDEX IF NOT EXISTS EFFECTUERLIVRAISON_FK ON Livraison (idCollaborateur);

-- Index pour la table Produit
CREATE INDEX IF NOT EXISTS PRODUIT_PK ON Produit (idproduit);
CREATE INDEX IF NOT EXISTS PRODUITMARCHANDISE_FK ON Produit (idMarchandise);
CREATE INDEX IF NOT EXISTS FAMILLEPRODUIT_FK ON Produit (idfamille);
CREATE INDEX IF NOT EXISTS STOCKPRODUIT_FK ON Produit (idstock);
CREATE INDEX IF NOT EXISTS CANALCOMMANDEPRODUIT_FK ON Produit (id);
CREATE INDEX IF NOT EXISTS CANALVENTEPRODUIT_FK ON Produit (Lig_id2);
CREATE INDEX IF NOT EXISTS CANALAPPROPRODUIT_FK ON Produit (Lig_id);

-- Index pour la table Vente
CREATE INDEX IF NOT EXISTS VENTE_PK ON Vente (idvente);
CREATE INDEX IF NOT EXISTS CANALVENTE_FK ON Vente (id);
CREATE INDEX IF NOT EXISTS GERERVENTE_FK ON Vente (idEmploye);

-- Index pour la table collabVente
CREATE INDEX IF NOT EXISTS COLLABVENTE_PK ON collabVente (idVente, idEmploye, idCollab);
CREATE INDEX IF NOT EXISTS COLLABVENTE_FK ON collabVente (idVente);
CREATE INDEX IF NOT EXISTS COLLABVENTE_FK2 ON collabVente (idEmploye, idCollab);

-- Index pour la table Avis
CREATE INDEX IF NOT EXISTS AVIS_PK ON Avis (idAvis);
CREATE INDEX IF NOT EXISTS AVISCLIENT_FK ON Avis (idClient);

-- Index pour la table FluctuationPrix
CREATE INDEX IF NOT EXISTS FLUCTUATIONPRIX_PK ON FluctuationPrix (idHistorique);

-- Index pour la table variationPrixArticle
CREATE INDEX IF NOT EXISTS VARIATIONPRIXARTICLE_PK ON variationPrixArticle (idpiece, idHistorique);
CREATE INDEX IF NOT EXISTS VARIATIONPRIXARTICLE_FK ON variationPrixArticle (idpiece);
CREATE INDEX IF NOT EXISTS VARIATIONPRIXARTICLE_FK2 ON variationPrixArticle (idHistorique);

-- Index pour la table augmentationPrix
CREATE INDEX IF NOT EXISTS AUGMENTATIONPRIX_PK ON augmentationPrix (idPeriode, idHistorique);
CREATE INDEX IF NOT EXISTS AUGMENTATIONPRIX_FK ON augmentationPrix (idPeriode);
CREATE INDEX IF NOT EXISTS AUGMENTATIONPRIX_FK2 ON augmentationPrix (idHistorique);

-- Index pour la table Bilan
CREATE INDEX IF NOT EXISTS BILAN_PK ON Bilan (IdBilan);
CREATE INDEX IF NOT EXISTS BILANVENTE_FK ON Bilan (idVente);

-- Index pour la table Transaction
CREATE INDEX IF NOT EXISTS TRANSACTION_PK ON Transaction (IdTransaction);
CREATE INDEX IF NOT EXISTS TRANSACTIONVENTE_FK ON Transaction (idVente);
CREATE INDEX IF NOT EXISTS BILANTRANSACTION_FK ON Transaction (IdBilan);

-- Index pour la table Article
CREATE INDEX IF NOT EXISTS ARTICLE_PK ON Article (IdArticle);
CREATE INDEX IF NOT EXISTS CANALVENTEARTICLE_FK ON Article (idLigneVente);

-- Index pour la table LigneVente
CREATE INDEX IF NOT EXISTS LIGNEVENTE_PK ON LigneVente (id);

-- Index pour la table Approvisionnement
CREATE INDEX IF NOT EXISTS APPROVISIONNEMENT_PK ON Approvisionnement (id);
CREATE INDEX IF NOT EXISTS APPROVISIONNEMENTSTOCK_FK ON Approvisionnement (idCollaborateur);
CREATE INDEX IF NOT EXISTS CANALAPPROVISIONNEMENT_FK ON Approvisionnement (Lig_id);
CREATE INDEX IF NOT EXISTS GERERAPPRO_FK ON Approvisionnement (idEmploye);

-- Index pour la table LigneApprovisionnement
CREATE INDEX IF NOT EXISTS LIGNEAPPROVISIONNEMENT_PK ON LigneApprovisionnement (id);

-- Index pour la table LigneCommande
CREATE INDEX IF NOT EXISTS LIGNECOMMANDE_PK ON LigneCommande (id);

-- Index pour la table piececommandee
CREATE INDEX IF NOT EXISTS PIECECOMMANDEE_PK ON piececommandee (id, idpiece);
CREATE INDEX IF NOT EXISTS PIECECOMMANDEE_FK ON piececommandee (id);
CREATE INDEX IF NOT EXISTS PIECECOMMANDEE_FK2 ON piececommandee (idpiece);

-- Index pour la table catalogueProduit
CREATE INDEX IF NOT EXISTS CATALOGUEPRODUIT_PK ON catalogueProduit (idproduit, idcatalogue);
CREATE INDEX IF NOT EXISTS CATALOGUEPRODUIT_FK ON catalogueProduit (idproduit);
CREATE INDEX IF NOT EXISTS CATALOGUEPRODUIT_FK2 ON catalogueProduit (idcatalogue);

/*==============================================================*/
/* Ajout des contraintes de clé étrangère corrigées             */
/*==============================================================*/

-- Contraintes pour la table Utilisateur
ALTER TABLE Utilisateur ADD CONSTRAINT FK_UTILISAT_AFFECTERR_ROLE 
    FOREIGN KEY (idRole) REFERENCES Role (idRole) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Utilisateur ADD CONSTRAINT FK_UTILISAT_ASSOCIERT_TYPEUTIL 
    FOREIGN KEY (idTypeUser) REFERENCES TypeUtilisateur (idTypeUser) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table CompteUtilisateur
ALTER TABLE CompteUtilisateur ADD CONSTRAINT FK_COMPTEUT_ASSOCIERC_UTILISAT 
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Profile
ALTER TABLE Profile ADD CONSTRAINT FK_PROFILE_PROFILUSE_COMPTEUT 
    FOREIGN KEY (idcompte) REFERENCES CompteUtilisateur (idcompte) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Collaborateur
ALTER TABLE Collaborateur ADD CONSTRAINT FK_COLLABOR_GENERALIS_UTILISAT 
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table TypeCollaborateur
ALTER TABLE TypeCollaborateur ADD CONSTRAINT FK_TYPECOLL_TYPECOLLA_COLLABOR 
    FOREIGN KEY (idUtilisateur, idCollab) REFERENCES Collaborateur (idUtilisateur, idCollab) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Contrat
ALTER TABLE Contrat ADD CONSTRAINT FK_CONTRAT_CONTRATCO_COLLABOR 
    FOREIGN KEY (idUtilisateur, idCollab) REFERENCES Collaborateur (idUtilisateur, idCollab) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Contrat ADD CONSTRAINT FK_CONTRAT_CONTRATTY_TYPECONT 
    FOREIGN KEY (idTypeContrat) REFERENCES TypeContrat (idTypeContrat) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Salaire
ALTER TABLE Salaire ADD CONSTRAINT FK_SALAIRE_VARIETEDE_VARIETES 
    FOREIGN KEY (idVariete) REFERENCES VarieteSalaire (idVariete) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table salaireContrat
ALTER TABLE salaireContrat ADD CONSTRAINT FK_SALAIREC_SALAIRECO_CONTRAT 
    FOREIGN KEY (idContrat) REFERENCES Contrat (idContrat) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE salaireContrat ADD CONSTRAINT FK_SALAIREC_SALAIRECO_SALAIRE 
    FOREIGN KEY (idSalaire) REFERENCES Salaire (idSalaire) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table AugmentationSalaire
ALTER TABLE AugmentationSalaire ADD CONSTRAINT FK_AUGMENTA_SALAIREAU_CONTRAT 
    FOREIGN KEY (idContrat) REFERENCES Contrat (idContrat) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE AugmentationSalaire ADD CONSTRAINT FK_AUGMENTA_VARIETEAU_VARIETES 
    FOREIGN KEY (idVariete) REFERENCES VarieteSalaire (idVariete) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Employe
ALTER TABLE Employe ADD CONSTRAINT FK_EMPLOYE_CONTRATEM_CONTRAT 
    FOREIGN KEY (idContrat) REFERENCES Contrat (idContrat) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Employe ADD CONSTRAINT FK_EMPLOYE_GERER_PATRONBO 
    FOREIGN KEY (idPatron) REFERENCES PatronBoutique (idPatron) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Employe ADD CONSTRAINT FK_EMPLOYE_POSTEEMPL_POSTE 
    FOREIGN KEY (idPoste) REFERENCES Poste (idPoste) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Conge
ALTER TABLE Conge ADD CONSTRAINT FK_CONGE_ALLERCONG_EMPLOYE 
    FOREIGN KEY (idEmploye) REFERENCES Employe (idEmploye) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Conge ADD CONSTRAINT FK_CONGE_CONGERTYP_TYPECONG 
    FOREIGN KEY (idTypeConge) REFERENCES TypeConge (idTypeConge) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table lier
ALTER TABLE lier ADD CONSTRAINT FK_LIER_LIER_CONGE 
    FOREIGN KEY (idConge) REFERENCES Conge (idConge) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE lier ADD CONSTRAINT FK_LIER_LIER_MOTIF 
    FOREIGN KEY (idMotif) REFERENCES Motif (idMotif) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table horaireEmployee
ALTER TABLE horaireEmployee ADD CONSTRAINT FK_HORAIREE_HORAIREEM_EMPLOYE 
    FOREIGN KEY (idEmploye) REFERENCES Employe (idEmploye) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE horaireEmployee ADD CONSTRAINT FK_HORAIREE_HORAIREEM_HORAIRET 
    FOREIGN KEY (idHT) REFERENCES HoraireTravail (idHT) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Notifications
ALTER TABLE Notifications ADD CONSTRAINT FK_NOTIFICA_RECEVOIR_UTILISAT 
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Pays
ALTER TABLE Pays ADD CONSTRAINT FK_PAYS_APPARTENI_UTILISAT 
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Villes
ALTER TABLE Villes ADD CONSTRAINT FK_VILLES_VILLEAPPA_UTILISAT 
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Session
ALTER TABLE Session ADD CONSTRAINT FK_SESSION_ARCHIVERA_JOURNALA 
    FOREIGN KEY (idJournal) REFERENCES JournalActivite (idJournal) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Session ADD CONSTRAINT FK_SESSION_ENREGISTR_UTILISAT 
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table constituer
ALTER TABLE constituer ADD CONSTRAINT FK_CONSTITU_CONSTITUE_PERMISSI 
    FOREIGN KEY (idpermission) REFERENCES Permission (idpermission) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE constituer ADD CONSTRAINT FK_CONSTITU_CONSTITUE_ROLE 
    FOREIGN KEY (idRole) REFERENCES Role (idRole) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table preferenceClient
ALTER TABLE preferenceClient ADD CONSTRAINT FK_PREFEREN_PREFERENC_CLIENT 
    FOREIGN KEY (idClient) REFERENCES Client (idClient) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE preferenceClient ADD CONSTRAINT FK_PREFEREN_PREFERENC_PREFEREN 
    FOREIGN KEY (idPreference) REFERENCES Preference (idPreference) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Caisse
ALTER TABLE Caisse ADD CONSTRAINT FK_CAISSE_CAISSEBOU_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Caisse ADD CONSTRAINT FK_CAISSE_ENTREECAI_EMPLOYE 
    FOREIGN KEY (idEmploye) REFERENCES Employe (idEmploye) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table SessionCaisse
ALTER TABLE SessionCaisse ADD CONSTRAINT FK_SESSIONC_OPENSESSI_CAISSE 
    FOREIGN KEY (idCaisse, numeroSerie) REFERENCES Caisse (idCaisse, numeroSerie) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Solde
ALTER TABLE Solde ADD CONSTRAINT FK_SOLDE_SOLDEBOUT_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table ArchiveActiviteBoutique
ALTER TABLE ArchiveActiviteBoutique ADD CONSTRAINT FK_ARCHIVEA_LOGBOUTIQ_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Evenement
ALTER TABLE Evenement ADD CONSTRAINT FK_EVENEMEN_EVENEMENT_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Promotion
ALTER TABLE Promotion ADD CONSTRAINT FK_PROMOTIO_PERIODEPR_PERIODE 
    FOREIGN KEY (idPeriode) REFERENCES Periode (idPeriode) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Promotion ADD CONSTRAINT FK_PROMOTIO_PROMOBOUT_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Rapport
ALTER TABLE Rapport ADD CONSTRAINT FK_RAPPORT_GENERERRA_ARCHIVEA 
    FOREIGN KEY (idlog) REFERENCES ArchiveActiviteBoutique (idlog) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table horairesBoutique
ALTER TABLE horairesBoutique ADD CONSTRAINT FK_HORAIREB_HORAIREBO_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE horairesBoutique ADD CONSTRAINT FK_HORAIREB_HORAIREBO_HORAIREB 
    FOREIGN KEY (idHoraire) REFERENCES HoraireBoutique (idHoraire) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table paramBoutique
ALTER TABLE paramBoutique ADD CONSTRAINT FK_PARAMBOU_PARAMBOUT_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE paramBoutique ADD CONSTRAINT FK_PARAMBOU_PARAMBOUT_CONFIGUR 
    FOREIGN KEY (idConfiguration) REFERENCES ConfigurationBoutique (idConfiguration) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Famille
ALTER TABLE Famille ADD CONSTRAINT FK_FAMILLE_SOUSFAMIL_FAMILLE 
    FOREIGN KEY (Fam_idfamille) REFERENCES Famille (idfamille) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Marchandise
ALTER TABLE Marchandise ADD CONSTRAINT FK_MARCHAND_MARCHANDI_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Stock
ALTER TABLE Stock ADD CONSTRAINT FK_STOCK_GERERSTOC_EMPLOYE 
    FOREIGN KEY (idEmploye) REFERENCES Employe (idEmploye) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Stock ADD CONSTRAINT FK_STOCK_STOCKBOUT_BOUTIQUE 
    FOREIGN KEY (idBoutique) REFERENCES Boutique (idBoutique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Lot
ALTER TABLE Lot ADD CONSTRAINT FK_LOT_LOTMARCHA_MARCHAND 
    FOREIGN KEY (idMarchandise) REFERENCES Marchandise (idMarchandise) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Lot ADD CONSTRAINT FK_LOT_STOCKDULO_STOCK 
    FOREIGN KEY (idstock) REFERENCES Stock (idstock) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;





/*  Erreur  */

-- Contraintes pour la table Approvisionnement
/*ALTER TABLE Approvisionnement ADD CONSTRAINT FK_APPROVIS_APPROVISI_COLLABOR 
    FOREIGN KEY (idCollaborateur) REFERENCES Collaborateur (idUtilisateur, idCollab) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Approvisionnement ADD CONSTRAINT FK_APPROVIS_CANALAPPR_LIGNEAPP 
    FOREIGN KEY (Lig_id) REFERENCES LigneApprovisionnement (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Approvisionnement ADD CONSTRAINT FK_APPROVIS_GERERAPPR_EMPLOYE 
    FOREIGN KEY (idEmploye) REFERENCES Employe (idEmploye) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;
*/



/*  Correction   */

-- Ajouter les colonnes manquantes pour la clé composite
ALTER TABLE Approvisionnement 
    ADD COLUMN idUtilisateur_collab INT4,
    ADD COLUMN idCollab_collab INT4;

-- Mettre à jour les nouvelles colonnes si nécessaire (selon votre logique métier)
-- Par exemple, si idCollaborateur correspondait à idCollab :
UPDATE Approvisionnement SET 
    idUtilisateur_collab = (SELECT idUtilisateur FROM Collaborateur WHERE idCollab = idCollaborateur),
    idCollab_collab = idCollaborateur
WHERE idCollaborateur IS NOT NULL;

-- Ajouter la contrainte de clé étrangère correcte
ALTER TABLE Approvisionnement ADD CONSTRAINT FK_Approvisionnement_Collaborateur
    FOREIGN KEY (idUtilisateur_collab, idCollab_collab) 
    REFERENCES Collaborateur(idUtilisateur, idCollab)
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Supprimer l'ancienne colonne si elle n'est plus nécessaire
ALTER TABLE Approvisionnement DROP COLUMN idCollaborateur;

-- Contrainte vers LigneApprovisionnement
ALTER TABLE Approvisionnement ADD CONSTRAINT FK_Approvisionnement_LigneApprovisionnement
    FOREIGN KEY (Lig_id) REFERENCES LigneApprovisionnement(id)
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contrainte vers Employe
ALTER TABLE Approvisionnement ADD CONSTRAINT FK_Approvisionnement_Employe
    FOREIGN KEY (idEmploye) REFERENCES Employe(idEmploye)
    ON DELETE RESTRICT ON UPDATE RESTRICT;





-- Contraintes pour la table Piece
ALTER TABLE Piece ADD CONSTRAINT FK_PIECE_APPROPIEC_APPROVIS 
    FOREIGN KEY (id) REFERENCES Approvisionnement (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Piece ADD CONSTRAINT FK_PIECE_LOTARTICL_LOT 
    FOREIGN KEY (idLot) REFERENCES Lot (idLot) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Piece ADD CONSTRAINT FK_PIECE_ORIGINEPI_ORIGINE 
    FOREIGN KEY (idOrigine) REFERENCES Origine (idOrigine) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Piece ADD CONSTRAINT FK_PIECE_PIECEMARC_MARCHAND 
    FOREIGN KEY (idMarchandise) REFERENCES Marchandise (idMarchandise) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table CategorieVariete
ALTER TABLE CategorieVariete ADD CONSTRAINT FK_CATEGORI_CATEGORIE_PIECE 
    FOREIGN KEY (idpiece) REFERENCES Piece (idpiece) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Variete
ALTER TABLE Variete ADD CONSTRAINT FK_VARIETE_VARITECAT_CATEGORI 
    FOREIGN KEY (idcatvariete) REFERENCES CategorieVariete (idcatvariete) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Commande
ALTER TABLE Commande ADD CONSTRAINT FK_COMMANDE_CANALCOMM_LIGNECOM 
    FOREIGN KEY (Lig_id) REFERENCES LigneCommande (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Commande ADD CONSTRAINT FK_COMMANDE_EFFECTUER_CLIENT 
    FOREIGN KEY (idClient) REFERENCES Client (idClient) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Commande ADD CONSTRAINT FK_COMMANDE_LIVRERCOM_LIVRAISO 
    FOREIGN KEY (Liv_id) REFERENCES Livraison (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;





/*  Erreur   */
-- Contraintes pour la table Livraison
/*ALTER TABLE Livraison ADD CONSTRAINT FK_LIVRAISO_EFFECTUER_COLLABOR 
    FOREIGN KEY (idCollaborateur) REFERENCES Collaborateur (idUtilisateur, idCollab) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;
*/


/*  Correction   */

-- 1. Ajouter les colonnes nécessaires pour la clé composite
ALTER TABLE Livraison 
    ADD COLUMN idUtilisateur INT4,
    ADD COLUMN idCollab INT4;

-- 2. Mettre à jour les nouvelles colonnes avec des valeurs existantes
-- (Cette étape dépend de votre logique métier)
-- Exemple si idCollaborateur correspond à idCollab :
UPDATE Livraison SET 
    idUtilisateur = (SELECT idUtilisateur FROM Collaborateur WHERE idCollab = idCollaborateur),
    idCollab = idCollaborateur
WHERE idCollaborateur IS NOT NULL;

-- 3. Ajouter la contrainte de clé étrangère corrigée
ALTER TABLE Livraison ADD CONSTRAINT FK_Livraison_Collaborateur
    FOREIGN KEY (idUtilisateur, idCollab) 
    REFERENCES Collaborateur(idUtilisateur, idCollab)
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- 4. Optionnel : rendre les nouvelles colonnes NOT NULL si nécessaire
ALTER TABLE Livraison ALTER COLUMN idUtilisateur SET NOT NULL;
ALTER TABLE Livraison ALTER COLUMN idCollab SET NOT NULL;

-- 5. Optionnel : supprimer l'ancienne colonne si elle n'est plus utilisée
ALTER TABLE Livraison DROP COLUMN idCollaborateur;




-- Contraintes pour la table piececommandee
ALTER TABLE piececommandee ADD CONSTRAINT FK_PIECECOM_PIECECOMM_COMMANDE 
    FOREIGN KEY (id) REFERENCES Commande (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE piececommandee ADD CONSTRAINT FK_PIECECOM_PIECECOMM_PIECE 
    FOREIGN KEY (idpiece) REFERENCES Piece (idpiece) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Produit
ALTER TABLE Produit ADD CONSTRAINT FK_PRODUIT_CANALAPPR_LIGNEAPP 
    FOREIGN KEY (Lig_id) REFERENCES LigneApprovisionnement (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Produit ADD CONSTRAINT FK_PRODUIT_CANALCOMM_LIGNECOM 
    FOREIGN KEY (id) REFERENCES LigneCommande (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Produit ADD CONSTRAINT FK_PRODUIT_CANALVENT_LIGNEVEN 
    FOREIGN KEY (Lig_id2) REFERENCES LigneVente (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Produit ADD CONSTRAINT FK_PRODUIT_FAMILLEPR_FAMILLE 
    FOREIGN KEY (idfamille) REFERENCES Famille (idfamille) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Produit ADD CONSTRAINT FK_PRODUIT_PRODUITMA_MARCHAND 
    FOREIGN KEY (idMarchandise) REFERENCES Marchandise (idMarchandise) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Produit ADD CONSTRAINT FK_PRODUIT_STOCKPROD_STOCK 
    FOREIGN KEY (idstock) REFERENCES Stock (idstock) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table catalogueProduit
ALTER TABLE catalogueProduit ADD CONSTRAINT FK_CATALOGU_CATALOGUE_CATALOGU 
    FOREIGN KEY (idcatalogue) REFERENCES Catalogue (idcatalogue) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE catalogueProduit ADD CONSTRAINT FK_CATALOGU_CATALOGUE_PRODUIT 
    FOREIGN KEY (idproduit) REFERENCES Produit (idproduit) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Vente
ALTER TABLE Vente ADD CONSTRAINT FK_VENTE_CANALVENT_LIGNEVEN 
    FOREIGN KEY (id) REFERENCES LigneVente (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Vente ADD CONSTRAINT FK_VENTE_GERERVENT_EMPLOYE 
    FOREIGN KEY (idEmploye) REFERENCES Employe (idEmploye) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table collabVente
ALTER TABLE collabVente ADD CONSTRAINT FK_COLLABVE_COLLABVEN_COLLABOR 
    FOREIGN KEY (idEmploye, idCollab) REFERENCES Collaborateur (idUtilisateur, idCollab) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE collabVente ADD CONSTRAINT FK_COLLABVE_COLLABVEN_VENTE 
    FOREIGN KEY (idVente) REFERENCES Vente (idvente) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Avis
ALTER TABLE Avis ADD CONSTRAINT FK_AVIS_AVISCLIEN_CLIENT 
    FOREIGN KEY (idClient) REFERENCES Client (idClient) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table variationPrixArticle
ALTER TABLE variationPrixArticle ADD CONSTRAINT FK_VARIATIO_VARIATION_FLUCTUAT 
    FOREIGN KEY (idHistorique) REFERENCES FluctuationPrix (idHistorique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE variationPrixArticle ADD CONSTRAINT FK_VARIATIO_VARIATION_PIECE 
    FOREIGN KEY (idpiece) REFERENCES Piece (idpiece) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table augmentationPrix
ALTER TABLE augmentationPrix ADD CONSTRAINT FK_AUGMENTA_AUGMENTAT_FLUCTUAT 
    FOREIGN KEY (idHistorique) REFERENCES FluctuationPrix (idHistorique) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE augmentationPrix ADD CONSTRAINT FK_AUGMENTA_AUGMENTAT_PERIODE 
    FOREIGN KEY (idPeriode) REFERENCES Periode (idPeriode) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Bilan
ALTER TABLE Bilan ADD CONSTRAINT FK_BILAN_BILANVENT_VENTE 
    FOREIGN KEY (idVente) REFERENCES Vente (idvente) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Transaction
ALTER TABLE Transaction ADD CONSTRAINT FK_TRANSACT_BILANTRAN_BILAN 
    FOREIGN KEY (IdBilan) REFERENCES Bilan (IdBilan) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE Transaction ADD CONSTRAINT FK_TRANSACT_TRANSACTI_VENTE 
    FOREIGN KEY (idVente) REFERENCES Vente (idvente) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Contraintes pour la table Article
ALTER TABLE Article ADD CONSTRAINT FK_ARTICLE_CANALVENT_LIGNEVEN 
    FOREIGN KEY (idLigneVente) REFERENCES LigneVente (id) 
    ON DELETE RESTRICT ON UPDATE RESTRICT;