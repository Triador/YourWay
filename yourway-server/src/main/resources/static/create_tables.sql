CREATE TABLE books
(
  book_id SERIAL PRIMARY KEY,
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
  user_id SERIAL PRIMARY KEY,
  password VARCHAR(255),
  name VARCHAR(255),
  role VARCHAR(255)
);

CREATE TABLE users_books
(
  user_id int REFERENCES "user" (id) ON UPDATE CASCADE ON DELETE CASCADE,
  book_id int REFERENCES book (id) ON UPDATE CASCADE ON DELETE CASCADE,
  status VARCHAR(255),
  CONSTRAINT users_books_pkey PRIMARY KEY (user_id, book_id)
);

CREATE TABLE notes
(
  note_id SERIAL PRIMARY KEY,
  book_id int REFERENCES book (id) ON UPDATE CASCADE ON DELETE CASCADE,
  user_id int REFERENCES "user" (id) ON UPDATE CASCADE ON DELETE CASCADE,
  text VARCHAR(255)
);

CREATE TABLE marathons
(
  marathon_id SERIAL PRIMARY KEY,
  start_date date,
  end_date date,
  description VARCHAR(4096)
);
