CREATE SCHEMA projeto2

CREATE TABLE projeto2.Curso
(
	idCurso INT NOT NULL,
	nome VARCHAR(35) NOT NULL

	PRIMARY KEY(idCurso)
)

CREATE TABLE projeto2.Aluno
(
	RA INT NOT NULL,
	Curso INT NOT NULL,
	PrimeiroNome VARCHAR(25) NOT NULL,
	UltimoNome VARCHAR(25) NOT NULL,
	RG VARCHAR(12) NOT NULL,
	DataNascimento DATE NOT NULL,
	Endereco VARCHAR(50) NOT NULL

	PRIMARY KEY(RA)

	FOREIGN KEY(Curso)
		REFERENCES projeto2.Curso (idCurso)
)

select *from projeto2.Aluno
select *from projeto2.Curso