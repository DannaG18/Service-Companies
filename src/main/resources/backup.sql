
-- CREACIÓN DE MODULOS
INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
SET @module_customer_id = LAST_INSERT_ID();
INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');
SET @module_auth_id = LAST_INSERT_ID();

-- CREACIÓN DE OPERACIONES

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS','', 'GET', false, @module_customer_id);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','', 'POST', true, @module_customer_id);

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, @module_auth_id);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, @module_auth_id);
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', false, @module_auth_id);

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
SET @role_customer_id = LAST_INSERT_ID();
INSERT INTO role (name) VALUES ('ADMIN');
SET @role_admin_id = LAST_INSERT_ID();

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_customer_id, 5);

INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, 2);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, 4);