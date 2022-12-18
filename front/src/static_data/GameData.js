export const initialGameData = JSON.parse(`{
  "letters": [
    {
      "letter": "а",
      "state": "yellow"
    },
    {
      "letter": "в",
      "state": "yellow"
    },
    {
      "letter": "а",
      "state": "green"
    },
    {
      "letter": "н",
      "state": "grey"
    },
    {
      "letter": "с",
      "state": "yellow"
    },
    {
      "letter": "с",
      "state": "green"
    },
    {
      "letter": "м",
      "state": "grey"
    },
    {
      "letter": "о",
      "state": "grey"
    },
    {
      "letter": "л",
      "state": "yellow"
    },
    {
      "letter": "а",
      "state": "green"
    },
    {
      "letter": "с",
      "state": "green"
    },
    {
      "letter": "л",
      "state": "green"
    },
    {
      "letter": "а",
      "state": "green"
    },
    {
      "letter": "в",
      "state": "green"
    },
    {
      "letter": "а",
      "state": "green"
    }
  ],
  "wordLength": 5,
  "currentLine": 3,
  "isEnd": true,
  "postLink": "t.me/id_post",
  "description": "Описание слова после того как закончатся попытки"
}`)

export const russianLetters = ['а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
    'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ы', 'ъ', 'э', 'ю', 'я']

const keyboardData = [
    [
        {id: 1, letter: 'й'}, {id: 2, letter: 'ц'}, {id: 3, letter: 'у'}, {id: 4, letter: 'к'},
        {id: 5, letter: 'е'}, {id: 6, letter: 'н'}, {id: 7, letter: 'г'}, {id: 8, letter: 'ш'},
        {id: 9, letter: 'щ'}, {id: 10, letter: 'з'}, {id: 11, letter: 'х'}, {id: 12, letter: 'ъ'}],
    [
        {id: 13, letter: 'ф'}, {id: 14, letter: 'ы'}, {id: 15, letter: 'в'}, {id: 16, letter: 'а'},
        {id: 17, letter: 'п'}, {id: 18, letter: 'р'}, {id: 19, letter: 'о'}, {id: 20, letter: 'л'},
        {id: 21, letter: 'д'}, {id: 22, letter: 'ж'}, {id: 23, letter: 'э'}],
    [
        {id: 52, letter: '✓'}, {id: 24, letter: 'я'}, {id: 25, letter: 'ч'}, {id: 26, letter: 'с'}, {
        id: 27,
        letter: 'м'
    },
        {id: 28, letter: 'и'}, {id: 29, letter: 'т'}, {id: 30, letter: 'ь'}, {id: 31, letter: 'б'},
        {id: 32, letter: 'ю'}, {id: 51, letter: '🠔'}]
]