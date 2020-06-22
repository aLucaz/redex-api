/* ROLE TABLE AUTO FILLING */
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by,
                  registered_date)
SELECT 1,
       'ADMIN',
       'Administrador',
       'Administrador',
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_role FROM role WHERE id_role = 1);
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by,
                  registered_date)
SELECT 2,
       'ASIST',
       'Asistente',
       'Asistente',
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_role FROM role WHERE id_role = 2);
INSERT INTO role (id_role, friendly_id, name, description, last_modified_by, last_modified_date, registered_by,
                  registered_date)
SELECT 3,
       'INSPEC',
       'Inspector',
       'Inspector',
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_role FROM role WHERE id_role = 3);
/* BRANCH TABLE AUTO FILLING*/
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 1,
       'Bogota-Colombia',
       'SKBO',
       600,
       0,
       1,
       'SA',
       4.6097102,
       -74.081749,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 1);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 2,
       'Quito-Ecuador',
       'SEQM',
       750,
       0,
       1,
       'SA',
       -0.22985,
       -78.5249481,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 2);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 3,
       'Caracas-Venezuela',
       'SVMI',
       800,
       0,
       1,
       'SA',
       10.4880104,
       -66.8791885,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 3);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 4,
       'Brasilia-Brasil',
       'SBBR',
       900,
       0,
       1,
       'SA',
       -15.7797203,
       -47.9297218,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 4);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 5,
       'Lima-Perú',
       'SPIM',
       1000,
       0,
       1,
       'SA',
       -12.0431805,
       -77.0282364,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 5);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 6,
       'La Paz-Bolivia',
       'SLLP',
       400,
       0,
       1,
       'SA',
       -16.5,
       -68.1500015,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 6);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 7,
       'Santiago de Chile-Chile',
       'SCEL',
       850,
       0,
       1,
       'SA',
       -33.4488897,
       -70.6692655,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 7);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 8,
       'Buenos Aires-Argentina',
       'SABE',
       900,
       0,
       1,
       'SA',
       -34.6036844,
       -58.3815591,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 8);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 9,
       'Asunción-Paraguay',
       'SGAS',
       700,
       0,
       1,
       'SA',
       -25.2637399,
       -57.575926,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 9);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 10,
       'Motenvideo-Uruguay',
       'SUAA',
       650,
       0,
       1,
       'SA',
       -34.9011127,
       -56.1645314,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 10);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 11,
       'Tirana-Albania',
       'LATI',
       1000,
       0,
       1,
       'EU',
       41.3275459,
       19.8186982,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 11);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 12,
       'Berlin-Alemania',
       'EDDI',
       600,
       0,
       1,
       'EU',
       52.5243683,
       13.4105301,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 12);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 13,
       'Viena-Austria',
       'LOWW',
       750,
       0,
       1,
       'EU',
       48.2084885,
       16.3720798,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 13);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 14,
       'Bruselas-Belgica',
       'EBCI',
       800,
       0,
       1,
       'EU',
       50.8504486,
       4.3487802,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 14);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 15,
       'Minsk-Bielorrusia',
       'UMMS',
       900,
       0,
       1,
       'EU',
       53.9000015,
       27.5666695,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 15);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 16,
       'Sofia-Bulgaria',
       'LBSF',
       1000,
       0,
       1,
       'EU',
       42.6975098,
       23.3241501,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 16);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 17,
       'Praga-Checa',
       'LKPR',
       400,
       0,
       1,
       'EU',
       50.0880394,
       14.4207602,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 17);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 18,
       'Zagreb-Croacia',
       'LDZA',
       850,
       0,
       1,
       'EU',
       45.8144417,
       15.9779797,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 18);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 19,
       'Copenhague-Dinamarca',
       'EKCH',
       900,
       0,
       1,
       'EU',
       55.6759415,
       12.5655298,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 19);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 20,
       'Bratislava-Eslovaquia',
       'LZIB',
       700,
       0,
       1,
       'EU',
       48.148159,
       17.106741,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 20);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 21,
       'Liubliana-Eslovenia',
       'LJLJ',
       650,
       0,
       1,
       'EU',
       46.0510788,
       14.5051298,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 21);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 22,
       'Madrid-España',
       'LEMD',
       600,
       0,
       1,
       'EU',
       40.4165001,
       -3.7025599,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 22);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 23,
       'Tallin-Estonia',
       'EETN',
       750,
       0,
       1,
       'EU',
       59.4369583,
       24.7535305,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 23);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 24,
       'Helsinki-Finlandia',
       'EFHK',
       800,
       0,
       1,
       'EU',
       60.1698557,
       24.9383791,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 24);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 25,
       'Paris-Francia',
       'LFPG',
       900,
       0,
       1,
       'EU',
       48.8534088,
       2.3487999,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 25);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 26,
       'Atenas-Grecia',
       'LGAV',
       1000,
       0,
       1,
       'EU',
       37.9794502,
       23.7162209,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 26);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 27,
       'Amsterdam-Holanda',
       'EHAM',
       400,
       0,
       1,
       'EU',
       52.3740311,
       4.8896899,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 27);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 28,
       'Budapest-Hungría',
       'LHBP',
       850,
       0,
       1,
       'EU',
       47.4980087,
       19.0399094,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 28);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 29,
       'Dublin-Irlanda (Eire)',
       'EIDW',
       900,
       0,
       1,
       'EU',
       53.3330612,
       -6.2488899,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 29);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 30,
       'Reykjavik-Islandia',
       'BIKF',
       700,
       0,
       1,
       'EU',
       64.146582,
       -21.9426354,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 30);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 31,
       'Roma-Italia',
       'LIRA',
       650,
       0,
       1,
       'EU',
       41.8919296,
       12.5113297,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 31);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 32,
       'Riga-Letonia',
       'EVRA',
       600,
       0,
       1,
       'EU',
       56.9459991,
       24.1058903,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 32);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 33,
       'Luxemburgo-Luxemburgo',
       'ELLX',
       750,
       0,
       1,
       'EU',
       49.6116714,
       6.1300001,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 33);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 34,
       'LaValletta-Malta',
       'LMML',
       800,
       0,
       1,
       'EU',
       35.8989085,
       14.5145528,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 34);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 35,
       'Oslo-Noruega',
       'ENGM',
       900,
       0,
       1,
       'EU',
       59.9127312,
       10.7460899,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 35);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 36,
       'Varsovia-Polonia',
       'EPMO',
       1000,
       0,
       1,
       'EU',
       52.2297707,
       21.0117798,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 36);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 37,
       'Lisboa-Portugal',
       'LPPT',
       400,
       0,
       1,
       'EU',
       38.716671,
       -9.1333303,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 37);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 38,
       'Londres-Reino Unido',
       'EGLL',
       850,
       0,
       1,
       'EU',
       51.5085297,
       -0.12574,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 38);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 39,
       'Estocolmo-Suecia',
       'ESKN',
       900,
       0,
       1,
       'EU',
       59.3325806,
       18.0648994,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 39);
INSERT INTO branch (id_branch, name, friendly_id, capacity, quantity, is_active, continent, latitude, longitude,
                    last_modified_by, last_modified_date, registered_by, registered_date)
SELECT 40,
       'Berna-Suiza',
       'LSZB',
       700,
       0,
       1,
       'EU',
       46.9480896,
       7.4474401,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_branch FROM branch WHERE id_branch = 40);
/* DOCUMENT TYPE TABLE AUTO FILLING */
INSERT INTO document_type (id_document_type, name, description, last_modified_by, last_modified_date, registered_by,
                           registered_date)
SELECT 1, 'Documento Nacional de Identidad', 'Documento Nacional de Identidad', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS(SELECT id_document_type FROM document_type WHERE id_document_type = 1);
INSERT INTO document_type (id_document_type, name, description, last_modified_by, last_modified_date, registered_by,
                           registered_date)
SELECT 2, 'Pasaporte', 'Pasaporte', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS(SELECT id_document_type FROM document_type WHERE id_document_type = 2);
SELECT 3, 'Carnet de extranjeria', 'Carnet de extranjeria', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS(SELECT id_document_type FROM document_type WHERE id_document_type = 3);
/* PACKAGING TYPE TABLE AUTO FILLING*/
INSERT INTO packaging_type (id_packaging_type, name, description, price, last_modified_by, last_modified_date,
                            registered_by,
                            registered_date)
SELECT 1,
       'Especial A1',
       'Especial A1',
       60.0,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_packaging_type FROM packaging_type WHERE id_packaging_type = 1);
INSERT INTO packaging_type (id_packaging_type, name, description, price, last_modified_by, last_modified_date,
                            registered_by,
                            registered_date)
SELECT 2,
       'Especial A2',
       'Especial A2',
       40.0,
       'SYSTEM',
       NOW(),
       'SYSTEM',
       NOW()
WHERE NOT EXISTS(SELECT id_packaging_type FROM packaging_type WHERE id_packaging_type = 2);
/* PACKAGE CATEGORY TABLE AUTO FILLING*/
INSERT INTO package_category (id_package_category, name, description, last_modified_by, last_modified_date,
                              registered_by,
                              registered_date)
SELECT 1, 'Documentos', 'Documentos', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS(SELECT id_package_category FROM package_category WHERE id_package_category = 1);
INSERT INTO package_category (id_package_category, name, description, last_modified_by, last_modified_date,
                              registered_by,
                              registered_date)
SELECT 2, 'Dispositivos electronicos', 'Dispositivos electronicos', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS(SELECT id_package_category FROM package_category WHERE id_package_category = 2);
INSERT INTO package_category (id_package_category, name, description, last_modified_by, last_modified_date,
                              registered_by,
                              registered_date)
SELECT 3, 'Prendas', 'Prendas', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS(SELECT id_package_category FROM package_category WHERE id_package_category = 3);
INSERT INTO package_category (id_package_category, name, description, last_modified_by, last_modified_date,
                              registered_by,
                              registered_date)
SELECT 4, 'Other', 'Other', 'SYSTEM', NOW(), 'SYSTEM', NOW()
WHERE NOT EXISTS(SELECT id_package_category FROM package_category WHERE id_package_category = 4);
/* USERS CATEGORY AUTO FILLING*/
INSERT INTO user(id_user, birthday, document_id, email, first_name, genre, is_active, last_modified_by,
                 last_modified_date, last_name, password_hash, password_salt, phone, registered_by, registered_date,
                 id_document_type, id_role)
SELECT 1,
       NOW(),
       '72974217',
       'administrador@gmail.com',
       'admin',
       'Femenino',
       1,
       'SYSTEM',
       NOW(),
       'admin',
       'QzGGXM4HtJa+TTNd/rSCRHnhyp0TICD3ux5xARa84H3vgJ0nSaWxskAv0gGcllSbI+IXhwE+h6QM
3l5/vlg9ow==',
       'V�W���YY���9�',
       '992882772',
       'SYSTEM',
       NOW(),
       1,
       1
WHERE NOT EXISTS(SELECT id_user FROM user WHERE id_user = 1);

INSERT INTO employee(id_employee, id_branch, id_user)
SELECT 1,
       1,
       1
WHERE NOT EXISTS(SELECT id_employee FROM employee WHERE id_employee = 1);
INSERT INTO user(id_user, birthday, document_id, email, first_name, genre, is_active, last_modified_by,
                 last_modified_date, last_name, password_hash, password_salt, phone, registered_by, registered_date,
                 id_document_type, id_role)
SELECT 2,
       NOW(),
       '72974217',
       'asistente@gmail.com',
       'asistente',
       'Femenino',
       1,
       'SYSTEM',
       NOW(),
       'asistente',
       'QzGGXM4HtJa+TTNd/rSCRHnhyp0TICD3ux5xARa84H3vgJ0nSaWxskAv0gGcllSbI+IXhwE+h6QM
3l5/vlg9ow==',
       'V�W���YY���9�',
       '992882772',
       'SYSTEM',
       NOW(),
       1,
       2
WHERE NOT EXISTS(SELECT id_user FROM user WHERE id_user = 2);

INSERT INTO employee(id_employee, id_branch, id_user)
SELECT 2,
       2,
       2
WHERE NOT EXISTS(SELECT id_employee FROM employee WHERE id_employee = 2);
INSERT INTO user(id_user, birthday, document_id, email, first_name, genre, is_active, last_modified_by,
                 last_modified_date, last_name, password_hash, password_salt, phone, registered_by, registered_date,
                 id_document_type, id_role)
SELECT 3,
       NOW(),
       '72974217',
       'inspector@gmail.com',
       'inspector',
       'Femenino',
       1,
       'SYSTEM',
       NOW(),
       'inspector',
       'QzGGXM4HtJa+TTNd/rSCRHnhyp0TICD3ux5xARa84H3vgJ0nSaWxskAv0gGcllSbI+IXhwE+h6QM
3l5/vlg9ow==',
       'V�W���YY���9�',
       '992882772',
       'SYSTEM',
       NOW(),
       1,
       3
WHERE NOT EXISTS(SELECT id_user FROM user WHERE id_user = 3);

INSERT INTO employee(id_employee, id_branch, id_user)
SELECT 3,
       3,
       3
WHERE NOT EXISTS(SELECT id_employee FROM employee WHERE id_employee = 3);


