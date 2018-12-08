 CREATE SEQUENCE SEQ_FUNCIONARIO;

CREATE TABLE FUNCIONARIO
(NUMERO         NUMBER(5)
   CONSTRAINT FUNCIONARIO_ID_PK
   PRIMARY KEY,
 NOME           VARCHAR2(30) 
   CONSTRAINT FUNCIONARIO_NOME_NN
   NOT NULL,
 SEXO           CHAR(1)
   CONSTRAINT FUNCIONARIO_SEXO_NN
   NOT NULL,
  IDADE          INT
   CONSTRAINT FUNCIONARIO_IDADE_NN
   NOT NULL,
   SALARIO        float
  CONSTRAINT FUNCIONARIO_SALARIO_NN
   NOT NULL);

INSERT INTO FUNCIONARIO(NUMERO, NOME, SEXO, IDADE,SALARIO)
VALUES(SEQ_FUNCIONARIO.NEXTVAL, 'Jose Roberto', 'M', 45,1222);

INSERT INTO FUNCIONARIO(NUMERO, NOME, SEXO, IDADE,SALARIO)
VALUES(SEQ_FUNCIONARIO.NEXTVAL, 'Joana Rocha', 'F', 35,1222);

INSERT INTO FUNCIONARIO(NUMERO, NOME, SEXO, IDADE,SALARIO)
VALUES(SEQ_FUNCIONARIO.NEXTVAL, 'Vinicius Ribeiro', 'M', 22,12222);

INSERT INTO FUNCIONARIO(NUMERO, NOME, SEXO, IDADE,SALARIO)
VALUES(SEQ_FUNCIONARIO.NEXTVAL, 'Pedro Arnaldo', 'M', 61,12222);

INSERT INTO FUNCIONARIO(NUMERO, NOME, SEXO, IDADE,SALARIO)
VALUES(SEQ_FUNCIONARIO.NEXTVAL, 'Teresa Rosa', 'F', 32,12222);

INSERT INTO FUNCIONARIO(NUMERO, NOME, SEXO, IDADE,SALARIO)
VALUES(SEQ_FUNCIONARIO.NEXTVAL, 'Rodirio Senna', 'M', 33,1555);

CREATE SEQUENCE SEQ_DEPENDENTE;

CREATE TABLE DEPENDENTE
(NUMERO         NUMBER(5)
   CONSTRAINT DEPENDENTE_ID_PK
   PRIMARY KEY,
 NOME           VARCHAR2(30) 
   CONSTRAINT DEPENDENTE_NOME_NN
   NOT NULL,
 SEXO           CHAR(1)
   CONSTRAINT DEPENDENTE_SEXO_NN
   NOT NULL,
 IDADE          INT
   CONSTRAINT DEPENDENTE_IDADE_NN
   NOT NULL,
 FUNCIONARIO_TITULAR   NUMBER(5)   
   CONSTRAINT FUNCIONARIO_FK_NN
   REFERENCES FUNCIONARIO(NUMERO)
   CONSTRAINT DEPENDENTE_FUNCIONARIO_ID_NN
   NOT NULL);


INSERT INTO DEPENDENTE(NUMERO, NOME, SEXO, IDADE,FUNCIONARIO_TITULAR )
VALUES(SEQ_DEPENDENTE.NEXTVAL, 'Mirza Roman', 'F', 2, 1);

INSERT INTO DEPENDENTE(NUMERO, NOME, SEXO, IDADE, FUNCIONARIO_TITULAR)
VALUES(SEQ_DEPENDENTE.NEXTVAL, 'Sonia Albuquerque', 'F', 3, 2);

INSERT INTO DEPENDENTE(NUMERO, NOME, SEXO, IDADE, FUNCIONARIO_TITULAR)
VALUES(SEQ_DEPENDENTE.NEXTVAL, 'Luis Carlos Rosa', 'M', 2, 3);

INSERT INTO DEPENDENTE(NUMERO, NOME, SEXO, IDADE, FUNCIONARIO_TITULAR)
VALUES(SEQ_DEPENDENTE.NEXTVAL, 'Vitor Cerqueira', 'M', 1, 4);

INSERT INTO DEPENDENTE(NUMERO, NOME, SEXO, IDADE, FUNCIONARIO_TITULAR)
VALUES(SEQ_DEPENDENTE.NEXTVAL, 'Rosana Lima', 'F', 1, 5);


COMMIT;