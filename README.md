Como deployar
--------

1- Gere um war do guj pelo eclipse(precisa ter o nome `guj.com.br.war`)

2- Faça um scp para mandar o war pro servidor

```
scp -i <chave.pem> guj.com.br.war ubuntu@guj.com.br:~/
```

3- Logue no servidor

```
ssh -i <chave.pem> ubuntu@guj.com.br
```

4- Execute o script de deploy

```
./deploy-guj-velho.sh
```

Esse script também existe aqui no repositório do git pra manter histórico, se mudar aqui, lembra de mudar no servidor também e vice e versa(sim, da pra melhorar, sinta-se à vontade :D)