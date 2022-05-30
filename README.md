# Leitura/Escrita de arquivo para 'De x Para' de processo contábil!

#### :open_file_folder: Programas e métodos utilizados para criação e teste?

Para editar/visualizar o código você poderá utilizar uma IDE de sua preferência, o que te permitirá realizar os testes na própria ferramenta.
Ou, você poderá utilizar qualquer editor de texto para visualizar e editar o código. Nesse caso, eu utilizei para execução do código o próprio
Prompt de Comando (Windows) com os comandos javac "Classe".java e java "Classe" ("Classe", você indicará o nome da Classe - Main - neste exemplo), conforme
print abaixo:

:one:. Navegue pelo Prompt até a pasta onde se encontra o arquivo ".java" (O caminho c:\Temp serve apenas de exemplo);
:two:. Execute o comando javac "Classe".java;
:three:. Execute o comando java "Classe".
*Desconsiderar as aspas em cada comando.

![cmd](https://user-images.githubusercontent.com/57046710/171024500-7b5bdd95-8c75-4f5d-bfc3-4e251332908f.PNG)


#### :question: O que faz?

Este trabalho realiza a leitura de um arquivo de lançamentos contábeis no template Data;Debito;Credito;valor;Codigo;Historico.
O arquivo poderá ser de formato .txt ou .csv. O arquivo precisa conter o cabeçalho e o delimitador dos campos precisa ser o ';'.
Arquivo exemplo no projeto "ctbFile".

Este trabalho também realiza a leitura de um arquivo com contas de referência (de x Para), quando precisamos trocar a conta (seja débito ou crédito)
de um plano de contas por outro (Normalmente o cliente possui um plano de contas diferente do sistema contábil da contabilidade).
Arquivo exemplo no projeto "FromTo" - seguindo o modelo do "ctbFile".

Por último, é gerado um novo arquivo "newCtbFile" com as contas débito e crédito trocadas/relacionadas com o arquivo "FromTo".
Quando contas não existem no arquivo "FromTo" para realizar o De x Para, um novo arquivo "newAccountsFile" é gerado com a lista das 'novas' contas que precisam
ser adicionadas no arquivo "FromTo" para que o De x Para funcione corretamente, atribuindo assim uma conta válida ao "newCtbFile". Quando a conta não possui
relacionamento, o valor '0' é atribuído no arquivo.

#### :punch: O que motivou?

Alguns anos trabalhando em um escritório de contabilidade e a necessidade de ensinar pessoas que não atuam diretamente com TI e sim com a área de negócio.
Dar mais autonomia para analistas/assistentes/contadores para executar a geração do arquivo contábil e importar para o sistema.

