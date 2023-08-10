CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20) UNIQUE,
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),
  lastname VARCHAR(20)
);
insert into USERS(username, salt, password, firstname, lastname)
values ('admin', 'GS0ShMq29PAT4G1IJ/FeMw==', 'nyKhExEW3DNlFjHmhzPL0QF1GokyaRziH6KLEDOpGqeQCGqNmpuTezXRxHasEFn6HmuUHd/2vOCIU8ThHpr6s1/sli/5sRYeux0EcwGht0an4TxFSJ1l7yf7UATE1SMl+da4lxhrFsol8AZLyLOmgjXCLnpPW2F15FN1yyfDXhPAcpDysUV3TLfdINjGnLcn1483xEs0D5FyPPos1m52PYiNSdOKKkKCHzTy8CCSLhaAUbOwDQ1+mUoPIWBWEobTTHZQ4gFjj98pBohL95jprYYOW6xjsyqTdsMfgvC6koNzOo5VkctLhMOrCc5tjS7m1DYqgepo9w/TsvqgGFqvUA==', 'admin', 'admin');

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
    filedata BLOB,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    foreign key (userid) references USERS(userid)
);
