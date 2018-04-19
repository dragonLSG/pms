DROP PROCEDURE IF EXISTS `saveDayElectUse`;
CREATE PROCEDURE saveDayElectUse()
  BEGIN
    DECLARE unitPrice DECIMAL(5, 4);

    SELECT price
    INTO unitPrice
    FROM tunit_price
    WHERE date = (
      SELECT max(date)
      FROM tunit_price
    );

    INSERT INTO tday_use
      SELECT
        *,
        delect_use * unitPrice AS dfee
      FROM (
             SELECT
               dormitoryid,
               sum(helect_use) AS delect_use,
               max(date)       AS date
             FROM thour_use
             GROUP BY dormitoryid
           ) tmp;

    DELETE FROM thour_use;
  END