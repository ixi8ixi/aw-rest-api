CREATE SEQUENCE user_id_seq START 10;

ALTER TABLE users
ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass),
ALTER COLUMN id SET NOT NULL;

CREATE SEQUENCE event_id_seq START 1;

ALTER TABLE events
ALTER COLUMN id SET DEFAULT nextval('event_id_seq'::regclass),
ALTER COLUMN id SET NOT NULL;
