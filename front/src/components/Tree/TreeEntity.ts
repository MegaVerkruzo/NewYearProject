import * as PIXI from 'pixi.js'
import { TreeEntityOptions } from '../../types/pixi'

enum Textures {
  ShadowGift = 'ShadowGift.png',
  Gift1 = 'Gift1.png',
  Gift2 = 'Gift2.png',
  Gift3 = 'Gift3.png',
  Gift4 = 'Gift4.png',
  Gift5 = 'Gift5.png',
  GiftDark1 = 'GiftDark1.png',
  GiftDark2 = 'GiftDark2.png',
  GiftDark3 = 'GiftDark3.png',
  GiftDark4 = 'GiftDark4.png',
  GiftDark5 = 'GiftDark5.png',
}

export class TreeEntity extends PIXI.Application {
  readonly textures: PIXI.Spritesheet
  readonly treeTexture: PIXI.Texture

  readonly activeGifts: number
  readonly activePrizes?: string
  readonly nonActivePrizes?: string
  readonly clientWidth: number
  readonly clientHeight: number
  readonly setMessage: (text: string | null) => void

  mainContainer = new PIXI.Container()

  constructor(treeEntityOptions: TreeEntityOptions) {
    const {
      textures,
      treeTexture,
      activeGifts,
      activePrizes,
      nonActivePrizes,
      setMessage,
      ...options
    } = treeEntityOptions
    super(options)

    this.textures = textures
    this.treeTexture = treeTexture
    this.activeGifts = activeGifts
    this.activePrizes = activePrizes
    this.nonActivePrizes = nonActivePrizes
    this.setMessage = setMessage
    this.clientWidth = this.screen.width
    this.clientHeight = this.screen.height

    this.setupTree()
    this.setupGifts()

    const scale = Math.min(
      this.clientWidth / this.mainContainer.width,
      this.clientHeight / this.mainContainer.height,
    )
    this.mainContainer.scale.set(scale)

    this.mainContainer.pivot.set(0.5)
    this.mainContainer.x = this.clientWidth / 2 - this.mainContainer.width / 2
    this.mainContainer.sortableChildren = true
    this.mainContainer.interactive = true

    this.mainContainer.on('pointerdown', (e) => {
      setMessage(null)
    })

    this.stage.addChild(this.mainContainer)
    this.renderer.plugins.interaction.autoPreventDefault = false

    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-ignore
    this.renderer.view.style['touch-action'] = 'auto'
  }

  setupTree() {
    const treeSprite = new PIXI.Sprite(this.treeTexture)
    treeSprite.x = 33
    treeSprite.zIndex = 1
    this.mainContainer.addChild(treeSprite)
  }

  setupGifts() {
    const giftTexture1 = this.getTexture(this.getGiftNameByCount(1))
    const giftSprite1 = new PIXI.Sprite(giftTexture1)
    giftSprite1.x = 13
    giftSprite1.y = 1211
    giftSprite1.zIndex = 4
    giftSprite1.interactive = true
    giftSprite1.on('pointerdown', (e) => {
      e.stopPropagation()
      this.activeGifts < 1 ? this.onDarkGiftClick() : this.onActiveGiftClick()
    })
    this.mainContainer.addChild(giftSprite1)

    const giftTexture2 = this.getTexture(this.getGiftNameByCount(2))
    const giftSprite2 = new PIXI.Sprite(giftTexture2)
    giftSprite2.x = 474
    giftSprite2.y = 1195
    giftSprite2.zIndex = 6
    giftSprite2.interactive = true
    giftSprite2.on('pointerdown', (e) => {
      e.stopPropagation()
      this.activeGifts < 2 ? this.onDarkGiftClick() : this.onActiveGiftClick()
    })
    this.mainContainer.addChild(giftSprite2)

    const giftTexture3 = this.getTexture(this.getGiftNameByCount(3))
    const giftSprite3 = new PIXI.Sprite(giftTexture3)
    giftSprite3.x = 631
    giftSprite3.y = 1158
    giftSprite3.zIndex = 3
    giftSprite3.interactive = true
    giftSprite3.on('pointerdown', (e) => {
      e.stopPropagation()
      this.activeGifts < 3 ? this.onDarkGiftClick() : this.onActiveGiftClick()
    })
    this.mainContainer.addChild(giftSprite3)

    const giftTexture4 = this.getTexture(this.getGiftNameByCount(4))
    const giftSprite4 = new PIXI.Sprite(giftTexture4)
    giftSprite4.x = 168
    giftSprite4.y = 1118
    giftSprite4.zIndex = 4
    giftSprite4.interactive = true
    giftSprite4.on('pointerdown', (e) => {
      e.stopPropagation()
      this.activeGifts < 4 ? this.onDarkGiftClick() : this.onActiveGiftClick()
    })
    this.mainContainer.addChild(giftSprite4)

    const giftTexture5 = this.getTexture(this.getGiftNameByCount(5))
    const giftSprite5 = new PIXI.Sprite(giftTexture5)
    giftSprite5.x = 334
    giftSprite5.y = 1102
    giftSprite5.zIndex = 5
    giftSprite5.interactive = true
    giftSprite5.on('pointerdown', (e) => {
      e.stopPropagation()
      this.activeGifts < 5 ? this.onDarkGiftClick() : this.onActiveGiftClick()
    })
    this.mainContainer.addChild(giftSprite5)

    const shadowGiftTexture = this.getTexture(Textures.ShadowGift)
    const shadowGiftSptite = new PIXI.Sprite(shadowGiftTexture)
    shadowGiftSptite.y = 1287
    shadowGiftSptite.zIndex = 2
    this.mainContainer.addChild(shadowGiftSptite)
  }

  onDarkGiftClick() {
    if (this.nonActivePrizes !== undefined) {
      this.setMessage(this.nonActivePrizes)
    }
  }

  onActiveGiftClick() {
    if (this.activePrizes !== undefined) {
      this.setMessage(this.activePrizes)
    }
  }

  getTexture(name: string): PIXI.Texture {
    return this.textures.textures[name] || PIXI.Texture.from('')
  }

  getGiftNameByCount(num: number) {
    switch (num) {
      case 1:
        return this.activeGifts < 1 ? Textures.GiftDark1 : Textures.Gift1
      case 2:
        return this.activeGifts < 2 ? Textures.GiftDark2 : Textures.Gift2
      case 3:
        return this.activeGifts < 3 ? Textures.GiftDark3 : Textures.Gift3
      case 4:
        return this.activeGifts < 4 ? Textures.GiftDark4 : Textures.Gift4
      case 5:
        return this.activeGifts < 5 ? Textures.GiftDark5 : Textures.Gift5
      default:
        return ''
    }
  }
}
