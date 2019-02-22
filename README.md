# Reservas BioRitmo
Script para fazer reservas no site da BioRitmo.

#Pré-Req:
Alterar o arquivo BasicPage.java, linha 18 para o local onde está o ChromeDriver compatível com a versão instalada na máquina.

#Como executar:
Clonar repositório.
No Eclipse, "Import maven project".
Executar comando "mvn clean test".

#Observação
Formato do arquivo de input:
URL_BIORITMO;USUÁRIO;SENHA;SIGLA_ACADEMIA;DATA_AULA_RESERVA;NOME_AULA_RESERVA;HORA_AULA_RESERVA;DATA_EFETUAR_RESERVA

Exemplo:
http://reservas.bioritmo.com.br/session/new;user;pass;CEN;27-02-2019;Squad;07:00;21-02-2019