const path = require("path")

module.exports = {
    mode: 'development',
    entry: {
        main: './src/indoor.js'
    },
    output: {
        filename: 'indoor_pack.js',
        path: path.resolve('./dist')
    },
    experiments: {
        topLevelAwait: true
    },
    watch:true
}