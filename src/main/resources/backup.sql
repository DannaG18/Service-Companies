-- CREACIÓN DE MODULOS
INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');

-- CREACIÓN DE OPERACIONES

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS','', 'GET', false, 1);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','', 'POST', true, 1);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, 2);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', false, 2);

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
INSERT INTO role (name) VALUES ('ADMIN');

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permission (role_id, operation_id) VALUES (1, 5);

INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (2, 5);

