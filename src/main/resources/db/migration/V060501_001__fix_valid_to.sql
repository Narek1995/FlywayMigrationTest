CREATE TABLE public."Test"
(
    test bit(1),
    test2 bigint NOT NULL,
    CONSTRAINT "Test_pkey" PRIMARY KEY (test2)
)

TABLESPACE pg_default;