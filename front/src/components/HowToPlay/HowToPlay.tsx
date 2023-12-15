export const HowToPlay = () => {
  return (
    <div className="main-page__how2play">
      <div className="how2play__title">
        <h1>Как проходит игра?</h1>
      </div>
      <div className="how2play__main">
        <div className="how2play__container">
          <div className="how2play__subtitle">
            Введите ваш вариант ответа. После этого каждая буква окрасится в
            один из трёх цветов:
          </div>
          <div className="how2play__letters">
            <div className="letter__item">
              <div className="example__cell green">
                <div className="letter">г</div>
              </div>
              <div className="letter__text">
                Зелёный: правильная буква на правильном месте, отлично!
              </div>
            </div>
            <div className="letter__item">
              <div className="example__cell yellow">
                <div className="letter">о</div>
              </div>
              <div className="letter__text">
                Жёлтый: такая буква есть, но стоит на другом месте, подумайте
                ещё
              </div>
            </div>
            <div className="letter__item">
              <div className="example__cell grey">
                <div className="letter">т</div>
              </div>
              <div className="letter__text">
                Серый: нет такой буквы в этом слове
              </div>
            </div>
          </div>
          <div className="how2play__example">
            <div className="example__wrapper">
              <div className="example__column">
                <div className="example__title">Пример:</div>
                <div className="example__subtitle">Загаданное слово: ВЬЮГА</div>
              </div>
              <div className="example__column">
                <div className="example__grid">
                  <div className="example__cell yellow">
                    <div className="letter">г</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">е</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">р</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">о</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">й</div>
                  </div>
                  <div className="example__cell green">
                    <div className="letter">в</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">и</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">л</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">л</div>
                  </div>
                  <div className="example__cell grey">
                    <div className="letter">а</div>
                  </div>
                  <div className="example__cell green">
                    <div className="letter">в</div>
                  </div>
                  <div className="example__cell green">
                    <div className="letter">ь</div>
                  </div>
                  <div className="example__cell green">
                    <div className="letter">ю</div>
                  </div>
                  <div className="example__cell green">
                    <div className="letter">г</div>
                  </div>
                  <div className="example__cell green">
                    <div className="letter">а</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default HowToPlay
