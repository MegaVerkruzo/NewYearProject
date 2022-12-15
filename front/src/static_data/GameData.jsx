export const initialGameData = JSON.parse(`{
            "attempts": [
                {"id": 1, "number": 1, "word": [{"id": 1, "letter": "a", "state": 1}, {"id": 2, "letter": "b", "state": 0}, {"id": 3, "letter": "b", "state": 0}, {"id": 4, "letter": "b", "state": 0}, {"id": 5, "letter": "b", "state": 0}]},
                {"id": 2, "number": 2, "word": [{"id": 1, "letter": "a", "state": 1}, {"id": 2, "letter": "b", "state": 0}, {"id": 3, "letter": "b", "state": 0}, {"id": 4, "letter": "b", "state": 0}, {"id": 5, "letter": "b", "state": 0}]}
            ],
            "word_length": 5,
            "attempts_left": 3,
            "post_link": "t.me/id_post"
        }`)

export const keyboardData = [
    [
        {id: 1, letter: 'й'}, {id: 2, letter: 'ц'}, {id: 3, letter: 'у'}, {id: 4, letter: 'к'},
        {id: 5, letter: 'е'}, {id: 6, letter: 'н'}, {id: 7, letter: 'г'}, {id: 8, letter: 'ш'},
        {id: 9, letter: 'щ'}, {id: 10, letter: 'з'}, {id: 11, letter: 'х'}, {id: 12, letter: 'ъ'}],
    [
        {id: 13, letter: 'ф'}, {id: 14, letter: 'ы'}, {id: 15, letter: 'в'}, {id: 16, letter: 'а'},
        {id: 17, letter: 'п'}, {id: 18, letter: 'р'}, {id: 19, letter: 'о'}, {id: 20, letter: 'л'},
        {id: 21, letter: 'д'}, {id: 22, letter: 'ж'}, {id: 23, letter: 'э'}],
    [
        {id: 51, letter: '🠔'}, {id: 24, letter: 'я'}, {id: 25, letter: 'ч'}, {id: 26, letter: 'с'}, {id: 27, letter: 'м'},
        {id: 28, letter: 'и'}, {id: 29, letter: 'т'}, {id: 30, letter: 'ь'}, {id: 31, letter: 'б'},
        {id: 32, letter: 'ю'},{id: 52, letter: '✓'}]
]