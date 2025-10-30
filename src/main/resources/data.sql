-- ========================
-- Insert sample doctors
-- ========================
INSERT INTO doctor (name, specialization, email, password, phone, years_of_experience, availability)
VALUES
('Dr. Meera Sharma', 'Cardiologist', 'meera@hospital.com', 'pass123', '9000000001', 12, 'MON-FRI 10:00-16:00'),
('Dr. Rajesh Kumar', 'Dermatologist', 'rajesh@hospital.com', 'pass123', '9000000002', 8,  'MON-SAT 12:00-18:00'),
('Dr. Anjali Verma', 'General Physician', 'anjali@hospital.com', 'pass123', '9000000003', 10, 'MON-FRI 09:00-15:00');

-- ========================
-- Insert sample patients
-- ========================
INSERT INTO patient (name, age, gender, medical_history, contact, address, visit_reason, appointment_date_time)
VALUES
('Rohit Kapoor', 28, 'Male', 'No major issues', '9876543210', 'Mumbai', 'chest pain', '2025-08-29 10:30:00'),
('Sneha Patel', 34, 'Female', 'Allergic to dust', '9876501234', 'Pune', 'skin rash acne', '2025-08-30 14:00:00');
