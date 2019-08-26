sudo docker build -t postgres/rest-auth .
sudo docker run --name db_rest_auth -p 5433:5432 -d postgres/rest-auth