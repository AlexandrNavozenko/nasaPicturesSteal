create schema if not exists nasa;

create table nasa.cameras(
    id SERIAL PRIMARY KEY,
    nasa_id INTEGER NOT NULL,
    name VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

create table if not exists nasa.pictures(
    id SERIAL PRIMARY KEY,
    nasa_id INTEGER NOT NULL,
    img_src VARCHAR(255),
    camera_id INTEGER NOT NULL REFERENCES nasa.cameras (id),
    created_at TIMESTAMP NOT NULL DEFAULT now()
)