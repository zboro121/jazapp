ALTER TABLE users
ADD role VARCHAR(20);

CREATE TABLE departments
(
    id          BIGINT          NOT NULL,
    name        VARCHAR(25)     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id          BIGINT          NOT NULL,
    name        VARCHAR(25)     NOT NULL,
    department_id  BIGINT     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (department_id) REFERENCES departments (id)
);


CREATE TABLE auctions
(
    id          BIGINT      NOT NULL,
    title       VARCHAR(25) NOT NULL,
    description varchar(200) NOT NULL,
    price       NUMERIC(100, 2)      NOT NULL,
    owner_id    BIGINT      NOT NULL,
    category_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES users(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE photos
(
    id          BIGINT          NOT NULL,
    url         VARCHAR(500)    NOT NULL,
    auction_id  BIGINT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (auction_id) REFERENCES auctions (id)
);