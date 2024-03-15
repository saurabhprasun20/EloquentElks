FROM node:14-alpine
LABEL Author Carmine DiMascio <cdimascio@gmail.com>

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY . /usr/src/app
RUN npm install

ENV PORT=3001
EXPOSE 3001

CMD [ "npm", "start" ]
