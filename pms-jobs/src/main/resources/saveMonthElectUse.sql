DROP PROCEDURE IF EXISTS `saveMonthElectUse`;
CREATE PROCEDURE saveMonthElectUse()
  BEGIN

    DECLARE lstMonth DATETIME;
    SELECT date_sub(max(date), INTERVAL 1 MONTH)
    INTO lstMonth
    FROM tday_use;
    DELETE FROM tday_use
    WHERE date <= lstMonth;

    INSERT INTO tmon_use
      SELECT
        dormitoryid,
        sum(delect_use) AS melect_use,
        max(date)       AS date,
        sum(dfee)       AS mfee
      FROM tday_use
      GROUP BY dormitoryid;

  END

