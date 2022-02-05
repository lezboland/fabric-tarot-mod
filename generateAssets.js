const path = require("path")
const fs = require("fs/promises")

const PATH = "./src/main/resources/assets/fabrictarot/"
const PATH_BLOCKSTATES = path.join(PATH, "blockstates")
const PATH_BLOCK_MODELS = path.join(PATH, "models/block")
const PATH_BLOCK_TEXTURES = path.join(PATH, "textures/block")

async function steps() {
    await generateCardBlockAssets()

    console.log("Done")
}

async function generateCardBlockAssets() {
    const blockTextures = await fs.readdir(PATH_BLOCK_TEXTURES)
    const cardBlockTextures = blockTextures.filter(fn => fn.startsWith("card_block_"))
    const cardBlockTextureNames = cardBlockTextures.map(fn => fn.split('.')[0])

    console.log(`${cardBlockTextureNames.length} card textures present`)

    const blockstates = {
        variants: {}
    }

    for (let n = 0; n < cardBlockTextureNames.length; n++) {
        const model = {
            parent: "fabrictarot:block/card_block",
            textures: {
                all: `fabrictarot:block/card_block_${n}`
            }
        }

        await fs.writeFile(path.join(PATH_BLOCK_MODELS, `card_block_${n}.json`), JSON.stringify(model, null, 2))


        blockstates.variants[`facing=north,cardnumber=${n}`] = {
            model: `fabrictarot:block/card_block_${n}`
        }
        blockstates.variants[`facing=east,cardnumber=${n}`] = {
            model: `fabrictarot:block/card_block_${n}`,
            y: 90
        }
        blockstates.variants[`facing=south,cardnumber=${n}`] = {
            model: `fabrictarot:block/card_block_${n}`,
            y: 180
        }
        blockstates.variants[`facing=west,cardnumber=${n}`] = {
            model: `fabrictarot:block/card_block_${n}`,
            y: 270
        }
    }

    await fs.writeFile(path.join(PATH_BLOCKSTATES, "card_block.json"), JSON.stringify(blockstates, null, 2))
}

steps()