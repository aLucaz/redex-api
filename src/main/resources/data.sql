/* ROLE TABLE AUTO FILLING*/
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 1, 'COMMON', 'common', 'common role', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS (SELECT id_role FROM role WHERE id_role = 1);
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 2, 'ADMIN', 'administrator', 'employee administrator role', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS (SELECT id_role FROM role WHERE id_role = 2);
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 3, 'ASSISTANT', 'assistant', 'employee assistant role', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS (SELECT id_role FROM role WHERE id_role = 3);
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 4, 'CHECKER', 'checker', 'employee checker role', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS (SELECT id_role FROM role WHERE id_role = 4);
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 5, 'GALACTUS', 'galactus', 'developers role', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS (SELECT id_role FROM role WHERE id_role = 5);
