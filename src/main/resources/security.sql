-- CREACIÓN DE MÓDULOS
INSERT INTO module (name, base_path) VALUES ('CUSTOMER', '/customers');
SET @module_customer_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('AUTH', '/auth');
SET @module_auth_id = LAST_INSERT_ID();

-- CREACIÓN DE OPERACIONES, usando las variables de módulo para el campo module_id
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_ALL_CUSTOMERS','', 'GET', false, @module_customer_id);
SET @operation_read_all_customers_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('REGISTER_ONE','', 'POST', true, @module_customer_id);
SET @operation_register_one_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('AUTHENTICATE','/authenticate', 'POST', true, @module_auth_id);
SET @operation_authenticate_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('VALIDATE-TOKEN','/validate-token', 'GET', true, @module_auth_id);
SET @operation_validate_token_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('READ_MY_PROFILE','/profile','GET', false, @module_auth_id);
SET @operation_read_my_profile_id = LAST_INSERT_ID();

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
SET @role_customer_id = LAST_INSERT_ID();

INSERT INTO role (name) VALUES ('ASSISTANT_ADMINISTRATOR');
SET @role_assistant_admin_id = LAST_INSERT_ID();

INSERT INTO role (name) VALUES ('ADMINISTRATOR');
SET @role_admin_id = LAST_INSERT_ID();

-- CREACIÓN DE PERMISOS usando las variables de ID generadas previamente
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_customer_id, @operation_read_my_profile_id);

INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_assistant_admin_id, @operation_read_all_customers_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_assistant_admin_id, @operation_register_one_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_assistant_admin_id, @operation_authenticate_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_assistant_admin_id, @operation_validate_token_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_assistant_admin_id, @operation_read_my_profile_id);

INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_read_all_customers_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_register_one_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_authenticate_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_validate_token_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_read_my_profile_id);