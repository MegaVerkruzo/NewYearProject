FROM node as builder
WORKDIR /app
COPY ./package.json ./
RUN npm i
COPY . .
ENV REACT_APP_SERVER_NAME='http://localhost:5000/api'
ENV REACT_APP_SERVER_NAME_PUBLIC='http://localhost:5000'
RUN npm run build

FROM nginx
EXPOSE 80
COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/build /usr/share/nginx/html
