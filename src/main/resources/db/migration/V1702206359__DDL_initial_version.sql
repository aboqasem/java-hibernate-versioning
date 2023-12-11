DROP TABLE IF EXISTS company;
CREATE TABLE company (
    version_id uuid PRIMARY KEY          DEFAULT uuid7(),
    entity_id  uuid UNIQUE      NOT NULL DEFAULT gen_random_uuid(),
    name       text             NOT NULL
);

