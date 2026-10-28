# Write your MySQL query statement below
WITH RankedScores AS (
    SELECT
        student_id,
        subject,
        score,
        ROW_NUMBER() OVER (
            PARTITION BY student_id, subject
            ORDER BY exam_date
        ) AS first_rank,
        ROW_NUMBER() OVER (
            PARTITION BY student_id, subject
            ORDER BY exam_date DESC
        ) AS last_rank
    FROM Scores
),
FirstLast AS (
    SELECT
        f.student_id,
        f.subject,
        f.score AS first_score,
        l.score AS latest_score
    FROM RankedScores f
    JOIN RankedScores l
        ON f.student_id = l.student_id
        AND f.subject = l.subject
    WHERE f.first_rank = 1
      AND l.last_rank = 1
)
SELECT
    student_id,
    subject,
    first_score,
    latest_score
FROM FirstLast
WHERE latest_score > first_score
ORDER BY student_id, subject;