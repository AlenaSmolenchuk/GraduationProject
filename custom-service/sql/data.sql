INSERT INTO bank.bank_accounts(num_bank_account, amount) VALUES (20220287964359622567, 100000);
INSERT INTO bank.bank_accounts(num_bank_account, amount) VALUES (20220287964359622568, 500000);
INSERT INTO bank.bank_accounts(num_bank_account, amount) VALUES (20220287964359622569, 15000);

-- password = user1
INSERT INTO bank.customers(bank_account_id, phone_number, password) VALUES (1, '89992223355', '$2a$12$SWfAOn4rFq0sdSMvMG5dTORhJEgZ15s3mHMx4h2JWuxbQVBvm9IvK');
-- password = user2
INSERT INTO bank.customers(bank_account_id, phone_number, password) VALUES (2, '89872117843', '$2a$12$lcmUASY.GHauQ0x1dpdst.iOZOtY3SE.6sr/NbvlMaolmaUAnl8I2');
-- password = user3
INSERT INTO bank.customers(bank_account_id, phone_number, password) VALUES (3, '89152273863', '$2a$12$wBjyfsAJOIoMCNMl6xb9Qe7ehEKe/Unnu06PBFe1dcm3a5eoUTpcy');

INSERT INTO bank.deposits_types(deposit_type_name) VALUES ('ПОПОЛНЕНИЕ_СНЯТИЕ');
INSERT INTO bank.deposits_types(deposit_type_name) VALUES ('ПОПОЛНЕНИЕ_БЕЗ_СНЯТИЯ');
INSERT INTO bank.deposits_types(deposit_type_name) VALUES ('БЕЗ_ПОПОЛНЕНИЯ_БЕЗ_СНЯТИЯ');

INSERT INTO bank.types_percent_payment(type_percent_payment_period) VALUES ('КАЖДЫЙ_МЕСЯЦ');
INSERT INTO bank.types_percent_payment(type_percent_payment_period) VALUES ('В_КОНЦЕ_ПЕРИОДА');

INSERT INTO bank.request_statuses(request_status_name) VALUES ('ПОДТВЕРЖДЕНИЕ');
INSERT INTO bank.request_statuses(request_status_name) VALUES ('ПОДТВЕРЖДЕНА');
INSERT INTO bank.request_statuses(request_status_name) VALUES ('ОДОБРЕНА');
INSERT INTO bank.request_statuses(request_status_name) VALUES ('ОТКЛОНЕНА');
