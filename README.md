# NewYearProject

### This is commercial website for the week before the New Year

### Contacts:
* Alexey Grunskii - the backend on java/spring, devops, team-lead @lexa_hacker
* Andrey Burakov - the frontend on react @geek_py
* Artem Chernuha - ui/ux designer @tema_tedes
* Diana Gainutdinova - the artist who painted the Crhistmas tree @pacification_di

### Short description

It's website which has registration, logging and game wordle.
Backend java/spring, Frontend on React.
The site was made in 2 weeks (it includes working of designer and painter)

# How to launch in production?

### In folder "front"
You need to add new file '.env' with because in another case you couldn't make requests to backend
```
REACT_APP_API_URL='http://localhost:8080/api'
```

```bash
npm i

npm run start
```

### In folder "postgres"

You need to write database info like `db.example` in `db.env`

### In main folder
`docker compose --build --profile production up`

# How to launch for developing frontend?

You need to add new file '.env' with because in another case you couldn't make requests to backend
```
REACT_APP_API_URL='http://localhost:8080/api'
```

```bash
npm i

npm run start
```

# How to launch for developing backend?

### In folder "postgres"

You need to write database info like `db.example` in `db.env`

### In folder "backend"

You need to build by gradle project

```bash
./gradlew build
```

### In main folder
`docker compose --build --profile development up`

# Q&A

### What the difference between "development" and "production" profiles?
- When you use production mode - you compile .jar file in docker contatiner and it's more longer than compile it exactly in your os. In development mode, you at start compiling file with gradle and then launch docker-compose file
