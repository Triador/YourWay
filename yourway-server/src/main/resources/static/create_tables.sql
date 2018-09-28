CREATE TABLE books
(
id SERIAL PRIMARY KEY,
russian_title VARCHAR(255) UNIQUE NOT NULL,
origin_title VARCHAR(255),
author VARCHAR(255),
page_amount SMALLINT,
publication_year SMALLINT,
isbn VARCHAR(255),
description VARCHAR(4096),
image_link VARCHAR(255)
);