FROM node:15.3.0

WORKDIR /notification-service

COPY package*.json ./

RUN npm install

COPY . .

CMD [ "node", "scripts/init-all.js" ]