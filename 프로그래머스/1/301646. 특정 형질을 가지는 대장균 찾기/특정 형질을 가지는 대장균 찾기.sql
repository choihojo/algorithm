SELECT COUNT(*) AS `COUNT`
FROM ECOLI_DATA
WHERE ((GENOTYPE & 1) | (GENOTYPE & 4)) and !(GENOTYPE & 2)
