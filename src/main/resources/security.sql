
INSERT INTO module (name, base_path) VALUES ('PERSON', '/api/persons');
SET @module_person_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('PERSON_SUPPLY', '/api/person-supplies');
SET @module_person_supply_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('PERSON_TYPE', '/api/person-types');
SET @module_person_type_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('PERSON_PHONE', '/api/person-phones');
SET @module_person_phone_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('PHONE_TYPES', '/api/phone-types');
SET @module_phone_types_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('REGION', '/api/regions');
SET @module_region_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('SERVICE_APPROVALS', '/api/service-approvals');
SET @module_service_approvals_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('SERVICE_ORDER', '/api/service-orders');
SET @module_service_order_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('SERVICE', '/api/services');
SET @module_service_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('SERVICE_SUPPLY', '/api/service-supplies');
SET @module_service_supply_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('STATUS_ORDER', '/api/status-orders');
SET @module_status_order_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('STATUS_SERVICE_ORDER', '/api/status-service-orders');
SET @module_status_service_order_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('SUPPLY', '/api/supplies');
SET @module_supply_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('WORK_ORDER', '/api/work-orders');
SET @module_work_order_id = LAST_INSERT_ID();

INSERT INTO module (name, base_path) VALUES ('WORK_ORDER_DETAIL', '/api/work-order-details');
SET @module_work_order_detail_id = LAST_INSERT_ID();

-- CREACIÓN DE OPERACIONES, usando las variables de módulo para el campo module_id

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_PERSON','','POST', true, @module_person_id);
SET @operation_add_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_PERSON', '', 'POST', true, @module_person_id);
SET @operation_add_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_PERSON', '/[0-9]*', 'PUT', true, @module_person_id);
SET @operation_update_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_PERSON', '/[0-9]*', 'DELETE', true, @module_person_id);
SET @operation_delete_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_PERSON', '', 'GET', true, @module_person_id);
SET @operation_get_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_PERSON', '/[0-9]*', 'GET', true, @module_person_id);
SET @operation_get_one_person_id = LAST_INSERT_ID();

-- CREACIÓN DE ROLES
INSERT INTO role (name) VALUES ('CUSTOMER');
SET @role_customer_id = LAST_INSERT_ID();

INSERT INTO role (name) VALUES ('ASSISTANT_ADMINISTRATOR');
SET @role_assistant_admin_id = LAST_INSERT_ID();

INSERT INTO role (name) VALUES ('ADMIN');
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
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_person_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_person_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_person_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_person_id);

-- PERSON_SUPPLY Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_PERSON_SUPPLY', '', 'POST', true, @module_person_supply_id);
SET @operation_add_person_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_PERSON_SUPPLY', '/[0-9]*', 'PUT', true, @module_person_supply_id);
SET @operation_update_person_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_PERSON_SUPPLY', '/[0-9]*', 'DELETE', true, @module_person_supply_id);
SET @operation_delete_person_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_PERSON_SUPPLY', '', 'GET', true, @module_person_supply_id);
SET @operation_get_person_supply_id = LAST_INSERT_ID();

-- Assign permissions for PERSON_SUPPLY
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_person_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_person_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_person_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_person_supply_id);

-- PERSON_TYPE Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_PERSON_TYPE', '', 'POST', true, @module_person_type_id);
SET @operation_add_person_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_PERSON_TYPE', '/[0-9]*', 'PUT', true, @module_person_type_id);
SET @operation_update_person_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_PERSON_TYPE', '/[0-9]*', 'DELETE', true, @module_person_type_id);
SET @operation_delete_person_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_PERSON_TYPE', '', 'GET', true, @module_person_type_id);
SET @operation_get_person_type_id = LAST_INSERT_ID();

-- Assign permissions for PERSON_TYPE
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_person_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_person_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_person_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_person_type_id);

-- PERSON_PHONE Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_PERSON_PHONE', '', 'POST', true, @module_person_phone_id);
SET @operation_add_person_phone_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_PERSON_PHONE', '/[0-9]*', 'PUT', true, @module_person_phone_id);
SET @operation_update_person_phone_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_PERSON_PHONE', '/[0-9]*', 'DELETE', true, @module_person_phone_id);
SET @operation_delete_person_phone_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_PERSON_PHONE', '', 'GET', true, @module_person_phone_id);
SET @operation_get_person_phone_id = LAST_INSERT_ID();

-- Assign permissions for PERSON_PHONE
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_person_phone_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_person_phone_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_person_phone_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_person_phone_id);

-- PHONE_TYPES Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_PHONE_TYPE', '', 'POST', true, @module_phone_types_id);
SET @operation_add_phone_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_PHONE_TYPE', '/[0-9]*', 'PUT', true, @module_phone_types_id);
SET @operation_update_phone_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_PHONE_TYPE', '/[0-9]*', 'DELETE', true, @module_phone_types_id);
SET @operation_delete_phone_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_PHONE_TYPE', '', 'GET', true, @module_phone_types_id);
SET @operation_get_phone_type_id = LAST_INSERT_ID();

-- Assign permissions for PHONE_TYPES
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_phone_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_phone_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_phone_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_phone_type_id);

-- REGION Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_REGION', '', 'POST', true, @module_region_id);
SET @operation_add_region_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_REGION', '/[0-9]*', 'PUT', true, @module_region_id);
SET @operation_update_region_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_REGION', '/[0-9]*', 'DELETE', true, @module_region_id);
SET @operation_delete_region_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_REGION', '', 'GET', true, @module_region_id);
SET @operation_get_region_id = LAST_INSERT_ID();

-- Assign permissions for REGION
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_region_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_region_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_region_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_region_id);

-- SERVICE_APPROVALS Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_SERVICE_APPROVAL', '', 'POST', true, @module_service_approvals_id);
SET @operation_add_service_approval_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_SERVICE_APPROVAL', '/[0-9]*', 'PUT', true, @module_service_approvals_id);
SET @operation_update_service_approval_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_SERVICE_APPROVAL', '/[0-9]*', 'DELETE', true, @module_service_approvals_id);
SET @operation_delete_service_approval_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_SERVICE_APPROVAL', '', 'GET', true, @module_service_approvals_id);
SET @operation_get_service_approval_id = LAST_INSERT_ID();

-- Assign permissions for SERVICE_APPROVALS
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_service_approval_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_service_approval_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_service_approval_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_service_approval_id);

-- SERVICE_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_SERVICE_ORDER', '', 'POST', true, @module_service_order_id);
SET @operation_add_service_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_SERVICE_ORDER', '/[0-9]*', 'PUT', true, @module_service_order_id);
SET @operation_update_service_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_SERVICE_ORDER', '/[0-9]*', 'DELETE', true, @module_service_order_id);
SET @operation_delete_service_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_SERVICE_ORDER', '', 'GET', true, @module_service_order_id);
SET @operation_get_service_order_id = LAST_INSERT_ID();

-- Assign permissions for SERVICE_ORDER
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_service_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_service_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_service_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_service_order_id);

-- SERVICE Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_SERVICE', '', 'POST', true, @module_service_id);
SET @operation_add_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_SERVICE', '/[0-9]*', 'PUT', true, @module_service_id);
SET @operation_update_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_SERVICE', '/[0-9]*', 'DELETE', true, @module_service_id);
SET @operation_delete_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_SERVICE', '', 'GET', true, @module_service_id);
SET @operation_get_service_id = LAST_INSERT_ID();

-- Assign permissions for SERVICE
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_service_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_service_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_service_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_service_id);

-- SERVICE_SUPPLY Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_SERVICE_SUPPLY', '', 'POST', true, @module_service_supply_id);
SET @operation_add_service_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_SERVICE_SUPPLY', '/[0-9]*', 'PUT', true, @module_service_supply_id);
SET @operation_update_service_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_SERVICE_SUPPLY', '/[0-9]*', 'DELETE', true, @module_service_supply_id);
SET @operation_delete_service_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_SERVICE_SUPPLY', '', 'GET', true, @module_service_supply_id);
SET @operation_get_service_supply_id = LAST_INSERT_ID();

-- Assign permissions for SERVICE_SUPPLY
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_service_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_service_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_service_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_service_supply_id);

-- STATUS_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_STATUS_ORDER', '', 'POST', true, @module_status_order_id);
SET @operation_add_status_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_STATUS_ORDER', '/[0-9]*', 'PUT', true, @module_status_order_id);
SET @operation_update_status_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_STATUS_ORDER', '/[0-9]*', 'DELETE', true, @module_status_order_id);
SET @operation_delete_status_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_STATUS_ORDER', '', 'GET', true, @module_status_order_id);
SET @operation_get_status_order_id = LAST_INSERT_ID();

-- Assign permissions for STATUS_ORDER
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_status_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_status_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_status_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_status_order_id);

-- STATUS_SERVICE_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_STATUS_SERVICE_ORDER', '', 'POST', true, @module_status_service_order_id);
SET @operation_add_status_service_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_STATUS_SERVICE_ORDER', '/[0-9]*', 'PUT', true, @module_status_service_order_id);
SET @operation_update_status_service_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_STATUS_SERVICE_ORDER', '/[0-9]*', 'DELETE', true, @module_status_service_order_id);
SET @operation_delete_status_service_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_STATUS_SERVICE_ORDER', '', 'GET', true, @module_status_service_order_id);
SET @operation_get_status_service_order_id = LAST_INSERT_ID();

-- Assign permissions for STATUS_SERVICE_ORDER
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_status_service_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_status_service_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_status_service_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_status_service_order_id);

-- SUPPLY Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_SUPPLY', '', 'POST', true, @module_supply_id);
SET @operation_add_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_SUPPLY', '/[0-9]*', 'PUT', true, @module_supply_id);
SET @operation_update_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_SUPPLY', '/[0-9]*', 'DELETE', true, @module_supply_id);
SET @operation_delete_supply_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_SUPPLY', '', 'GET', true, @module_supply_id);
SET @operation_get_supply_id = LAST_INSERT_ID();

-- Assign permissions for SUPPLY
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_supply_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_supply_id);

-- WORK_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_WORK_ORDER', '', 'POST', true, @module_work_order_id);
SET @operation_add_work_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_WORK_ORDER', '/[0-9]*', 'PUT', true, @module_work_order_id);
SET @operation_update_work_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_WORK_ORDER', '/[0-9]*', 'DELETE', true, @module_work_order_id);
SET @operation_delete_work_order_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_WORK_ORDER', '', 'GET', true, @module_work_order_id);
SET @operation_get_work_order_id = LAST_INSERT_ID();

-- Assign permissions for WORK_ORDER
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_work_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_work_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_work_order_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_work_order_id);

-- WORK_ORDER_DETAIL Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_WORK_ORDER_DETAIL', '', 'POST', true, @module_work_order_detail_id);
SET @operation_add_work_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_WORK_ORDER_DETAIL', '/[0-9]*', 'PUT', true, @module_work_order_detail_id);
SET @operation_update_work_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_WORK_ORDER_DETAIL', '/[0-9]*', 'DELETE', true, @module_work_order_detail_id);
SET @operation_delete_work_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_WORK_ORDER_DETAIL', '', 'GET', true, @module_work_order_detail_id);
SET @operation_get_work_order_detail_id = LAST_INSERT_ID();

-- Assign permissions for WORK_ORDER_DETAIL
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_work_order_detail_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_work_order_detail_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_work_order_detail_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_work_order_detail_id);

-- PERSON Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_PERSON', '/[0-9]*', 'GET', true, @module_person_id);
SET @operation_get_one_person_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_person_id);

-- PERSON_SUPPLY Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_PERSON_SUPPLY', '/[0-9]*', 'GET', true, @module_person_supply_id);
SET @operation_get_one_person_supply_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_person_supply_id);

-- PERSON_TYPE Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_PERSON_TYPE', '/[0-9]*', 'GET', true, @module_person_type_id);
SET @operation_get_one_person_type_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_person_type_id);

-- PERSON_PHONE Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_PERSON_PHONE', '/[0-9]*', 'GET', true, @module_person_phone_id);
SET @operation_get_one_person_phone_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_person_phone_id);

-- PHONE_TYPES Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_PHONE_TYPES', '/[0-9]*', 'GET', true, @module_phone_types_id);
SET @operation_get_one_phone_types_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_phone_types_id);

-- REGION Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_REGION', '/[0-9]*', 'GET', true, @module_region_id);
SET @operation_get_one_region_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_region_id);

-- SERVICE_APPROVALS Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_SERVICE_APPROVALS', '/[0-9]*', 'GET', true, @module_service_approvals_id);
SET @operation_get_one_service_approvals_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_service_approvals_id);

-- SERVICE_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_SERVICE_ORDER', '/[0-9]*', 'GET', true, @module_service_order_id);
SET @operation_get_one_service_order_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_service_order_id);

-- SERVICE Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_SERVICE', '/[0-9]*', 'GET', true, @module_service_id);
SET @operation_get_one_service_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_service_id);

-- SERVICE_SUPPLY Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_SERVICE_SUPPLY', '/[0-9]*', 'GET', true, @module_service_supply_id);
SET @operation_get_one_service_supply_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_service_supply_id);

-- STATUS_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_STATUS_ORDER', '/[0-9]*', 'GET', true, @module_status_order_id);
SET @operation_get_one_status_order_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_status_order_id);

-- STATUS_SERVICE_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_STATUS_SERVICE_ORDER', '/[0-9]*', 'GET', true, @module_status_service_order_id);
SET @operation_get_one_status_service_order_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_status_service_order_id);

-- SUPPLY Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_SUPPLY', '/[0-9]*', 'GET', true, @module_supply_id);
SET @operation_get_one_supply_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_supply_id);

-- WORK_ORDER Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_WORK_ORDER', '/[0-9]*', 'GET', true, @module_work_order_id);
SET @operation_get_one_work_order_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_work_order_id);

-- WORK_ORDER_DETAIL Operations
INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_WORK_ORDER_DETAIL', '/[0-9]*', 'GET', true, @module_work_order_detail_id);
SET @operation_get_one_work_order_detail_id = LAST_INSERT_ID();
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_work_order_detail_id);

-- APPROVAL_STATUS Module
INSERT INTO module (name, base_path) VALUES ('APPROVAL_STATUS', '/api/approval-status');
SET @module_approval_status_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_APPROVAL_STATUS', '', 'POST', true, @module_approval_status_id);
SET @operation_add_approval_status_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_APPROVAL_STATUS', '/[0-9]*', 'PUT', true, @module_approval_status_id);
SET @operation_update_approval_status_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_APPROVAL_STATUS', '/[0-9]*', 'DELETE', true, @module_approval_status_id);
SET @operation_delete_approval_status_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_APPROVAL_STATUS', '', 'GET', true, @module_approval_status_id);
SET @operation_get_approval_status_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_APPROVAL_STATUS', '/[0-9]*', 'GET', true, @module_approval_status_id);
SET @operation_get_one_approval_status_id = LAST_INSERT_ID();

-- Assign permissions for APPROVAL_STATUS
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_approval_status_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_approval_status_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_approval_status_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_approval_status_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_approval_status_id);

-- BRANCH Module
INSERT INTO module (name, base_path) VALUES ('BRANCH', '/api/branch');
SET @module_branch_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_BRANCH', '', 'POST', true, @module_branch_id);
SET @operation_add_branch_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_BRANCH', '/[0-9]*', 'PUT', true, @module_branch_id);
SET @operation_update_branch_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_BRANCH', '/[0-9]*', 'DELETE', true, @module_branch_id);
SET @operation_delete_branch_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_BRANCH', '', 'GET', true, @module_branch_id);
SET @operation_get_branch_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_BRANCH', '/[0-9]*', 'GET', true, @module_branch_id);
SET @operation_get_one_branch_id = LAST_INSERT_ID();

-- Assign permissions for BRANCH
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_branch_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_branch_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_branch_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_branch_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_branch_id);

-- CITY Module
INSERT INTO module (name, base_path) VALUES ('CITY', '/api/city');
SET @module_city_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_CITY', '', 'POST', true, @module_city_id);
SET @operation_add_city_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_CITY', '/[0-9]*', 'PUT', true, @module_city_id);
SET @operation_update_city_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_CITY', '/[0-9]*', 'DELETE', true, @module_city_id);
SET @operation_delete_city_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_CITY', '', 'GET', true, @module_city_id);
SET @operation_get_city_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_CITY', '/[0-9]*', 'GET', true, @module_city_id);
SET @operation_get_one_city_id = LAST_INSERT_ID();

-- Assign permissions for CITY
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_city_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_city_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_city_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_city_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_city_id);

-- COMPANY Module
INSERT INTO module (name, base_path) VALUES ('COMPANY', '/api/company');
SET @module_company_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_COMPANY', '', 'POST', true, @module_company_id);
SET @operation_add_company_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_COMPANY', '/[0-9]*', 'PUT', true, @module_company_id);
SET @operation_update_company_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_COMPANY', '/[0-9]*', 'DELETE', true, @module_company_id);
SET @operation_delete_company_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_COMPANY', '', 'GET', true, @module_company_id);
SET @operation_get_company_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_COMPANY', '/[0-9]*', 'GET', true, @module_company_id);
SET @operation_get_one_company_id = LAST_INSERT_ID();

-- Assign permissions for COMPANY
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_company_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_company_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_company_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_company_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_company_id);

-- COMPANY_SERVICE Module
INSERT INTO module (name, base_path) VALUES ('COMPANY_SERVICE', '/api/company-service');
SET @module_company_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_COMPANY_SERVICE', '', 'POST', true, @module_company_service_id);
SET @operation_add_company_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_COMPANY_SERVICE', '/[0-9]*', 'PUT', true, @module_company_service_id);
SET @operation_update_company_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_COMPANY_SERVICE', '/[0-9]*', 'DELETE', true, @module_company_service_id);
SET @operation_delete_company_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_COMPANY_SERVICE', '', 'GET', true, @module_company_service_id);
SET @operation_get_company_service_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_COMPANY_SERVICE', '/[0-9]*', 'GET', true, @module_company_service_id);
SET @operation_get_one_company_service_id = LAST_INSERT_ID();

-- Assign permissions for COMPANY_SERVICE
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_company_service_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_company_service_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_company_service_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_company_service_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_company_service_id);

-- COMPANY-TYPE Module
INSERT INTO module (name, base_path) VALUES ('COMPANY-TYPE', '/api/company-type');
SET @module_company_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_COMPANY_TYPE', '', 'POST', true, @module_company_type_id);
SET @operation_add_company_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_COMPANY_TYPE', '/[0-9]*', 'PUT', true, @module_company_type_id);
SET @operation_update_company_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_COMPANY_TYPE', '/[0-9]*', 'DELETE', true, @module_company_type_id);
SET @operation_delete_company_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_COMPANY_TYPE', '', 'GET', true, @module_company_type_id);
SET @operation_get_company_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_COMPANY_TYPE', '/[0-9]*', 'GET', true, @module_company_type_id);
SET @operation_get_one_company_type_id = LAST_INSERT_ID();

-- Assign permissions for COMPANY-TYPE
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_company_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_company_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_company_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_company_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_company_type_id);

-- COUNTRY Module
INSERT INTO module (name, base_path) VALUES ('COUNTRY', '/api/country');
SET @module_country_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_COUNTRY', '', 'POST', true, @module_country_id);
SET @operation_add_country_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_COUNTRY', '/[0-9]*', 'PUT', true, @module_country_id);
SET @operation_update_country_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_COUNTRY', '/[0-9]*', 'DELETE', true, @module_country_id);
SET @operation_delete_country_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_COUNTRY', '', 'GET', true, @module_country_id);
SET @operation_get_country_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_COUNTRY', '/[0-9]*', 'GET', true, @module_country_id);
SET @operation_get_one_country_id = LAST_INSERT_ID();

-- Assign permissions for COUNTRY
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_country_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_country_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_country_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_country_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_country_id);

-- DOCUMENT_TYPE Module
INSERT INTO module (name, base_path) VALUES ('DOCUMENT_TYPE', '/api/document-type');
SET @module_document_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_DOCUMENT_TYPE', '', 'POST', true, @module_document_type_id);
SET @operation_add_document_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_DOCUMENT_TYPE', '/[0-9]*', 'PUT', true, @module_document_type_id);
SET @operation_update_document_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_DOCUMENT_TYPE', '/[0-9]*', 'DELETE', true, @module_document_type_id);
SET @operation_delete_document_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_DOCUMENT_TYPE', '', 'GET', true, @module_document_type_id);
SET @operation_get_document_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_DOCUMENT_TYPE', '/[0-9]*', 'GET', true, @module_document_type_id);
SET @operation_get_one_document_type_id = LAST_INSERT_ID();

-- Assign permissions for DOCUMENT_TYPE
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_document_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_document_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_document_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_document_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_document_type_id);

-- EMAIL_PERSON Module
INSERT INTO module (name, base_path) VALUES ('EMAIL_PERSON', '/api/email-person');
SET @module_email_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_EMAIL_PERSON', '', 'POST', true, @module_email_person_id);
SET @operation_add_email_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_EMAIL_PERSON', '/[0-9]*', 'PUT', true, @module_email_person_id);
SET @operation_update_email_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_EMAIL_PERSON', '/[0-9]*', 'DELETE', true, @module_email_person_id);
SET @operation_delete_email_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_EMAIL_PERSON', '', 'GET', true, @module_email_person_id);
SET @operation_get_email_person_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_EMAIL_PERSON', '/[0-9]*', 'GET', true, @module_email_person_id);
SET @operation_get_one_email_person_id = LAST_INSERT_ID();

-- Assign permissions for EMAIL_PERSON
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_email_person_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_email_person_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_email_person_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_email_person_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_email_person_id);

-- EMAIL_TYPE Module
INSERT INTO module (name, base_path) VALUES ('EMAIL_TYPE', '/api/email-type');
SET @module_email_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_EMAIL_TYPE', '', 'POST', true, @module_email_type_id);
SET @operation_add_email_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_EMAIL_TYPE', '/[0-9]*', 'PUT', true, @module_email_type_id);
SET @operation_update_email_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_EMAIL_TYPE', '/[0-9]*', 'DELETE', true, @module_email_type_id);
SET @operation_delete_email_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_EMAIL_TYPE', '', 'GET', true, @module_email_type_id);
SET @operation_get_email_type_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_EMAIL_TYPE', '/[0-9]*', 'GET', true, @module_email_type_id);
SET @operation_get_one_email_type_id = LAST_INSERT_ID();

-- Assign permissions for EMAIL_TYPE
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_email_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_email_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_email_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_email_type_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_email_type_id);

-- ORDER-DETAIL Module
INSERT INTO module (name, base_path) VALUES ('ORDER-DETAIL', '/api/order-detail');
SET @module_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('ADD_ORDER_DETAIL', '', 'POST', true, @module_order_detail_id);
SET @operation_add_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('UPDATE_ORDER_DETAIL', '/[0-9]*', 'PUT', true, @module_order_detail_id);
SET @operation_update_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('DELETE_ORDER_DETAIL', '/[0-9]*', 'DELETE', true, @module_order_detail_id);
SET @operation_delete_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ORDER_DETAIL', '', 'GET', true, @module_order_detail_id);
SET @operation_get_order_detail_id = LAST_INSERT_ID();

INSERT INTO operation (name, path, http_method, permit_all, module_id) VALUES ('GET_ONE_ORDER_DETAIL', '/[0-9]*', 'GET', true, @module_order_detail_id);
SET @operation_get_one_order_detail_id = LAST_INSERT_ID();

-- Assign permissions for ORDER-DETAIL
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_add_order_detail_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_update_order_detail_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_delete_order_detail_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_order_detail_id);
INSERT INTO granted_permission (role_id, operation_id) VALUES (@role_admin_id, @operation_get_one_order_detail_id);
