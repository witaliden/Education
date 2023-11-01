FROM postgres:11-alpine

ENV POSTGRES_DB education_services
ENV POSTGRES_USER education_
ENV POSTGRES_PASSWORD P@ssw0rd

COPY *.sql /docker-entrypoint-initdb.d/