CREATE TABLE client
(
    id_client VARCHAR(50),
    nom       VARCHAR(50),
    numero    VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_client),
    UNIQUE (numero)
);

CREATE TABLE espace
(
    id_espace VARCHAR(50),
    nom       VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_espace),
    UNIQUE (nom)
);

CREATE TABLE status_reservation
(
    id_status_reservation VARCHAR(50),
    nom                   VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_status_reservation),
    UNIQUE (nom)
);

CREATE TABLE option
(
    id_option VARCHAR(50),
    code      VARCHAR(50) NOT NULL,
    option    VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_option),
    UNIQUE (code),
    UNIQUE (option)
);

CREATE TABLE status_paiement
(
    id_status_paiement VARCHAR(50),
    nom                VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_status_paiement),
    UNIQUE (nom)
);

CREATE TABLE prix_option
(
    id_prix_option    VARCHAR(50),
    val               NUMERIC(15, 2) NOT NULL,
    date_modification DATE,
    id_option         VARCHAR(50)    NOT NULL,
    PRIMARY KEY (id_prix_option),
    UNIQUE (id_option),
    FOREIGN KEY (id_option) REFERENCES option (id_option)
);

CREATE TABLE prix_heure_espace
(
    id_prix_heure_espace VARCHAR(50),
    val                  NUMERIC(15, 2) NOT NULL,
    date_modification    DATE,
    id_espace            VARCHAR(50)    NOT NULL,
    PRIMARY KEY (id_prix_heure_espace),
    FOREIGN KEY (id_espace) REFERENCES espace (id_espace)
);

CREATE TABLE reservation
(
    id_reservation        VARCHAR(50),
    date_reservation      DATE           NOT NULL,
    heure_debut           TIME           NOT NULL,
    heure_fin             TIME           NOT NULL,
    duree                 INTEGER        NOT NULL,
    montant               NUMERIC(15, 2) NOT NULL,
    id_prix_heure_espace  VARCHAR(50)    NOT NULL,
    id_status_reservation VARCHAR(50)    NOT NULL,
    id_client             VARCHAR(50)    NOT NULL,
    PRIMARY KEY (id_reservation),
    FOREIGN KEY (id_prix_heure_espace) REFERENCES prix_heure_espace (id_prix_heure_espace),
    FOREIGN KEY (id_status_reservation) REFERENCES status_reservation (id_status_reservation),
    FOREIGN KEY (id_client) REFERENCES client (id_client)
);

CREATE TABLE paiement
(
    id_paiement        VARCHAR(50),
    date_paiement      DATE,
    id_status_paiement VARCHAR(50) NOT NULL,
    id_reservation     VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_paiement),
    UNIQUE (id_reservation),
    FOREIGN KEY (id_status_paiement) REFERENCES status_paiement (id_status_paiement),
    FOREIGN KEY (id_reservation) REFERENCES reservation (id_reservation)
);

CREATE TABLE creneau
(
    id_creneau     VARCHAR(50),
    date_creneau   DATE        NOT NULL,
    heure_creneau  TIME        NOT NULL,
    id_reservation VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_creneau),
    FOREIGN KEY (id_reservation) REFERENCES reservation (id_reservation)
);

CREATE TABLE option_reservation
(
    id_reservation VARCHAR(50),
    id_prix_option VARCHAR(50),
    PRIMARY KEY (id_reservation, id_prix_option),
    FOREIGN KEY (id_reservation) REFERENCES reservation (id_reservation),
    FOREIGN KEY (id_prix_option) REFERENCES prix_option (id_prix_option)
);

-- Insertions
insert into status_reservation (id_status_reservation, nom)
values ('ST_RES0001', 'Fait'),
       ('ST_RES0002', 'A payer'),
       ('ST_RES0003', 'En attente'),
       ('ST_RES0004', 'Payé');

insert into status_paiement (id_status_paiement, nom)
values ('ST_PAY0001', 'En attente'),
       ('ST_PAY0002', 'Validé');