CREATE TABLE books
(
id SERIAL PRIMARY KEY,
russian_title VARCHAR(255) NOT NULL,
origin_title VARCHAR(255),
author VARCHAR(255),
page_amount SMALLINT,
isbn SMALLINT,
description VARCHAR(255),
image_link VARCHAR(255)
);