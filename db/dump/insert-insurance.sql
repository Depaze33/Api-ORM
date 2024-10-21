CREATE TABLE insurance
(
    id        SERIAL PRIMARY KEY,    -- Identifiant unique de type entier avec auto-incrémentation
    name      VARCHAR(255) NOT NULL, -- Colonne pour le nom de l'assurance
    client_id UUID,                  -- Clé étrangère vers la table client (type UUID)
    CONSTRAINT fk_client
        FOREIGN KEY (client_id)
            REFERENCES client (id)   -- Relation avec la table client sur la colonne id
            ON DELETE CASCADE        -- Supprime les assurances si le client est supprimé
);
CREATE TABLE insurance
(
    id        SERIAL PRIMARY KEY,    -- Identifiant unique de type entier avec auto-incrémentation
    name      VARCHAR(255) NOT NULL, -- Colonne pour le nom de l'assurance
    client_id UUID,                  -- Clé étrangère vers la table client (type UUID)
    CONSTRAINT fk_client
        FOREIGN KEY (client_id)
            REFERENCES client (id)   -- Relation avec la table client sur la colonne id
            ON DELETE CASCADE        -- Supprime les assurances si le client est supprimé
);