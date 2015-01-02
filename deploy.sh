set -e

cd ~
unzip guj.com.br.war -d guj-deploy
rm guj.com.br.war

cp -R guj4/content guj-deploy

yes | cp -R guj4/upload guj-deploy

cp guj4/WEB-INF/classes/*.xml guj-deploy/WEB-INF/classes
cp guj4/WEB-INF/classes/*.properties guj-deploy/WEB-INF/classes
cp guj4/WEB-INF/classes/niceurl.routes guj-deploy/WEB-INF/classes
cp guj4/WEB-INF/log4j.xml guj-deploy/WEB-INF
cp -R guj4/WEB-INF/config guj-deploy/WEB-INF
cp -R guj4/images guj-deploy/images
cp -R guj4/approve/ guj-deploy/
cp -R guj4/article/ guj-deploy/
cp guj4/*.gif guj-deploy/
cp -R guj4/category/ guj-deploy/
cp guj4/*.ico guj-deploy/
cp -R guj4/tmp/ guj-deploy/
cp -R guj4/tools/ guj-deploy/

jetty/bin/jetty.sh stop

mv guj4 guj4-old
mv guj-deploy guj4

jetty/bin/jetty.sh start