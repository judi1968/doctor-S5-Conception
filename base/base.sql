CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE maladie_id_maladie_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE medicament_id_medicament_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE parametre_id_parametre_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE patient_id_patient_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE patient_nom_seq START WITH 1 INCREMENT BY 1;

CREATE  TABLE maladie ( 
	id_maladie           integer DEFAULT nextval('maladie_id_maladie_seq'::regclass) NOT NULL  ,
	designation          varchar(225)    ,
	CONSTRAINT pk_maladie PRIMARY KEY ( id_maladie )
 );

CREATE  TABLE medicament ( 
	id_medicament        integer DEFAULT nextval('medicament_id_medicament_seq'::regclass) NOT NULL  ,
	designation          varchar(225)    ,
	prix                 double precision DEFAULT 1000   ,
	CONSTRAINT pk_medicament PRIMARY KEY ( id_medicament )
 );

CREATE  TABLE parametre ( 
	id_parametre         integer DEFAULT nextval('parametre_id_parametre_seq'::regclass) NOT NULL  ,
	designation          varchar(225)    ,
	CONSTRAINT pk_parametre PRIMARY KEY ( id_parametre )
 );

CREATE  TABLE parametre_maladie ( 
	id_parametre_fk      integer    ,
	id_maladie_fk        integer    ,
	note_min             integer    ,
	note_max             integer    ,
	an_min               integer    ,
	an_max               integer    ,
	CONSTRAINT fk_parametre_maladie_maladie FOREIGN KEY ( id_maladie_fk ) REFERENCES maladie( id_maladie )   ,
	CONSTRAINT fk_parametre_maladie_parametre FOREIGN KEY ( id_parametre_fk ) REFERENCES parametre( id_parametre )   
 );

CREATE  TABLE parametre_medicament ( 
	id_medicament_fk     integer    ,
	id_parametre_fk      integer    ,
	note_effet           integer    ,
	CONSTRAINT fk_paramentre_medicament FOREIGN KEY ( id_medicament_fk ) REFERENCES medicament( id_medicament )   ,
	CONSTRAINT fk_medicament_parametre FOREIGN KEY ( id_parametre_fk ) REFERENCES parametre( id_parametre )   
 );

CREATE  TABLE patient ( 
	id_patient           integer DEFAULT nextval('patient_id_patient_seq'::regclass) NOT NULL  ,
	nom                  varchar DEFAULT nextval('patient_nom_seq'::regclass) NOT NULL  ,
	ans                  integer    ,
	CONSTRAINT pk_patient PRIMARY KEY ( id_patient )
 );

CREATE  TABLE parametre_patient ( 
	id_patient_fk        integer    ,
	note_parametre       integer    ,
	id_parametre_fk      integer    ,
	CONSTRAINT fk_parametre_patient_parametre FOREIGN KEY ( id_parametre_fk ) REFERENCES parametre( id_parametre )   ,
	CONSTRAINT fk_parametre_patient_patient FOREIGN KEY ( id_patient_fk ) REFERENCES patient( id_patient )   
 );

CREATE VIEW v_parametre_patient AS SELECT pp.note_parametre,
    pp.id_parametre_fk,
    p.id_patient,
    p.nom,
    p.ans
   FROM (parametre_patient pp
     JOIN patient p ON ((p.id_patient = pp.id_patient_fk)));

CREATE VIEW v_parametre_patient_v2 AS SELECT pp.note_parametre,
    pp.id_patient,
    pp.nom,
    pp.ans,
    p.id_parametre,
    p.designation
   FROM (v_parametre_patient pp
     JOIN parametre p ON ((pp.id_parametre_fk = p.id_parametre)));

INSERT INTO maladie( id_maladie, designation ) VALUES ( 1, 'Grippe');
INSERT INTO maladie( id_maladie, designation ) VALUES ( 2, 'Indigestion');
INSERT INTO maladie( id_maladie, designation ) VALUES ( 3, 'Fatigue');
INSERT INTO medicament( id_medicament, designation, prix ) VALUES ( 2, 'Sirop', 1000.0);
INSERT INTO medicament( id_medicament, designation, prix ) VALUES ( 1, 'Paracetamol', 200.0);
INSERT INTO medicament( id_medicament, designation, prix ) VALUES ( 3, 'Charbon', 2000.0);
INSERT INTO medicament( id_medicament, designation, prix ) VALUES ( 4, 'Pertamol', 800.0);
INSERT INTO parametre( id_parametre, designation ) VALUES ( 1, 'Manotika');
INSERT INTO parametre( id_parametre, designation ) VALUES ( 2, 'Mikaka');
INSERT INTO parametre( id_parametre, designation ) VALUES ( 3, 'Marary kibo');
INSERT INTO parametre( id_parametre, designation ) VALUES ( 4, 'Lelo');
INSERT INTO parametre( id_parametre, designation ) VALUES ( 5, 'Vizaka');
INSERT INTO parametre( id_parametre, designation ) VALUES ( 6, 'Temperature');
INSERT INTO parametre( id_parametre, designation ) VALUES ( 7, 'Aretin andoha');
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 4, 1, 6, 10, 0, 12);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 4, 1, 8, 10, 13, 45);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 4, 1, 5, 8, 46, 120);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 6, 1, 7, 9, 0, 30);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 6, 1, 3, 7, 31, 120);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 7, 1, 5, 7, 0, 11);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 1, 2, 6, 10, 0, 12);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 1, 2, 8, 10, 13, 45);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 1, 2, 2, 5, 46, 120);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 5, 3, 7, 9, 0, 10);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 7, 3, 4, 7, 11, 49);
INSERT INTO parametre_maladie( id_parametre_fk, id_maladie_fk, note_min, note_max, an_min, an_max ) VALUES ( 5, 3, 5, 8, 50, 120);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 2, 1, 1);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 3, 1, 3);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 3, 2, 2);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 2, 3, 3);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 3, 3, 3);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 2, 4, 2);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 4, 5, 4);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 1, 6, 1);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 4, 6, 2);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 1, 7, 2);
INSERT INTO parametre_medicament( id_medicament_fk, id_parametre_fk, note_effet ) VALUES ( 4, 7, 3);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 1, 'Rakoto', 25);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 2, 'John', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 3, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 4, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 5, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 6, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 7, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 8, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 9, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 10, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 11, 'Rabe', 9);
INSERT INTO patient( id_patient, nom, ans ) VALUES ( 12, 'Rabe', 9);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 1, 7, 2);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 1, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 1, 9, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 1, 8, 6);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 2, 5, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 2, 7, 3);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 3, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 3, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 3, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 4, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 4, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 4, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 5, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 5, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 5, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 6, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 6, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 6, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 7, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 7, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 7, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 8, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 8, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 8, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 9, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 9, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 9, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 10, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 10, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 10, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 11, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 11, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 11, 9, 1);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 12, 6, 4);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 12, 2, 5);
INSERT INTO parametre_patient( id_patient_fk, note_parametre, id_parametre_fk ) VALUES ( 12, 9, 1);
