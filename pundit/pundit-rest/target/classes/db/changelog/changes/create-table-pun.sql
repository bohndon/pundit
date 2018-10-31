--liquibase formatted sql

--changeset bohnd:001.001
CREATE SEQUENCE IF NOT EXISTS PUN_ID_SEQ INCREMENT BY 1 START WITH 1 CACHE 20;
--rollback DROP SEQUENCE IF EXISTS PUN_ID_SEQ;

--changeset bohnd:001.002
CREATE TABLE IF NOT EXISTS Pun (
  punId       INT8         NOT NULL,
  version     INT4         NOT NULL,
  created     TIMESTAMP  NULL,
--  created     TIMESTAMPTZ  NULL,  -- H2 does not support TIMESTAMPTZ
  edited      TIMESTAMP  NULL,
--  edited      TIMESTAMPTZ  NULL,  -- H2 does not support TIMESTAMPTZ
  title       VARCHAR(100) NOT NULL,
  prompt      VARCHAR(100) NOT NULL,
  statement   VARCHAR(100) NOT NULL,
  punType     VARCHAR(50)  NULL,
  enabled     BOOL         NULL,
  CONSTRAINT  punId_PK     PRIMARY KEY (punId)
);
--rollback DROP TABLE IF EXISTS Pun;

