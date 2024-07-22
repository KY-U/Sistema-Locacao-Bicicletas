FROM mysql:8.0

COPY setup.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes

ENV MYSQL_ROOT_PASSWORD=""
ENV MYSQL_DATABASE=sistemalocacao

EXPOSE 3306