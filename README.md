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

### How to launch?

#### In folder "front"
You need to add new file '.env' with because in another case you couldn't make requests to backend
```
REACT_APP_API_URL='http://localhost:8080/api'
```

```bash
npm i

npm run start
```

#### In folder "backend"

```bash
./gradlew build

docker-compose up --build
```

Now you may check registration and login.

If you want to play in game wordle. You need to fill table russian_words with russian words (I have unpublished nodejs script for this)
And you need to fill table answers.

![image with example of filled table answers](https://i.imgur.com/2bGMqlI.png)

Then if you has these things, you need to know that time in answers table is UTC+0, so you need to paste this time.
Time between answers is 1 minutes because it's time is default in Common.java  
Always you need to have 5 answers
And you need to reload page if you want to get new word.

That's all. Have fun