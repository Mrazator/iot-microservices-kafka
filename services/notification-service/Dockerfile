# Base image of the docker container
FROM node:latest
USER node
COPY . .
RUN npm install
EXPOSE 8081
CMD [ "node", "index.js" ]