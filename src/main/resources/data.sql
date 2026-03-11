INSERT INTO app_user (username, password, provider_id, auth_provider_type)
VALUES
    ('amit', 'password123', 'google123', 'GOOGLE'),
    ('neha', 'password456', 'github456', 'GITHUB');


INSERT INTO Patient (name, date_of_birth, email, gender, blood_group)
VALUES
    ('Amit Sharma', '1998-05-12', 'amit.sharma@gmail.com', 'Male', 'O_POSITIVE'),
    ('Neha Verma', '2000-08-21', 'neha.verma@gmail.com', 'Female', 'A_POSITIVE'),
    ('Rohit Patil', '1997-01-03', 'rohit.patil@gmail.com', 'Male', 'B_POSITIVE'),
    ('Sneha Kulkarni', '1999-11-15', 'sneha.kulkarni@gmail.com', 'Female', 'AB_POSITIVE'),
    ('Kunal Deshmukh', '1996-06-30', 'kunal.deshmukh@gmail.com', 'Male', 'O_NEGATIVE'),
    ('Priya Nair', '1998-03-14', 'priya.nair@gmail.com', 'Female', 'B_POSITIVE'),
    ('Vikram Singh', '2001-07-09', 'vikram.singh@gmail.com', 'Male', 'A_NEGATIVE'),
    ('Riya Kapoor', '1995-12-25', 'riya.kapoor@gmail.com', 'Female', 'O_POSITIVE'),
    ('Aditya Mehta', '1999-04-18', 'aditya.mehta@gmail.com', 'Male', 'AB_NEGATIVE'),
    ('Tanya Iyer', '2000-10-05', 'tanya.iyer@gmail.com', 'Female', 'A_POSITIVE'),
    ('Rahul Jain', '1996-08-21', 'rahul.jain@gmail.com', 'Male', 'B_NEGATIVE'),
    ('Sakshi Gupta', '1997-09-12', 'sakshi.gupta@gmail.com', 'Female', 'O_NEGATIVE'),
    ('Karan Malhotra', '1998-11-03', 'karan.malhotra@gmail.com', 'Male', 'AB_POSITIVE'),
    ('Anjali Reddy', '2000-01-22', 'anjali.reddy@gmail.com', 'Female', 'A_NEGATIVE'),
    ('Manish Kumar', '1995-05-17', 'manish.kumar@gmail.com', 'Male', 'O_POSITIVE'),
    ('Pooja Sharma', '1999-06-29', 'pooja.sharma@gmail.com', 'Female', 'B_POSITIVE'),
    ('Siddharth Roy', '1997-02-11', 'siddharth.roy@gmail.com', 'Male', 'AB_NEGATIVE'),
    ('Meera Joshi', '2001-03-30', 'meera.joshi@gmail.com', 'Female', 'O_POSITIVE'),
    ('Ankit Desai', '1998-12-14', 'ankit.desai@gmail.com', 'Male', 'A_POSITIVE'),
    ('Neha Singh', '1996-09-09', 'neha.singh@gmail.com', 'Female', 'B_NEGATIVE'),
    ('Vivek Sharma', '2000-07-21', 'vivek.sharma@gmail.com', 'Male', 'O_NEGATIVE'),
    ('Isha Chawla', '1997-10-28', 'isha.chawla@gmail.com', 'Female', 'AB_POSITIVE'),
    ('Rohan Patel', '1995-04-05', 'rohan.patel@gmail.com', 'Male', 'A_NEGATIVE'),
    ('Tina Verma', '1999-01-19', 'tina.verma@gmail.com', 'Female', 'O_POSITIVE'),
    ('Arjun Kapoor', '2001-05-15', 'arjun.kapoor@gmail.com', 'Male', 'B_POSITIVE');


-- Only doctors
INSERT INTO doctor (id, name, specialization, email, created_at) VALUES
 (1, 'John Smith', 'Cardiology', 'john.smith@example.com', NOW()),
(2, 'Emily Johnson', 'Neurology', 'emily.johnson@example.com', NOW()),
 (3, 'William Watson', 'Radiology', 'william.johnson@example.com', NOW());


INSERT INTO appointment (appointment_time, reason, patient_appointment_id, appointment_dcotor_id)
VALUES
    (NOW(), 'Regular wCheckup', 1, 1),
    (NOW(), 'Follow-up', 2, 1),
    (NOW(), 'Consultation', 3, 1);
