import RuleIcon1 from '../../assets/svgs/RuleIcon1'
import RuleIcon2 from '../../assets/svgs/RuleIcon2'
import RuleIcon3 from '../../assets/svgs/RuleIcon3'
import RuleIcon4 from '../../assets/svgs/RuleIcon4'

export const Rules = () => {
  return (
    <div className="rules" id="rules">
      <div className="rules__title">
        <h1>Правила игры</h1>
      </div>
      <div className="rules__container">
        <div className="rules__item">
          <div className="rule__icon">
            <RuleIcon1 />
          </div>
          <div className="rule__title">Найдите загадку</div>
          <div className="rule__text">
            Каждый день в Telegram-канале будет ждать новая загадка
          </div>
        </div>
        <div className="rules__item">
          <div className="rule__icon">
            <RuleIcon2 />
          </div>
          <div className="rule__title">Угадайте слово</div>
          <div className="rule__text">
            Ответ на загадку – одно слово. Введите его на этой странице
          </div>
        </div>
        <div className="rules__item">
          <div className="rule__icon">
            <RuleIcon3 />
          </div>
          <div className="rule__title">Нарядите ёлочку</div>
          <div className="rule__text">
            За каждое угаданное слово на ёлке будут появляться украшения
          </div>
        </div>
        <div className="rules__item">
          <div className="rule__icon">
            <RuleIcon4 />
          </div>
          <div className="rule__title">Получите призы</div>
          <div className="rule__text">
            Больше слов — больше шансов выиграть!
          </div>
        </div>
      </div>
    </div>
  )
}
