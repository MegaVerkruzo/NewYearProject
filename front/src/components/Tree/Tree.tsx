import { FC } from 'react'
import TreeSanki from '../../assets/images/Tree/TreeSanki.png'
import { TreeCanvas } from './TreeCanvas'

type TreeProps = {
  activeGifts?: number
  activePrizes?: string
  nonActivePrizes?: string
}

export const Tree: FC<TreeProps> = ({
  activeGifts,
  activePrizes,
  nonActivePrizes,
}) => {
  return (
    <>
      {!activeGifts ? (
        <div className="main-image__container">
          <div className="main-page__title">
            <h1>Наряди свою ёлочку Благополучия</h1>
          </div>

          <img src={TreeSanki} alt="Ёлочка" />
        </div>
      ) : (
        // <TreeCanvas
        //   activeGifts={activeGifts}
        //   activePrizes={activePrizes}
        //   nonActivePrizes={nonActivePrizes}
        // />
        <div className="main-image__container">
          <div className="main-page__title">
            <h1>Наряди свою ёлочку Благополучия</h1>
          </div>

          <img src={TreeSanki} alt="Ёлочка" />
        </div>
      )}
    </>
  )
}
