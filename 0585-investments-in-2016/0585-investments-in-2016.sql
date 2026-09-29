# Write your MySQL query statement below
SELECT ROUND(SUM(TIV_2016), 2) AS tiv_2016
FROM Insurance i
WHERE (
    SELECT COUNT(*)
    FROM Insurance
    WHERE TIV_2015 = i.TIV_2015
) > 1
AND (
    SELECT COUNT(*)
    FROM Insurance
    WHERE LAT = i.LAT
      AND LON = i.LON
) = 1;