CREATE TABLE public."Test2"
(
    test bit(1),
    test2 bigint NOT NULL,
    CONSTRAINT "Test_pkey2" PRIMARY KEY (test2)
)

TABLESPACE pg_default;