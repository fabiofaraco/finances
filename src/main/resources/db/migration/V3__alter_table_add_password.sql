ALTER TABLE f01_user ADD COLUMN password varchar(255);

UPDATE f01_user set password = '$2a$12$k.06nmVCw99LvjIo.HCF7erJ6/Ro2Ig2s27r.DAn20IgP2aX5oN9K' WHERE id = 1;