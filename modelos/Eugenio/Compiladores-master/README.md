# Compilador da Linguagem Lang


## Objetivo

O objetivo deste projeto é desenvolver um compilador para a mini-linguagem fictícia Lang.

## Como executar

Para a execução da geração de código, basta executar o seguinte comando na pasta do código fonte:

```console
foo@bar:~$ make file=caminhoArquivo compilar=true
```
Sendo "caminhoArquivo" o caminho da pasta raiz do trabalho até o arquivo de execução, por exemplo:

```console
foo@bar:~$ make file=testes/semantica/certo/teste0.lan compilar=true
```

O retorno será um OK, caso não exista problemas no código, e o código objeto gerado poderá ser encontrado no arquivo ``Main.java'' salvo na raiz do projeto. Caso ocorra um erro, ocorrerá uma impressão avisando do mesmo.

Caso se deseje executar a interpretação de código, basta rodar o seguinte comando:

```console
foo@bar:~$ make file=caminhoArquivo interpretar=true
```

Além dos comandos básicos apresentados anteriormente, alguns complementarem foram implementados no makefile com o intuito de facilitar mudanças e testes, sendo eles:

```console
foo@bar:~$ make clean
```
Apaga todos os arquivos do tipo ".class" da pasta "lang".

```console
foo@bar:~$ make lexical
```
Apaga o "Lexical.java" gerado pelo JFlex anteriormente e recria um novo.

## Status dos Módulos
* Analisador Léxico [![Generic badge](https://img.shields.io/badge/status-Ready-green.svg)](https://shields.io/)

* Analisador Sintático [![Generic badge](https://img.shields.io/badge/status-Ready-green.svg)](https://shields.io/)

* Interpretador [![Generic badge](https://img.shields.io/badge/status-Ready-green.svg)](https://shields.io/)

* Analisador Semântico  [![Generic badge](https://img.shields.io/badge/status-Ready-green.svg)](https://shields.io/)

* Gerador de código [![Generic badge](https://img.shields.io/badge/status-Ready-green.svg)](https://shields.io/)


## Ferramentas Utilizadas

* [GitHub](https://github.com/) - Usado para hospedar o sistema de controle de versões
* [Visual Studio Code](https://code.visualstudio.com/) - Editor de código fonte
* [JFlex](http://jflex.de/) - Usado como gerador de análise léxica
* [Overleaf](https://pt.overleaf.com/) - Editor de LaTeX online para redigir a documentação do projeto

## Autores

* **André Felipe de Souza Mota** - **Matrícula: 201665515B** - [AndMota](https://github.com/AndMota)

* **Eugenio Belizário Ribeiro Faria** - **Matrícula: 201665507B** - [eugeniobrf](https://github.com/eugeniobrf)

<p align="center">
UNIVERSIDADE FEDERAL DE JUIZ DE FORA <br/>
Departamento de Ciência da Computação – 2020.1 <br/>
DCC45 – Teoria dos Compiladores – Trabalho <br/>
Professor(a): Leonardo Reis <br/>
</p>
