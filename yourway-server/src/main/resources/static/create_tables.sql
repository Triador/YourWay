CREATE TABLE books
(
  books_id SERIAL PRIMARY KEY,
  russian_title VARCHAR(255) UNIQUE NOT NULL,
  origin_title VARCHAR(255),
  author VARCHAR(255),
  page_amount SMALLINT,
  publication_year SMALLINT,
  isbn VARCHAR(255),
  description VARCHAR(4096),
  image_link VARCHAR(255)
);

CREATE TABLE users
(
  users_id SERIAL PRIMARY KEY,
  password VARCHAR(255),
  name VARCHAR(255),
  role VARCHAR(255)
);

CREATE TABLE users_books
(
  users_id int REFERENCES users (users_id) ON UPDATE CASCADE ON DELETE CASCADE,
  books_id int REFERENCES books (books_id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT users_books_pkey PRIMARY KEY (users_id, books_id)
);

