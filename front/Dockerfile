FROM node:18-alpine as builder
WORKDIR /app
COPY ./package.json ./
COPY ./yarn.lock ./
RUN yarn
COPY . .
RUN yarn build

FROM nginx:latest

COPY ./nginx/main.conf /etc/nginx/conf.d/default.conf
COPY etc-letsencrypt/archive/testblago-2023.ru/fullchain.pem1 /etc/letsencrypt/archive/testblago-2023.ru/fullchain.pem1
COPY etc-letsencrypt/archive/testblago-2023.ru/cert.pem1 /etc/letsencrypt/archive/testblago-2023.ru/cert.pem1
COPY etc-letsencrypt/archive/testblago-2023.ru/chain.pem1 /etc/letsencrypt/archive/testblago-2023.ru/chain.pem1
COPY etc-letsencrypt/archive/testblago-2023.ru/privkey.pem1 /etc/letsencrypt/archive/testblago-2023.ru/privkey.pem1
COPY --from=builder /app/dist /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
