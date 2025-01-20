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
SET @role_customer_id = LAST_INSERT_ID();

INSERT INTO role (name) VALUES ('ADMIN');
SET @role_admin_id = LAST_INSERT_ID();

-- CREACIÓN DE PERMISOS
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_customer_id, 1);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_customer_id, 2);

INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, 3);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, 4);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, 5);

INSERT INTO user (username, name, password, role_id) VALUES ('coca@gmail.com', 'Dsanna', '$2a$10$a93wcWlj39uM1xCcgYXhku9kq8uU7Lwd2EafHcSpMkQwQcB.YGUwi', 2);