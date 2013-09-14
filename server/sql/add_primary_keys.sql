ALTER TABLE population ADD COLUMN id INT  ;
CREATE SEQUENCE population_id;
UPDATE population SET id = nextval('population_id');
ALTER TABLE population ADD PRIMARY KEY (id);
ALTER TABLE population ALTER COLUMN total TYPE integer USING CAST (total AS INTEGER);
ALTER TABLE population ALTER COLUMN men TYPE integer USING CAST (men AS INTEGER);
ALTER TABLE population ALTER COLUMN woman TYPE integer USING CAST (woman AS INTEGER);




