#para todos os containers
docker stop $(docker ps -q)

#remove todos os containers
docker rm $(docker ps -a -q)

#remove todas as imagens
docker rmi $(docker images -a -q)

#constrói a imagem
docker build -t sistemalocacao-image .

#roda a imagem
docker run --name sistemalocacao -d sistemalocacao-image