-- Main Table to store Features
CREATE TABLE FF4J_FEATURES (
  "FEAT_UID"     	VARCHAR(100),
  "ENABLE"  		INTEGER NOT NULL,
  "DESCRIPTION" 	VARCHAR(255),
  "STRATEGY"		VARCHAR(255),
  "EXPRESSION"	    VARCHAR(255),
  "GROUPNAME"		VARCHAR(255),
  PRIMARY KEY("FEAT_UID")
);

-- Roles to store ACL, FK to main table
CREATE TABLE FF4J_ROLES (
  "FEAT_UID"     VARCHAR(50) REFERENCES FF4J_FEATURES("FEAT_UID"),
  "ROLE_NAME"    VARCHAR(50),
  PRIMARY KEY("FEAT_UID", "ROLE_NAME")
);

-- Feature Internal Custom Properties
CREATE TABLE FF4J_CUSTOM_PROPERTIES (
  "PROPERTY_ID"  VARCHAR(100) NOT NULL,
  "CLAZZ" 		 VARCHAR(255) NOT NULL,
  "CURRENTVALUE" VARCHAR(255),
  "FIXEDVALUES"	 VARCHAR(1000),
  "FEAT_UID"     VARCHAR(50) REFERENCES FF4J_FEATURES("FEAT_UID"),
  PRIMARY KEY("PROPERTY_ID", "FEAT_UID")
);

-- @PropertyStore (edit general properties)
CREATE TABLE FF4J_PROPERTIES (
  "PROPERTY_ID"  VARCHAR(100) NOT NULL,
  "CLAZZ" 		 VARCHAR(255) NOT NULL,
  "CURRENTVALUE" VARCHAR(255),
  "FIXEDVALUES"	 VARCHAR(1000),
  PRIMARY KEY("PROPERTY_ID")
);

-- @see JdbcEventRepository (audit event)
CREATE TABLE FF4J_AUDIT (
  "EVT_TIME" TIMESTAMP NOT NULL,
  "EVT_TYPE" VARCHAR(30) NOT NULL,
  "FEAT_UID" VARCHAR(100) NOT NULL,
  PRIMARY KEY("EVT_TIME", "EVT_TYPE", "FEAT_UID")
);

