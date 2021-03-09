CREATE TABLE IF NOT EXISTS emp (
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name text,
    email text,
    phone text,
    constraint pk_employee PRIMARY KEY (id)
);