CREATE TABLE parameters
(
    id      BIGINT      NOT NULL,
    key     VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE auction_parameter
(
    auction_id   BIGINT          NOT NULL,
    parameter_id BIGINT          NOT NULL,
    value        VARCHAR(50)     NOT NULL,
    FOREIGN KEY (auction_id) REFERENCES auctions(id),
    FOREIGN KEY (parameter_id) REFERENCES parameters(id)
);