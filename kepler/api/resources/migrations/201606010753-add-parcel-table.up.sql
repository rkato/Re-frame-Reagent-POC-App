CREATE TABLE IF NOT EXISTS PARCEL(
ID bigserial  PRIMARY KEY NOT NULL,
NAME TEXT NOT NULL,
DESCRIPTION TEXT NOT NULL,
ISDELIVERED BOOLEAN,
CREATEDBY TEXT NOT NULL,
CREATEDON timestamp with time zone NOT NULL,
UPDATEDBY TEXT,
UPDATEDON timestamp  with time zone
);
