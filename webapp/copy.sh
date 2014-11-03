GUJ=ubuntu@guj.com.br:/home/ubuntu/guj4

scp -i ~/caelum/chaves/NovoGuj.pem header.jsp $GUJ
scp -i ~/caelum/chaves/NovoGuj.pem stylesheets/guj3.css $GUJ/stylesheets/guj3.css
scp -i ~/caelum/chaves/NovoGuj.pem templates/default/bottom.htm $GUJ/templates/default/bottom.htm
scp -i ~/caelum/chaves/NovoGuj.pem templates/default/header.htm $GUJ/templates/default/header.htm
scp -i ~/caelum/chaves/NovoGuj.pem templates/default/post_show.htm $GUJ/templates/default/post_show.htm
