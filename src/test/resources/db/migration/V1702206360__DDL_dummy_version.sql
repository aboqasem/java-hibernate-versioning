DROP TABLE IF EXISTS versioned_dummy_entity;
CREATE TABLE versioned_dummy_entity (
    version_id uuid PRIMARY KEY          DEFAULT uuid7(),
    entity_id  uuid UNIQUE      NOT NULL DEFAULT gen_random_uuid(),
    name       text             NOT NULL
);

