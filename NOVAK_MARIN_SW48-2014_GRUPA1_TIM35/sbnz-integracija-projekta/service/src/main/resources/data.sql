INSERT INTO user(id, first_name, last_name, password, username)
VALUES
(1, 'Doktor', 'Doktorić', '12345', 'doca');

INSERT INTO doctor(id) VALUES (1);

-- Inserts for symptom table
INSERT INTO symptom (dtype, id, code, has_special_diagnostics, isamental_illness, is_custom_symptom, name) 
VALUES 
('Symptom', 1,  NULL, false, false, false, 'Nedostatak lične higijene'),
('Symptom', 2,  NULL, false, false, false, 'Poteškoće sa ustajanjem iz kreveta'),
('Symptom', 3,  NULL, false, false, false, 'Insomnija'),
('Symptom', 4,  NULL, false, false, false, 'Hipersomnija'),
('Symptom', 5,  NULL, false, false, false, 'Izbjegavanje socijalnih situacija'),
('Symptom', 6,  NULL, false, false, false, 'Znojenje'),
('Symptom', 7,  NULL, false, false, false, 'Crvenilo u licu'),
('Symptom', 8,  NULL, false, false, false, 'Drhtanje'),
('Symptom', 9,  NULL, false, false, false, 'Vizuelne halucinacije'),
('Symptom', 10, NULL, false, false, false, 'Auditorne halucinacije'),
('Symptom', 11, NULL, false, false, false, 'Alogija'),
('Symptom', 12, NULL, false, false, false, 'Paralogizmi'),
('Symptom', 13, NULL, false, false, false, 'Eholalija'),
('Symptom', 14, NULL, false, false, false, 'Inkoherentnost govora'),
('Symptom', 15, NULL, false, false, false, 'Osjećaj težine u tijelu'),
('Symptom', 16, NULL, false, false, false, 'Tromost'),
('Symptom', 17, NULL, false, false, false, 'Ubrzan rad srca'),
('Symptom', 18, NULL, false, false, false, 'Uznemirenost'),
('Symptom', 19, NULL, false, false, false, 'Strah'),
('Symptom', 20, NULL, false, false, false, 'Bezbrižnost'),
('Symptom', 21, NULL, false, false, false, 'Smanjen apetit'),
('Symptom', 22, NULL, false, false, false, 'Nedostatak motivacije'),
('Symptom', 23, NULL, false, false, false, 'Suicidalnost'),
('Symptom', 24, NULL, false, false, false, 'Smanjena koncentracija'),
('Symptom', 25, NULL, false, false, false, 'Povećana motivacija'),
('Symptom', 26, NULL, true, false, false, 'Manjak volje'),
('Symptom', 27, NULL, true, false, false, 'Halucinacije'),
('Symptom', 28, NULL, false, false, false, 'Strah od socijalnih situacija'),
('Symptom', 29, NULL, true, false, false, 'Problemi sa snom'),
('Symptom', 30, NULL, false, false, false, 'Anksioznost'),
('Symptom', 31, NULL, false, false, false, 'Manjak energije'),
('Symptom', 32, 'F40.11', false, true,  false, 'Socijalna anksioznost'),
('Symptom', 33, NULL, true, false,  false, 'Depresija'),
('Symptom', 34, NULL, false, false, false, 'Briga'),
('Symptom', 35, NULL, false, false, false, 'Zamor'),
('Symptom', 36, NULL, false, false, false, 'Razdražljivost'),
('Symptom', 37, 'F41.1', true,  true,  false, 'Generalizovani anksiozni poremećaj'),
('Symptom', 38, 'F30', true,  true,  false, 'Manična epizoda'),
('Symptom', 39, NULL, false, false, false, 'Grandioznost'),
('Symptom', 40, NULL, false, false, false, 'Pretjerana razgovorljivost'),
('Symptom', 41, NULL, false, false, false, 'Ubrzane misli'),
('Symptom', 42, NULL, false, false, false, 'Nestabilna pažnja'),
('Symptom', 43, NULL, false, false, false, 'Višak energije'),
('Symptom', 44, NULL, true, false, false, 'Nepovezano mišljenje i govor'),
('Symptom', 45, 'F32', true, true,  false, 'Depresivni poremećaj'),
('Symptom', 46, 'F31', true, true,  false, 'Bipolarni afektivni poremećaj'),
('Symptom', 47, 'F20', true, true,  false, 'Shizofrenija'),
('Symptom', 48, 'F25', true, true,  false, 'Shizoafektivni poremećaj'),
('Symptom', 49, NULL , false,false,  false, 'Deluzije'),
('Symptom', 50, NULL , false,false,  false, 'Katatonija'),
('Symptom', 51, NULL , true,false,  false, 'Negativni simptomi'),
('Symptom', 52, NULL , false,false,  false, 'Anhedonija'),
('Symptom', 53, NULL , false,false,  false, 'Zaravnjen afekt');

-- Inserts for symptom_relationship table (unchanged)
INSERT INTO symptom_relationship (parent_symptom_id, child_symptom_id) 
VALUES 
(26, 1),
(26, 2),
(26, 16),
(26, 21),
(26, 22),
(27, 9),
(27, 10),
(28, 5),
(28, 6),
(28, 7),
(28, 17),
(29, 3),
(29, 4),
(30, 34),
(30, 19),
(30, 18),
(31, 2),
(31, 4),
(31, 15),
(31, 16),
(32, 28),
(32, 30),
(33, 26),
(33, 31),
(33, 29),
(33, 23),
(33, 24),
(33, 52),
(37, 30),
(37, 24),
(37, 35),
(37, 36),
(38, 39),
(38, 40),
(38, 41),
(38, 42),
(38, 43),
(38, 3),
(38, 25),
(44, 12),
(44, 13),
(44, 14),
(44, 11),
(46, 33),
(46, 38),
(47, 49),
(47, 27),
(47, 44),
(47, 50),
(47, 51),
(49, 39),
(51, 11),
(51, 52),
(51, 53);


-- Inserts for patient table
INSERT INTO patient(id, dob, first_name, health_card_id, last_name) 
VALUES
('1', '1984-07-02', 'Marko', 'QWER122', 'Markovic'),
('2', '1992-06-02', 'Backwards', 'QWER222', 'Backwardsic');


-- Inserts for patient_current_symptoms table
INSERT INTO patient_current_symptoms(patient_id, symptom_id) 
VALUES
(1, 30),
(1, 35),
(1, 24),
(1, 36),
(2, 32);


-- Inserts for appointment table
INSERT INTO appointment(id, date, note, diagnosis_id, doctor_id, patient_id)
VALUES
('2', '2023-04-02 22:00:00', NULL, NULL, NULL, '1');


-- Inserts for appointment_symptoms table
INSERT INTO appointment_symptoms(appointment_id, symptom_id)
VALUES
(2, 30),
(2, 35),
(2, 24),
(2, 36);
