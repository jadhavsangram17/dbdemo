DROP TABLE IF EXISTS tradestore;

CREATE TABLE tradestore (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tradeid VARCHAR(250) NOT NULL,
  tradeversion INT NOT NULL,
  counterpartyid VARCHAR(250) NOT NULL,
  bookid VARCHAR(250) NOT NULL,
  maturitydate TIMESTAMP  NOT NULL,
  createddate TIMESTAMP  NOT NULL,
  expired VARCHAR(1) NOT NULL
);

INSERT INTO tradestore (tradeId, tradeVersion, counterPartyId, bookId, maturityDate, createdDate, expired) VALUES
  ('T1', 1, 'CP-1', 'B1', '2020-05-20 12:00:00', '2021-05-21 12:00:00', 'N'),
  ('T2', 2, 'CP-2', 'B1', '2020-05-20 12:00:00', '2021-05-21 12:00:00', 'N'),
  ('T2', 1, 'CP-1', 'B1', '2020-05-20 12:00:00', '2021-05-14 12:00:00', 'N'),
  ('T2', 3, 'CP-3', 'B2', '2014-05-20 12:00:00', '2021-05-21 12:00:00', 'Y');