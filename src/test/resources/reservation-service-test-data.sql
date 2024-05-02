SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE reservation RESTART IDENTITY;
TRUNCATE TABLE reservation_time RESTART IDENTITY;
TRUNCATE TABLE theme RESTART IDENTITY;
SET REFERENTIAL_INTEGRITY TRUE;

INSERT INTO theme (name, description, thumbnail) VALUES
('테마1', '테마1 설명 설명 설명', 'thumbnail1.jpg');

INSERT INTO reservation_time (start_at) VALUES
('10:00');

INSERT INTO reservation (name, date, time_id, theme_id) VALUES
('테드', CURRENT_DATE() - INTERVAL '1' DAY, 1, 1);