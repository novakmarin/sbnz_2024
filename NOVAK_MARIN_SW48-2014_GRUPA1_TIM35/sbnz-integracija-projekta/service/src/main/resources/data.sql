-- Inserts for symptom table
INSERT INTO symptom (dtype, id, has_special_diagnostics, isamental_illness, name) 
VALUES 
('Symptom', 1,  false, false, 'Nedostatak lične higijene'),
('Symptom', 2,  false, false, 'Poteškoće sa ustajanjem iz kreveta'),
('Symptom', 3,  false, false, 'Insomnija'),
('Symptom', 4,  false, false, 'Hipersomnija'),
('Symptom', 5,  false, false, 'Izbjegavanje socijalnih situacija'),
('Symptom', 6,  false, false, 'Znojenje'),
('Symptom', 7,  false, false, 'Crvenilo u licu'),
('Symptom', 8,  false, false, 'Drhtanje'),
('Symptom', 9,  false, false, 'Vizuelne halucinacije'),
('Symptom', 10, false, false, 'Auditorne halucinacije'),
('Symptom', 11, false, false, 'Alogija'),
('Symptom', 12, false, false, 'Paralogizmi'),
('Symptom', 13, false, false, 'Eholalija'),
('Symptom', 14, false, false, 'Inkoherentnost govora'),
('Symptom', 15, false, false, 'Osjećaj težine u tijelu'),
('Symptom', 16, false, false, 'Tromost'),
('Symptom', 17, false, false, 'Ubrzan rad srca'),
('Symptom', 18, false, false, 'Uznemirenost'),
('Symptom', 19, false, false, 'Strah'),
('Symptom', 20, false, false, 'Bezbrižnost'),
('Symptom', 21, false, false, 'Smanjen apetit'),
('Symptom', 22, false, false, 'Nedostatak motivacije'),
('Symptom', 23, false, false, 'Suicidalnost'),
('Symptom', 24, false, false, 'Smanjena koncentracija'),
('Symptom', 25, false, false, 'Povećana motivacija'),
('Symptom', 26, false, false, 'Manjak volje'),
('Symptom', 27, false, false, 'Halucinacije'),
('Symptom', 28, false, false, 'Strah od socijalnih situacija'),
('Symptom', 29, false, false, 'Problemi sa snom'),
('Symptom', 30, false, false, 'Anksioznost'),
('Symptom', 31, false, false, 'Manjak energije'),
('Symptom', 32, false, false,  'Socijalna anksioznost'),
('Symptom', 33, false, true,  'Depresivni poremećaj'),
('Symptom', 34, false, false, 'Briga'),
('Symptom', 35, false, false, 'Zamor'),
('Symptom', 36, false, false, 'Razdražljivost'),
('Symptom', 37, true, true,  'Generalizovani anksiozni poremećaj');

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
(37, 30),
(37, 24),
(37, 35),
(37, 36);

-- Inserts for patient table
INSERT INTO patient(id, dob, first_name, health_card_id, last_name) 
VALUES
('1', '1984-07-02', 'Marko', 'QWER122', 'Markovic');


-- Inserts for patient_current_symptoms table
INSERT INTO patient_current_symptoms(patient_id, symptom_id) 
VALUES
(1, 30),
(1, 35),
(1, 24),
(1, 36);


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
