SELECT I.ANIMAL_ID, I.ANIMAL_TYPE, I.NAME
FROM ANIMAL_INS I
WHERE I.SEX_UPON_INTAKE LIKE "Intact%"
AND I.ANIMAL_ID IN (
    SELECT O.ANIMAL_ID
    FROM ANIMAL_OUTS O
    WHERE O.SEX_UPON_OUTCOME LIKE "Spayed%"
    OR O.SEX_UPON_OUTCOME LIKE "Neutered%"
);