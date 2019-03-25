CREATE TABLE bookResponses
(
  id SERIAL PRIMARY KEY,
  title VARCHAR(255),
  author VARCHAR(255),
  page_amount SMALLINT,
  publication_year SMALLINT,
  isbn VARCHAR(255),
  description VARCHAR(4096),
  image_link VARCHAR(255)
);

CREATE TABLE "user"
(
  id SERIAL PRIMARY KEY,
  password VARCHAR(255),
  name VARCHAR(255),
  role VARCHAR(255),
  image_link VARCHAR(255)
);

CREATE TABLE user_book
(
  user_id int REFERENCES "user" (id) ON UPDATE CASCADE ON DELETE CASCADE,
  book_id int REFERENCES book (id) ON UPDATE CASCADE ON DELETE CASCADE,
  status VARCHAR(255),
  CONSTRAINT users_books_pkey PRIMARY KEY (user_id, book_id)
);

CREATE TABLE notes
(
  id SERIAL PRIMARY KEY,
  book_id int REFERENCES book (id) ON UPDATE CASCADE ON DELETE CASCADE,
  user_id int REFERENCES "user" (id) ON UPDATE CASCADE ON DELETE CASCADE,
  text VARCHAR(255)
);

CREATE TABLE marathons
(
  id SERIAL PRIMARY KEY,
  start_date date,
  end_date date,
  description VARCHAR(4096)
);
