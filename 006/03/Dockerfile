FROM php:latest
RUN mkdir www
WORKDIR www
ADD src/ .
EXPOSE 8080
CMD [ "php", "-S", "0.0.0.0:8080" ]