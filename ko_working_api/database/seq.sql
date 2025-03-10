-- espace
CREATE SEQUENCE espace_seq START 1 INCREMENT 1;

CREATE OR REPLACE FUNCTION generate_espace_seq()
    RETURNS TEXT AS $$
DECLARE
    next_val INT;
BEGIN
    SELECT nextval('espace_seq') INTO next_val;
    RETURN 'ESPACE' || LPAD(next_val::TEXT, 4, '0');
END;
$$ LANGUAGE plpgsql;

-- prix_heure_espace
CREATE SEQUENCE prix_heure_espace_seq START 1 INCREMENT 1;

CREATE OR REPLACE FUNCTION generate_prix_heure_espace_seq()
    RETURNS TEXT AS $$
DECLARE
    next_val INT;
BEGIN
    SELECT nextval('prix_heure_espace_seq') INTO next_val;
    RETURN 'PRIX_ESPACE' || LPAD(next_val::TEXT, 4, '0');
END;
$$ LANGUAGE plpgsql;

-- option
CREATE SEQUENCE option_seq START 1 INCREMENT 1;

CREATE OR REPLACE FUNCTION generate_option_seq()
    RETURNS TEXT AS $$
DECLARE
    next_val INT;
BEGIN
    SELECT nextval('option_seq') INTO next_val;
    RETURN 'OPT' || LPAD(next_val::TEXT, 4, '0');
END;
$$ LANGUAGE plpgsql;

-- prix_option
CREATE SEQUENCE prix_option_seq START 1 INCREMENT 1;

CREATE OR REPLACE FUNCTION generate_prix_option_seq()
    RETURNS TEXT AS $$
DECLARE
    next_val INT;
BEGIN
    SELECT nextval('prix_option_seq') INTO next_val;
    RETURN 'PRIX_OPT' || LPAD(next_val::TEXT, 4, '0');
END;
$$ LANGUAGE plpgsql;