# notification service (all)
FROM node:15.3.0 as notification-service-all

WORKDIR /notification-service-all

COPY package*.json ./

RUN npm install

COPY . .

CMD [ "node", "scripts/init-all.js" ]


# notification service (cli client)
FROM node:15.3.0 as notification-service-client

WORKDIR /notification-service-client

COPY package*.json ./

RUN npm install

COPY . .

CMD [ "node", "scripts/init-cli-client.js" ]


# notification service (just a server)
FROM node:15.3.0 as notification-service-server

WORKDIR /notification-service-server

COPY package*.json ./

RUN npm install

COPY . .

CMD [ "node", "scripts/init-notification-service.js" ]